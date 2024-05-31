package GPT.St4.HW8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {
    private HashSet<Penguin> penguins;

    public PenguinColony() {
        this.penguins = new HashSet<>();
    }

    public void addPenguin(Penguin penguin) {
        this.penguins.add(penguin);
    }

    public void uniteColonies(PenguinColony otherColony) {
        this.penguins.addAll(otherColony.penguins);
        otherColony.penguins.clear();
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        PenguinColony newColony = new PenguinColony();
        this.penguins.removeIf(penguin -> {
            if (pred.test(penguin)) {
                newColony.addPenguin(penguin);
                return true;
            }
            return false;
        });
        return newColony;
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        for (Penguin friend : penguinFriends) {
            if (this.penguins.contains(friend)) {
                return friend;
            }
        }
        return null;
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        for (Penguin penguin : this.penguins) {
            if (pred.test(penguin) && !fishes.contains(penguin.getFavoriteFish())) {
                return false;
            }
        }
        return true;
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        int sum = 0;
        for (Penguin penguin : this.penguins) {
            sum += fun.apply(penguin);
        }
        return sum;
    }
}
