import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSumIncreaseSubsequence {

    // Function to find the maximum sum of increasing subsequence and display the subsequence
    public static Result maxSumIncreasingSubsequence(int[] sequence) {
        int n = sequence.length;
        int[] dl = new int[n];
        int[] preIndex = new int[n];

        // Initialize dp array with the values of the original sequence
        // Initialize prevIndex array with -1 indicating no previous element
        for (int i = 0; i < n; i++) {
            dl[i] = sequence[i];
            preIndex[i] = -1;
        }

        // Compute the maximum sum of increasing subsequences
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && dl[i] < dl[j] + sequence[i]) {
                    dl[i] = dl[j] + sequence[i];
                    preIndex[i] = j;
                }
            }
        }

        // Find the maximum value in the dp array and its index
        int maxSum = dl[0];
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dl[i] > maxSum) {
                maxSum = dl[i];
                maxIndex = i;
            }
        }

        // Reconstruct the subsequence using prevIndex array
        List<Integer> subsequence = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i = preIndex[i]) {
            subsequence.add(sequence[i]);
            if (preIndex[i] == -1) break;
        }
        Collections.reverse(subsequence);

        return new Result(maxSum, subsequence);
    }

    // Result class to store the maximum sum and the corresponding subsequence
    static class Result {
        int maxSum;
        List<Integer> subsequence;

        Result(int maxSum, List<Integer> subsequence) {
            this.maxSum = maxSum;
            this.subsequence = subsequence;
        }
    }

    // Main method 
    public static void main(String[] args) {
        int[] sequence = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11};
        Result result = maxSumIncreasingSubsequence(sequence);
        System.out.println("Maximum Sum of Increasing Subsequence: " + result.maxSum);
        System.out.println("Subsequence: " + result.subsequence);
    }
}
