import java.util.Arrays;

public class Knapsack01 {
    public static int knapsack(int w[], int p[], int c, int n){
        if(c == 0 || n == 0){
            return 0;
        }
        else if(w[n - 1] > c){
            return knapsack(w, p, c, n - 1);
        }
        else{
            return Math.max(p[n - 1] + knapsack(w, p, c - w[n - 1], n - 1), knapsack(w, p, c, n - 1));
        }
    }

    // Memoized version of the knapsack algorithm
    public static int knapsackMemoized(int w[], int p[], int c, int n, int[][] memo) {
        if (c == 0 || n == 0) {
            return 0;
        }
        if (memo[n][c] != -1) {
            return memo[n][c];
        }
        if (w[n - 1] > c) {
            return memo[n][c] = knapsackMemoized(w, p, c, n - 1, memo);
        } else {
            return memo[n][c] = Math.max(p[n - 1] + knapsackMemoized(w, p, c - w[n - 1], n - 1, memo), knapsackMemoized(w, p, c, n - 1, memo));
        }
    }

    public static void main(String[] args) {
        int[] w = {2, 4, 6, 7};
        int[] p = {12, 55, 8, 9};
        int c = 7;
        int n = w.length;

        System.out.println("Recursive solution: " + knapsack(w, p, c, n));

        int[][] memo = new int[n + 1][c + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        System.out.println("Memoized solution: " + knapsackMemoized(w, p, c, n, memo));
    }
}
