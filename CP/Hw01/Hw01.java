
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Hw01 {

	public static void main(String[] args) throws IOException {
		/*test.hoo�뙆�씪*/
		BufferedReader reader = new BufferedReader(new FileReader("test.hoo")); //C:\Users\s\eclipse-workspace\CP
		
		/*test.c파일 만들기*/
		BufferedWriter cwriter = new BufferedWriter(new FileWriter("test.c")); //C:\Users\s\eclipse-workspace\CP
		
		String line;
		cwriter.write("#include <stdio.h> \n\n int main(){ \n"); //모든 c파일에 들어가는 부분
		String temp = ""; //臾몄옄 ���옣�쓣 �쐞�븳 蹂��닔
		String Extractcharacter=""; //�엯�젰�븳 臾몄옄 異붿텧
		
		while ((line = reader.readLine()) != null) {
			String inputst[] = line.split(":"); // :瑜� 湲곗��쑝濡� �굹�늻�뼱 �궎�썙�뱶 �솗�씤�쓣 �쐞�븿
			String confirm_ud[] = inputst[0].split("]"); // ]瑜� 湲곗��쑝濡� �굹�늻�뼱 �샃�뀡�씠 遺숈뿀�뒗吏� �솗�씤�쓣 �쐞�븿 
			Extractcharacter = confirm_ud[0].substring(1, confirm_ud[0].length()); //[]�븞�뿉 �엳�뒗 臾몄옄�뿴 異붿텧
	
			if(inputst[inputst.length - 1].contains("ignore") ){
				temp = "";
				continue;
			}
			else {	
			/*---------------------諛섎났�씠 �엳�뒗 寃쎌슦--------------------------*/
				if(line.contains("(")) {  
					String number= "";
	                String count[] = inputst[1].split("\\)");
	                number += count[0].charAt(count[0].length() - 1); //諛섎났 �슏�닔 
					if( temp.equals("")) { //temp�뿉 ���옣�릺�뼱�엳�뒗 媛믪씠 �뾾�뒗 寃쎌슦
						if(Extractcharacter.equals("")) {
							for(int i=0; i<Integer.parseInt(number); i++) {
								temp += "\\n";
								}
							}
						else {
							for(int i=0; i<Integer.parseInt(number); i++) {
								temp += Extractcharacter;
							}
						/*�샃�뀡*/
						 if (line.contains("/")) {  //移섑솚�씠 �엳�뒗 寃쎌슦
				               String subti[] = inputst[0].split("/");
				               String last = subti[0].substring(subti[0].length() - 1, subti[0].length());
				               char old = subti[1].charAt(0);
				               String toStringold =""+old;
				               temp = temp.replace(toStringold, last);
						 }
						 else if (confirm_ud.length == 2 && ( confirm_ud[1].charAt(1) == 'U' || confirm_ud[1].charAt(1) == 'L')) { 	//���냼臾몄옄 蹂��솚�씠 �엳�뒗 寃쎌슦
							 if(confirm_ud[1].charAt(1) == 'U' ) {
							 		temp = temp.toUpperCase();
							 	}
							 	else if(confirm_ud[1].charAt(1) == 'L') {
							 		temp = temp.toLowerCase();
							 	}  
					 	}
						}
					}
					else {  //temp�뿉 ���옣�릺�뼱 �엳�뒗 媛믪씠 �엳�뒗 寃쎌슦
						if(Extractcharacter.equals("")) {
							temp += "\\n";
						}
						else {
							if (line.contains("/")) {  //移섑솚�씠 �엳�뒗 寃쎌슦
				               String subti[] = inputst[0].split("/");
				               String last = subti[0].substring(subti[0].length() - 1, subti[0].length());
				               char old = subti[1].charAt(0);
				               String toStringold =""+old;
				               temp += Extractcharacter.replace(toStringold, last);
							}
							else if (confirm_ud.length == 2 && ( confirm_ud[1].charAt(1) == 'U' || confirm_ud[1].charAt(1) == 'L')) { 	//���냼臾몄옄蹂��솚�씠 �엳�뒗 寃쎌슦	
							 	if(confirm_ud[1].charAt(1) == 'U' ) {
							 		temp += Extractcharacter.toUpperCase();
							 	}
							 	else if(confirm_ud[1].charAt(1) == 'L') {
							 		temp += Extractcharacter.toLowerCase();
							 	} 
							  }
							else {
								temp += Extractcharacter; //�샃�뀡�씠 �뾾�뒗 寃쎌슦
							}
							String temp_result = temp;
							for( int i=0; i<Integer.parseInt(number)-1; i++ ) {
								temp += temp_result;
							}
						}	
					}
			}		
				/*---------------------諛섎났�씠 �뾾�뒗 寃쎌슦--------------------------*/
			else { 
				if(Extractcharacter.equals("")) {
					temp += "\\n";
				}
				else {
					if(line.contains("/")) { //移섑솚�씠 �엳�뒗 寃쎌슦
							   String subti[] = inputst[0].split("/");
				               String last = subti[0].substring(subti[0].length() - 1, subti[0].length());
				               char old = subti[1].charAt(0);
				               String toStringold =""+old;
				               temp += Extractcharacter.replace(toStringold, last);
						}
					else if (confirm_ud.length == 2 && ( confirm_ud[1].charAt(1) == 'U' || confirm_ud[1].charAt(1) == 'L')) { 	//���냼臾몄옄 蹂��솚�씠 �엳�뒗 寃쎌슦	
					 	if(confirm_ud[1].charAt(1) == 'U' ) {
					 		temp += Extractcharacter.toUpperCase();
					 	}
					 	else if(confirm_ud[1].charAt(1) == 'L') {
					 		temp += Extractcharacter.toLowerCase();
					 	} 
					  }
					else {
						temp +=Extractcharacter;
					}
					}
				}
				if( line.contains("print")) {
					 cwriter.write("   printf(\"%s\", \"" + temp + "\");\n");
		             temp="";
				}
			}
		}
		cwriter.write(" return 0; \n }");
		reader.close();
		cwriter.close();
	}
}
