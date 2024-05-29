package fop.w9colony;

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
        // TODO
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        // TODO
        return null;
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        // TODO
        return null;
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        // TODO
        return false;
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        // TODO
        return -1;
    }

}
