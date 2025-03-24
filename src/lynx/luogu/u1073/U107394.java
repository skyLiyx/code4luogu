package lynx.luogu.u1073;

import java.io.*;
import java.util.Arrays;

/**
 * U107394 拓扑排序模板
 */
public class U107394 {

    private static final int MAXN = 100001;

    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXN];
    private static final int[] to = new int[MAXN];
    private static int cnt;

    private static final int[] indegree = new int[MAXN];

    private static final int[] heap = new int[MAXN];
    private static int heapSize;

    private static final int[] ans = new int[MAXN];

    private static int n, m, u, v;

    private static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        Arrays.fill(indegree, 0, n + 1, 0);
        heapSize = 0;
    }

    private static void addEdge(int f, int t) {
        // u -> v
        next[cnt] = head[f];
        to[cnt] = t;
        head[f] = cnt++;
    }

    private static void push(int num) {
        int i = heapSize++;
        heap[i] = num;
        while (heap[i] < heap[(i - 1) / 2]) {
            swap((i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    private static int pop() {
        int ans = heap[0];
        heap[0] = heap[--heapSize];
        int i = 0, l = 1;
        while (l < heapSize) {
            // 先从左右子节点找最小值
            int best = l + 1 < heapSize && heap[l + 1] < heap[l] ? l + 1 : l;
            // 再与本节点比较
            best = heap[i] < heap[best] ? i : best;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }
        return ans;
    }

    private static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static void topologicalSort() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                push(i);
            }
        }
        int fill = 0;
        while (heapSize > 0) {
            int cur = pop();
            ans[fill++] = cur;
            for (int ei = head[cur]; ei > 0; ei = next[ei]) {
                if (--indegree[to[ei]] == 0) {
                    push(to[ei]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            build(n);
            in.nextToken();
            m = (int)in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                u = (int)in.nval;
                in.nextToken();
                v = (int)in.nval;
                addEdge(u, v);
                indegree[v]++;
            }
            topologicalSort();
            for (int i = 0; i < n - 1; i++) {
                out.print(ans[i] + " ");
            }
            out.println(ans[n - 1]);
        }
        out.flush();
        out.close();
        br.close();
    }
}
