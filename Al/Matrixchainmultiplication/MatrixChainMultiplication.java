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
		int m[][] = new int[a.size()+1][a.size()+1];
		int s[][] = new int[a.size()+1][a.size()+1];
		
	int n = m.length-1;
		for(int l=2; l<=n; l++) {
			for(int i=1; i<=n-l+1; i++) {
				int j = i+l-1;
				m[i][j]= Integer.MAX_VALUE;
				for(int k=i; k<j; k++) {
					int q = m[i][k] + m[k+1][j] + a.get(i-1).p*a.get(k).p*a.get(j-1).q;
					if( q < m[i][j]) {
						m[i][j] = q;
						s[i][j]=k;
					}
				}
			}
		}
		
		for(int i=1; i<m.length;i++) {
			for(int j=1; j<m[i].length; j++) {
				System.out.printf("%8d",m[i][j] );
			}
			System.out.println();
		}
		System.out.println("-----------------------");

		for(int i=1; i<m.length;i++) {
			for(int j=1; j<m[i].length; j++) {
				System.out.printf("%4d",s[i][j] );
			}
			System.out.println();
		}
		
		print(s, 1,6);
	}
	public static void print(int s[][],int i, int j) {
		if(i == j ) {
			System.out.print(a.get(i-1).a);
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
