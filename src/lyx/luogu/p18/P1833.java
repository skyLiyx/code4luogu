package lyx.luogu.p18;

import java.io.*;
import java.util.Arrays;

/**
 * P1833 樱花
 *
 * @apiNote 完全背包+多重背包
 */
public class P1833 {
    private static final int MAXN = 110001;
    private static final int MAXM = 1001;
    private static final int LIMITED = 1000;

    private static final int[] dp = new int[MAXM];

    private static final int[] w = new int[MAXN];
    private static final int[] v = new int[MAXN];
    private static int h1, m1, h2, m2, n, t, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            h1 = (int) in.nval;
            in.nextToken(); // 跳过冒号
            in.nextToken(); m1 = (int) in.nval;
            in.nextToken(); h2 = (int) in.nval;
            in.nextToken();
            in.nextToken(); m2 = (int) in.nval;
            t = (h2 - h1) * 60 + (m2 - m1); // 计算时间
            in.nextToken(); n = (int) in.nval;
            m = 0;
            for (int i = 1, weight, value, count; i <= n; i++) {
                in.nextToken(); weight = (int) in.nval;
                in.nextToken(); value = (int) in.nval;
                in.nextToken(); count = (int) in.nval;
                if (count == 0) {
                    // 完全背包转换成多重背包
                    count = LIMITED;
                }
                // 二进制分组. 将数量分为1,2,4,8,16...将数量级降低到log n
                // 将问题转变为01背包, 最终组合实现1,2,3,4,5,6,7...的实际数量
                for (int k = 1; k <= count; k <<= 1) {
                    v[++m] = value * k;
                    w[m] = weight * k;
                    count -= k;
                }
                if (count > 0) {
                    v[++m] = value * count;
                    w[m] = weight * count;
                }
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int f() {
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= m; i++) {
            for (int j = t; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        return dp[t];
    }
}
