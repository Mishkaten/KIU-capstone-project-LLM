package GPT.St1.HW8;

// Reading the content of the file Fish.java
public class Fish {
    private String species;
    private double weight;

    public Fish(String species, double weight) {
        this.species = species;
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "species='" + species + '\'' +
                ", weight=" + weight +
                '}';
    }
}
