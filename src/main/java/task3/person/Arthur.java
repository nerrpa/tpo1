package task3.person;

import task3.item.CornFlakes;
import task3.item.MSEReport;

public class Arthur extends Person {
    private double confidence = (Math.random() * 60);
    private boolean blinking = false;

    public Arthur() {
        super("Артур", "Земля");
    }

    public String lookAt(Object object) {
        blink();

        if (object instanceof CornFlakes) {
            confidence = CornFlakes.confidenceBoost(confidence);
        }
        if (object instanceof MigrationServiceEmployee) {
            confidence = 0;
        }
        if (object instanceof MSEReport) {
            confidence = ((MSEReport)object).willDeparted() ? 0: 100;
        }

        return getName() + " смотрит на " + object.toString();
    }

    private void blink() {
        blinking = true;
    }

    public boolean isBlinking() { return blinking; }
    public void resetBlink() { blinking = false; }

    public double getConfidence() { return confidence; }

}