import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.xml.transform.Result;

public class Quicksort {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data04.txt"));
		String input[] = reader.readLine().split(",");
		Quicksort qs = new Quicksort();
		
		int result[] = new int[1000000];
		for(int i=0; i<input.length; i++) {
			result[i] = Integer.parseInt(input[i]);
		}
		
		qs.quickSort_withRandom(result , 0 , input.length-1);
		
		for(int i=0; i<result.length; i++) {
			if(input.length == i) {
				break;
			}
			else {
				System.out.print(result[i]+" ");
			}
		}
		
	}
	private void quickSort(int a[] , int start , int end) {
		if(start < end) {
			int standardnum = Partition(a, start , end);
			quickSort(a, start , standardnum-1);
			quickSort(a , standardnum+1 , end);
		}
	}
	
	private int Partition(int a[], int start , int end) {
		int r = end;
		int i = start-1;
		int j;
		int pivot = a[r];
		for(j=start; j<=r-1; j++) {
			if( a[j] <= pivot ) {
				i++;
				int temp = a[j];
				a[j] = a[i];
				a[i] = temp;
			}
		}
		i++;
		a[j] = a[i];
		a[i] = pivot;
		if( end-start  != 0 && i >start && i <end ) {
			Partition( a ,start , i-1); //¿ÞÂÊ
			Partition( a, i+1 , end);  //¿À¸¥ÂÊ
		}
		return i;
	}
	private void quickSort_withRandom(int a[] , int start , int end) {
		if (start < end) {
			int standardnum = randomizedPartition(a, start, end);
			quickSort_withRandom(a, start, standardnum - 1);
			quickSort_withRandom(a, standardnum + 1, end);
		}
	}
	
	private int randomizedPartition(int a[] , int start , int end) {
		Random random = new Random();
		int randomnum[] = new int[3];
		int randomnumindex[] = new int[3];
		int temp = a[end];
		if(end-start > 1) {
			for(int i=0; i<3; i++ ) {
				randomnumindex[i] = random.nextInt(end-start) + start;
				randomnum[i] =valuesearch(a, randomnumindex[i]);
			}
			if( randomnum[0] < randomnum[1]) {
				if(randomnum[1] < randomnum[2]) {
					a[end] = randomnum[1];
					a[randomnumindex[1]] = temp;
				}
				else {
					a[end] = randomnum[2];
					a[randomnumindex[2]] = temp;
				}
			}
			else {
				if(randomnum[0] > randomnum[2]) {
					a[end] = randomnum[2];
					a[randomnumindex[2]] = temp;
				}
				else {
					a[end] = randomnum[0];
					a[randomnumindex[0]] = temp;
				}
			}
		}
		return Partition(a,start , end);
	}
	private int valuesearch(int a[], int value) {
		int valueofindex = 0;
		for(int j = 0 ; j< a.length; j++) {
			if(j == value) {
				valueofindex = a[j];
				break;
			}
		}
		return valueofindex;
	}
	
	

}
