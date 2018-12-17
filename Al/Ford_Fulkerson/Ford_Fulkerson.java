
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

	public class Ford_Fulkerson {

	    private int[] parent;
	    private Queue<Integer> queue;
	    private int numberOfVertices;
	    private boolean[] visited;
	 
	    public Ford_Fulkerson(int numberOfVertices)
	    {
	        this.numberOfVertices = numberOfVertices;
	        this.queue = new LinkedList<Integer>();
	        parent = new int[numberOfVertices + 1];
	        visited = new boolean[numberOfVertices + 1];		
	    }
	 
	    public boolean bfs(int source, int goal, int graph[][])
	    {
	        boolean pathFound = false;
	        int destination, element;
	 
	        for(int vertex = 1; vertex <= numberOfVertices; vertex++)
	        {
	            parent[vertex] = -1;
	            visited[vertex] = false;
	        }
	 
	        queue.add(source);
	        parent[source] = -1;
	        visited[source] = true;
	 
	        while (!queue.isEmpty())
	        { 
	            element = queue.remove();
	            destination = 1;
	 
	            while (destination <= numberOfVertices)
	            {
	                if (graph[element][destination] > 0 &&  !visited[destination])
	                {
	                    parent[destination] = element;
	                    queue.add(destination);
	                    visited[destination] = true;
	                }
	                destination++;
	            }
	        }
	        if(visited[goal])
	        {
	            pathFound = true;
	        }
	        return pathFound;
	    }
	 
	    public int fordFulkerson(int graph[][], int source, int destination)
	    {
	        int u, v;
	        int maxFlow = 0;
	        int pathFlow;
	 
	        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];
	        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++)
	        {
	            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++)
	            {
	                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];
	            }
	        }
	 
	        while (bfs(source ,destination, residualGraph))
	        {
	            pathFlow = Integer.MAX_VALUE;
	            System.out.print("Path : ");
	            String path="";
	            for (v = destination; v != source; v = parent[v])
	            {
	                u = parent[v];
	                path += u;
	                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
	               
	            }
	            for (v = destination; v != source; v = parent[v])
	            {
	                u = parent[v];
	                residualGraph[u][v] -= pathFlow;
	                residualGraph[v][u] += pathFlow;
	            }
	            maxFlow += pathFlow;
	            for(int i=path.length()-1; i>=0; i--) {
	            	System.out.print(path.charAt(i));
	            }
	            System.out.print(destination);
	            System.out.println(" / Path 최대 유량 : "+pathFlow);
	        }
	 
	        return maxFlow;
	    }
	 
	    public static void main(String...arg) {
	        int numberOfNodes;
	        int source;
	        int sink;
	        int maxFlow;
	 
	        
	        numberOfNodes = 6;  //노드 수 
	        int[][] graph = { {0,0,0,0,0,0,0},
  				  {0,0,11,12,0,0,0},
  				  {0,0,0,0,12,0,0},
  				  {0,0,1,0,0,11,0},
  				  {0,0,0,0,0,0,19},
  				  {0,0,0,0,7,0,4},
  				  {0,0,0,0,0,0,0}
  		
	        };   //노드간의 흐를수 있는 유량
	        source= 1;  //시작 노드
	        sink = 6;   //도착 노드
	 
	        System.out.println("시작 노드 : " + source);
	        System.out.println("도착 노드 : " + sink);
	        Ford_Fulkerson fordFulkerson = new Ford_Fulkerson(numberOfNodes);
	        maxFlow = fordFulkerson.fordFulkerson(graph, source, sink);
	       
	        System.out.println("최대 유량 : " + maxFlow);
	        
	    }
	}

	
	
