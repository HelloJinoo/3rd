import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class bubble_Sort {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data04.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("data04_Sort_bub.txt"));
		String line = reader.readLine();
		String temp[] = line.split(",");
		int[] number = new int[temp.length];
		
		for(int i=0; i< number.length; i++) {
			number[i] = Integer.parseInt(temp[i]);
		}
	
		reader.close();
		int length= number.length;
		for(int i=0; i<number.length-1; i++) {
			for(int j= 0; j < length-1; j++) {
				if( number[j] > number[j+1]) {
					int box = number[j];
					number[j] = number[j+1];
					number[j+1] = box;
				}
			}
			length--;
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
