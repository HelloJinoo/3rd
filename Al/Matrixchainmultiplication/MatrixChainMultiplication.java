import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MatrixChainMultiplication {

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		ArrayList<value> a = new ArrayList();
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
					int d= a.get(0).p;
					int b = a.get(k).p;
					int c= a.get(j-2).q;
					int q = m[i][k] + m[k+1][j] + a.get(0).p*a.get(k).p*a.get(j-1).q;
					if( q < m[i][j]) {
						m[i][j] = q;
						s[i][j]=k;
					}
				}
			}
		}
		
		for(int i=1; i<m.length;i++) {
			for(int j=1; j<m[i].length; j++) {
				System.out.printf("%4d",m[i][j] );
			}
			System.out.println();
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
