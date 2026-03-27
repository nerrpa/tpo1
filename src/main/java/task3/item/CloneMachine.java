package task3.item;

public class CloneMachine extends Item {
  public CloneMachine() {
    super("Машина для клонирования");
  }
  
  public <T> T copy(T obj) {
    try {
      return (T) obj.getClass().getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      return null;
    }
  }
}
