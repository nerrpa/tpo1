package task3.person;

public class Person {
    private String name;
    private String homePlanet;
    private String currentPlanet;

    public Person(String name, String homePlanet) {
        this.name = name;
        this.homePlanet = homePlanet;
        this.currentPlanet = homePlanet;
    }

    public String getName() { return name; }
    public String getHomePlanet() { return homePlanet; }
    public String getCurrentPlanet() { return currentPlanet; }
    public void travelTo(String planet) { this.currentPlanet = planet; }

    @Override
    public String toString() {
        return getName();
    }
}