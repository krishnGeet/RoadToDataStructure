import java.util.Arrays;

/**
 * This class demonstrates three different approaches to solve the 0/1 Knapsack problem:
 * 1. Simple Recursion: A straightforward implementation of the recursive formula.
 * 2. Memoization (Top-Down DP): A recursive solution optimized by storing results of subproblems.
 * 3. Tabulation (Bottom-Up DP): An iterative solution that builds up the solution from the base case.
 */
public class Knapsack01 {

    /**
     * Solves the 0/1 knapsack problem using a simple recursive approach.
     * This method is inefficient for larger inputs due to re-calculating the same subproblems multiple times.
     *
     * @param w Array of item weights.
     * @param p Array of item profits.
     * @param c The capacity of the knapsack.
     * @param n The number of items to consider.
     * @return The maximum profit.
     */
    public static int knapsack(int w[], int p[], int c, int n){
        // Base Case: If the knapsack has no capacity or there are no items left, the profit is 0.
        if(c == 0 || n == 0){
            return 0;
        }

        // If the weight of the nth item is more than the knapsack capacity,
        // this item cannot be included. We recur for the remaining n-1 items.
        if(w[n - 1] > c){
            return knapsack(w, p, c, n - 1);
        }
        // Otherwise, we return the maximum of two cases:
        // 1. The nth item is included: Profit is profit of nth item + max profit for remaining capacity and items.
        // 2. The nth item is not included: Max profit for the same capacity with the remaining n-1 items.
        else{
            return Math.max(p[n - 1] + knapsack(w, p, c - w[n - 1], n - 1), knapsack(w, p, c, n - 1));
        }
    }

    /**
     * Solves the 0/1 knapsack problem using memoization (Top-Down Dynamic Programming).
     * It stores the results of subproblems in a 2D array to avoid redundant calculations.
     *
     * @param w Array of item weights.
     * @param p Array of item profits.
     * @param c The capacity of the knapsack.
     * @param n The number of items to consider.
     * @param memo A 2D array to store the results of solved subproblems.
     * @return The maximum profit.
     */
    public static int knapsackMemoized(int w[], int p[], int c, int n, int[][] memo) {
        // Base Case: If the knapsack has no capacity or there are no items left, the profit is 0.
        if (c == 0 || n == 0) {
            return 0;
        }
        // If the result for this state (n, c) has already been computed, return it from the memo table.
        if (memo[n][c] != -1) {
            return memo[n][c];
        }

        // If the weight of the nth item is more than the knapsack capacity, it cannot be included.
        // Store the result of the subproblem in the memo table before returning.
        if (w[n - 1] > c) {
            return memo[n][c] = knapsackMemoized(w, p, c, n - 1, memo);
        }
        // Otherwise, calculate the max profit, store it in the memo table, and then return it.
        else {
            return memo[n][c] = Math.max(p[n - 1] + knapsackMemoized(w, p, c - w[n - 1], n - 1, memo), knapsackMemoized(w, p, c, n - 1, memo));
        }
    }

    /**
     * Solves the 0/1 knapsack problem using tabulation (Bottom-Up Dynamic Programming).
     * It builds a table iteratively to find the optimal solution.
     *
     * @param w Array of item weights.
     * @param p Array of item profits.
     * @param c The capacity of the knapsack.
     * @param n The number of items.
     * @return The maximum profit.
     */
    public static int knapsackTable(int[] w, int[] p, int c, int n){
        // Create a table t[i][j] to store the maximum profit for capacity j with the first i items.
        int[][] t = new int[n + 1][c + 1];

        // Build the table t[][] in a bottom-up manner.
        for(int i = 1; i <= n; i++){ // Iterate through items
            for(int j = 1; j <= c; j++){ // Iterate through capacities
                // If the weight of the current item (i-1) is less than or equal to the current capacity (j)
                if(w[i - 1] <= j){
                    // We have two choices:
                    // 1. Include the item: Profit is p[i-1] + max profit for remaining capacity (j - w[i-1]) with previous items (t[i-1][...])
                    // 2. Don't include the item: Profit is the max profit with the previous items for the same capacity (t[i-1][j])
                    t[i][j] = Math.max(p[i - 1] + t[i - 1][j - w[i - 1]], t[i - 1][j]);
                }
                else{
                    // If the current item's weight is more than the current capacity, we can't include it.
                    // So, the max profit is the same as the max profit with the previous items.
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        // The final answer is the maximum profit for n items with capacity c.
        return t[n][c];
    }

    public static void main(String[] args) {
        // Sample data
        int[] w = {2, 4, 6, 7}; // weights
        int[] p = {12, 55, 8, 9}; // profits
        int c = 7; // knapsack capacity
        int n = w.length; // number of items
        
        // --- Memoization Table Setup ---
        // Create a memoization table and initialize it with -1 (or another indicator for 'not computed').
        int[][] memo = new int[n + 1][c + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        // --- Execute and Print Solutions ---
        System.out.println("Recursive solution: " + knapsack(w, p, c, n));
        System.out.println("Memoized solution: " + knapsackMemoized(w, p, c, n, memo));
        System.out.println("Tabulation Method: " + knapsackTable(w, p, c, n));
    
    }
}
