package lyx.luogu.p50;

import java.io.*;
import java.util.Arrays;

/**
 * P5026 Lycanthropy
 *
 * @apiNote 等差数列差分
 */
public class P5026 {
    // 数量
    private static int n;
    // 湖泊宽度
    private static int m;
    // 体积
    private static int v;
    // 入水点
    private static int x;

    private static final int MAX = 1000001;

    private static final int OFFSET = 30000;

    private static final int[] AD = new int[OFFSET + MAX + OFFSET];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bf);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            in.nextToken();
            m = (int)in.nval;
            Arrays.fill(AD, 0);
            for (int i = 0; i < n; i++) {
                in.nextToken();
                v = (int)in.nval;
                in.nextToken();
                x = (int)in.nval;
                build();
            }
            compute();
            for (int i = 0; i < m; i++) {
                out.print(AD[i + OFFSET + 1] + " ");
            }
            out.println();
            out.flush();
        }
        out.close();
    }

    private static void build() {
        build(x - 3 * v + 1, x - 2 * v, 1, v, 1);
        build(x - 2 * v + 1, x, v - 1, -v, -1);
        build(x + 1, x + 2 * v, -v + 1, v, 1);
        build(x + 2 * v + 1, x + 3 * v - 1, v - 1, 1, -1);
    }

    private static void build(int l, int r, int s, int e, int d) {
        AD[l + OFFSET] += s;
        AD[l + 1 + OFFSET] += d - s;
        AD[r + 1 + OFFSET] -= e + d;
        AD[r + 2 + OFFSET] += e;
    }

    private static void compute() {
        for (int i = 1; i <= m + OFFSET; i++) {
            AD[i] += AD[i - 1];
        }
        for (int i = 1; i <= m + OFFSET; i++) {
            AD[i] += AD[i - 1];
        }
    }
}
