package co.com.niki.algorithms.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 2, 6, 15, 322, 1254, 3669};
        System.out.println("Binary: " + find(nums, -10, 0, nums.length-1));
    }

    private static int find(int[] container, int item, int left, int right){
        if(right<left) {
            return -1;
        }

        int middleIndex = (left + right) / 2;

        if (container[middleIndex] == item){
            return middleIndex;
        } else if (container[middleIndex] > item){
            return find(container, item, left, middleIndex-1);
        } else{
            return find(container, item, middleIndex+1, right);
        }
    }
}
