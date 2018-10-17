import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;


public class MiniGoPrintListener extends MiniGoBaseListener {
	ParseTreeProperty<String> newTexts = new ParseTreeProperty<>();

	String indent = "";

	boolean isBinaryOperation(MiniGoParser.ExprContext ctx) {
		return ctx.getChildCount() == 3 && ctx.getChild(1) != ctx.expr();
	}

	@Override
	public void exitProgram(MiniGoParser.ProgramContext ctx) {

		int i = 0;
		while (ctx.decl(i) != null) {
			System.out.println(newTexts.get(ctx.decl(i)));
			i++;
		}
	}

	@Override
	public void exitDecl(MiniGoParser.DeclContext ctx) {

		if (ctx.getChild(0) == ctx.var_decl()) { // var_decl
			newTexts.put(ctx, newTexts.get(ctx.var_decl()));
		} else { // fun_decl
			newTexts.put(ctx, newTexts.get(ctx.fun_decl()));
		}
	}

	@Override
	public void exitVar_decl(MiniGoParser.Var_declContext ctx) {

		if (ctx.getChildCount() == 3) { // dec_spec IDENT type_spec';';
			newTexts.put(ctx, newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1).getText() + " "
					+ newTexts.get(ctx.type_spec()));
		} else if (ctx.getChildCount() == 5) { // dec_spec IDENT type_spec '=' LITERAL ';';
			newTexts.put(ctx, newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1) + " " + newTexts.get(ctx.type_spec())
					+ " = " + ctx.getChild(4));
		} else { // dec_spec IDENT '[' LITERAL ']' type_spec ";" ;
			newTexts.put(ctx, indent + newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1).getText() + "["
					+ ctx.getChild(3).getText() + "] " + newTexts.get(ctx.type_spec()));
		}
	}

	@Override
	public void exitDec_spec(MiniGoParser.Dec_specContext ctx) {
		newTexts.put(ctx, ctx.getChild(0).getText());
	}

	@Override
	public void exitType_spec(MiniGoParser.Type_specContext ctx) {
		if (ctx.getChild(0) == null) {
			newTexts.put(ctx, "");
		} else {
			newTexts.put(ctx, ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitFun_decl(MiniGoParser.Fun_declContext ctx) {
		if (ctx.getChildCount() == 7) { // FUNC IDENT '(' params ')' type_spec compound_stmt
			newTexts.put(ctx,
					ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " (" + newTexts.get(ctx.params())
							+ ") " + newTexts.get(ctx.type_spec(0)) + " " + newTexts.get(ctx.compound_stmt()));
		} else if (ctx.getChildCount() == 11) { // FUNC IDENT '(' params ')' '(' type_spec ',' type_spec ')'
												// compound_stmt;
			newTexts.put(ctx,
					ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " (" + newTexts.get(ctx.params())
							+ ")" + "(" + newTexts.get(ctx.type_spec(0)) + "," + newTexts.get(ctx.type_spec(1)) + ")"
							+ newTexts.get(ctx.compound_stmt()));
		}
	}

	@Override
	public void exitParams(MiniGoParser.ParamsContext ctx) {
		String box = "";
		if (ctx.getChildCount() >= 1) {
			box += newTexts.get(ctx.param(0));
			if (ctx.getChildCount() > 2) { // param(',' param)*
				for (int i = 1; i <= ctx.getChildCount() / 2; i++) {
					box += ", " + newTexts.get(ctx.param(i));
				}
			}

		}

		newTexts.put(ctx, box);
	}

	@Override
	public void exitParam(MiniGoParser.ParamContext ctx) {
		if (ctx.getChildCount() == 1) { // IDNET
			newTexts.put(ctx, ctx.getChild(0).getText());
		} else { // IDNET type_spec;
			newTexts.put(ctx, ctx.getChild(0).getText() + " " + newTexts.get(ctx.type_spec()));
		}
	}

	@Override
	public void enterCompound_stmt(MiniGoParser.Compound_stmtContext ctx) {
		indent += "....";
	}

	@Override
	public void exitCompound_stmt(MiniGoParser.Compound_stmtContext ctx) {
		String box = "";
		if (ctx.parent.parent instanceof MiniGoParser.For_stmtContext
				|| ctx.parent.parent instanceof MiniGoParser.If_stmtContext
				|| ctx.parent instanceof MiniGoParser.Fun_declContext) {
			box = "{\n";
		} else {
			indent = indent.substring(0, indent.length() - 4);
			box += indent;
			box += "{\n";
			indent += "....";	}
		int i = 0;
		while (ctx.local_decl(i) != null) { // local_decl*
			box += newTexts.get(ctx.local_decl(i));
			i++;
		}
		i = 0;
		while (ctx.stmt(i) != null) { // stmt*
			box += newTexts.get(ctx.stmt(i));
			i++;
		}
		indent = indent.substring(0, indent.length() - 4);
		box += indent;
		box += "}\n";
		newTexts.put(ctx, box);
	}

	@Override
	public void exitLocal_decl(MiniGoParser.Local_declContext ctx) {
		if (ctx.getChildCount() == 3) { // dec_spec IDENT type_spec';';
			newTexts.put(ctx, indent + newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1).getText() + " "
					+ newTexts.get(ctx.type_spec()) + "\n");
		} else if (ctx.getChildCount() == 5) { // dec_spec IDENT type_spec '=' LITERAL ';';
			newTexts.put(ctx, indent + newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1) + " "
					+ newTexts.get(ctx.type_spec()) + " = " + ctx.getChild(4) + "\n");
		} else { // dec_spec IDENT '[' LITERAL ']' type_spec ";" ;
			newTexts.put(ctx, indent + newTexts.get(ctx.dec_spec()) + " " + ctx.getChild(1).getText() + "["
					+ ctx.getChild(3).getText() + "] " + newTexts.get(ctx.type_spec()) + "\n");
		}
	}
	

	@Override
	public void exitExpr_stmt(MiniGoParser.Expr_stmtContext ctx) {
		newTexts.put(ctx, indent + newTexts.get(ctx.expr()) + "\n");
		
	}

	@Override
	public void exitStmt(MiniGoParser.StmtContext ctx) {
		if (ctx.getChild(0) == ctx.expr_stmt()) { // expr_stmt
			newTexts.put(ctx, newTexts.get(ctx.expr_stmt()));
		} else if (ctx.getChild(0) == ctx.compound_stmt()) { // compound_stmt
			newTexts.put(ctx, newTexts.get(ctx.compound_stmt()));
		} else if (ctx.getChild(0) == ctx.if_stmt()) { // if_stmt
			newTexts.put(ctx, newTexts.get(ctx.if_stmt()));
		} else if (ctx.getChild(0) == ctx.for_stmt()) { // for_stmt
			newTexts.put(ctx, newTexts.get(ctx.for_stmt()));
		} else if (ctx.getChild(0) == ctx.return_stmt()) { // return_stmt
			newTexts.put(ctx, newTexts.get(ctx.return_stmt()));
		}
	}

	@Override
	public void exitFor_stmt(MiniGoParser.For_stmtContext ctx) {
		if (ctx.getChild(1) instanceof MiniGoParser.Loop_exprContext) { // FOR loop_expr stmt
			if (ctx.stmt().getChild(0) instanceof MiniGoParser.Compound_stmtContext) {
				newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.loop_expr()) + " "
						+ newTexts.get(ctx.stmt()));
			} else {
				newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.loop_expr()) + "\n"
						+ newTexts.get(ctx.stmt()));
			}
		} else { // FOR expr stmt
			if (ctx.stmt().getChild(0) instanceof MiniGoParser.Compound_stmtContext) {
				newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr()) + " "
						+ newTexts.get(ctx.stmt()));
			} else {
				newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr()) + "\n"
						+ newTexts.get(ctx.stmt()));
			}
		}
		
	}

	@Override
	public void exitLoop_expr(MiniGoParser.Loop_exprContext ctx) {
		newTexts.put(ctx, newTexts.get(ctx.expr(0)) + "; " + newTexts.get(ctx.expr(1)) + "; "
				+ newTexts.get(ctx.expr(2)) + ctx.getChild(5).getText());
	}

	@Override
	public void exitIf_stmt(MiniGoParser.If_stmtContext ctx) {
		String box = indent + ctx.getChild(0) + " " + newTexts.get(ctx.expr());
		if (ctx.stmt(0).getChild(0) instanceof MiniGoParser.Compound_stmtContext) {
			box += " ";
		} else {
			box += "\n";
		}
		box += newTexts.get(ctx.stmt(0));
		if (ctx.getChildCount() == 5) { // If expr stmt ElSE stmt;
			box += indent + ctx.getChild(3).getText();
			if (ctx.stmt(1).getChild(0) instanceof MiniGoParser.Compound_stmtContext) {
				box += "";
			} else {
				box += "\n";
			}
			box += newTexts.get(ctx.stmt(1));
		}
		newTexts.put(ctx, box);
	}

	@Override
	public void exitReturn_stmt(MiniGoParser.Return_stmtContext ctx) {
		if (ctx.getChildCount() == 2) {
			newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr(0)) + "\n");
		} else if (ctx.getChildCount() == 4) {
			newTexts.put(ctx, indent + ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr(0)) + " , "
					+ newTexts.get(ctx.expr(1)) + "\n");
		} else {
			newTexts.put(ctx, indent + ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitExpr(MiniGoParser.ExprContext ctx) {
		String s1 = null, s2 = null, op = null;
		/* LITERAL|IDENT */
		if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof TerminalNodeImpl) {
			newTexts.put(ctx, ctx.getChild(0).getText());
		}
		/* IDENT '=' expr */
		else if (ctx.getChild(0) instanceof TerminalNodeImpl && ctx.getChild(1).getText().equals("=")) {
			newTexts.put(ctx, ctx.getChild(0).getText() + " = " + newTexts.get(ctx.expr(0)));
		}
		/* IDENT '[' expr ']' '=' expr ; */
		else if (ctx.getChild(0) instanceof TerminalNodeImpl && ctx.getChild(1).getText().equals("[")
				&& ctx.getChild(2) instanceof MiniGoParser.ExprContext && ctx.getChildCount() == 6) {
			newTexts.put(ctx,
			ctx.getChild(0).getText() + "[" + newTexts.get(ctx.expr(0)) + "] = " + newTexts.get(ctx.expr(1)));
		}
		/* FMT '.' IDENT '(' args ')' */
		else if (ctx.getChild(0) instanceof TerminalNodeImpl && ctx.getChild(1).getText().equals(".")) {
			newTexts.put(ctx, ctx.getChild(0).getText() + ". " + ctx.getChild(2).getText() + " ("
					+ newTexts.get(ctx.args()) + ")");
		}
		/* '(' expr ')' */
		else if (ctx.getChild(0).getText().equals("(") && ctx.getChild(1) instanceof MiniGoParser.ExprContext) {
			newTexts.put(ctx, ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)) + ctx.getChild(2).getText());
		}
		/* IDENT '[' expr ']' */
		else if (ctx.getChild(0) instanceof TerminalNodeImpl && ctx.getChild(1).getText().equals("[")
				&& ctx.getChild(2) instanceof MiniGoParser.ExprContext) {

			newTexts.put(ctx, ctx.getChild(0).getText() + " [ " + newTexts.get(ctx.expr(0)) + " ]");
		}
		/* IDENT '(' args ')' */
		else if (ctx.getChild(0) instanceof TerminalNodeImpl && ctx.getChild(1).getText().equals("(")
				&& ctx.getChild(2) instanceof MiniGoParser.ArgsContext) {

			newTexts.put(ctx, ctx.getChild(0).getText() + " (" + newTexts.get(ctx.args()) + ")");
		} else if (isBinaryOperation(ctx)) {
			s1 = newTexts.get(ctx.expr(0));
			s2 = newTexts.get(ctx.expr(1));
			op = ctx.getChild(1).getText();
			newTexts.put(ctx, s1 + " " + op + " " + s2);

		} else { // op=('-'|'+'|'--'|'++'|'!') expr
			newTexts.put(ctx, ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)));
		}
	}

	@Override
	public void exitArgs(MiniGoParser.ArgsContext ctx) {
		String box = "";
		if (ctx.getChildCount() > 0) {
			box = newTexts.get(ctx.expr(0));
			for (int i = 0; i < ctx.getChildCount() / 2; i++) {
				box += ", ";
				box += newTexts.get(ctx.expr(i + 1));
			}
		}
		newTexts.put(ctx, box);
	}
}
