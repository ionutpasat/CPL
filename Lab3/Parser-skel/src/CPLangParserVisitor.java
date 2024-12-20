// Generated from D:/Intellij IDEA/Code/CPL/Lab3/Parser-skel/CPLangParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPLangParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CPLangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CPLangParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDef}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(CPLangParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDef}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(CPLangParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CPLangParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(CPLangParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcall}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncall(CPLangParser.FuncallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(CPLangParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(CPLangParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CPLangParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(CPLangParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CPLangParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(CPLangParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negative}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegative(CPLangParser.NegativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(CPLangParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CPLangParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CPLangParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(CPLangParser.AssignContext ctx);
}