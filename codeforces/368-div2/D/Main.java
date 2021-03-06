import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out;
  public static int[][] b;
  public static int[] sums;
  public static BitSet[] bs;
  public static int n, m, q, tot=0;
  public static void main(String[] args) {
    MyScanner sc = new MyScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    n = sc.nextInt(); // shelves
    m = sc.nextInt(); // positions
    q = sc.nextInt();
    b = new int[q + 1][n + 1];
    sums = new int[q + 1];
    bs = new BitSet[100001];
    BitSet ones = new BitSet(m);
    ones.flip(0, m);
    sums[0] = 0;
    bs[0] = new BitSet();
    for (int r = 1; r <= q; r++) {
      int i = 0, j = 0, k = 0;
      int op = sc.nextInt();
      if (op == 4) {
        k = sc.nextInt();
        for (i = 0; i < n; i++)
          b[r][i] = b[k][i];
        sums[r] = sums[k];
      } else {
        for (i = 0; i < n; i++) {
          b[r][i] = b[r-1][i];
          sums[r] = sums[r-1];
        }
        switch (op) {
          case 1:
            i = sc.nextInt() - 1;
            j = sc.nextInt() - 1;
            if (!bs[b[r][i]].get(j)) {
              sums[r]++;
              bs[++tot] = (BitSet)bs[b[r][i]].clone();
              b[r][i] = tot;
              bs[b[r][i]].set(j);
            }
            break;
          case 2:
            i = sc.nextInt() - 1;
            j = sc.nextInt() - 1;
            if (bs[b[r][i]].get(j)) {
              sums[r]--;
              bs[++tot] = (BitSet)bs[b[r][i]].clone();
              b[r][i] = tot;
              bs[b[r][i]].clear(j);
            }
            break;
          case 3:
            i = sc.nextInt() - 1;
            int x = bs[b[r][i]].cardinality();
            bs[++tot] = (BitSet)bs[b[r][i]].clone();
            b[r][i] = tot;
            bs[b[r][i]].xor(ones);
            int ret = 0;
            //for (i = 0; i < n; i++)
              //ret += bs[b[r][i]].cardinality();
              //
            sums[r] = sums[r] - x - x + m;

            break;
        }
      }
      out.println(sums[r]);
    }
    out.close();
  }

  public static class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
       br = new BufferedReader(new InputStreamReader(System.in));
    }
 
    public boolean hasNext() {
      try {
        boolean a = br.ready();
        return a;
      } catch (IOException e) {
        return false;
      }
    }

    public String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }
 
    public int nextInt() {
      return Integer.parseInt(next());
    }
 
    public long nextLong() {
      return Long.parseLong(next());
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }

    public char nextChar() {
      return next().charAt(0);
    }

    public String nextLine() {
      String str = "";
      try {
         str = br.readLine();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return str;
    }
  }
}
