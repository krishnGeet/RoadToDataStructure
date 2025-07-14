public class SubArrSum {

    // Recursive Solution
    public static boolean subArrSum(int[] arr, int n, int sum){
        if(sum == 0){
            return true;
        }
        if(n == 0){
            return false;
        }
        if(arr[n - 1] > sum){
            return subArrSum(arr, n - 1, sum);
        }
        else{
            return subArrSum(arr, n - 1, sum) || subArrSum(arr, n - 1, sum - arr[n - 1]);
        }
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6};
        int sum = 15;
        System.out.println(subArrSum(arr, arr.length, sum));
    }
}
