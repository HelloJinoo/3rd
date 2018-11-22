import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Knapsack {

	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new FileReader("data11.txt"));
		String line = "";
		int value_table[][] = new int[5][3]; //ItemNum, Value, Weight
		int i=0;

		int max = 0; //합인 최대의 가치 값
		int maxindex1=0; 
		int maxindex2=0;
		
		/*입력 -> 저장*/
		while(( line = br.readLine()) != null) {
			String read[] = line.split(",");
			value_table[i][0] = Integer.parseInt(read[0]);
			value_table[i][1] = Integer.parseInt(read[1]);
			value_table[i][2] = Integer.parseInt(read[2]);
			i++;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("배낭 사이즈를 입력해주세요(0~50) : ");
		int size = Integer.parseInt(reader.readLine());
		
		int knap[][] = new int[6][size+1];  //가치 합
		String knap_index[][] = new String[6][size+1]; //아이템 
		/*초기화*/
		for(int k=0; k<knap_index.length; k++) {
			for(int w =0; w<knap_index[k].length; w++) {
				knap_index[k][w] = "";
			}
		}
		
		if(size>=0 && size<=50) {
			for(int item=1; item < knap.length; item++) {
				for(int weight=1; weight < knap[item].length; weight++) {
					if(value_table[item-1][2] <= weight) {
						if(knap[item-1][weight] < value_table[item-1][1] 
								+ knap[item-1][weight - value_table[item-1][2]] ) {
							knap[item][weight] = value_table[item-1][1] 
									+ knap[item-1][weight - value_table[item-1][2]];
							knap_index[item][weight] += (knap_index[item-1][weight - value_table[item-1][2]] 
									+" " +(Integer.toString(value_table[item-1][0])));
						}
						else {
							knap[item][weight] = knap[item-1][weight];
							knap_index[item][weight] += knap_index[item-1][weight];
						}
						
					} else {
						knap[item][weight] = knap[item-1][weight];
						knap_index[item][weight] += Integer.toString(value_table[item-1][0]); 
					}
					
					/*최댓값과 비교*/
					if(max < knap[item][weight]) {
						max = knap[item][weight];
						maxindex1 = item;
						maxindex2 = weight;
					}
						
				}
			}
		
			/*가치 출력*/
			for(int index=0; index< knap.length; index++) {
				for(int index2 = 0; index2 < knap[index].length; index2++) {
					System.out.print(knap[index][index2]+ " ");
				}
				System.out.println();
			}
		}
		System.out.println("Max : "+max); //최대 가치
		System.out.println("item :"+knap_index[maxindex1][maxindex2]); //최대 가치를 구성하는 item번호
		
	}
	
	

}
