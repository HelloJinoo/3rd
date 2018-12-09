
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;


public class UcodeGenListener extends MiniGoBaseListener {
	ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
	int base = 2;
	int jmp_label = 0;
	int blockNo = 1;	//전역은 1, 함수들은 2
	int forCount = 0;
	int ifCount = 0;
	int globalVar_offset = 0;
	int localVar_offset = 0;
	List<Variable> symbol_table = new ArrayList<>();
	
	//보조 메소드
	public static boolean isNumber(char firstLetter) {
        if(firstLetter == ' ')	//비어있을 때 
            return false;
     
        if(firstLetter<'0' || firstLetter>'9')
                return false;
        
        return true;
    }
	
	private String printTab()
	{
		// 왼쪽에 11칸은 빈칸 (space 11개) - 12칸부터 코드 시작
		String blank = "";
		for(int i=0; i<11; i++)
			blank += " ";
		return blank;
	}
	
	private String printTab(String label)
	{	
		//레이블과 하나이상 공백띄우고 12칸부터 코드 시작
		String blank = label;
		for(int i=0; i<11-label.length(); i++)
			blank += " ";
		return blank;
	}
	
	private Variable lookupTable(String varName, int blockNo) {	//LITERAL을 위해!
		for(int i = symbol_table.size()-1; i>=0; i--) {	//다른 함수에 이름이 같은 변수가 있을 수 있으므로
			String cmp_varName = symbol_table.get(i).name;
			int cmp_blockNo = symbol_table.get(i).base;
			if(cmp_varName.equals(varName) && cmp_blockNo == blockNo) {
				return symbol_table.get(i);
			}
		}
		return null;
	}
	
	private void insertTable(String varName, int base, int offset, int size, int param) {
		if(blockNo == 1) {								//위치가 전역인 경우
			if(lookupTable(varName, blockNo) == null) {	//테이블에 똑같은 변수가 없으면 insert
				Variable newVar = new Variable(varName, base, offset, size, param);
				symbol_table.add(newVar);
			}
			else {
				System.out.println("전역 변수 " + varName + " 이미 있어요~");
			}
		}
		else {
			if(lookupTable(varName,blockNo) == null) {
				Variable newVar = new Variable(varName, base, offset, size, param);
				symbol_table.add(newVar);
			}
			else {
				if(lookupTable(varName, blockNo).param == 1) {	//위치가 로컬인 경우 테이블에 파라미터로 같은 변수가 있는지 확인해야 함
					System.out.println("변수 " + varName + "가 parameter로 이미 있어요~");
				}
				else {
					Variable newVar = new Variable(varName, base, offset, size, param);
					symbol_table.add(newVar);
				}
			}
		}
	}
	
	
	//내부 클래스
	public class Variable {	//변수 클래스
		String name;
		int base;
		int offset;
		int size;
		int param=0; //parameter면 1, 아니면 0
		
		public Variable(String name, int base, int offset, int size, int param) {
			this.name = name;
			this.base = base;
			this.offset = offset;
			this.size = size;
			this.param = param;
		}
		
	}
   
   boolean isBinaryOperation(MiniGoParser.ExprContext ctx) {
      return ctx.getChildCount() == 3 && ctx.getChild(1) != ctx.expr();
   }

   
   @Override
   public void exitProgram(MiniGoParser.ProgramContext ctx) {
   
	   String decl = "";
		for(int i=0; i<ctx.getChildCount(); i++) {
				decl += newTexts.get(ctx.decl(i));
		}
		newTexts.put(ctx, decl);
		String finish = printTab() + "bgn " + globalVar_offset + "\n";
		finish += printTab() + "ldp\n";			//메인 호출
		finish += printTab() + "call main\n";	//.
		finish += printTab() + "end";
		System.out.println(newTexts.get(ctx) +finish);
   }

   @Override
   public void exitDecl(MiniGoParser.DeclContext ctx) {
      if (ctx.getChild(0) == ctx.var_decl()) { // var_decl
         newTexts.put(ctx, newTexts.get(ctx.var_decl()));
      } else { // fun_decl
         newTexts.put(ctx, newTexts.get(ctx.fun_decl()));
      }
   }
   /*전역 변수*/
   @Override
public void enterVar_decl(MiniGoParser.Var_declContext ctx) {
	    globalVar_offset++;
	    String decl="";
		int base = 1;	//전역은 base 1
		int offset = globalVar_offset;
		int size;
		
		String varName = ctx.getChild(1).getText();
		
		if(ctx.getChildCount() == 3) {	//VAR IDENT type_spec
		
			size = 1;
			insertTable(varName, base, offset, size, 0);
		}
		else if( ctx.getChildCount() == 5) {	//VAR IDENT ',' IDENT type_spec
			
			
			size = 1;
			insertTable(varName, base, offset, size, 0);
				
			globalVar_offset++;
			offset = globalVar_offset;
			
			String varName2 = ctx.getChild(3).getText();
			size = 1;
			insertTable(varName2, base, offset, size, 0);
			
		}
		else if( ctx.getChildCount() == 6) {	//VAR IDENT '[' LITERAL ']' type_spec
			size = Integer.parseInt(ctx.getChild(3).getText());
			globalVar_offset += (size - 1);
			insertTable(varName, base, offset, size, 0);
		}
	
}
   

   @Override
   public void exitVar_decl(MiniGoParser.Var_declContext ctx) {
	   String varName = ctx.getChild(1).getText();
	Variable ident = lookupTable(varName, blockNo);
	
	String decl ="";
	   if( ctx.getChildCount() == 3) {
		   decl = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
	   }
	   else if( ctx.getChildCount() ==5) {
		   String varName2 = ctx.getChild(3).getText();
			Variable ident2 = lookupTable(varName2, blockNo);
		   decl = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
		   decl += printTab() + "sym " + ident2.base + " " + ident2.offset + " " + ident2.size + "\n";
	   }
	   else if( ctx.getChildCount() ==6) {
		   decl = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
	   }
	   newTexts.put(ctx, decl);
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
	public void enterFun_decl(MiniGoParser.Fun_declContext ctx) {
		blockNo++;
		localVar_offset = 0;
	}
   
   @Override
   public void exitFun_decl(MiniGoParser.Fun_declContext ctx) {
	 
			String stmt="";
			String func_name = ctx.getChild(1).getText();	// IDENT
			stmt += printTab(func_name)	+"proc " + localVar_offset + " 2 2 \n";
			stmt += newTexts.get(ctx.params());
			stmt += newTexts.get(ctx.compound_stmt());
			stmt += printTab() + "end\n";
			newTexts.put(ctx, stmt);
			blockNo--;
			localVar_offset = 0;
		
   }

   @Override
   public void exitParams(MiniGoParser.ParamsContext ctx) {
		String params = "";
		if(ctx.getChildCount() > 0)
		{	// param (',' param)*
			for(int i=0; i<ctx.getChildCount(); i++)
			{
				if(i % 2 == 0)
					params += newTexts.get(ctx.param(i/2));	// param
			}
		}
		newTexts.put(ctx, params);
   }

   @Override
   public void enterParam(MiniGoParser.ParamContext ctx) {
	   
	   	localVar_offset++;
		String varName = ctx.getChild(0).getText();
		int base = 2;	//전역 아닌 나머지 상황에서는 base 2
		int offset = localVar_offset;
		int size;
		
		/* symbol_table에 변수 넣어주기 */
		if(ctx.getChildCount() >= 2)
		{
			if(ctx.getChildCount() ==4) {
				size = lookupTable(varName, blockNo).size;
			}
			else {
			size = 1;
			}
			insertTable(varName, base, offset, size, 1);
		}
   }
   
   @Override
public void exitParam(MiniGoParser.ParamContext ctx) {
	   super.exitParam(ctx);
		String param = "";
		if(ctx.getChildCount() >= 2)
		{
			String varName = ctx.getChild(0).getText();		// IDENT
			Variable ident = lookupTable(varName, blockNo);
			if(ident != null) {
				param += printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
				newTexts.put(ctx, param);
			}
			else {
				System.out.println("[exitParam] : 변수 " + varName + " 없어요~");
			}
		}
}

  

   @Override
   public void exitCompound_stmt(MiniGoParser.Compound_stmtContext ctx) {
	   String stmt = "";
		int local_i = 0, stmt_i = 0;
		if(ctx.getChildCount() >= 2)
		{
			for(int i=1; i<ctx.getChildCount()-1; i++)
			{
				if(ctx.local_decl().contains(ctx.getChild(i)))		// local_decl인 경우
					stmt += newTexts.get(ctx.local_decl(local_i++));
				else 											// stmt인 경우
					stmt += newTexts.get(ctx.stmt(stmt_i++));
			}
		}
		newTexts.put(ctx, stmt);
      
   }

   @Override
   public void enterLocal_decl(MiniGoParser.Local_declContext ctx) {
	   localVar_offset++;
	   String varName = ctx.getChild(1).getText();
		int base = 2;	//전역 아닌 나머지 상황에서는 base 2
		int offset = localVar_offset;
		int size;
		
		/* symbol_table에 변수 넣어주기 */
		if(ctx.getChildCount() >= 3)
		{
			if(ctx.getChildCount() == 6){	//VAR IDENT '[' LITERAL ']' type_spec인 경우
											//배열 변수이므로 size 받아와야하고, 위에서 하나 증가시킨 거 뺀 size만큼 offset 증가
				size = Integer.parseInt(ctx.getChild(3).getText());
				localVar_offset += (size - 1);
				insertTable(varName, base, offset, size, 0);
			}
			else {	//VAR IDENT type_spec 인 경우
				size = 1;
				insertTable(varName, base, offset, size, 0);
			}
		}
   }
   
   @Override
public void exitLocal_decl(MiniGoParser.Local_declContext ctx) {
	   String varName = ctx.getChild(1).getText();
		Variable ident = lookupTable(varName, blockNo);
		
		String decl ="";
		   if( ctx.getChildCount() == 3) {
			   decl = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
		   }
		   else if( ctx.getChildCount() ==6) {
			   decl = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
		   }
		   newTexts.put(ctx, decl);
}

   @Override
   public void exitExpr_stmt(MiniGoParser.Expr_stmtContext ctx) {
	   newTexts.put(ctx, newTexts.get(ctx.expr()));
      
   }

   @Override
   public void exitStmt(MiniGoParser.StmtContext ctx) {
	   if (ctx.getChild(0) == ctx.expr_stmt()) { // expr_stmt
			newTexts.put(ctx, newTexts.get(ctx.expr_stmt()));
		} else if (ctx.getChild(0) == ctx.compound_stmt()) { // compound_stmt
			newTexts.put(ctx, newTexts.get(ctx.compound_stmt()));
		} else if( ctx.getChild(0) == ctx.assign_stmt()) {
			newTexts.put(ctx,newTexts.get(ctx.assign_stmt()));
		}
		else if (ctx.getChild(0) == ctx.if_stmt()) { // if_stmt
			newTexts.put(ctx, newTexts.get(ctx.if_stmt()));
		} else if (ctx.getChild(0) == ctx.for_stmt()) { // for_stmt
			newTexts.put(ctx, newTexts.get(ctx.for_stmt()));
		} else if (ctx.getChild(0) == ctx.return_stmt()) { // return_stmt
			newTexts.put(ctx, newTexts.get(ctx.return_stmt()));
		}
   }
   @Override
	public void exitAssign_stmt(MiniGoParser.Assign_stmtContext ctx) {
	   String s1 ="" , s2="", v1="",v2="",value="";
	   Variable ident,ident2;
	   localVar_offset++;
	   int base = 2;	//전역 아닌 나머지 상황에서는 base 2
	   int offset = localVar_offset;
	   int size;
		
	   
	   if(ctx.getChildCount() == 9) {  //VAR IDENT ',' IDENT type_spec '=' LITERAL ',' LITERAL
		  s1 = ctx.getChild(1).getText();
		  s2 = ctx.getChild(3).getText();
		  v1 = ctx.getChild(6).getText();
		  v2 = ctx.getChild(8).getText();
		  size = 1;
		  insertTable(s1, base, offset, size, 0);
		  localVar_offset++;
		  offset = localVar_offset;
		  insertTable(s2, base, offset, size, 0);
		  
		  ident =lookupTable(s1,blockNo);
		  ident2 =lookupTable(s2, blockNo);
		  
		  if(ident != null) {
			    value = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
			  	value += printTab()+"ldc" + " "+v1+"\n";
				value += printTab()+"str "+ident.base + " "+ident.offset + "\n";
				
				value += printTab() + "sym " + ident2.base + " " + ident2.offset + " " + ident2.size + "\n";
			 	value += printTab()+"ldc" +" "+v2+"\n";
				value += printTab()+"str "+ident2.base + " "+ident2.offset + "\n";
	
				newTexts.put(ctx, value);
			}
			else {
				if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
					ident = lookupTable(s1, 1);
					ident2 = lookupTable(s2, 1);
					if(ident != null && ident2 != null) {
						value = printTab()+"lod "+ident.base+" "+ident.offset+"\n";
						value += printTab()+"ldc"+" "+v1+"\n";
						value += printTab()+"str"+" "+ident.base + " "+ident.offset + "\n";
						
						value += printTab()+"lod "+ident2.base+" "+ident2.offset;
					 	value += printTab()+"ldc"+" "+v2+"\n";
						value += printTab()+"str"+" "+ident2.base + " "+ident2.offset + "\n";
					
						newTexts.put(ctx, value);
					}
					else {
						System.out.println(" 변수가  없어요~");
					}
				}
				System.out.println(" 변수가  없어요~");
			}
	   }
	   else if(ctx.getChildCount() ==5) { //VAR IDENT type_spec '=' expr
		   s1 = ctx.getChild(1).getText();
		   size =1;
		   insertTable(s1, base, offset, size, 0);
		   ident =lookupTable( s1,blockNo);
		   v1 = newTexts.get(ctx.expr(0));
			  
			  if(ident != null) {
				  value = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
				  	value += v1;
					value += printTab()+"str "+ident.base + " "+ident.offset + "\n";
					newTexts.put(ctx, value);
				}
				else {
					if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
						ident = lookupTable(s1, 1);
					
						if(ident != null) {
							value = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
							value += v1;
							value += printTab()+"str "+ident.base + " "+ident.offset + "\n";
							newTexts.put(ctx, value);
						}
						else {
							System.out.println(" 변수가  없어요~");
						}
					}
					System.out.println(" 변수가  없어요~");
				}
	   } 
	   else if(ctx.getChildCount() == 4) { //IDENT type_spec '=' expr
		   s1 = ctx.getChild(0).getText();
		   size=1;
		   insertTable(s1, base, offset, size, 0);
		   ident =lookupTable( s1,blockNo);
		   v1 = newTexts.get(ctx.expr(0));
			  
			  if(ident != null) {
				  	value = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
				  	value += v1;
					value += printTab()+"str "+ident.base + " "+ident.offset + "\n";
					newTexts.put(ctx, value);
				}
				else {
					if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
						ident = lookupTable(s1, 1);
					
						if(ident != null) {
							value = printTab() + "lod " + ident.base + " " + ident.offset + "\n";
							value += v1;
							value += printTab()+"str "+ident.base + " "+ident.offset + "\n";
							newTexts.put(ctx, value);
						}
						else {
							System.out.println(" 변수가  없어요~");
						}
					}
					System.out.println(" 변수가  없어요~");
				}
		   
	   }else { // IDENT '[' expr ']' '=' expr 
		  
		    s1 = ctx.getChild(0).getText();	// IDENT
		    size = Integer.parseInt(ctx.getChild(3).getText());
			localVar_offset += (size - 1);
		    insertTable(s1, base, offset, size, 0);
			v1 = newTexts.get(ctx.expr(0));	// expr
			v2 = newTexts.get(ctx.expr(1));	// expr
			ident = lookupTable(s1, blockNo);
			if(ident != null) {
				value = printTab() + "sym " + ident.base + " " + ident.offset + " " + ident.size + "\n";
				value += v1;
				value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
				value += printTab() + "add\n";
				value += v2; 
				value += printTab() + "sti\n";
				newTexts.put(ctx, value);
			}
			else {
				if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
					ident = lookupTable(s1, 1);
					if(ident != null) {
						value = printTab() + "lod " + ident.base + " " + ident.offset + "\n";
						value += v1;
						value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
						value += printTab() + "add\n";
						value += v2; 
						value += printTab() + "sti\n";
						newTexts.put(ctx, value);
					}
					else {
						System.out.println("[exitExpr7] : 변수 " + s1 + " 없어요~");
					}
				}
				System.out.println("[exitExpr8] : 변수 " + s1 + " 없어요~");
			}	
	}
   }
   
   @Override
	public void enterFor_stmt(MiniGoParser.For_stmtContext ctx) {
	   forCount++;
	}
   
   @Override
   public void exitFor_stmt(MiniGoParser.For_stmtContext ctx) {
	   String stmt = "";
	
		stmt += printTab("$$"+jmp_label) + "nop\n";
		stmt += newTexts.get(ctx.expr());	// expr
		stmt += printTab() + "fjp $$" + (jmp_label+1) + "\n";
		stmt += newTexts.get(ctx.compound_stmt());
		stmt += printTab() + "ujp $$" + (jmp_label) + "\n";
		stmt += printTab("$$"+(++jmp_label)) + "nop\n";
		newTexts.put(ctx, stmt);
		forCount--;
   }


   @Override
	public void enterIf_stmt(MiniGoParser.If_stmtContext ctx) {
	   ifCount++;
	}
   @Override
   public void exitIf_stmt(MiniGoParser.If_stmtContext ctx) {
	   String stmt = "";
		if(ctx.getChildCount() == 3)
		{
			stmt += newTexts.get(ctx.expr());	// expr
			stmt += printTab() + "fjp $$" + jmp_label + "\n";
			stmt += newTexts.get(ctx.compound_stmt(0));	// stmt
			stmt += printTab("$$" + (jmp_label++)) + "nop\n";
			newTexts.put(ctx, stmt);
		}
		else if(ctx.getChildCount() == 5){
			stmt += newTexts.get(ctx.expr());	// expr
			stmt += printTab() + "fjp $$" + jmp_label + "\n";
			stmt += newTexts.get(ctx.compound_stmt(0));
			stmt += printTab("$$" + (jmp_label++)) + "nop\n";
			stmt += newTexts.get(ctx.compound_stmt(1));	// stmt
			newTexts.put(ctx, stmt);
		}
	
		ifCount--;
   }

   @Override
   public void exitReturn_stmt(MiniGoParser.Return_stmtContext ctx) {
	   String stmt = "";
		if(ctx.getChildCount() >= 2)
		{
			if(ctx.getChildCount() == 2) {	//RETURN expr
				stmt += newTexts.get(ctx.expr(0));
				stmt += printTab() + "retv\n";
				newTexts.put(ctx, stmt);
			}
			else {	//RETURN expr ,expr
				stmt += newTexts.get(ctx.expr(0));
				stmt += newTexts.get(ctx.expr(1));
				stmt += printTab() + "retv\n";
				newTexts.put(ctx, stmt);
			}
		}
		else {
			stmt += printTab() + "retv\n";
			newTexts.put(ctx, stmt);
		}
	    
	   
   }

   @Override
   public void exitExpr(MiniGoParser.ExprContext ctx) {
	   String s1 = "", s2 = "", s3 = "", op = "", value = ""; 
		
		if(ctx.getChildCount() > 0)
		{
			// IDENT | LITERAL일 경우
			if(ctx.getChildCount() == 1) {
				s1 = ctx.getChild(0).getText();
				char s1_first = s1.charAt(0);
				if(isNumber(s1_first)){	//LITERAL - 첫 글자가 숫자
					newTexts.put(ctx, printTab() + "ldc " + s1 + "\n");
				}
				
				else {	//IDENT
					Variable ident = lookupTable(s1, blockNo);	//base랑 offset 가져오기
					if(ident != null) {
						newTexts.put(ctx, printTab() + "lod " + ident.base + " " + ident.offset + "\n");
					}
					else {
						if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
							ident = lookupTable(s1, 1);
							if(ident != null) {
								newTexts.put(ctx, printTab() + "lod " + ident.base + " " + ident.offset + "\n");
							}
							else {
								System.out.println("[exitExpr1] : 변수 " + s1 + " 없어요~");
							}
						}
						System.out.println("[exitExpr2] : 변수 " + s1 + " 없어요~");
					}
				}
			}
			// pre-operation
			else if(ctx.getChildCount() == 2)
			{
				op = ctx.getChild(0).getText();
				s1 = newTexts.get(ctx.expr(0));
				switch(op) {
				case "-":
					op = "neg\n";
					break;
				case "+":
					op = "pos\n";
					break;
				case "--":
					op = "decop\n";
					break;
				case "++":
					op = "incop\n";
					break;
				case "!":
					op = "notop\n";
					break;
				}
				newTexts.put(ctx, s1 + printTab() + op);
			}
			else if(ctx.getChildCount() == 3)
			{
				// '(' expr ')'
				if(ctx.getChild(0).getText().equals("("))
				{
					s1 += newTexts.get(ctx.expr(0));
					newTexts.put(ctx, s1);
				}
				// IDENT '=' expr
				else if(ctx.getChild(1).getText().equals("="))
				{
					s1 = ctx.getChild(0).getText();
					s2 = newTexts.get(ctx.expr(0));
					Variable ident = lookupTable(s1, blockNo);
					if(ident != null) {
						value = s2;
						value += printTab() + "str " + ident.base + " " + ident.offset + "\n";
						newTexts.put(ctx, value);
					}
					else {
						if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
							ident = lookupTable(s1, 1);
							if(ident != null) {
								value = printTab() + "lod " + ident.base + " " + ident.offset + "\n";
								value += s2;
								value += printTab() + "str " + ident.base + " " + ident.offset + "\n";
								newTexts.put(ctx, value);
							}
							else {
								System.out.println("[exitExpr3] : 변수 " + s1 + " 없어요~");
							}
						}
						System.out.println("[exitExpr4] : 변수 " + s1 + " 없어요~");
					}
				}
				//LITERAL , LITERAL
				else if(ctx.getChild(1).getText().equals(",") ) {
					String s ="";
					s1 = ctx.getChild(0).getText();
					char s1_first = s1.charAt(0);
					s2 = ctx.getChild(2).getText();
					char s2_first = s2.charAt(0);
					if(isNumber(s1_first)){	//LITERAL - 첫 글자가 숫자
						s += printTab() + "ldc " + s1 + "\n";
					}
					if(isNumber(s2_first)) {
						s += printTab() + "ldc " + s2 + "\n";
					}
					newTexts.put(ctx, s);
				}
		
				// binary operation
				else
				{
					s1 = newTexts.get(ctx.expr(0));
					s2 = newTexts.get(ctx.expr(1));
					op = ctx.getChild(1).getText();
					switch(op) {
					case "*":
						op = "mult\n";
						break;
					case "/":
						op = "div\n";
						break;
					case "%":
						op = "modop\n";
						break;
					case "+":
						op = "add\n";
						break;
					case "-":
						op = "sub\n";
						break;
					case "==":
						op = "eq\n";
						break;
					case "!=":
						op = "ne\n";
						break;
					case "<=":
						op = "le\n";
						break;
					case "<":
						op = "lt\n";
						break;
					case ">=":
						op = "ge\n";
						break;
					case ">":
						op = "gt\n";
						break;
					case "and":
						op = "andop\n";
						break;
					case "or":
						op = "orop\n";
						break;
					}
					newTexts.put(ctx, s1 + s2 + "           " + op);
				}
			}
			// IDENT '(' args ')' |  IDENT '[' expr ']'일 경우
			else if(ctx.getChildCount() == 4)
			{
				s1 = ctx.getChild(0).getText();	// IDENT
				if(ctx.args() != null)				// args
				{
					value = printTab() + "ldp\n"; 
					value += newTexts.get(ctx.args());
					value += printTab() + "call " + s1 + "\n";
					newTexts.put(ctx, value);
				}
				else								// expr
				{
					Variable ident = lookupTable(s1, blockNo);
					if(ident != null) {
						value = newTexts.get(ctx.expr(0));
						value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
						value += printTab() + "add\n";
						newTexts.put(ctx, value);
					}
					else {
						if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
							ident = lookupTable(s1, 1);
							if(ident != null) {
								value = printTab() + "lod " + ident.base + " " + ident.offset + "\n";
								value += newTexts.get(ctx.expr(0));
								value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
								value += printTab() + "add\n";
								newTexts.put(ctx, value);
							}
							else {
								System.out.println("[exitExpr5] : 변수 " + s1 + " 없어요~");
							}
						}
						System.out.println("[exitExpr6] : 변수 " + s1 + " 없어요~");
					}
				}
			}
			// IDENT '[' expr ']' '=' expr
			else
			{
				s1 = ctx.getChild(0).getText();	// IDENT
				s2 = newTexts.get(ctx.expr(0));	// expr
				s3 = newTexts.get(ctx.expr(1));	// expr
				Variable ident = lookupTable(s1, blockNo);
				if(ident != null) {
					value = s2;
					value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
					value += printTab() + "add\n";
					value += s3; 
					value += printTab() + "sti\n";
					newTexts.put(ctx, value);
				}
				else {
					if(blockNo == 2) {	//로컬에서 변수 찾고 있는 경우 해당하는 로컬 변수가 없으면 전역에서 찾기
						ident = lookupTable(s1, 1);
						if(ident != null) {
							value = printTab() + "lod " + ident.base + " " + ident.offset + "\n";
							value += s2;
							value += printTab() + "lda " + ident.base + " " + ident.offset + "\n";
							value += printTab() + "add\n";
							value += s3; 
							value += printTab() + "sti\n";
							newTexts.put(ctx, value);
						}
						else {
							System.out.println("[exitExpr7] : 변수 " + s1 + " 없어요~");
						}
					}
					System.out.println("[exitExpr8] : 변수 " + s1 + " 없어요~");
				}
			}
		}
   }

   @Override
   public void exitArgs(MiniGoParser.ArgsContext ctx) {
	   String args = "";
		if(ctx.getChildCount() > 0)
		{
			for(int i=0; i<ctx.getChildCount(); i++)
			{
				if(i % 2 == 0)
					args += newTexts.get(ctx.expr(i/2));	// expr
			}
		}
		newTexts.put(ctx, args);
   }
}