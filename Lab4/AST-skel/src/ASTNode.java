import org.antlr.v4.runtime.Token;
import java.util.*;

// Rădăcina ierarhiei de clase reprezentând nodurile arborelui de sintaxă
// abstractă (AST). Singura metodă permite primirea unui visitor.
public abstract class ASTNode {
    // Reținem un token descriptiv, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    Token token;

    ASTNode(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

// Orice expresie.
abstract class Expression extends ASTNode {
    Expression(Token token) {
        super(token);
    }
}

// Identificatori
class Id extends Expression {
    Id(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Literali întregi
class Int extends Expression {
    Int(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Prog extends ASTNode {
    List<ASTNode> exprs;

    public Prog(Token token, List<ASTNode> exprs) {
        super(token);
        this.exprs = exprs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Construcția if.
class If extends Expression {
    // Sunt necesare trei câmpuri pentru cele trei componente ale expresiei.
    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    If(Expression cond,
       Expression thenBranch,
       Expression elseBranch,
       Token start) {
        super(start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Float extends Expression {
    Float(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Bool extends Expression {
    Bool(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class VarDef extends Expression {
    Token type;
    Token name;
    Expression value;

    public VarDef(Token type, Token name, Expression value) {
        super(type);
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public VarDef(Token type, Token name) {
        super(type);
        this.type = type;
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Function call */
class Call extends Expression {
    Token name;
    List<Expression> args;

    public Call(Token token) {
        super(token);
        this.name = token;
    }

    public Call(Token token, List<Expression> args) {
        super(token);
        this.name = token;
        this.args = args;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Negative number */
class UnaryMinus extends Expression {
    Expression e;

    public UnaryMinus(Token token, Expression e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Multipliction and Division */
class MultDiv extends Expression {
    Expression left;
    Expression right;
    Token op;

    public MultDiv(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Plus and Minus */
class PlusMinus extends Expression {
    Expression left;
    Expression right;
    Token op;

    public PlusMinus(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Relational */
class Relational extends Expression {
    Expression left;
    Expression right;
    Token op;

    public Relational(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Assign */
class Assign extends Expression {
    Token id;
    Expression e;

    Assign(Token token, Expression e) {
        super(token);
        id = token;
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Function definition */
class FuncDef extends Expression {
    Token returnType;
    Token name;
    List<Expression> formals;
    Expression body;

    public FuncDef(Token returnType, Token name, Expression e) {
        super(returnType);
        this.returnType = returnType;
        this.name = name;
        this.body = e;
    }

    public FuncDef(Token returnType, Token name, Expression e, List<Expression> formals) {
        super(returnType);
        this.returnType = returnType;
        this.name = name;
        this.body = e;
        this.formals = formals;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Formal */
class Formal extends Expression {
    Token type;
    Token id;

    Formal(Token type, Token id) {
        super(type);
        this.type = type;
        this.id = id;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}


// TODO 1: Definiți restul claselor de care ați avea nevoie. Asigurați-vă
// că moșteniți mereu nodul de bază ASTNode
