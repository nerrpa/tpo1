package task3.person;

import task3.item.MSEReport;

public class MigrationServiceEmployee extends Person {

  public MigrationServiceEmployee(String name, String homePlanet) {
    super(name, homePlanet);
  }
  
  public boolean checkPerson(Person person, MSEReport report) {
    String ans = getName() + " проверяет " + person.getName() + ":\n";
    boolean cond = getHomePlanet() == person.getHomePlanet();
    if (!cond) {
      ans += person.getName() + 
      " не с планеты " + getHomePlanet() + 
      ", а с планеты " + person.getHomePlanet();
    } else {
      ans += person.getName() + 
      " с планеты " + getHomePlanet() +
      ", всё ок";
    }

    report.evalReport(ans, cond, person.getName());
    return cond;
  }
}