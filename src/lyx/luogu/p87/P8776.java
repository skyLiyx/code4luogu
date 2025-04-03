package lyx.luogu.p87;

import java.io.*;

/**
 * P8776 [蓝桥杯 2022 省 A] 最长不下降子序列
 *
 * @apiNote 动态规划
 */
public class P8776 {
    private static final int MAXN = 1000001;
    // right[i]: [i, N-1]范围内最长不下降子序列的长度
    private static final int[] right = new int[MAXN];
    private static final int[] endsLIS = new int[MAXN];
    private static final int[] endsLDS = new int[MAXN];

    private static int N, K;
    private static final int[] A = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            in.nextToken();
            K = (int) in.nval;
            for (int i = 0; i < N; i++) {
                in.nextToken();
                A[i] = (int) in.nval;
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    /**
     * 根据常识，修改的连续 K 个数字紧接的下一个数字一定参与构成子序列。
     * 那么可以从 0 开始枚举这 K 个数字的位置，将这个范围的值逻辑更新为
     * 下一个位置的值 x。
     * <pre>
     * 当前整个数组的最长不下降子序列长度
     *   =
     * 范围左边的不大于 x 的最长不下降子序列长度
     *   +
     *  K 个长度
     *   +
     * 范围右边的不小于 x 的最长不下降子序列长度
     * </pre>
     * 找出枚举过程中最大的。
     */
    private static int f() {
        // 从 n-1 开始向左反向计算每个位置作为起点，最长不上升子序列的长度，
        // 来替代以 0 开始向右正向计算每个位置作为起点的最长不下降子序列。
        buildRight();
        int ans = 0, left;
        int len = 0;
        for (int l = 0, r = K, find; r < N; l++, r++) {
            // 找出左边不大于r的长度
            find = bsLIS(len, A[r]);
            left = find == -1 ? len : find;
            ans = Math.max(ans, left + K + right[r]);
            // 将右移后即将释放的数加入
            find = bsLIS(len, A[l]);
            if (find == -1) {
                endsLIS[len++] = A[l];
            } else {
                endsLIS[find] = A[l];
            }
        }
        // 特例：修改范围在最右边，此时无right[r]
        ans = Math.max(ans, len + K);
        return ans;
    }

    private static void buildRight() {
        int len = 0;
        for (int i = N - 1, find; i >= 0; i--) {
            find = bsLDS(len, A[i]);
            if (find == -1) {
                endsLDS[len++] = A[i];
                right[i] = len;
            } else {
                endsLDS[find] = A[i];
                right[i] = find + 1;
            }
        }
    }

    private static int bsLDS(int len, int target) {
        int ans = -1, l = 0, r = len - 1, m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (endsLDS[m] < target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    private static int bsLIS(int len, int target) {
        int ans = -1, l = 0, r = len - 1, m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (endsLIS[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
