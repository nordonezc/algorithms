public class Anagram {


    /**
     *
     * e.g.
     * Silent = Listen
     * Triangle = Integral
     * Dormitory = Dirty room
     * @param args
     */
    public static void main(String[] args) {

        String word11 = "Dormitory";
        String word21 = "Dirty room";

        String word1 = "ab";
        String word2 = "aaaaaaaaaaaaaab";

        String word3 = word2.toLowerCase().strip();
        word1 = word1.toLowerCase().strip();

        boolean isAnagram = true;

        for(int i=0; i<word1.length(); i++){
            if(word3.contains(word1.charAt(i)+"")){
                int positionFoundedChar = word3.indexOf(word1.charAt(i));
                word3 = word3.substring(0, positionFoundedChar) + 
                        word3.substring(positionFoundedChar, word3.length());
            }
            else{
                break;
            }

        }

        if(isAnagram)
            System.out.println("It is! :D");
    }
}
