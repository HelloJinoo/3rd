import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RecursiveDescentParser {
	private HashMap<String, ArrayList<String>> first = new HashMap<>();
	private HashMap<String, ArrayList<String>> follow = new HashMap<>();
	
	static String[] inputArray;
	static int arrayIndex = 0;
	static HashMap<Integer, ArrayList<String>> parsingTree = new HashMap<>();
			
	public RecursiveDescentParser(Grammar g, String input) {
		inputArray = input.split(" ");
		ArrayList<String> levelList = new ArrayList<>();
		levelList.add("E");
		parsingTree.put(1, levelList);
		pE(1);
		
		for(int i=1; i<=parsingTree.size(); i++) {
			for(int j=0; j<parsingTree.get(i).size(); j++) {
				System.out.print(parsingTree.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	private static boolean tPlus(int level) {	// +
		if(arrayIndex<inputArray.length) {	
			if(inputArray[arrayIndex].equals("+")) {
				arrayIndex++;
				return true;
			}
		}
		return false;
	}
	
	private static boolean tMul(int level) {	// *
		if(arrayIndex<inputArray.length) {	
			if(inputArray[arrayIndex].equals("*")) {
				arrayIndex++;
				return true;
			}
		}
		return false;
	}
	
	private static boolean tId(int level) {	// id
		if(arrayIndex<inputArray.length) {	
			if(inputArray[arrayIndex].equals("id")) {
				arrayIndex++;
				return true;
			}
		}
		return false;
	}
	
	private static boolean tOpen(int level) {	// (
		if(arrayIndex<inputArray.length) {	
			if(inputArray[arrayIndex].equals("(")) {
				arrayIndex++;
				return true;
			}
		}
		return false;
	}
	
	private static boolean tClose(int level) {	// )
		if(arrayIndex<inputArray.length) {	
			if(inputArray[arrayIndex].equals(")")) {
				arrayIndex++;
				return true;
			}
		}
		return false;
	}
	
	private static boolean pE(int level) {	// TE'
		ArrayList<String> levelList;
		if(parsingTree.containsKey(level+1)) {
			levelList = parsingTree.get(level+1);
		} else {
			levelList = new ArrayList<>();
		}
		
		if(pT(level+1)) {
			if(pE_(level+1)) {
				levelList.add("T");
				levelList.add("E'");
				parsingTree.put(level+1, levelList);
				return true;
			}
		}
		return false;
	}
	
	private static boolean pE_(int level) {	// +TE' | e
		ArrayList<String> levelList;
		if(parsingTree.containsKey(level+1)) {
			levelList = parsingTree.get(level+1);
		} else {
			levelList = new ArrayList<>();
		}
		if(tPlus(level+1)) {
			if(pT(level+1)) {
				if(pE_(level+1)) {
					levelList.add("+");
					levelList.add("T");
					levelList.add("E'");
					parsingTree.put(level+1, levelList);
					return true;
				}
			}
		}
			levelList.add("e");
			parsingTree.put(level, levelList);
			return true;
	}
	
	private static boolean pT(int level) {	// FT'
		ArrayList<String> levelList;
		if(parsingTree.containsKey(level+1)) {
			levelList = parsingTree.get(level+1);
		} else {
			levelList = new ArrayList<>();
		}
		
		if(pF(level+1)) {
			if(pT_(level+1)) {
				levelList.add("F");
				levelList.add("T'");
				parsingTree.put(level+1, levelList);
				return true;
			}
		}
		return false;
	}
	
	private static boolean pT_(int level) {	// *FT' | e
		ArrayList<String> levelList;
		if(parsingTree.containsKey(level+1)) {
			levelList = parsingTree.get(level+1);
		} else {
			levelList = new ArrayList<>();
		}
		
		if(tMul(level+1)) {
			if(pF(level+1)) {
				if(pT_(level+1)) {
					levelList.add("*");
					levelList.add("F");
					levelList.add("T'");
					parsingTree.put(level+1, levelList);
					return true;
				}
			}
		}
			levelList.add("e");
			parsingTree.put(level+1, levelList);
			return true;
		
	}
	
	private static boolean pF(int level) {	// (E) | id
		ArrayList<String> levelList;
		if(parsingTree.containsKey(level+1)) {
			levelList = parsingTree.get(level+1);
		} else {
			levelList = new ArrayList<>();
		}
		if(tOpen(level+1)) {
			if(pE(level+1)) {
				if(tClose(level+1)) {
					levelList.add("(");
					levelList.add("E");
					levelList.add(")");
					parsingTree.put(level+1, levelList);
					return true;
					
				}
			}
		}
		if(tId(level+1)) {
			levelList.add("id");
			parsingTree.put(level+1, levelList);
			return true;
		}
		return false;
	}
}
