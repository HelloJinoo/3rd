import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class merge {

	static int[] toint;
	static int mergecount;
	public static void main(String[] args) throws IOException {

		
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("[알고리즘]02_주차_201402415_merge.txt"));

		String line[] = reader.readLine().split(",");
		toint = new int[line.length];
		mergecount=0;
		reader.close();
		
		for(int i=0; i<line.length; i++) {
			toint[i] = Integer.parseInt(line[i]);
		}
		
		long start = System.nanoTime();
		
		mergesort(0, toint.length-1);
		
		long end = System.nanoTime();
		
		for(int i=0; i<toint.length; i++) {
			writer.write(Integer.toString(toint[i]));
			if( i == toint.length-1) {
				writer.write(" / mergemethod:"+mergecount);
			}
			else {
				writer.write(",");
			}
		}

		writer.close();
		System.out.println("time(ms): " +  Float.toString( (float) ((end-start)/1000000.0)));
	}
	
	/*나누기*/
	public static void mergesort(int p , int r) {	
		if(p != r) {
			int q = (p+r)/2; //중간
			mergesort(p,q);  //왼쪽 나누기
			mergesort(q+1,r); //오른쪽 나누기
			merge(p , q ,q+1 , r);   //합하기
		}	
	}
	
	/*합하기*/
	public static void merge(int start,int end ,int start2 , int end2) {
		int index=0;
		mergecount++;
		int box = start;
		int[] buffer = new int[end2-start+1];
		while(start <= end && start2 <= end2) {
			if(toint[start] > toint[start2] ) {
				buffer[index] = toint[start2];
				index++;
				start2++;
			}
			else {
				buffer[index] = toint[start];
				index++;
				start++;
			}
		}
		
		if( start <= end) {
			for(int i=start; i<=end; i++) {
				buffer[index] = toint[i];
				index++;
			}
		}
		if( start2 <= end2) {
			for(int i=start2; i<=end2; i++) {
				buffer[index] = toint[i];
				index++;
			}
		}
		
		for(int i=0; i<buffer.length; i++, box++) {
			toint[box] = buffer[i];
		}
		
	}
}
