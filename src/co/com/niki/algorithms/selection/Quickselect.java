package co.com.niki.algorithms.selection;

import java.util.Random;

public class Quickselect {

    public static void main(String[] args) {
        int nums[] = {1, -5, 10, 55, 2, 3, -7, 7, 11, 100};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(quickselect(nums, i));
        }
    }

    private static int quickselect(int[] nums, int k) {
        return quickselect(nums, k, 0, nums.length - 1);
    }

    private static int quickselect(int[] nums, int k, int firstIndex, int lastIndex) {
        int pivot = partitionPhase(nums, firstIndex, lastIndex);

        if (pivot < k) {
            return quickselect(nums, k, pivot + 1, lastIndex);
        } else if (pivot > k) {
            return quickselect(nums, k, firstIndex, pivot - 1);
        } else {
            return nums[pivot];
        }
    }

    private static void swap(int[] nums, int firstPosition, int secondPosition) {
        int temporal = nums[firstPosition];
        nums[firstPosition] = nums[secondPosition];
        nums[secondPosition] = temporal;
    }

    private static int partitionPhase(int[] nums, int firstIndex, int lastIndex) {
        int pivot = new Random().nextInt(lastIndex - firstIndex + 1) + firstIndex;
        swap(nums, pivot, lastIndex);

        for (int i = firstIndex; i < lastIndex; i++) {
            if (nums[i] < nums[lastIndex]) {
                swap(nums, i, firstIndex);
                firstIndex++;
            }
        }

        swap(nums, lastIndex, firstIndex);

        return firstIndex;
    }
}
