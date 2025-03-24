package lynx.luogu.p23;

import java.io.*;
import java.util.Arrays;

/**
 * P2330 [SCOI2005] 繁忙的都市
 *
 * @apiNote 并查集
 */
public class P2330 {
    private static final int[] father = new int[301];

    private static final int[][] edges = new int[8001][3];

    private static int n, m;
    
    private static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    private static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    private static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            father[fa] = fb;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            build();
            in.nextToken();
            m = (int)in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int)in.nval;
                in.nextToken();
                edges[i][1] = (int)in.nval;
                in.nextToken();
                edges[i][2] = (int)in.nval;
            }
            Arrays.sort(edges, 0, m, (e1, e2) -> e1[2] - e2[2]);
            int ans = 0;
            for (int i = 0; i < m; i++) {
                if (union(edges[i][0], edges[i][1])) {
                    ans = Math.max(ans, edges[i][2]);
                }
            }
            out.println((n - 1) + " " + ans);
        }
        out.flush();
        out.close();
        br.close();
    }
}
