import java.io.*;
import java.util.*;

public class bigtruck {
  static short cost = Short.MAX_VALUE;
  static short items = 0;
  static short m;
  static byte n;
  static byte[] locItems;
  static ArrayList<byte[]>[] gEdges;
  static Set<Byte> visited = new HashSet();

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Byte.parseByte(in.readLine());
    String[] itemsIn = in.readLine().split("\\s+");
    locItems = new byte[n];
    for (int i = 0; i < n; i++) locItems[i] = Byte.parseByte(itemsIn[i]);
    gEdges = new ArrayList[n];
    for(int i = 0; i < n; i++) gEdges[i] = new ArrayList<byte[]>();

    m = Short.parseShort(in.readLine());
    for (int i = 0; i < m; i++) {
      String[] edge = in.readLine().split("\\s+");
      byte a = Byte.parseByte(edge[1]); a--;
      byte b = Byte.parseByte(edge[2]);
      gEdges[Integer.parseInt(edge[0]) - 1].add(new byte[]{a, b});
    }

    recursiveSolve((byte)0, (short)0, locItems[0]);

    if (cost == Short.MAX_VALUE) out.write("impossible\n");
    else out.write(cost + " " + items + "\n");
    out.flush();
  }

  public static void recursiveSolve(byte loc, short cCost, short cItems) {
    /* Base Case - reach the final location */
    if (loc == (n - 1)) {
      if (cCost < cost) {
        cost = cCost;
        items = cItems;
      } else if (cCost == cost && cItems > items) {
        items = cItems;
      }
      return;
    }
    if ( visited.contains(loc) )
      return;
    else
      visited.add(loc);

	

    Iterator<byte[]> edgesIt = gEdges[loc].iterator();
    while ( edgesIt.hasNext() ) {
      byte[] edge = edgesIt.next();
      short temp = cCost;
      temp += edge[1];
      short temp2 = cItems;
      temp2 += locItems[edge[0]];
      recursiveSolve(edge[0], temp, temp2);
    }
    visited.remove(loc);
  }
}
