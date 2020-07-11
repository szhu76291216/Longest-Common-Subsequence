/**
 * Calculates the longest common subsequence (LCS) of two input strings.
 * Two command line arguments can be supplied as input strings X and Y.
 * Otherwise, default values set in the `main` function will be used.
 */
public class LCS {

    public enum Direction {
        LEFT,
        UP,
        DIAG
    }

    public static String lcs(String x, String y) {
        int m = x.length();
        int n = y.length();
        Direction[][] from = new Direction[m + 1][n + 1];
        int[][] dp = new int[m + 1][n + 1];

        // initialize dp base case
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }
        
        // fill up dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    from[i][j] = Direction.DIAG;
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    from[i][j] = Direction.UP;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    from[i][j] = Direction.LEFT;
                }
            }
        }

        // backtrace to get result string
        String result = "";
        int i = m, j = n;
        while (i > 0 && j > 0) {
            switch (from[i][j]) {
            case DIAG:
                result = x.substring(i - 1, i) + result;
                i--;
                j--;
                break;
            case UP:
                i--;
                break;
            case LEFT:
                j--;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String x = "accbs";
        String y = "cbased";

        if (args.length >= 2) {
            x = args[0];
            y = args[1];
        }

        System.out.println("Inputs are:");
        System.out.format("  x: %s\n", x);
        System.out.format("  y: %s\n", y);
        System.out.format("LCS of x and y is: %s\n", lcs(x, y));
    }
}