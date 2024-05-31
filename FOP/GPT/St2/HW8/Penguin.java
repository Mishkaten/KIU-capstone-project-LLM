package GPT.St2.HW8;

import java.util.Objects;

public class Penguin {
    private final int birthYear;
    private final String name;
    private final Gender gender;
    private Fish favoriteFish;

    public Penguin(int birthYear, String name, Gender gender) {
        this.birthYear = birthYear;
        this.name = name;
        this.gender = gender;
    }

    // Getters and setters for favoriteFish
    public Fish getFavoriteFish() {
        return favoriteFish;
    }

    public void setFavoriteFish(Fish favoriteFish) {
        this.favoriteFish = favoriteFish;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Penguin penguin = (Penguin) other;
        return birthYear == penguin.birthYear &&
                Objects.equals(name, penguin.name) &&
                gender == penguin.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, name, gender);
    }
}
