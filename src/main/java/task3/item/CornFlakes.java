package task3.item;

public class CornFlakes extends Item {
  public CornFlakes() {
    super("кукурузные хлопья");
  }

  public static double confidenceBoost(double confidence) {
    return Math.min(Math.pow(confidence / 10, 2) + confidence * Math.PI * 3 / 10 + 1, 100); 
  }
}
