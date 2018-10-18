
public class Search_x {

	public static void main(String[] args) {
		int number[] = { 1, 2,3 ,4 ,5, 6, 7 ,8};
		
		search_index(number, 0, number.length-1, 10);
		
	}

	static public void search_index(int[] arr, int start , int end, int x) {
		int middle = (start + end) /2;
		
		if( arr[middle] == x ) {
			System.out.println(x+" 발견!!");
		}
		else if( middle >= start) {
			if( x > arr[middle] ) {
				 search_index(arr, middle+1, end, x);
			}
			else if( x < arr[middle] ){
				search_index(arr, start, middle-1, x);
			}
		}
		else {
			System.out.println(x+ " 발견하지 못했습니다!!");
		}
		
	}

}
