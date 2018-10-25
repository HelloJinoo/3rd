import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class karatsuba_algorithm {

	static int Threshold = 3;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("����1�� �Է����ּ���:");
		String num_x= br.readLine();
	
		System.out.print("����2�� �Է����ּ���:");
		String num_y=br.readLine();
		
		long result=0;
		if( num_x.length() > Threshold) {
			result = Divide( num_x, num_y);  //karasuba_algorithm����
		}
		else {
			 result =Long.parseLong(num_x) * Long.parseLong(num_y); //�ܼ� ������ 
		}
		System.out.println(num_x+" * "+num_y+" = " + result);
	}
 	private static long Combine(long z2, long z1, long z0 , int leng) { 
 		long fristvalue	 =  (long) (z2*Math.pow(10,leng*2));  
 		long secondvalue = (long) (z1*Math.pow(10,leng));
 		long thirdvalue = z0;
 		return fristvalue + secondvalue+ thirdvalue;
		
	}
	private static Long Divide(String num1 , String num2) {
 		long num1_value = Long.parseLong(num1);
 		long num2_value = Long.parseLong(num2);
 		int len = num1.length()/2;
 		long result=0;
 		long x1 = (long) (num1_value / Math.pow(10 , len));  //num1���� ����
 		long x0 = (long) (num1_value % Math.pow(10 , len));  //num1���� ���� 		
 		long y1 = (long) (num2_value / Math.pow(10 , len));  //num2���� ����
 		long y0 = (long) (num2_value % Math.pow(10 , len));  //num2���� ����

 		if(Long.toString(x1).length() > Threshold) {  //���̰� Threshold���� ũ�� 
			long z2 = Divide(Long.toString(x1), Long.toString(y1)); 
			long z0 = 0;
			long z1 = 0;
			if(Long.toString(x0).length() > Threshold ) {
	 			 z0 = Divide(Long.toString(x0), Long.toString(y0));
				 z1 = Divide(Long.toString(x0+x1), Long.toString(y0+y1)) -z2-z0;
				 result= Combine( z2, z1, z0, len);
			 }
			else {   
				result= Combine( z2,(x1+x0)*(y0+y1)-x1*y1-x0*y0, x0*y0, len);  //Threshold���� ������ combine
			}
		}
		else {
			 result= Combine( x1*y1, (x1+x0)*(y0+y1)-x1*y1-x0*y0, x0*y0, len); 
		}	
			return result;
 		
 	}
}
