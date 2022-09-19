// Time Complexity : O(n log n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) return 0;

        //Sort the elements(ascending order) by its width, if two elements have same width, sort the heights in descending order
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        //If the element is greater than the last element in the dp array, insert it into the dp array
        //Else find the element that is just greater than the current element in the dp array and replace that element with the current element
        int n = envelopes.length;
        int[] dp = new int[n];
        dp[0] = envelopes[0][1];
        int len = 1;

        for(int i=1; i<envelopes.length; i++) {
            if(envelopes[i][1] > dp[len-1]) {
                dp[len] = envelopes[i][1];
                len++;
            } else {
                int bsIndex =  binarySearch(dp, 0, len-1, envelopes[i][1]);
                dp[bsIndex] = envelopes[i][1];
            }
        }
        return len;
    }

    //search for the element that is just greater than the target element
    public int binarySearch(int[] dp, int low, int high, int target) {
        while(low <= high) {
            int mid = low+(high-low)/2; //prevent integer overflow
            if(dp[mid] == target) return mid;
            else if(dp[mid] < target) {
                low = mid+1;
            } else if(dp[mid] > target) {
                high = mid - 1;
            }
        }
        return low;
    }
}