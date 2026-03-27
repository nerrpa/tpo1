package task3;

import task3.item.CloneMachine;
import task3.item.CornFlakes;
import task3.item.DentrassiUnderwear;
import task3.item.Item;
import task3.item.MSEReport;
import task3.item.ScvorshneineMatrass;
import task3.person.Arthur;
import task3.person.Ford;
import task3.person.MigrationServiceEmployee;
import task3.person.Person;

public class Main {
  public static void main(String[] args) {
    Ford f = new Ford();
    Arthur a = new Arthur();

    System.out.println(
      f.getName() + 
      (f.getVial() == null ? "лох" : 
      " имеет " + f.getVial().toString() +
      (f.getVial().getFish() == null ? " пустой ": " с " + f.getVial().getFish().toString())
      )
    );

    System.out.println(a.lookAt(f) + (a.isBlinking() ? " моргая " : " пересохшими глазами "));

    System.out.println(f.offerFishToEar(a));

    Item[] items = {
      new DentrassiUnderwear(),
      new CornFlakes(),
      new ScvorshneineMatrass(),
      new CornFlakes(),
      new CornFlakes(),
      new CornFlakes(),
    };

    for (Item i : items) {
      System.out.println("когда " + a.lookAt(i) + ", уверенность: " + a.getConfidence());
    }

    System.out.println();

    MigrationServiceEmployee jack = new MigrationServiceEmployee("Jack", "Москва");
    Person[] persons = {
      f,
      new Person("Виталя", "Москва"),
      a,
      new Person("Чингачкук", "Марс"),
      new Person("Сер гей", "Моа"),
      new Person("Серега", "Москва"),
    };

    for (Person p: persons) {
      String s = p.getName() + " путешествует с " + p.getCurrentPlanet();
      p.travelTo(jack.getHomePlanet());
      s += " на " + p.getCurrentPlanet();
      System.out.println(s);
    }

    System.out.println("когда " + a.lookAt(jack) + ", уверенность: " + a.getConfidence());

    for (Person possibleMigrant: persons) {
      MSEReport rep = new MSEReport();
      boolean checkRes = jack.checkPerson(possibleMigrant, rep);
      
      System.out.println(rep.getText());
      if (!checkRes) {
        System.out.println(possibleMigrant.getName() + (rep.willDeparted() ? "" : " не") + " будет депортирован");
      }

      if (possibleMigrant instanceof Arthur) {
        System.out.println("когда " + ((Arthur) possibleMigrant).lookAt(rep) + ", уверенность: " + a.getConfidence());
      }
    }

    CloneMachine cm = new CloneMachine();

    Arthur a2 = cm.copy(a);
    if (a2 != null) {
      System.out.println(a.getName() + " был клонирован с помошью " + cm.toString());
    }

    System.out.println("клонированный " + a2.lookAt(a) + ", его уверенность: " + a2.getConfidence());
    System.out.println("обычный " + a.lookAt(a2) + ", его уверенность: " + a.getConfidence());
    
    Ford f2 = cm.copy(f);
    if (f2 != null) {
      System.out.println(f.getName() + " был клонирован с помошью " + cm.toString());
    }

    System.out.println("клонированный " + f2.getName() + " держит во флаконе " + f2.getVial().getFish().toString());
  }
}
