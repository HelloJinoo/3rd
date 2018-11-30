import java.util.ArrayList;

public class SequenceAlignment {
	
	
	static int len =0;
	public static void main(String[] args) {
		String input ="actgagttaa";
		String input2 ="acagaagta";
		
		ArrayList<String> a = new ArrayList<String>();
		
		if(input.length() < input2.length()) {
			len = input2.length();
		}
		else {
			len = input.length();
		}
		int[][] similarity = new int[input2.length()+1][input.length()+1];
		String[][] source = new String[input2.length()+1][input.length()+1];
		
		for(int i=0; i<source.length; i++) {
			for(int j=0; j<source[i].length; j++) {
				source[i][j] = "";
			}
		}
		
		for(int i=0; i<similarity.length; i++) {
			for(int j=0; j<similarity[i].length; j++) {
				if( j ==0 && i > 0 ) {
					similarity[i][j] = similarity[i-1][j]-2;
				
				}
				else if( i == 0 && j > 0 ) {
					similarity[i][j] = similarity[i][j-1]-2;
					
				}
				else if( i== 0 && j == 0) {
					similarity[i][j] = 0;
					
				}
				else {
					int value =0; 
					if(input.charAt(j-1) == input2.charAt(i-1)) {
						value = similarity[i-1][j-1] + 1;   //문자비교
					}
					else {
						value = similarity[i-1][j-1] - 1;
					}
					int value2 = similarity[i][j-1]-2;     //input과 공백비교
					int value3 = similarity[i-1][j]-2;     //input2와 공백비교
					
					/*값 비교(큰값찾기) , 선택된 배열의 인덱스 저장*/
					if( value > value2) {
						if( value == value3) {
							similarity[i][j] = value3;	
							source[i][j] += "("+(i-1)+","+(j-1)+")/";
							source[i][j] += "("+i+","+j+")";
						}
						else {
							if( value > value3) {
								similarity[i][j] = value;
								source[i][j] += "("+(i-1)+","+(j-1)+")/";
								
							}
							else {
								similarity[i][j] = value3;
								source[i][j] += "("+(i-1)+","+j+")/";
								}
							}
					
					}
					else if(value < value2) {
						if( value2 == value3) {
							similarity[i][j] = value3;	
							source[i][j] += "("+i+","+(j-1)+")/";
							source[i][j] += "("+(i-1)+","+j+")";
							
						}
						else {
							if( value2 > value3 ) {
								similarity[i][j] = value2;
								source[i][j] += "("+i+","+(j-1)+")/";
							}
							else {
								similarity[i][j] = value3;
								source[i][j] += "("+(i-1)+","+(j)+")/";
								
							}
							
						}
					}
					else if( value == value2) {
						if( value == value3) {
							similarity[i][j] = value;
							source[i][j] += "("+(i-1)+","+(j-1)+")/";
							source[i][j] += "("+i+","+(j-1)+")/";
							source[i][j] += "("+(i-1)+","+j+")";
							
						}
						else {
							if( value > value3) {
								similarity[i][j] = value;
								source[i][j] += "("+(i-1)+","+(j-1)+")/";
								source[i][j] += "("+i+","+(j-1)+")";
							}
							else {
								similarity[i][j] = value;
								source[i][j] += "("+(i-1)+","+(j-1)+")/";
								source[i][j] += "("+(i-1)+","+j+")";
							}
							
						}
					}
				
				}
			}
		}
		
		/*얻은 값 출력*/
		for(int i=0; i<similarity.length; i++) {
			for(int j=0; j<similarity[i].length; j++) {
					System.out.print(similarity[i][j]+" ");
				}
				System.out.println();
			}
		System.out.println();
				
		dfs(a,source ,input2.length(), input.length() , 1 ,("("+input2.length()+","+input.length()+")"));
		
		String comp ="";
		String comp2 ="";
		for(int i=0; i<a.size(); i++) {
			int index=0;
			int index2=0;
			String line[] = a.get(i).toString().split(" ");
			for(int j=line.length-1; j>0; j--) {
				String current_sp2[] = line[j].split(",");
			
				int current_numx = Integer.parseInt(current_sp2[0].substring(1, current_sp2[0].length()));
				int current_numy = Integer.parseInt(current_sp2[1].substring(0, current_sp2[1].length()-1));
				
				String next_sp2[] = line[j-1].split(",");
				int next_numx = Integer.parseInt(next_sp2[0].substring(1, next_sp2[0].length()));
				int next_numy =Integer.parseInt(next_sp2[1].substring(0, next_sp2[1].length()-1));
				
				/*대각선*/
				if( next_numx == current_numx+1 && next_numy == current_numy+1) {
					comp += input.charAt(index);
					comp2 += input2.charAt(index2);
					index++;
					index2++;
				}
				/*수직*/
				else if( next_numx == current_numx+1 && next_numy == current_numy ) {
					comp += "_";
					comp2 += input2.charAt(index2);
					index2++;
				}
				/*수평*/
				else if(next_numx == current_numx && next_numy == current_numy+1) {
					comp += input.charAt(index);
					comp2 += "_";
					index++;
				}
			}
			System.out.println(comp);
			System.out.println(comp2);
			comp = "";
			comp2= "";
			System.out.println();
		}
		
		System.out.println("점수 :"+similarity[input2.length()][input.length()]);
	}
	/*역추적*/
	public static void dfs(ArrayList a , String des[][] ,int x , int y ,int depth , String path) {
		int count=0;
		if(depth == len+1) {
			if(x == 0 && y==0) {
				a.add(path);
			}
		}else {
		String sp[] = des[x][y].split("/");
		count = sp.length;
		
		for(int i=0; i<count; i++){
			int e = sp[i].charAt(1)-48;
			int f = sp[i].charAt(sp[i].length()-2)-48;
			 dfs(a,des ,e ,f ,depth+1, path +" ("+e+","+f+")");
		}
	
		
	}
	}

}
