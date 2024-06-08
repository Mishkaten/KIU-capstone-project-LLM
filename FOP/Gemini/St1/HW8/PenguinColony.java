package Claude.St1.HW8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {
    private HashSet<Penguin> penguins;

    public PenguinColony(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public HashSet<Penguin> getPenguins() {
        return penguins;
    }

    public void setPenguins(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }
    public void uniteColonies(PenguinColony otherColony) {
        penguins.addAll(otherColony.penguins);
        otherColony.penguins.clear(); // Empty the other colony
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        HashSet<Penguin> newPenguins = new HashSet<>();
        penguins.removeIf(penguin -> {
            if (pred.test(penguin)) {
                newPenguins.add(penguin);
                return true; // Remove from this colony
            }
            return false;
        });
        return new PenguinColony(newPenguins);
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        for (Penguin friend : penguinFriends) {
            if (penguins.contains(friend)) {
                return friend;
            }
        }
        return null; // No friend found in this colony
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        for (Penguin penguin : penguins) {
            if (pred.test(penguin) && !fishes.contains(penguin.getFavoriteFish())) {
                return false;
            }
        }
        return true; // All penguins can be fed
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        int sum = 0;
        for (Penguin penguin : penguins) {
            sum += fun.apply(penguin);
        }
        return sum;
    }
}
