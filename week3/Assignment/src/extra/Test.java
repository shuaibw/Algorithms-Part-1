package extra;

public class Test implements Comparable<Test>{
    private String name;
    private int age;

    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Test o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name+" "+age;
    }
}
