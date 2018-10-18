import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ClosetPairOfPoints {

	static float point_x1;
	static float point_y1;
	static float point_x2;
	static float point_y2;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data05.txt"));
		LinkedList<xy> xysXsort = new LinkedList<>();
		String line;
		
		
		while((line = reader.readLine()) != null) {
			String input[] = line.split(",");
			xy xy_atom = new xy( Float.parseFloat(input[0]) , Float.parseFloat(input[1]));
			if( xysXsort.size() == 0 ) {
				xysXsort.add(xy_atom);
			}
			else {
				for(int i=0; i < xysXsort.size(); i++) {
					if(xysXsort.get(i).x > xy_atom.x) {
						xysXsort.add( i , xy_atom);
						break;
					}
					else if( i+1 == xysXsort.size()) {
						xysXsort.addLast(xy_atom);
						break;
					}
				}
				
			}
			
		}
		divide(0,xysXsort.size()-1, xysXsort);
		System.out.println("�ִ� �Ÿ�: " + min);
		System.out.println("(��1�� x : "+ point_x1+" , ��1�� y : " + point_y1 +")");
		System.out.println("(��2�� x : "+ point_x2+" , ��2�� y : " + point_y2 +")");
		reader.close();

	}

	static float min = Float.MAX_VALUE;
	static public void divide(int start , int end , LinkedList<xy> ls) {

		if(end - start > 2) { //���� 4�� �̻� 
			int middle = (start + end ) / 2; //�߰��κ�
			divide(start , middle-1 , ls); //���ʺκ�
			divide(middle , end , ls); //�����ʺκ�
			window(min , middle,ls); 
		}
		else {
			float a = mindistance(start , end , ls);
			if(min > a) {
				min = a;
			}
		}
	}
	
	static public float mindistance( int start , int end , LinkedList<xy> ls) {
		float mindis = Float.MAX_VALUE;
			for(int i=start; i<=end ; i++) {
					xy box = (xy)ls.get(i);
				for(int j=i+1; j<=end; j++) {
					float dist = (float) Math.sqrt(Math.pow(box.x-((xy)ls.get(j)).x, 2)+Math.pow(box.y-((xy)ls.get(j)).y, 2));
			
					if(mindis > dist) {
						mindis = dist;
						if( min>mindis) {
							point_x1 = box.x;
							point_x2 = ls.get(j).x;
							point_y1 = box.y;
							point_y2= ls.get(j).y;
						}
					}
				}
			}
		
		return mindis;

	}	
	static public void window(double range ,int middle,LinkedList<xy> ls){
		LinkedList<xy> tempxys = new LinkedList<>();
		LinkedList<xy> xysYsort = new LinkedList<>();
		
		/*window�������ִ� ������ tempxys�� ����*/
		for(int i=0; i<ls.size(); i++) {
			if( ls.get(i).x  >= ls.get(middle).x -range &&  ls.get(i).x  <= ls.get(middle).x +range ) {
				tempxys.add(ls.get(i));
			}
			else if(ls.get(i).x > ls.get(middle).x+range) {
				break;
			}
			
		}
		
		/*window�� ������ Y�� ����*/
		for(int i=0; i<tempxys.size(); i++) {
			if( i ==0) {
				xysYsort.add(tempxys.get(i));
			}
			else {
				for( int j=0; j<xysYsort.size(); j++) {
					if( xysYsort.get(j).y > tempxys.get(i).y ) {
						xysYsort.add(j, tempxys.get(i));
						break;
					}
					else if( j+1 == xysYsort.size()) {
						xysYsort.addLast(tempxys.get(i));
						break;
					}
				}
			}
		}
			
		/*window������ �ּҰŸ� ã��*/
		for(int i=0; i<xysYsort.size(); i++) {
			xy box = xysYsort.get(i);
			for(int j=i+1; j<xysYsort.size(); j++) {
				float dist = 0;
				if( i == j ) {
					continue;
				}
				else {
					if( xysYsort.get(j).y-box.y <= range) {  //y�� range
						dist = (float) Math.sqrt(Math.pow(box.x-(xysYsort.get(j)).x, 2)+Math.pow(box.y-(xysYsort.get(j)).y, 2));
						if(min > dist) {
							min = dist;
							point_x1 = box.x;
							point_x2 = xysYsort.get(j).x;
							point_y1 = box.y;
							point_y2 = xysYsort.get(j).y;
						}
					}
					else {
						break;
					}
				}

			}
		}
	}	
	
	/*x,y��ǥ class*/
	static public class xy{
		private float x;
		private float y;
		xy(float x, float y ){
			this.x = x;
			this.y =y;
		}
	}

}
