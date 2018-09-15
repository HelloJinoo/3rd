import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class insertion {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("[알고리즘]02_주차_201402415_insertion.txt"));
		
		String line[] = reader.readLine().split(",");
		int toint[] = new int[line.length];
	
		reader.close();
		for(int i=0; i<line.length; i++) {
			toint[i] = Integer.parseInt(line[i]);
		}
		
		long start = System.nanoTime();
		for(int comp=1; comp<toint.length; comp++) {
			int key = toint[comp];
			for(int comp_target=comp-1; comp_target>=0; comp_target--) {
				if( key <= toint[comp_target] ) {
					toint[comp_target+1] = toint[comp_target];
				}
				else if( key > toint[comp_target]) {
					toint[comp_target+1] = key;
					break;
				}
			}
		}
		
		long end = System.nanoTime();
		
		for(int i=0; i<toint.length; i++) {
			writer.write(Integer.toString(toint[i]));
			if(i != toint.length-1) {
				writer.write(",");
			}
		}
		writer.close();
		
		System.out.println("time(ms): " +  Float.toString( (float) ((end-start)/1000000.0)));
		

	}

}
