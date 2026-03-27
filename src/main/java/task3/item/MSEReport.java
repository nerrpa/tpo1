package task3.item;

public class MSEReport extends Item {

  private String text;
  private boolean willDep = false;

  public MSEReport() {
    super("отчет");
    text = "";
  }

  public void evalReport(String s, boolean c, String name) {
    text = s;
    willDep = !c && name.contains(" ");
  }

  public String getText() {
    return text;
  }

  public boolean willDeparted() {
    return willDep;
  }
  
}
