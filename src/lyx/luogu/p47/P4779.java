package lyx.luogu.p47;

import java.io.*;
import java.util.Arrays;

/**
 * P4779 【模板】单源最短路径（标准版）
 */
public class P4779 {
    // 点
    private static final int MAXN = 100001;
    // 边
    private static final int MAXM = 200001;

    private static int n, m, s, u, v, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            build(n);
            in.nextToken();
            m = (int)in.nval;
            in.nextToken();
            s = (int)in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                u = (int)in.nval;
                in.nextToken();
                v = (int)in.nval;
                in.nextToken();
                w = (int)in.nval;
                addEdge(u, v, w);
            }
            addOrUpdateOrIgnore(s, 0);
            while (heapSize > 0) {
                int u = pop();
                for (int ei = head[u]; ei > 0; ei = next[ei]) {
                    addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
                }
            }
            for (int i = 1; i < n; i++) {
                out.print(distance[i] + " ");
            }
            out.println(distance[n]);
        }
        out.flush();
        out.close();
        br.close();
    }

    // 链式前向星
    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXM];
    private static final int[] to = new int[MAXM];
    private static final int[] weight = new int[MAXM];
    private static int cnt;
    // 反向索引堆
    private static final int[] heap = new int[MAXN];
    private static int heapSize;
    private static final int[] where = new int[MAXN];

    private static final int[] distance = new int[MAXN];

    private static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        heapSize = 0;
        Arrays.fill(where, 0, n + 1, -1);
        Arrays.fill(distance, 0, n + 1, Integer.MAX_VALUE);
    }

    private static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    private static void addOrUpdateOrIgnore(int v, int cost) {
        if (where[v] == -1) {
            // 第一次进堆
            distance[v] = cost;
            heap[heapSize] = v;
            where[v] = heapSize++;
            heapInsert(where[v]);
        } else if (where[v] >= 0) {
            if (cost < distance[v]) {
                distance[v] = cost;
                heapInsert(where[v]);
            }
        }
    }

    private static void heapInsert(int i) {
        while (distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        where[ans] = -2;
        return ans;
    }

    private static void heapify(int i) {
        int j = 1;
        while (j < heapSize) {
            int best = j + 1 < heapSize && distance[heap[j + 1]] < distance[heap[j]] ? j + 1 : j;
            best = distance[heap[best]] < distance[heap[i]] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            j = (i << 1) + 1;
        }
    }

    private static void swap(int i, int j) {
        where[heap[i]] = j;
        where[heap[j]] = i;
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
