import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Grammar {
	private HashMap<String, ArrayList<String>> table = new HashMap<String,ArrayList<String>>();
	private HashMap<String, ArrayList<String>> first = new HashMap<>();
	private HashMap<String, ArrayList<String>> new_first = new HashMap<>();
	private HashMap<String, ArrayList<String>> follow = new HashMap<>();
	private HashMap<String, ArrayList<String>> new_follow = new HashMap<>();
	private ArrayList<String> nonTerminal = new ArrayList<>();
	
	int start = 0;
	String startSimbol="";
	public HashMap<String, ArrayList<String>> getTable(){ return table; }

	public void AddRule(String nonterminal, String rule){
		/*���۽ɺ� Ȯ��*/
		if( start ==0) {
			startSimbol = nonterminal;
			ArrayList<String> list = new ArrayList<String>();
			list.add("$");
			follow.put(nonterminal, list);
			start++;
		}
		
		if(table.get(nonterminal) == null){
			ArrayList<String> list = new ArrayList<String>();
			list.add(rule);
			table.put(nonterminal, list);
		} else {
			table.get(nonterminal).add(rule);
		}
	}
	public String getRule(String nonTerminal, int index) {
		return table.get(nonTerminal).get(index);
	}

	public ArrayList<String> getRules(String nonTerminal) {
		return table.get(nonTerminal);
	}

	String add_terminal = "";
	public void findFirst() {
		/*��ȭ�� �ִ��� ��*/
		HashMap onestep = search_first(first);
		HashMap twostep = search_first(new_first);
		if(!onestep.equals(twostep)) {
			findFirst();
		}
		else {
			printfirst();
		}
	}
	public HashMap search_first(HashMap a) {
		for(String lhs : table.keySet()){
	         ArrayList<String> terminal = new ArrayList<>();
	         for(String rhs : table.get(lhs)){
	            if( (rhs.charAt(0) > 90 || rhs.charAt(0) < 65) ) { //terminal�� ���
	               if( rhs.equals("id")) {
	                  add_terminal = "id"; 
	               }
	               else {
	                  add_terminal = ""+rhs.charAt(0);
	               }
	               terminal.add(add_terminal);
	             }
	         else if( rhs.charAt(0) >= 65 && rhs.charAt(0) <= 90  ){   //NonTerminal�� ���
	        	 ArrayList<String> ringsum_result = ringsum(rhs);
	        	 for(int e =0; e< ringsum_result.size(); e++) {
	        		 terminal.add(ringsum_result.get(e));
	        	 }
	           }   
	       }
           a.put(lhs, terminal);
	         }	
		return a;
	}
		
	private ArrayList ringsum(String rhs) {
		 ArrayList<String> terminal = new ArrayList<>();
		 for(int i=0; i<rhs.length(); i++) {
			 String word="";
			 if(i+1<rhs.length() && rhs.charAt(i+1) == '\'') {
					  word = rhs.charAt(i)+"'";
					  i++;
			 }
			 else {
				 word = ""+rhs.charAt(i);
			 }
				if( first.get(word) != null) {
				 for(int k=0; k<first.get(word).size(); k++) {
					 if(!terminal.contains(first.get(word).get(k))) {
						 terminal.add(first.get(word).get(k));
					 }
				 }
				 if(terminal.contains("e")) {
					if(i != rhs.length()-1) {
					 terminal.remove("e");
					 continue;
					 }
				 }
				 else {
					 break;
				 }
				}
				else {
					break;
				}
			 }
		 return terminal;
	}

		/*first ���*/
	public void printfirst() {
		System.out.println("--------------------------------");
		for(String lhs : first.keySet()){
			int count=0;
			System.out.print("first("+lhs+") : { ");
			for(String rhs : first.get(lhs)){
				if( first.get(lhs).size()-1 != count ) {
					System.out.print(rhs+" , ");
					count++;
				}
				else {
					System.out.print(rhs);
				}
			}
			System.out.println(" }");
		}
	}

	public void findFollow() {
		HashMap onestep = search_follow(follow);
		HashMap twostep = search_follow(new_follow);
		if(!onestep.equals(twostep)) {
			findFollow();
		}
		else {
			  printfollow();
		}		
    }
	
	public HashMap search_follow(HashMap a) {
		for(String total : table.keySet()){
				ArrayList plus = new ArrayList<>();
				for(String lhs : table.keySet()){
					for(String rhs : table.get(lhs)){
						if( rhs.contains(total) && !rhs.contains(total+"'")) { //follow�� ���� ���
							String sp[] = rhs.split(total);
							if(sp.length>1) {  //�ڿ� ���� ����.
								if(sp[1].charAt(0) > 45 && sp[1].charAt(0) < 90  ){ //nonterminal
									if(sp[1].length()>1 ) {
										String word = "";
										if( sp[1].charAt(1) =='\'') {
											word = sp[1].charAt(0)+"'";
										}
										else {
											word = ""+sp[1].charAt(0);
										}
											if( first.get(word).contains("e") ) {  //first�� null�� ���� ���
												for(int k=0; k<first.get(word).size(); k++ ) {
													if( !first.get(word).get(k).equals("e") 
															&& !plus.contains(first.get(word).get(k))) { 
														plus.add(first.get(word).get(k));
													}
												}
												if( follow.get(lhs) != null) {
												for(int k=0; k< follow.get(lhs).size(); k++) {
													if(!plus.contains(follow.get(lhs).get(k))) {
														plus.add(follow.get(lhs).get(k));
													}
													}
												}
											}
									else { //null�� ���� ���
										for( int k=0; k<first.get(word).size(); k++) {
											if( !plus.contains(first.get(word).get(k))) {
												plus.add(follow.get(word).get(k));
											}
										}
					
									}
								 }
								}
							
								else { //Terminal�� ���   2����Ģ
									if(!plus.contains(sp[1].charAt(0)) ) {
										plus.add(""+sp[1].charAt(0));
										}
									}
								}
							else { //�ڿ� ���¾ְ� ���� , total�� ���� �� ���    3-1����Ģ
								if(follow.get(lhs) != null && first.get(total).contains("e")) {
									for( int k=0; k<follow.get(lhs).size(); k++) {
										if(  !plus.contains(follow.get(lhs).get(k))) {
											plus.add(follow.get(lhs).get(k));
										}
									}
								}
							}
							}
					if( total ==startSimbol) {
						if( !plus.contains("$")) {
							plus.add("$");
						}
					}
					a.put(total, plus);
					}
				}
		}
		return a;
	}
	/*follow ���*/
	public void printfollow() {
		System.out.println("--------------------------------");
		for(String lhs : follow.keySet()){
			int count=0;
			System.out.print("follow("+lhs+") : { ");
			for(int i=0; i< follow.get(lhs).size(); i++){
				if( follow.get(lhs).size()-1 != count ) {
					String b =follow.get(lhs).get(i);
					System.out.print(follow.get(lhs).get(i)+" , ");
					count++;
				}
				else {
					
					System.out.print(follow.get(lhs).get(i));
				}
			}
			System.out.println(" }");
		}
	}


	public void viewGrammar(){
		int index = 1;
		for(String lhs : table.keySet()){
			for(String rhs : table.get(lhs)){
				System.out.println((index++) + "." + lhs + " ->  "+ rhs);
			}
		}
	}
}
