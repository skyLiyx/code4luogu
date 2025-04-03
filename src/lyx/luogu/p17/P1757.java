package lyx.luogu.p17;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * P1757 通天之分组背包
 *
 * @apiNote 分组背包
 */
public class P1757 {
    private static final int MAXN = 1001;
    private static final int MAXM = 1001;

    private static final int[][] items = new int[MAXN][3];

    private static final int[] dp = new int[MAXM];

    private static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                items[i][0] = (int) in.nval;
                in.nextToken();
                items[i][1] = (int) in.nval;
                in.nextToken();
                items[i][2] = (int) in.nval;
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int f() {
        Arrays.fill(dp, 0, m + 1, 0);
        Arrays.sort(items, 1, n + 1, Comparator.comparing(a -> a[2]));
        for (int start = 1, end = 1, cur; start <= n; ) {
            while (end <= n && items[start][2] == items[end][2]) {
                end++;
            }
            // [start, end)当前组
            for (int j = m; j >= 0; j--) {
                for (cur = start; cur < end; cur++) {
                    if (j - items[cur][0] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - items[cur][0]] + items[cur][1]);
                    }
                }
            }
            start = end; // 到下一组
        }
        return dp[m];
    }
}
