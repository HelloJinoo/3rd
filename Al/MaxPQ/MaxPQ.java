import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class MaxPQ {
	private MaxPQ() {
		
	}
	
	
	static MaxPQ m = new MaxPQ();
	static LinkedList<Node> maxpq = new LinkedList<Node>();
	
	
	private void bulid_max_heap(LinkedList a) {
		int i=(a.size()/2)-1;
		while( i >=0 ) {
			m.heapify(a, (Node)a.get(i) , i);
			i--;
		}
	}
	/*삽입*/
	private void insert(Node x) {
		int select = maxpq.size();
		maxpq.add(select,x);
		int parent = (select-1)/2;
		while( ((Node)maxpq.get(parent)).number < x.number ) {
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
	static public void heapify(LinkedList s, Node x , int select) {
		int i = select;
		while(i < s.size()/2) {
			 int j = 2*i+1;
			 if( j+1 < s.size() && ((Node)s.get(j+1)).number >((Node)s.get(j)).number) {
				 j++;
			 }
			 if(((Node)s.get(j)).number < x.number) {
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
	
	 
	 private void delete(LinkedList s , int x ) {
		Node temp = (Node) s.get(s.size()-1);
		s.remove(x);
		s.add(x , temp);
		s.removeLast();
		m.heapify(s, temp, x);
		
	 }
	
	/*Max 원소 리턴*/
	private Node Max(int s) {
		return maxpq.get(0);
	}
	
	/*key값 증가*/
	private void increase_key(LinkedList s , int x , int k) {
		((Node)s.get(x)).number = k;
		Node change = (Node)s.get(x);
		int parent = (x-1)/2;
		int select = x;
		while( ((Node)s.get(parent)).number < ((Node)s.get(select)).number ) {
			Node temp = (Node) s.get(parent);
			s.remove(parent);
			s.add(parent , change);
			s.remove(select);
			s.add(select , temp);
			select = (select-1)/2;
			parent = (select-1)/2;
		}
	}
	
	/*Max 원소 제거*/
	private Node extract_max(LinkedList s) {
		Node max = (Node) s.get(0);
		Node temp = (Node) s.get(s.size()-1);
		s.removeFirst();
		s.removeLast();
		s.add(0 , temp);
		m.heapify(s, temp, 0);
		
		return max;
	}
	
	 static public class Node{
		 private int number;
		 private String subject;
		
		 public Node(int number , String subject){
			this.number = number;
			this.subject = subject;
		}
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream("data03.txt"),"euc-kr"));
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String read[];
		while(	(line =reader.readLine()) != null) {
			read = line.split(",");
			Node p = new Node(Integer.parseInt(read[0]), read[1]);
			maxpq.add(p);
		}
		m.bulid_max_heap(maxpq);
		int input = 0;
	
		while(input != 6) {
			System.out.println("***현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.***");
			System.out.println("[index]  key ,  value" );
			System.out.println("-------------------------");
			for(int i=0; i<maxpq.size(); i++) {
				System.out.println( "["+i+"] " + ((Node)maxpq.get(i)).number+ " ," + ((Node)maxpq.get(i)).subject );
			}
			System.out.println("-------------------------");
	
		
			System.out.println("1.작업 추가     2. 최댓값     3.최대 우선순위 작업 처리");
			System.out.println("4.원소 키 값 증가        5.작업 제거      6.종료");
			System.out.println("-------------------------");
			System.out.print("명령 번호 입력 : ");
			 input = Integer.parseInt(br.readLine());
			if(input ==1 ) {
				System.out.print("추가하려는 key 과목명을 입력해주세요:");
				read = br.readLine().split(" ");
				Node p = new Node(Integer.parseInt(read[0]), read[1]);
				m.insert(p);
			}
			else if( input == 2) {
				System.out.println("최댓값 : " + m.Max(0).number + " , " + m.Max(0).subject);
				System.out.println();
				System.out.println();
			}
			else if( input ==3 ) {
				m.extract_max(maxpq);
			}
			else if( input == 4 ) {
				System.out.print("증가하려는 원소의index key증가값을 적어주세요:");
				read = br.readLine().split(" ");
				int index = Integer.parseInt(read[0]);
				int increase_value = Integer.parseInt(read[1]);
				m.increase_key(maxpq, index, increase_value);
			}
			else if( input == 5 ) {
				System.out.print("제거하려는 원소의 index를 적어주세요:");
				int delnum = Integer.parseInt(br.readLine());
				m.delete(maxpq, delnum);
			}
		}
		
		
	}

	
	
}
