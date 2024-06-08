package Claude.St1.HW8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Create some Penguins
        Penguin p1 = new Penguin(2010, "Pingu", Gender.MALE, Fish.SARDINE);
        Penguin p2 = new Penguin(2012, "Pingy", Gender.FEMALE, Fish.SILVERFISH);
        Penguin p3 = new Penguin(2015, "Pinga", Gender.FEMALE, Fish.SARDELLE);
        Penguin p4 = new Penguin(2011, "Pingo", Gender.MALE, Fish.SARDINE);
        Penguin p5 = new Penguin(2013, "Pinguin", Gender.MALE, Fish.SILVERFISH);

        // Create a Penguin Colony
        HashSet<Penguin> penguinSet1 = new HashSet<>();
        penguinSet1.add(p1);
        penguinSet1.add(p2);
        PenguinColony colony1 = new PenguinColony(penguinSet1);

        HashSet<Penguin> penguinSet2 = new HashSet<>();
        penguinSet2.add(p3);
        penguinSet2.add(p4);
        penguinSet2.add(p5);
        PenguinColony colony2 = new PenguinColony(penguinSet2);

        // Test uniteColonies
        colony1.uniteColonies(colony2);
        System.out.println("Colony 1 after uniting with Colony 2: " + colony1.getPenguins());
        System.out.println("Colony 2 after uniting with Colony 1: " + colony2.getPenguins());
        // Expected output: Colony 1 should contain all penguins, Colony 2 should be empty

        // Test splitColony
        Predicate<Penguin> pred = penguin -> penguin.getGender() == Gender.FEMALE;
        PenguinColony femaleColony = colony1.splitColony(pred);
        System.out.println("Colony 1 after splitting females: " + colony1.getPenguins());
        System.out.println("Female Colony: " + femaleColony.getPenguins());
        // Expected output: Colony 1 should contain only males, Female Colony should contain only females

        // Test findFirstFriend
        LinkedList<Penguin> penguinFriends = new LinkedList<>();
        penguinFriends.add(p1);
        penguinFriends.add(p3);
        Penguin firstFriend = colony1.findFirstFriend(penguinFriends);
        System.out.println("First friend found in Colony 1: " + firstFriend);
        // Expected output: First friend should be Pingu (p1)

        // Test canFeedPenguinsWithProperty
        Set<Fish> availableFish = new HashSet<>();
        availableFish.add(Fish.SARDINE);
        availableFish.add(Fish.SILVERFISH);
        boolean canFeed = colony1.canFeedPenguinsWithProperty(penguin -> penguin.getGender() == Gender.MALE, availableFish);
        System.out.println("Can feed all male penguins in Colony 1: " + canFeed);
        // Expected output: true (if all male penguins' favorite fish are available)

        // Test computeSum
        Function<Penguin, Integer> fun = Penguin::getBirthYear;
        int sumBirthYears = colony1.computeSum(fun);
        System.out.println("Sum of birth years in Colony 1: " + sumBirthYears);
        // Expected output: Sum of birth years of all penguins in Colony 1
    }
}
