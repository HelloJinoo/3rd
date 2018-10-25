import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InversionCount {

	static int[] toint;
	static int mergecount;
	static int inversionCount=0;
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("data07.txt"));

		String line[] = reader.readLine().split(",");
		toint = new int[line.length];
		reader.close();
		
		for(int i=0; i<line.length; i++) {
			toint[i] = Integer.parseInt(line[i]);
		}
		
		long start = System.nanoTime();
		
		mergesort(0, toint.length-1);
		
		long end = System.nanoTime();
		
	
		System.out.println("InversionConut : " + inversionCount);
		
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
	public static void merge(int start, int end ,int start2 , int end2) {
		int index=0;
		int box = start;
		int[] buffer = new int[end2-start+1];
		
		/*나눈 부분의 원소를 하나씩 비교하면서 작은것부터 하나씩 새로운배열에 저장 */
		while(start <= end && start2 <= end2) {
			if(toint[start] > toint[start2] ) {
				buffer[index] = toint[start2];
				index++;
				start2++;
				for(int i=start; i<=end; i++) {
					inversionCount++;
					System.out.print("(" + toint[i] +"," + toint[start2] +")" + " " );
				}
				System.out.println();
			}
			else {
				buffer[index] = toint[start];
				index++;
				start++;
			}
		}
		
		/*나눈 배열에서 오른쪽만 비교가 끝난 경우 */
		if( start <= end ) {
			for(int i=start; i<=end; i++) {
				buffer[index] = toint[i];
				index++;
				
			}
		}
		/*나눈 배열에서 왼쪽만 끝난 경우 */
		if( start2 <= end2 ) {
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
