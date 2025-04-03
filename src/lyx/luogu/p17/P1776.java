package lyx.luogu.p17;

import java.io.*;
import java.util.Arrays;

/**
 * P1776 宝物筛选
 *
 * @apiNote 多重背包
 */
public class P1776 {
    // 二进制分组后最大数量
    // log2(每种物品数量上限),向上取整,再+1, 再乘以种类n的上限
    private static final int MAXM = 1800;
    private static final int MAXW = 40001;
    private static final int[] dp = new int[MAXW];

    private static int n, W, v, w, c, m;
    private static final int[] cost = new int[MAXM];
    private static final int[] val = new int[MAXM];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken(); W = (int) in.nval;
            m = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken(); v = (int) in.nval;
                in.nextToken(); w = (int) in.nval;
                in.nextToken(); c = (int) in.nval;
                // 二进制分组. 将数量分为1,2,4,8,16...将数量级降低到log n
                // 将问题转变为01背包, 最终组合实现1,2,3,4,5,6,7...的实际数量
                for (int k = 1; k <= c; k <<= 1) {
                    val[++m] = v * k;
                    cost[m] = w * k;
                    c -= k;
                }
                if (c > 0) {
                    val[++m] = v * c;
                    cost[m] = w * c;
                }
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int f() {
        Arrays.fill(dp, 0, W + 1, 0);
        for (int i = 1; i <= m; i++) {
            for (int j = W; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[W];
    }
}
