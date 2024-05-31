package GPT.St3.HW8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {
    private Set<Penguin> penguins;

    public PenguinColony() {
        this.penguins = new HashSet<>();
    }

    public void addPenguin(Penguin penguin) {
        penguins.add(penguin);
    }

    public void uniteColonies(PenguinColony otherColony) {
        penguins.addAll(otherColony.penguins);
        otherColony.penguins.clear();
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        PenguinColony newColony = new PenguinColony();
        penguins.removeIf(penguin -> {
            if (pred.test(penguin)) {
                newColony.addPenguin(penguin);
                return true;
            }
            return false;
        });
        return newColony;
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        return penguinFriends.stream().filter(penguins::contains).findFirst().orElse(null);
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        return penguins.stream()
                .filter(pred)
                .allMatch(penguin -> fishes.contains(penguin.getFavoriteFish()));
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        return penguins.stream().map(fun).mapToInt(Integer::intValue).sum();
    }
}
