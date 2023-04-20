package co.com.niki.algorithms.search;

public class LinearSearch {

    public static void main(String[] args) {

        int[] nums = {1,5,-5,3,10,100};
        System.out.println("forloop: " + find(nums, 200));
        System.out.println("recursive: " + findRecursive(nums, 200,0));

    }

    public static int find(int[] container, int item){
        for (int index = 0; index < container.length; index++) {
            if (container[index] == item){
                return index;
            }
        }

        return -1;
    }
    
    public static int findRecursive(int[] container, int item, int position){
        if(position == container.length)
            return -1;
        
        if(container[position] == item)
            return position;
        
        return findRecursive(container, item, position+1);
    }
}
