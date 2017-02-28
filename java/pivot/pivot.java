import java.io.*;
import java.util.*;

public class pivot {
  public static void main(String[] args) throws Exception{
    // Buffered* cuz I'm a cool kid
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    
    // Read Data In
    int n = Integer.parseInt(in.readLine());
    String[] elementsIn = in.readLine().split("\\s+");
    int[] elements = new int[n];
    for ( int i = 0; i < n; i++) elements[i] = Integer.parseInt(elementsIn[i]);
    
    // Supplemental Vars
    int[] min = new int[n], max = new int[n];
    int upper = elements[0], lower = elements[n-1];
    int solution = 0;
    
    // Greater than
    for ( int i = 0; i < n ; i++ ) {
      if ( elements[i] > upper) upper = elements[i];
      max[i] = upper;
    }
    
    // Less than
    for ( int i = n - 1; i >= 0; i-- ) {
      if ( elements[i] < lower) lower = elements[i];
      min[i] = lower;
    }
    
    // Count possible
    for ( int i = 0; i < n; i++ )
      if ( elements[i] <= min[i] && elements[i] >= max[i] ) solution++;
    
    out.write(solution + "\n");
    out.flush();
    }
}
