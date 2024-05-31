package GPT.St3.HW8;

import java.util.Objects;

public class Penguin {
    private final int birthYear;
    private final String name;
    private final Gender gender;
    private Fish favoriteFish;

    public Penguin(int birthYear, String name, Gender gender, Fish favoriteFish) {
        this.birthYear = birthYear;
        this.name = name;
        this.gender = gender;
        this.favoriteFish = favoriteFish;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Object getFavoriteFish() {
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

    @Override
    public String toString() {
        return "Penguin{" +
                "birthYear=" + birthYear +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", favoriteFish=" + favoriteFish +
                '}';
    }
}
