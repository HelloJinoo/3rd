import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MatrixChainMultiplication {

	static ArrayList<value> a = new ArrayList();
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		
		while(	!(input=br.readLine()).equals("0") ) {
			String sp[] = input.split("=");
			String val[] = sp[1].split("x");
			value v = new value(sp[0],Integer.parseInt(val[0]), Integer.parseInt(val[1]));
			a.add(v);
		}
		int m[][] = new int[a.size()][a.size()];
		int s[][] = new int[a.size()][a.size()];
		
	int n = m.length;
		for(int l=1; l<n; l++) {
			for(int i=0; i<n-l; i++) {
				int j = i+l;
				m[i][j]= Integer.MAX_VALUE;
				for(int k=i; k<j; k++) {
					int b = m[i][k];
					int c = m[k+1][j];
					int d = a.get(i).p;
					int f = a.get(k).q;
					int e = a.get(j).q;
					int q = m[i][k] + m[k+1][j] + a.get(i).p * a.get(k).q* a.get(j).q;
					if( q < m[i][j]) {
						m[i][j] = q;
						s[i][j]= k;
					}
				}
			}
		}
		
		for(int i=0; i<m.length;i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.printf("%8d",m[i][j] );
			}
			System.out.println();
		}
		System.out.println("-----------------------");

		for(int i=0; i<m.length;i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.printf("%4d",s[i][j] );
			}
			System.out.println();
		}
		
		print(s, 0,s.length-1);
	}
	public static void print(int s[][],int i, int j) {
		if(i == j ) {
			System.out.print(a.get(i).a);
		}
		else {
			System.out.print("(");
			print(s,i,s[i][j]);
			print(s,s[i][j]+1,j);
			System.out.print(")");
		}
	}
	static public class value{
		private int p;
		private int q;
		private String a;
		
		public value(String a, int p, int q){
			this.a = a;
			this.p = p;
			this.q = q;
		}
	}
}
