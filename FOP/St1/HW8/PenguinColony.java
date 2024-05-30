// Reading the content of the file PenguinColony.java
import java.util.ArrayList;
import java.util.List;

public class PenguinColony {
    private String name;
    private List<Penguin> penguins;
    private List<Fish> fishStock;

    public PenguinColony(String name) {
        this.name = name;
        this.penguins = new ArrayList<>();
        this.fishStock = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Penguin> getPenguins() {
        return penguins;
    }

    public List<Fish> getFishStock() {
        return fishStock;
    }

    public void addPenguin(Penguin penguin) {
        penguins.add(penguin);
    }

    public void addFish(Fish fish) {
        fishStock.add(fish);
    }

    public int getNumberOfPenguins() {
        return penguins.size();
    }

    public double getTotalFishWeight() {
        double totalWeight = 0;
        for (Fish fish : fishStock) {
            totalWeight += fish.getWeight();
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        PenguinColony colony = new PenguinColony("Antarctic Colony");

        Penguin penguin1 = new Penguin("Pingu", Gender.MALE, 5);
        Penguin penguin2 = new Penguin("Pinga", Gender.FEMALE, 4);

        Fish fish1 = new Fish("Salmon", 2.5);
        Fish fish2 = new Fish("Tuna", 3.0);

        colony.addPenguin(penguin1);
        colony.addPenguin(penguin2);

        colony.addFish(fish1);
        colony.addFish(fish2);

        System.out.println("Colony Name: " + colony.getName());
        System.out.println("Number of Penguins: " + colony.getNumberOfPenguins());
        System.out.println("Total Fish Weight: " + colony.getTotalFishWeight());

        System.out.println("Penguins:");
        for (Penguin penguin : colony.getPenguins()) {
            System.out.println(penguin);
        }

        System.out.println("Fish Stock:");
        for (Fish fish : colony.getFishStock()) {
            System.out.println(fish);
        }
    }
}
