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
        penguins.addAll(otherColony.getPenguins());
        otherColony.getPenguins().clear();
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        HashSet<Penguin> newColonyPenguins = new HashSet<>();
        penguins.removeIf(penguin -> {
            if (pred.test(penguin)) {
                newColonyPenguins.add(penguin);
                return true;
            }
            return false;
        });
        return new PenguinColony(newColonyPenguins);
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        return penguinFriends.stream()
                .filter(penguins::contains)
                .findFirst()
                .orElse(null);
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        return penguins.stream()
                .filter(pred)
                .allMatch(penguin -> fishes.contains(penguin.getFavoriteFish()));
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        return penguins.stream()
                .mapToInt(fun::apply)
                .sum();
    }

}
