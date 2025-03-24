package lynx.luogu.p45;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * P4568 [JLOI2011] 飞行路线
 *
 * @apiNote 分层图最短路
 */
public class P4568 {
    private static final int MAXN = 10001;
    private static final int MAXM = 100001;
    private static final int MAXK = 11;
    // 链式前向星
    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXM];
    private static final int[] to = new int[MAXM];
    private static final int[] weight = new int[MAXM];
    private static int cnt;
    // 城市编号&使用的免单次数 的代价
    private static final int[][] distance = new int[MAXN][MAXK];
    private static final boolean[][] visited = new boolean[MAXN][MAXK];
    private static final PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
    private static int n, m, k, s, t, a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken(); m = (int) in.nval;
            in.nextToken(); k = (int) in.nval;
            build(n, k);
            in.nextToken(); s = (int) in.nval;
            in.nextToken(); t = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken(); a = (int) in.nval;
                in.nextToken(); b = (int) in.nval;
                in.nextToken(); c = (int) in.nval;
                addEdge(a, b, c);
                addEdge(b, a, c);
            }
            out.println(dijkstra());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int dijkstra() {
        // 城市编号、使用的免费次数、代价
        heap.add(new int[]{s, 0, 0});
        distance[s][0] = 0;
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int u = poll[0];
            int use = poll[1];
            int cost = poll[2];
            if (u == t) {
                return cost;
            }
            if (visited[u][use]) {
                continue;
            }
            visited[u][use] = true;
            for (int ei = head[u], v, w; ei > 0; ei = next[ei]) {
                v = to[ei];
                w = weight[ei];
                // 使用免费次数
                if (use < k && distance[u][use] < distance[v][use + 1]) {
                    distance[v][use + 1] = distance[u][use];
                    heap.add(new int[]{v, use + 1, distance[v][use + 1]});
                }
                // 不使用免费次数
                if (distance[u][use] + w < distance[v][use]) {
                    distance[v][use] = distance[u][use] + w;
                    heap.add(new int[]{v, use, distance[v][use]});
                }
            }
        }
        return -1;
    }

    private static void build(int n, int k) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
            }
        }
        heap.clear();
    }

    private static void addEdge(int f, int t, int w) {
        next[cnt] = head[f];
        to[cnt] = t;
        weight[cnt] = w;
        head[f] = cnt++;
    }
}
