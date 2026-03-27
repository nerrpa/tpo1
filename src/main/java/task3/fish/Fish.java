package task3.fish;

public class Fish {
    private String color;
    public boolean swimming;
    public boolean shimmering;

    public Fish(String color) {
        this.color = color;
        this.swimming = true;
        this.shimmering = true;
    }

    public String getColor() { return color; }

    public String toString() {
      return getColor() + " рыба, которая " 
      + (swimming ? "плавает " : "не плавает ")
      + "и "
      + (shimmering ? "переливается" : "не переливается");
    }
}