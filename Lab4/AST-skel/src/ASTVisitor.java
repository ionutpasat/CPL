public interface ASTVisitor<T> {
    T visit(Id astId);
    T visit(Int astInt);
    T visit(If astIf);
    T visit(Float astFloat);
    T visit(Bool astBool);
    T visit(Prog astProg);
    T visit(VarDef astVarDef);
    T visit(Call call);
    T visit(UnaryMinus astUnaryMinus);
    T visit(MultDiv astMultDiv);
    T visit(PlusMinus astPlusMinus);
    T visit(Relational astRelational);
    T visit(Assign astAssign);
    T visit(FuncDef astFuncDef);
    T visit(Formal astFormal);
}