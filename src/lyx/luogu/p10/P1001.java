package lyx.luogu.p10;

import java.io.*;

/**
 * P1001 A+B Problem
 */
public class P1001 {
    private static int a;

    private static int b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            a = (int)in.nval;
            in.nextToken();
            b = (int)in.nval;
            out.println(a + b);
        }
        out.flush();
        out.close();
        br.close();
    }
}
