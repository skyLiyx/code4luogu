package lyx.luogu.p26;

import java.io.*;
import java.util.Arrays;

/**
 * P2698 [USACO12MAR] Flowerpot S
 */
public class P2698 {
    private static int N;
    private static int D;
    private static final int[][] POS = new int[100001][2];
    private static final int[] maxDeque = new int[100001];
    private static final int[] minDeque = new int[100001];
    private static int maxh, maxt, minh, mint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int)in.nval;
            in.nextToken();
            D = (int)in.nval;
            for (int i = 0; i < N; i++) {
                in.nextToken();
                POS[i][0] = (int)in.nval;
                in.nextToken();
                POS[i][1] = (int)in.nval;
            }
            out.println(process());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int process() {
        maxh = maxt = minh = mint = 0;
        Arrays.sort(POS, 0, N, (a, b) -> a[0] - b[0]);
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < N; l++) {
            // 当前来到水滴i
            // 判断是否满足最大-最小>=D
            while (!ok() && r < N) {
                push(r++);
            }
            if (ok()) {
                ans = Math.min(ans, POS[r - 1][0] - POS[l][0]);
            }
            pop(l);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static boolean ok() {
        int max = maxh < maxt ? POS[maxDeque[maxh]][1] : 0;
        int min = minh < mint ? POS[minDeque[minh]][1] : 0;
        return max - min >= D;
    }

    private static void push(int r) {
        while (maxh < maxt && POS[maxDeque[maxt - 1]][1] <= POS[r][1]) {
            maxt--;
        }
        maxDeque[maxt++] = r;
        while (minh < mint && POS[minDeque[mint - 1]][1] >= POS[r][1]) {
            mint--;
        }
        minDeque[mint++] = r;
    }

    private static void pop(int l) {
        if (maxh < maxt && maxDeque[maxh] == l) {
            maxh++;
        }
        if (minh < mint && minDeque[minh] == l) {
            minh++;
        }
    }
}
