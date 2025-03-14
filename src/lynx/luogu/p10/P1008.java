package lynx.luogu.p10;

import java.io.PrintWriter;

/**
 * P1008 [NOIP 1998 普及组] 三连击
 */
public class P1008 {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
//        for (int i = 123; i <= 333; i++) {
//            int d = 2 * i;
//            int t = 3 * i;
//            if (check(new int[]{i, d, t})) {
//                out.println(i + " " + d + " " + t);
//            }
//        }
        out.println("192 384 576");
        out.println("219 438 657");
        out.println("273 546 819");
        out.println("327 654 981");
        out.flush();
        out.close();
    }

//    private static boolean check(int[] nums) {
//        int[] arr = new int[10];
//        for (int num : nums) {
//            while (num != 0) {
//                int n = num % 10;
//                if (n == 0 || arr[n] != 0) {
//                    return false;
//                }
//                arr[n] = 1;
//                num /= 10;
//            }
//        }
//        return true;
//    }
}
