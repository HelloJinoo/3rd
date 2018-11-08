package AL09;

import AL09.MinPQ;
import AL09.MinPQ.Node;

public class mst_prims {
	static int[] d;
	static int[] p;
	static int MST =0 ;
	public static void main(String[] args) {
		/*초기화*/
		char vertex[] = { 'a', 'b', 'c', 'd', 'e','f','g','h','i' };
	      d = new int[vertex.length];
	      p = new int[vertex.length];
	      char s[] = new char[vertex.length];
	      
	      int[][] value = { 
	    		{ 0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,8,Integer.MAX_VALUE },
	    		{ 4, 0, 8, Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,11,Integer.MAX_VALUE }, 
	    		{ Integer.MAX_VALUE, 8, 0, 7, Integer.MAX_VALUE,4,Integer.MAX_VALUE,Integer.MAX_VALUE,2 },
	    		{ Integer.MAX_VALUE, Integer.MAX_VALUE, 7, 0, 9,14 ,Integer.MAX_VALUE ,Integer.MAX_VALUE,Integer.MAX_VALUE },
	    		{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9 , 0 ,10,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE },
	    		{Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 14 , 10 ,0,2,Integer.MAX_VALUE,Integer.MAX_VALUE },
	    		{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE , Integer.MAX_VALUE ,2,0,1,6 },
	    		{8, 11, Integer.MAX_VALUE, Integer.MAX_VALUE , Integer.MAX_VALUE ,Integer.MAX_VALUE,1,0,7 },
	    		{Integer.MAX_VALUE, Integer.MAX_VALUE, 2, Integer.MAX_VALUE , Integer.MAX_VALUE,Integer.MAX_VALUE,6,7,0}
	            };
		MinPQ min = new MinPQ();
		char start = vertex[0];  //시작 정점
		
		/*시작정점외 d배열 나머지는 Max값*/
		for (int i = 0; i < d.length; i++) {
			if (vertex[i] == start) {
				d[i] = 0;
			} else {
				d[i] = Integer.MAX_VALUE;
			}
		}
		/*Priority- heap 초기화*/
		for (int i = 0; i < vertex.length; i++) {
			if (vertex[i] == start) {
				min.insert(min.new Node(vertex[i], 0));
			} else {
				min.insert(min.new Node(vertex[i], Integer.MAX_VALUE));
			}
		}

		mst_prims dk = new mst_prims();
		dk.prims(min, vertex, value , s , start);
		
	}
	public void prims(MinPQ min ,char[] vertex ,int[][] value ,char[] s ,char start) {
		int index=0;
		while(!min.maxpq.isEmpty()){
				Node select = min.extract_min(MinPQ.maxpq);
				if( select.vertex == start ) {
					System.out.println("w( , "+ start +" ) = " + d[0]);
				}
				s[index] = select.vertex;
				index++;
			
				int vertex_one = select.vertex-97;
				int mindist = Integer.MAX_VALUE;
				int minvert =0;
				
				/*연결된 선의 값으로 d배열 설정*/
				for(int i=0; i<min.maxpq.size(); i++) {
					int Q_live = min.maxpq.get(i).vertex-97; //큐에 있는 정점
					int connet_value = value[vertex_one][Q_live];  //점과 연결된 값
					if( d[Q_live] >  connet_value && connet_value !=0) {
						d[Q_live] = connet_value;
						min.maxpq.get(i).distance = d[Q_live];
						p[Q_live] = vertex_one;
					}
				}
				
				/*d배열에서 가장 짧은선 찾기*/
				for(int i=0; i<d.length; i++) {
					if( compare(i,s,index)) {   //큐에있는지 없는지 확인
						if(mindist > d[i]) {
							mindist =d[i];
							minvert =i;
						}
					}
				}
				
			if( mindist < Integer.MAX_VALUE) {
			MST = MST + mindist;
			System.out.println("w( " + (char)(p[minvert]+97) +" , " + (char)(minvert+97) + " ) = "+ mindist);
			}
				min.bulid_min_heap(MinPQ.maxpq);
		}
		System.out.println();
		System.out.println("MST = " + MST);
	}
	private boolean compare(int i, char[] s , int index) {
		for(int k=0; k<index; k++) {
			if( i == s[k]-97) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
