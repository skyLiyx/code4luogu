package lynx.luogu.p29;

import java.io.*;

/**
 * P2910 [USACO08OPEN] Clear And Present Danger S
 *
 * @apiNote 最短路，Floyd算法
 */
public class P2910 {
    private static final int MAXN = 101;
    private static final int MAXM = 10001;
    private static final int[][] distance = new int[MAXN][MAXN];
    private static final int[] path = new int[MAXM];
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            in.nextToken();
            M = (int) in.nval;
            for (int i = 0; i < M; i++) {
                in.nextToken();
                path[i] = (int) in.nval - 1;
            }
            build();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    in.nextToken(); distance[i][j] = (int) in.nval;
                }
            }
            floyd();
            int ans = 0;
            for (int i = 1; i < M; i++) {
                ans += distance[path[i - 1]][path[i]];
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void build() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static void floyd() {
        for (int bridge = 0; bridge < N; bridge++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distance[i][bridge] != Integer.MAX_VALUE &&
                            distance[bridge][j] != Integer.MAX_VALUE &&
                            distance[i][j] > distance[i][bridge] + distance[bridge][j]) {
                        distance[i][j] = distance[i][bridge] + distance[bridge][j];
                    }
                }
            }
        }
    }
}
