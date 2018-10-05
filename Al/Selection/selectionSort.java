import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class selectionSort {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data04.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("data04_Sort.sel.txt"));
		String line = reader.readLine();
		String temp[] = line.split(",");
		int[] number = new int[temp.length];
		
		for(int i=0; i< number.length; i++) {
			number[i] = Integer.parseInt(temp[i]);
		}
		
		reader.close();
		
		for( int currentindex=0; currentindex<number.length-1; currentindex++ ) {
			int min = number[currentindex];
			int minindex=currentindex;
			for(int i=currentindex+1; i<number.length; i++) {
				if( min > number[i]) {
					min = number[i];
					minindex = i;
				}
			}
				int Box = number[currentindex];
				number[currentindex] = min;
				number[minindex] = Box;
		}
	
		
		for(int i=0; i<number.length; i++) {
			writer.write(Integer.toString(number[i]));
			if( i != number.length-1) {
				writer.write(",");
			}
		}
		writer.close();
		
	}
	
}
