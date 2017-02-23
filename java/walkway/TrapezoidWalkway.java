import java.io.*;
import java.util.*;

public class TrapezoidWalkway {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /* The number of trapezoid shapes: 1-1000 */
    int n = Integer.parseInt(in.readLine());
    /* Trapeziod Information: 1-1000 */
    int a, b, h;
    /* The starting Width and ending Width */
    int start, end;
    /* Hold the trapezoid shapes */
    ArrayList<int[]>[] trapStones = new ArrayList[1001];
    for (int i = 0; i < 1001; i++) trapStones[i] = new ArrayList<int[]>();

    while ( n != 0 ) {
      /* Clear the lists on each iteration */
      for ( int i = 0; i < 1001; i++ ) trapStones[i].clear();

      /* Reads in the Data for scenario */
      for (int i = 0; i < n; i++) {
        /* Read in a stones dims and add it to the list */
        String[] trapDimsIn = in.readLine().split("\\s");
        a = Integer.parseInt(trapDimsIn[0]);
        b = Integer.parseInt(trapDimsIn[1]);
        h = Integer.parseInt(trapDimsIn[2]);

        trapStones[a].add(new int[]{b, h});
        trapStones[b].add(new int[]{a, h});
      }
      String[] boundaries = in.readLine().split("\\s");
      start = Integer.parseInt(boundaries[0]);
      end = Integer.parseInt(boundaries[1]);

      /* Dijkstra's for finding the shortest path */
      int[] vertexes = new int[1001];
      PriorityQueue<Integer> dPQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
          /* We want the comparison to use the value of the vertex */
          /* If we haven't visited a vertex compare the edge sizes */
          if(vertexes[a] != vertexes[b]) return vertexes[a]-vertexes[b];
          else return Integer.MAX_VALUE;
        }
      });
      for (int i = 0; i < 1001; i++) vertexes[i] = Integer.MAX_VALUE;
      vertexes[start] = 0;
      dPQueue.add(start);
      while ( !dPQueue.isEmpty() ) {
        int at = dPQueue.poll();

        Iterator<int[]> edgesIt = trapStones[at].iterator();
        while ( edgesIt.hasNext() ) {
          int[] edges = edgesIt.next();
          int edgeSize = edges[0];
          int cost = edges[1] * (at + edges[0]);
          if (vertexes[edgeSize] > vertexes[at] + cost) {
            dPQueue.remove(edgeSize);
            vertexes[edgeSize] = vertexes[at] + cost;
            dPQueue.add(edgeSize);
          }
        }
      }

      /* Print out the Result to two decimal places */
      System.out.printf("%.2f\n", 0.01 * vertexes[end]);

      n = Integer.parseInt(in.readLine());
    }
  }
}
