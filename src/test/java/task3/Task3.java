package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task3.fish.Fish;
import task3.fish.YellowFish;
import task3.item.*;
import task3.person.Arthur;
import task3.person.MigrationServiceEmployee;
import task3.person.Person;

import static org.junit.jupiter.api.Assertions.*;

public class Task3 {

    @Nested
    class ArthurConfidenceTest {
        private Arthur arthur;

        @BeforeEach
        void setUp() {
            arthur = new Arthur();
        }

        @Test
        void initialConfidenceIsRandomInRange0to60() {
            double conf = arthur.getConfidence();
            assertTrue(conf >= 0 && conf < 60, "уверенность между [0,60)");
        }

        @Test
        void lookAtCornFlakesAppliesFormulaCorrectly() {
            double initial = arthur.getConfidence();
            double expected = Math.min(Math.pow(initial / 10, 2) + initial * Math.PI * 3 / 10 + 1, 100);
            arthur.lookAt(new CornFlakes());
            assertEquals(expected, arthur.getConfidence(), 1e-9);
        }

        @Test
        void multipleCornFlakesApplications() {
            double c1 = arthur.getConfidence();
            arthur.lookAt(new CornFlakes());
            double c2 = arthur.getConfidence();
            arthur.lookAt(new CornFlakes());
            double c3 = arthur.getConfidence();

            double expected2 = Math.min(Math.pow(c1 / 10, 2) + c1 * Math.PI * 3 / 10 + 1, 100);
            assertEquals(expected2, c2, 1e-9);
            double expected3 = Math.min(Math.pow(c2 / 10, 2) + c2 * Math.PI * 3 / 10 + 1, 100);
            assertEquals(expected3, c3, 1e-9);
        }

        @Test
        void lookAtMigrationServiceEmployeeResetsConfidenceToZero() {
            arthur.lookAt(new CornFlakes());
            assertTrue(arthur.getConfidence() > 0);
            arthur.lookAt(new MigrationServiceEmployee("Мыкола", "Украина"));
            assertEquals(0, arthur.getConfidence());
        }

        @Test
        void lookAtMSEReportWithWillDepartedTrueSetsConfidenceToZero() {
            MSEReport report = new MSEReport();
            report.evalReport("aladfvlqer lfelrpflerpflfl", false, "N N");
            assertTrue(report.willDeparted());

            arthur.lookAt(report);
            assertEquals(0, arthur.getConfidence());
        }

        @Test
        void lookAtMSEReportWithWillDepartedFalseSetsConfidenceTo100() {
            MSEReport report = new MSEReport();
            report.evalReport("asdfkqlrkqrqewglqw", true, "NN");
            assertFalse(report.willDeparted());

            arthur.lookAt(report);
            assertEquals(100, arthur.getConfidence());
        }

        @Test
        void lookAtNullThrowsNullPointerException() {
            assertThrows(NullPointerException.class, () -> arthur.lookAt(null));
        }
    }

    @Nested
    class CloneMachineTest {
        private CloneMachine cm;

        @BeforeEach
        void setUp() {
            cm = new CloneMachine();
        }

        @Test
        void copyObjectWithNoDefaultConstructorReturnsNull() {
            Person person = new Person("NN", "Польша");
            assertNull(cm.copy(person));

            Fish fish = new Fish("степан");
            assertNull(cm.copy(fish));

            GlassVial vial = new GlassVial(fish);
            assertNull(cm.copy(vial));
        }

        @Test
        void copyObjectWithDefaultConstructorCreatesNewInstance() {
            CornFlakes original = new CornFlakes();
            CornFlakes copy = cm.copy(original);
            assertNotNull(copy);
            assertNotSame(original, copy);
            assertEquals(original.toString(), copy.toString());

            DentrassiUnderwear originalUnderwear = new DentrassiUnderwear();
            DentrassiUnderwear copyUnderwear = cm.copy(originalUnderwear);
            assertNotNull(copyUnderwear);
            assertNotSame(originalUnderwear, copyUnderwear);
            assertEquals(originalUnderwear.toString(), copyUnderwear.toString());

            ScvorshneineMatrass originalMatrass = new ScvorshneineMatrass();
            ScvorshneineMatrass copyMatrass = cm.copy(originalMatrass);
            assertNotNull(copyMatrass);
            assertNotSame(originalMatrass, copyMatrass);
            assertEquals(originalMatrass.toString(), copyMatrass.toString());

            MSEReport originalReport = new MSEReport();
            MSEReport copyReport = cm.copy(originalReport);
            assertNotNull(copyReport);
            assertNotSame(originalReport, copyReport);
            assertEquals(originalReport.getText(), copyReport.getText());
            assertEquals(originalReport.willDeparted(), copyReport.willDeparted());
        }

        @Test
        void copyOfCornFlakesIsIndependent() {
            CornFlakes original = new CornFlakes();
            CornFlakes copy = cm.copy(original);
            assertNotSame(original, copy);
        }
    }

    @Nested
    class MSEReportTest {
        @Test
        void evalReportSetsTextAndWillDepartedCorrectly() {
            MSEReport report = new MSEReport();
            report.evalReport("Салам", false, "Але кум");
            assertEquals("Салам", report.getText());
            assertTrue(report.willDeparted());

            report.evalReport("Мир", true, "Тебе");
            assertEquals("Мир", report.getText());
            assertFalse(report.willDeparted());

            report.evalReport("", false, "14фз88");
            assertFalse(report.willDeparted());
        }
    }

    @Nested
    class MigrationServiceEmployeeTest {
        private MigrationServiceEmployee mse;
        private Person earthPerson;
        private Person marsPerson;

        @BeforeEach
        void setUp() {
            mse = new MigrationServiceEmployee("поли", "карбонат");
            earthPerson = new Person("у", "карбонат");
            marsPerson = new Person("а", "у");
        }

        @Test
        void checkPersonWithSamePlanetReturnsTrueAndReportText() {
            MSEReport report = new MSEReport();
            boolean result = mse.checkPerson(earthPerson, report);
            assertTrue(result);
            String expectedText = "поли проверяет у:\nу с планеты карбонат, всё ок";
            assertEquals(expectedText, report.getText());
            assertFalse(report.willDeparted());
        }

        @Test
        void checkPersonWithDifferentPlanetReturnsFalseAndReportText() {
            MSEReport report = new MSEReport();
            boolean result = mse.checkPerson(marsPerson, report);
            assertFalse(result);
            String expectedText = "поли проверяет а:\nа не с планеты карбонат, а с планеты у";
            assertEquals(expectedText, report.getText());
            assertFalse(report.willDeparted());
        }

        @Test
        void checkPersonWithNameContainingSpaceSetsWillDepartedTrue() {
            Person personWithSpace = new Person("N N", "срам");
            MSEReport report = new MSEReport();
            mse.checkPerson(personWithSpace, report);
            assertTrue(report.willDeparted());
        }

        @Test
        void checkPersonWithSamePlanetAndNameWithSpaceDoesNotSetWillDeparted() {
            Person personWithSpace = new Person("З е", "мля");
            MSEReport report = new MSEReport();
            mse.checkPerson(personWithSpace, report);
            assertTrue(report.willDeparted());
        }
    }

    @Nested
    class PersonTravelTest {
        @Test
        void travelToChangesCurrentPlanet() {
            Person p = new Person("ественник", "Земля");
            assertEquals("Земля", p.getCurrentPlanet());
            p.travelTo("Марс");
            assertEquals("Марс", p.getCurrentPlanet());
        }
    }

    @Nested
    class FishBehaviorTest {
        @Test
        void fishToStringAfterChangingFlags() {
            Fish fish = new Fish("жоски");
            fish.swimming = false;
            fish.shimmering = true;
            assertEquals("жоски рыба, которая не плавает и переливается", fish.toString());

            fish.swimming = true;
            fish.shimmering = false;
            assertEquals("жоски рыба, которая плавает и не переливается", fish.toString());
        }

        @Test
        void yellowFishIsYellowAndDefaults() {
            YellowFish yellow = new YellowFish();
            assertEquals("желтый", yellow.getColor());
            assertTrue(yellow.swimming);
            assertTrue(yellow.shimmering);
        }
    }

    @Nested
    class GlassVialTest {
        @Test
        void constructorAcceptsNullFish() {
            GlassVial vial = new GlassVial(null);
            assertNull(vial.getFish());
            assertEquals("стеклянный флакон", vial.toString());
        }
    }

    @Nested
    class ItemConfidenceBoostTest {
        @Test
        void itemConfidenceBoostReturnsSameValue() {
            assertEquals(10.0, Item.confidenceBoost(10.0));
            assertEquals(0.0, Item.confidenceBoost(0.0));
            assertEquals(-5.0, Item.confidenceBoost(-5.0));
        }

        @Test
        void cornFlakesConfidenceBoostAppliesFormula() {
            assertEquals(1.0, CornFlakes.confidenceBoost(0.0));
            double c = 50.0;
            double expected = Math.min(Math.pow(c/10, 2) + c * Math.PI * 3 / 10 + 1, 100);
            assertEquals(expected, CornFlakes.confidenceBoost(c));
            assertEquals(100.0, CornFlakes.confidenceBoost(200.0));

            assertTrue(CornFlakes.confidenceBoost(14.0) > 14.0);
            assertTrue(CornFlakes.confidenceBoost(-15.0) < 0);
        }
    }
}