package co.com.niki.algorithms.recursion;

/**
 * Tower of Hanoi
 * <p>
 * Order disk of different sizes using 3 rods
 * Complex: O(2^n)
 * <p>
 * Rules
 * * Single disk can be moved at a time
 * * Move upper disk from one of the stacks
 * * No disk may be placed on top of smaller plate
 * <p>
 * Recursion
 * There will be a situation where we have to shift n-1 plates
 * and move the largest plate to the destination rod
 */
public class HanoiTower {

    public static void main(String[] args) {
        solve(4, 'a', 'b', 'c');
    }

    /**
     * Move the disk number from the source to the destination with the help
     * of the middle rod
     *
     * @param disk disk number to move
     * @param source source rod
     * @param middle helping rod
     * @param destination destination to move all the disk
     */
    public static void solve(int disk, char source, char middle, char destination) {
        if (disk == 1) {
            printMovement(disk, source, destination);
            return;
        }

        solve(disk - 1, source, destination, middle);
        printMovement(disk, source, destination);
        solve(disk - 1, middle, source, destination);
    }

    /**
     * Print the movement done
     *
     * @param disk
     * @param source
     * @param destination
     */
    public static void printMovement(int disk, char source, char destination) {
        System.out.println("Plate " + disk + " from " + source + " to " + destination);
    }

}
