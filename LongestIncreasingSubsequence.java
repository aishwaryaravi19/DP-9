// Time Complexity : O(n log n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

//If the element is greater than the last element in the dp array, insert it into the dp array
//Else find the element that is just greater than the current element in the dp array and replace that element with the current element
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int len = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > dp[len-1]) {
                dp[len] = nums[i];
                len++;
            }
            else {
                int bsIndex = binarySearch(dp, 0, len-1, nums[i]);
                dp[bsIndex] = nums[i];
            }
        }
        return len;
    }

    //search for the element that is just greater than the target element
    public int binarySearch(int[] dp, int low, int high, int target) {
        while(low <= high) {
            int mid = low + (high-low) / 2; //prevent integer overflow
            if(dp[mid] == target) {
                return mid;
            }
            else if(dp[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return low;
    }
}
