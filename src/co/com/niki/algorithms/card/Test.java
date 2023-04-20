package co.com.niki.algorithms.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Test {

    private List<CardRange> cardRanges = List.of(
            new CardRange("400000000000", "499999999999", "visa"),
            new CardRange("500000000000", "599999999999", "mc")
    );

    public static void main(String[] args) {

    }

    interface CardTypeCache{
        String get(String cardNumber);
    }

    class CardRange{
        String start;
        String end;
        String cardType;

        public CardRange(String start, String end, String cardType) {
            this.start = start;
            this.end = end;
            this.cardType = cardType;
        }
    }

    public static CardTypeCache build(List<CardRange> cardRange){
        return new CardTypeCacheImpl(cardRange);
    }

    class CardTypeCacheImpl implements CardTypeCache{

        List<CardRange> cardRanges;


        public CardTypeCacheImpl(List<CardRange> cardRanges) {
            this.cardRanges = cardRanges;
        }

        @Override
        public String get(String cardNumber) {

            for (CardRange carRange: cardRanges) {
                
            }
            return null;
        }

        private String printList(){
            StringJoiner joiner = new StringJoiner("\n");
            for (CardRange carRange: cardRanges) {
                joiner.add(carRange.start + ", " + carRange.end + carRange.cardType);
            }
        }
    }
}
