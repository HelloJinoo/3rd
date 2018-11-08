package AL09;

import java.util.LinkedList;

public class MinPQ {
     MinPQ() {
		
	}
	
	static MinPQ m = new MinPQ();
	static LinkedList<Node> maxpq = new LinkedList<Node>();
	

	 public void bulid_min_heap(LinkedList a) {
		int i=(a.size()/2)-1;
		while( i >=0 ) {
			m.heapify(a, (Node)a.get(i) , i);
			i--;
		}
	}
	 /*삽입*/
	 public void insert(Node x) {
			int select = maxpq.size();
			maxpq.add(select,x);
			int parent = (select-1)/2;
			while( ((Node)maxpq.get(parent)).distance > x.distance ) {
				Node temp = (Node) maxpq.get(parent);
				maxpq.remove(parent);
				maxpq.add(parent , x);
				maxpq.remove(select);
				maxpq.add(select , temp);
				select = (select-1)/2;
				parent = (select-1)/2;
			}
		}
	
	/*히프화*/
	 public void heapify(LinkedList s, Node x , int select) {
		int i = select;
		while(i < s.size()/2) {
			 int j = 2*i+1;
			 if( j+1 < s.size() && ((Node)s.get(j+1)).distance < ((Node)s.get(j)).distance) {
				 j++;
			 }
			 if(((Node)s.get(j)).distance > x.distance) {
				 break;
			 }
			 Node temp = (Node) s.get(j);
			 s.remove(i);
			 s.add(i,temp);
			 s.remove(j);
			 s.add(j,x);
			 i=j;
		 }
	}
	 public Node minvalue(LinkedList s) {
		 return (Node) s.get(0);
	 }

	/*Min 원소 제거*/
	 public Node extract_min(LinkedList s) {
		Node min = (Node) s.get(0);
		Node temp = (Node) s.get(s.size()-1);
		s.removeFirst();
		if(s.size() >= 1) {
			s.removeLast();
			s.add(0 , temp);
			m.heapify(s, temp, 0);
		
		}

		return min;
	}
	
	  public class Node{
		 public char vertex;
		 public int distance;

		 public Node( char vertex , int distance){
			 this.vertex = vertex;
			this.distance = distance;
			
		}

	 }
}
