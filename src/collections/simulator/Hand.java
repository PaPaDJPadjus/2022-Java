package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        if (checkIfStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        }
        if (checkIfFourAKind()) {
            return HandType.FOUR_OF_A_KIND;
        }
        if (Objects.equals(checkIfTripleOrHouse(), "house")) {
            return HandType.FULL_HOUSE;
        }
        if (checkIfFlush()) {
            return HandType.FLUSH;
        }
        if (checkIfStraight()) {
            return HandType.STRAIGHT;
        }
        if (Objects.equals(checkIfTripleOrHouse(), "triple")) {
            return HandType.TRIPS;
        }
        return checkIfPairOrTwoPairsElseHighCard();
    }

    public HandType checkIfPairOrTwoPairsElseHighCard() {
        if (checkIfTwoPairs()) {
            return HandType.TWO_PAIRS;
        }
        if (checkIfPair()) {
            return HandType.ONE_PAIR;
        }
        return HandType.HIGH_CARD;
    }

    public boolean checkIfStraightFlush() {
        if (cards.size() == 5) {
            Collections.sort(cards);

            int compareCounter = 0;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).compareTo(cards.get(i + 1)) == -1 && cards.get(i).getSuit() == cards.get(i + 1).getSuit()) {
                    compareCounter++;
                }
            }
            if (compareCounter == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfFourAKind() {
        int compareCounter = 0;

        Collections.sort(cards);
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
                compareCounter++;
            } else if (compareCounter != 3 && compareCounter != 0){
                return false;
            }
        }
        return compareCounter == 3;
    }

    public String checkIfTripleOrHouse() {
        Collections.sort(cards);
        int compareCounter = 0;

        boolean doubleInfront = false;
        boolean tripsInfront = false;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
                compareCounter++;

            } else if (compareCounter == 1) {
                compareCounter = 0;
                doubleInfront = true;

            } else if (compareCounter == 2) {
                compareCounter = 0;
                tripsInfront = true;
            }
        }
        if (doubleInfront && compareCounter == 2 || tripsInfront && compareCounter == 1) {
            return "house";
        }
        if (tripsInfront && compareCounter == 0 || compareCounter == 2) {
            return "triple";
        }
        return "neither";
    }

    public boolean checkIfFlush() {
        Collections.sort(cards);
        boolean checkerForFlush = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).compareTo(cards.get(i + 1)) < 0 && cards.get(i).getSuit() == cards.get(i + 1).getSuit()) {
                checkerForFlush = true;
            } else {
                return false;
            }
        }
        return checkerForFlush;
    }

    public boolean checkIfStraight() {
        Collections.sort(cards);

        if (cards.size() != 5) {
            return false;
        }

        int aceInfront = 1;
        if (cards.get(0).getValue().equals(Card.CardValue.S2) && cards.get(cards.size() - 1).getValue().equals(Card.CardValue.A)) {
            aceInfront = 2;
        }

        boolean straightChecker = false;
        for (int i = 0; i < cards.size() - aceInfront; i++) {
            if (cards.get(i).compareTo(cards.get(i + 1)) == -1) {
                straightChecker = true;
            } else {
                return false;
            }
        }
        return straightChecker;
    }

    public boolean checkIfTwoPairs() {
        Collections.sort(cards);
        boolean firstPairCounter = false;
        boolean secondPairCounter = false;
        int bookmark = 0;

        for (int i = 0; i < cards.size() - 1; i++) {
            bookmark = i;
            if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
                firstPairCounter = true;
                break;
            }
        }
        for (int i = bookmark + 1; i < cards.size() - 1; i++) {
            if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
                secondPairCounter = true;
                break;
            }
        }
        return firstPairCounter && secondPairCounter;
    }

    public boolean checkIfPair() {
        int compareCounter = 0;
        Collections.sort(cards);
        if (Objects.equals(checkIfTripleOrHouse(), "neither")) {
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
                    compareCounter++;
                }
            }
            if (compareCounter == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
