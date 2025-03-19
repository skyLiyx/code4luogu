package lynx.luogu.p33;

import java.io.*;

/**
 * P3367 并查集模板
 */
public class P3367 {
    private static final int MAXN = 200001;

    private static final int[] father = new int[MAXN];

    private static int N;
    private static int M;
    private static int Z;
    private static int X;
    private static int Y;

    private static void build() {
        for (int i = 0; i < N; i++) {
            father[i] = i;
        }
    }

    private static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    private static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        father[find(a)] = find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int)in.nval;
            build();
            in.nextToken();
            M = (int)in.nval;
            for (int i = 0; i < M; i++) {
                in.nextToken();
                Z = (int)in.nval;
                in.nextToken();
                X = (int)in.nval;
                in.nextToken();
                Y = (int)in.nval;
                if (Z == 1) {
                    union(X, Y);
                }
                if (Z == 2) {
                    out.println(isSameSet(X, Y) ? "Y" : "N");
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
