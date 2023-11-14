import java.io.IOException;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("if.txt");

        var lexer = new CPLangLexer(input);
        var tokenStream = new CommonTokenStream(lexer);

        /*
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        for (var token : tokens) {
            var text = token.getText();
            var type = CPLangLexer.VOCABULARY.getSymbolicName(token.getType());

            System.out.println(text + " : " + type);
        }
        */

        var parser = new CPLangParser(tokenStream);
        var tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // Visitor-ul de mai jos parcurge arborele de derivare și construiește
        // un arbore de sintaxă abstractă (AST).
        var astConstructionVisitor = new CPLangParserBaseVisitor<ASTNode>() {
            @Override
            public ASTNode visitId(CPLangParser.IdContext ctx) {
                return new Id(ctx.ID().getSymbol());
            }

            @Override
            public ASTNode visitInt(CPLangParser.IntContext ctx) {
                return new Int(ctx.INT().getSymbol());
            }

            @Override
            public ASTNode visitIf(CPLangParser.IfContext ctx) {
                return new If((Expression)visit(ctx.cond),
                              (Expression)visit(ctx.thenBranch),
                              (Expression)visit(ctx.elseBranch),
                              ctx.start);
            }

            @Override
            public ASTNode visitBool(CPLangParser.BoolContext ctx) {
                return new Bool(ctx.BOOL().getSymbol());
            }

            @Override
            public ASTNode visitFloat(CPLangParser.FloatContext ctx) {
                return new Float(ctx.FLOAT().getSymbol());
            }

            @Override
            public ASTNode visitProg(CPLangParser.ProgContext ctx) {
                List<ASTNode> exprs = new ArrayList<>();

                for (ParseTree child : ctx.children) {
                    exprs.add(visit(child));
                }


                return new Prog(ctx.start, exprs);
            }

            @Override
            public ASTNode visitVarDef(CPLangParser.VarDefContext ctx) {
                if (ctx.init != null)
                    return new VarDef(ctx.type, ctx.name, (Expression) visit(ctx.init));
                else
                    return new VarDef(ctx.type, ctx.name);
            }

            @Override
            public ASTNode visitFuncDef(CPLangParser.FuncDefContext ctx) {
                List<Expression> formals = new ArrayList<>();

                if (ctx.formals == null) {
                    return new FuncDef(ctx.type, ctx.name, (Expression) visit(ctx.body));
                }

                for (CPLangParser.FormalContext e : ctx.formals) {
                    formals.add((Expression) visit(e));
                }

                return new FuncDef(ctx.type, ctx.name, (Expression) visit(ctx.body), formals);
            }

            @Override
            public ASTNode visitFormal(CPLangParser.FormalContext ctx) {
                return new Formal(ctx.type, ctx.name);
            }

            @Override
            public ASTNode visitCall(CPLangParser.CallContext ctx) {
                List<Expression> args = new ArrayList<>();

                if (ctx.args == null)
                    return new Call(ctx.name);
                else
                    for (CPLangParser.ExprContext e : ctx.args) {
                        args.add((Expression)visit(e));
                    }
                return new Call(ctx.name, args);
            }

            @Override
            public ASTNode visitParen(CPLangParser.ParenContext ctx) {
                return super.visitParen(ctx);
            }

            @Override
            public ASTNode visitPlusMinus(CPLangParser.PlusMinusContext ctx) {
                return new PlusMinus(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitMultDiv(CPLangParser.MultDivContext ctx) {
                return new MultDiv(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitUnaryMinus(CPLangParser.UnaryMinusContext ctx) {
                return new UnaryMinus(ctx.start, (Expression)visit(ctx.e));
            }

            @Override
            public ASTNode visitRelational(CPLangParser.RelationalContext ctx) {
                return new Relational(ctx.op, (Expression)visit(ctx.left), (Expression)visit(ctx.right));
            }

            @Override
            public ASTNode visitAssign(CPLangParser.AssignContext ctx) {
                return new Assign(ctx.name, (Expression)visit(ctx.e));
            }

            // TODO 3: Completati cu alte metode pentru a trece din arborele
            // generat automat in reprezentarea AST-ului dorit

        };

        // ast este AST-ul proaspăt construit pe baza arborelui de derivare.
        var ast = astConstructionVisitor.visit(tree);

        // Acest visitor parcurge AST-ul generat mai sus.
        // ATENȚIE! Avem de-a face cu două categorii de visitori:
        // (1) Cei pentru arborele de derivare, care extind
        //     CPLangParserBaseVisitor<T> și
        // (2) Cei pentru AST, care extind ASTVisitor<T>.
        // Aveți grijă să nu îi confundați!
        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            @Override
            public Void visit(Id id) {
                printIndent("ID " + id.token.getText());
                return null;
            }

            @Override
            public Void visit(Int intt) {
                printIndent("INT " + intt.token.getText());
                return null;
            }

            @Override
            public Void visit(If iff) {
                printIndent("IF");
                indent++;
                iff.cond.accept(this);
                iff.thenBranch.accept(this);
                iff.elseBranch.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Prog prog) {
                printIndent("PROGR ");
                for (ASTNode instr : prog.exprs) {
                    indent++;
                    if (instr != null) {
                        instr.accept(this);
                    }

                    indent--;
                }
                return null;
            }

            @Override
            public Void visit(Float astFloat) {
                printIndent("FLOAT " + astFloat.token.getText());
                return null;
            }

            @Override
            public Void visit(Bool astBool) {
                printIndent("BOOL " + astBool.token.getText());
                return null;
            }

            @Override
            public Void visit(VarDef astVarDef) {
                printIndent("VAR DEF");
                indent++;

                printIndent(astVarDef.type.getText());
                printIndent(astVarDef.name.getText());

                if (astVarDef.value != null) {
                    indent++;
                    astVarDef.value.accept(this);
                    indent--;
                }

                indent--;
                return null;
            }

            @Override
            public Void visit(Call call) {
                printIndent("CALL");

                printIndent(call.name.getText());
                if (call.args != null) {
                    for (Expression e : call.args) {
                        indent++;
                        e.accept(this);
                        indent--;
                    }
                }
                return null;
            }

            @Override
            public Void visit(UnaryMinus astUnaryMinus) {
                printIndent("UNARY MINUS");
                indent++;
                printIndent("-");
                astUnaryMinus.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(MultDiv astMultDiv) {
                printIndent(astMultDiv.op.getText());
                indent++;
                astMultDiv.left.accept(this);
                astMultDiv.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(PlusMinus astPlusMinus) {
                printIndent(astPlusMinus.op.getText());
                indent++;
                astPlusMinus.left.accept(this);
                astPlusMinus.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Relational astRelational) {
                printIndent(astRelational.op.getText());
                indent++;
                astRelational.left.accept(this);
                astRelational.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Assign astAssign) {
                printIndent("ASSIGN");
                indent++;
                printIndent(astAssign.id.getText());
                astAssign.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(FuncDef astFuncDef) {
                printIndent("FUNCTION");
                indent++;
                printIndent(astFuncDef.name.getText());
                indent++;
                if (astFuncDef.formals != null) {
                    for (Expression e : astFuncDef.formals) {
                        e.accept(this);
                    }
                }
                indent++;
                printIndent("FUNCTION BODY");
                astFuncDef.body.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Formal astFormal) {
                printIndent("FORMAL");
                printIndent(astFormal.type.getText());
                printIndent(astFormal.id.getText());
                return null;
            }

            // TODO 4: Afisati fiecare nod astfel incat nivelul pe care acesta
            // se afla in AST sa fie reprezentat de numarul de tab-uri.
            // Folositi functia de mai jos 'printIndent' si incrementati /
            // decrementati corespunzator numarul de tab-uri

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("\t");
                System.out.println(str);
            }
        };

        // TODO 5: Creati un program CPLang care sa cuprinda cat mai multe
        // constructii definite in laboratorul de astazi. Scrieti codul CPLang
        // intr-un fisier txt si modificati fisierul de input de la inceputul
        // metodei 'main'

        System.out.println("The AST is");
        ast.accept(printVisitor);
    }


}
