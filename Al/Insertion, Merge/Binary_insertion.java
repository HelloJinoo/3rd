import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Binary_insertion {

	static int toint[];
	static int index;
	static ArrayList al; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("[알고리즘]02_주차_201402415_binary_insertion.txt"));
		
		int key;
		 al = new ArrayList<Integer>();
		String line[] = reader.readLine().split(",");
		 toint = new int[line.length];
		
		reader.close();
		for(int i=0; i<line.length; i++) {
			toint[i] = Integer.parseInt(line[i]);
		}
		al.add(toint[0]);
		
		long start = System.nanoTime();
		
		for(int i=1; i<toint.length; i++) {
			key = toint[i];
			int selectposition = searchposition(0 , i-1 , key); //넣을 위치찾기
			al.add(selectposition, key);  // 넣을 위체에 삽입
		}
		long end = System.nanoTime();
		
		for(int i=0; i<al.size(); i++) {
			writer.write(Integer.toString((int)al.get(i)));
			if( i != al.size()-1) {
				writer.write(",");
			}
		}
		writer.close();
		System.out.println("time(ms): " +  Float.toString( (float) ((end-start)/1000000.0)));
	}

	private static int searchposition(int minposition, int maxposition , int value) {
			int middle = (minposition+maxposition)/2; //중간
			if( minposition == maxposition ) {
				if((int)al.get(minposition) <= value ) {
					index = minposition+1;
				}
				else if((int)al.get(minposition) > value ) {
					if(middle == 0) {
						index = 0;
					}
					else {
						index = minposition;
						}
					}	
				}
			else if( minposition == maxposition-1) {
				if((int)al.get(minposition) < value && (int)al.get(maxposition) > value ) {
					index = maxposition;
				}
				else if((int)al.get(minposition) >= value) {
					index = minposition;
				}
				else if( (int)al.get(maxposition) <= value) {
					index = maxposition+1;
				}
				
			}
			else if( value > (int)al.get(middle) ) {  //오른쪽에서찾기
				return searchposition( middle+1,maxposition ,value);
			}
			else if( value < (int)al.get(middle)) { //왼쪽에서 찾기
				return searchposition(0, middle-1, value);
			}
			return index;	

		
	}

}
