package GPT.St1.HW8;

// Reading the content of the file Penguin.java
public class Penguin {
    private String name;
    private Gender gender;
    private int age;

    public Penguin(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
