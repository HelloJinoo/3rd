package AL08;

import AL08.MinPQ;
import AL08.MinPQ.Node;

public class dijkstra {
	static int[] d;
	static int S_index = 0;

	public static void main(String[] args) {
		/*초기화*/
		char vertex[] = { 'A', 'B', 'C', 'D', 'E' };
	      d = new int[vertex.length];
	      char s[] = new char[vertex.length];
	      int[][] value = { 
	    		{ 0, 10, 3, Integer.MAX_VALUE, Integer.MAX_VALUE },
	            { Integer.MAX_VALUE, 0, 1, 2, Integer.MAX_VALUE }, 
	            { Integer.MAX_VALUE, 4, 0, 8, 2 },
	            { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 7 },
	            { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 0 } 
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

		dijkstra dk = new dijkstra();
		dk.update_d(min, value, vertex, s);

	}

	/*dijkstra*/
	private void update_d(MinPQ min, int[][] distBox, char[] check_vertex, char[] visited_vertex) {
		while(!MinPQ.maxpq.isEmpty()) {
		Node view_vertex = min.extract_min(MinPQ.maxpq);
		visited_vertex[S_index] = view_vertex.vertex;
		System.out.println("──────────────────────────────────────────────");
		System.out.println("S[" + S_index + "] : d[" + view_vertex.vertex + "] = " + view_vertex.distance);
		System.out.println("---------------------------------------------");

for (int i = 0; i < min.maxpq.size(); i++) {
	System.out.print("Q["+i+"] : d[" +min.maxpq.get(i).vertex+"] = " + d[min.maxpq.get(i).vertex-65]  );
	if (distBox[view_vertex.vertex - 65][min.maxpq.get(i).vertex-65] != Integer.MAX_VALUE) {
		if (d[min.maxpq.get(i).vertex-65] > distBox[view_vertex.vertex - 65][min.maxpq.get(i).vertex-65] 
				+d[view_vertex.vertex - 65]) {
			d[min.maxpq.get(i).vertex-65] = distBox[view_vertex.vertex - 65][min.maxpq.get(i).vertex-65]
				+ d[view_vertex.vertex - 65];
			System.out.println("-> d["+ min.maxpq.get(i).vertex + "] = " + d[min.maxpq.get(i).vertex-65] );
			for (int j = 0; j < MinPQ.maxpq.size(); j++) {
				if (MinPQ.maxpq.get(j).vertex == check_vertex[min.maxpq.get(i).vertex-65]) {
					MinPQ.maxpq.get(j).distance = d[min.maxpq.get(i).vertex-65];
					break;
					}
				}
			}
			else {
				System.out.println();
			}
		}
		else {
			System.out.println();
		}
	}
	min.bulid_min_heap(MinPQ.maxpq);
	System.out.println();
	S_index++;
	}	
  }
}
