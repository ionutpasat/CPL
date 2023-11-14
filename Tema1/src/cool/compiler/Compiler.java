package cool.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import cool.lexer.*;
import cool.parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;
        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);
                
            // Test lexer only.
//            tokenStream.fill();
//            List<Token> tokens = tokenStream.getTokens();
//            tokens.stream().forEach(token -> {
//                var text = token.getText();
//                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
//
//                System.out.println(text + " : " + name);
//                //System.out.println(token);
//            });

            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + (charPositionInLine + 1) + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() == CoolLexer.ERROR)
                        newMsg += "Lexical error: " + token.getText();
                    else
                        newMsg += "Syntax error: " + msg;
                    
                    System.err.println(newMsg);
                    errors = true;
                }
            };
            
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            // Actual parsing
            var tree = parser.program();
            if (globalTree == null)
                globalTree = tree;
            else
                // Add the current parse tree's children to the global tree.
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));
                    
            // Annotate class nodes with file names, to be used later
            // in semantic error messages.
            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;
        }

        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }
        
        // TODO Print tree
        var astConstructionVisitor = new CoolParserBaseVisitor<ASTNode>() {
            @Override
            public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
                var classes = ctx.classes.stream().map(classNode -> (Class) visit(classNode)).collect(Collectors.toList());
                return new Program(classes, ctx.start);
            }

            @Override
            public ASTNode visitClass(CoolParser.ClassContext ctx) {
                var features = (ctx.features != null) ?
                        ctx.features.stream().map(feature -> (Feature) visit(feature)).collect(Collectors.toList()) :
                        null;
                return new Class(ctx.name, ctx.inherits, features, ctx.start);
            }

            @Override
            public ASTNode visitFuncDef(CoolParser.FuncDefContext ctx) {
                var formalParams = (ctx.formals != null) ?
                        ctx.formals.stream().map(formal -> (FormalParam) visit(formal)).collect(Collectors.toList()) :
                        null;
                return new FunctionDefinition((Expression) visit(ctx.funcDefExpr), formalParams, ctx.ID().getSymbol(), ctx.TYPE().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
                Expression varExpr = (ctx.varDefExpr != null) ? (Expression) visit(ctx.varDefExpr) : null;
                return new VariabileDefinition(varExpr, ctx.ID().getSymbol(), ctx.TYPE().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitFormal(CoolParser.FormalContext ctx) {
                return new FormalParam(ctx.type, ctx.name, ctx.start);
            }

            @Override
            public ASTNode visitLocal(CoolParser.LocalContext ctx) {
                var value = (ctx.value != null) ? (Expression) visit(ctx.value) : null;
                return new LocalParam(ctx.ID().getSymbol(), ctx.TYPE().getSymbol(), value, ctx.start);
            }

            @Override
            public ASTNode visitCaseBranch(CoolParser.CaseBranchContext ctx) {
                return new CaseBranch(ctx.ID().getSymbol(), ctx.TYPE().getSymbol(), (Expression) visit(ctx.value), ctx.start);
            }

            @Override
            public ASTNode visitNew(CoolParser.NewContext ctx) {
                return new New(ctx.TYPE().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
                return new BinaryOperator((Expression) visit(ctx.left), (Expression) visit(ctx.right), ctx.op, ctx.start);
            }

            @Override
            public ASTNode visitBool(CoolParser.BoolContext ctx) {
                return new BoolNode(ctx.start);
            }

            @Override
            public ASTNode visitString(CoolParser.StringContext ctx) {
                return new StringNode(ctx.start);
            }

            @Override
            public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
                return new UnaryOperator((Expression) visit(ctx.expr()), ctx.ISVOID().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitInitcall(CoolParser.InitcallContext ctx) {
                var args = (ctx.args != null) ?
                        ctx.args.stream().map(arg -> (Expression) visit(arg)).collect(Collectors.toList()) :
                        null;
                return new InitCall(ctx.ID().getSymbol(), args, ctx.start);
            }

            @Override
            public ASTNode visitWhile(CoolParser.WhileContext ctx) {
                return new While((Expression) visit(ctx.cond),
                        (Expression) visit(ctx.action),
                        ctx.start);
            }

            @Override
            public ASTNode visitInt(CoolParser.IntContext ctx) {
                return new Int(ctx.INT().getSymbol());
            }

            @Override
            public ASTNode visitCall(CoolParser.CallContext ctx) {
                var atType = (ctx.TYPE()) != null ? ctx.TYPE().getSymbol() : null;
                var args = (ctx.args != null) ?
                        ctx.args.stream().map(arg -> (Expression) visit(arg)).collect(Collectors.toList()) :
                        null;
                return new Call((Expression) visit(ctx.prefix), atType, ctx.ID().getSymbol(), args, ctx.start);
            }

            @Override
            public ASTNode visitNot(CoolParser.NotContext ctx) {
                return new UnaryOperator((Expression) visit(ctx.expr()), ctx.NOT().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitParen(CoolParser.ParenContext ctx) {
                return visit(ctx.expr());
            }

            @Override
            public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
                return new BinaryOperator((Expression) visit(ctx.left), (Expression) visit(ctx.right), ctx.op, ctx.start);
            }

            @Override
            public ASTNode visitUnaryMinus(CoolParser.UnaryMinusContext ctx) {
                return new UnaryOperator((Expression) visit(ctx.expr()), ctx.MINUS().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitBlock(CoolParser.BlockContext ctx) {
                return new Block(ctx.actions.stream().map(action -> (Expression) visit(action)).collect(Collectors.toList()), ctx.start);
            }

            @Override
            public ASTNode visitLet(CoolParser.LetContext ctx) {
                var localParams = ctx.localParams.stream().map(local -> (LocalParam) visit(local)).collect(Collectors.toList());
                return new Let(localParams, (Expression) visit(ctx.action), ctx.start);
            }

            @Override
            public ASTNode visitRelational(CoolParser.RelationalContext ctx) {
                return new BinaryOperator((Expression) visit(ctx.left), (Expression) visit(ctx.right), ctx.op, ctx.start);
            }

            @Override
            public ASTNode visitId(CoolParser.IdContext ctx) {
                return new Id(ctx.ID().getSymbol());
            }

            @Override
            public ASTNode visitComplement(CoolParser.ComplementContext ctx) {
                return new UnaryOperator((Expression) visit(ctx.expr()), ctx.COMPLEMENT().getSymbol(), ctx.start);
            }

            @Override
            public ASTNode visitIf(CoolParser.IfContext ctx) {
                return new If((Expression) visit(ctx.cond),
                        (Expression) visit(ctx.thenBranch),
                        (Expression) visit(ctx.elseBranch),
                        ctx.start);
            }

            @Override
            public ASTNode visitCase(CoolParser.CaseContext ctx) {
                return new Case((Expression) visit(ctx.caseValue),
                        ctx.branches.stream().map(branch -> (CaseBranch) visit(branch)).collect(Collectors.toList()),
                        ctx.start);
            }

            @Override
            public ASTNode visitAssign(CoolParser.AssignContext ctx) {
                return new Assign(ctx.name, (Expression) visit(ctx.expr()), ctx.start);
            }

            // TODO 3: Completati cu alte metode pentru a trece din arborele
            // generat automat in reprezentarea AST-ului dorit

        };

        var ast = astConstructionVisitor.visit(globalTree);

        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("  ");
                System.out.println(str);
            }

            @Override
            public Void visit(Id id) {
                printIndent(id.token.getText());
                return null;
            }

            @Override
            public Void visit(FormalParam param) {
                printIndent("formal");
                indent++;
                printIndent(param.type.getText());
                printIndent(param.name.getText());
                indent--;
                return null;
            }

            @Override
            public Void visit(Program program) {
                printIndent("program");
                indent++;
                program.classes.forEach(classNode -> classNode.accept(this));
                indent--;
                return null;
            }

            @Override
            public Void visit(Class myClass) {
                printIndent("class");
                indent++;
                printIndent(myClass.name.getText());
                if (myClass.inherits != null) {
                    printIndent(myClass.inherits.getText());
                }
                if (myClass.features != null) {
                    myClass.features.forEach(feat -> feat.accept(this));
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(VariabileDefinition variabileDefinition) {
                printIndent("attribute");
                indent++;
                printIndent(variabileDefinition.name.getText());
                printIndent(variabileDefinition.type.getText());
                if (variabileDefinition.variableValue != null) {
                    variabileDefinition.variableValue.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(FunctionDefinition functionDefinition) {
                printIndent("method");
                indent++;
                printIndent(functionDefinition.name.getText());
                if (functionDefinition.formalParams != null) {
                    functionDefinition.formalParams.forEach(param -> param.accept(this));
                }
                printIndent(functionDefinition.type.getText());
                if (functionDefinition.functionValue != null) {
                    functionDefinition.functionValue.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Int intVal) {
                printIndent(intVal.token.getText());
                return null;
            }

            @Override
            public Void visit(StringNode string) {
                printIndent(string.token.getText());
                return null;
            }

            @Override
            public Void visit(BoolNode bool) {
                printIndent(bool.token.getText());
                return null;
            }

            @Override
            public Void visit(BinaryOperator binaryOperator) {
                printIndent(binaryOperator.operator.getText());
                indent++;
                binaryOperator.left.accept(this);
                binaryOperator.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(UnaryOperator unaryOperator) {
                printIndent(unaryOperator.operator.getText());
                indent++;
                unaryOperator.expr.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(New newNode) {
                printIndent("new");
                indent++;
                printIndent(newNode.type.getText());
                indent--;
                return null;
            }

            @Override
            public Void visit(Assign assign) {
                printIndent("<-");
                indent++;
                printIndent(assign.name.getText());
                assign.expr.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(InitCall initCall) {
                printIndent("implicit dispatch");
                indent++;
                printIndent(initCall.name.getText());
                if (initCall.args != null) {
                    initCall.args.forEach(arg -> arg.accept(this));
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Call call) {
                printIndent(".");
                indent++;
                call.prefix.accept(this);
                if (call.atType != null) {
                    printIndent(call.atType.getText());
                }
                printIndent(call.name.getText());
                if (call.args != null) {
                    call.args.forEach(arg -> arg.accept(this));
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(If ifNode) {
                printIndent("if");
                indent++;
                ifNode.cond.accept(this);
                ifNode.thenBranch.accept(this);
                ifNode.elseBranch.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(While whileNode) {
                printIndent("while");
                indent++;
                whileNode.cond.accept(this);
                whileNode.action.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(LocalParam localParam) {
                printIndent("local");
                indent++;
                printIndent(localParam.name.getText());
                printIndent(localParam.type.getText());
                if (localParam.value != null) {
                    localParam.value.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Let let) {
                printIndent("let");
                indent++;
                let.params.forEach(param -> param.accept(this));
                let.action.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(CaseBranch caseBranch) {
                printIndent("case branch");
                indent++;
                printIndent(caseBranch.name.getText());
                printIndent(caseBranch.type.getText());
                caseBranch.value.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Case caseNode) {
                printIndent("case");
                indent++;
                caseNode.caseExpr.accept(this);
                caseNode.caseBranches.forEach(branch -> branch.accept(this));
                indent--;
                return null;
            }

            @Override
            public Void visit(Block block) {
                printIndent("block");
                indent++;
                block.actions.forEach(action -> action.accept(this));
                indent--;
                return null;
            }
        };

        // TODO 5: Creati un program CPLang care sa cuprinda cat mai multe
        // constructii definite in laboratorul de astazi. Scrieti codul CPLang
        // intr-un fisier txt si modificati fisierul de input de la inceputul
        // metodei 'main'

        ast.accept(printVisitor);
    }
}
