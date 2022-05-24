import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testToString() {
//        String evaTest = "Person{Anrede='Frau', Titel='Dr.', Vorname='Eva', Nachname='Müller',           " +
//                "     Organisation=' Hochschule Bonn-Rhein-Sieg', Abteilung=' Fachbereich Informatik',        " +
//                "        Straße='Grantham-Allee', Hausnummer='20', PLZ='53757', Stadt='Sankt Augustin'} ";
        String evaString = "Frau Dr. Eva Müller, Hochschule Bonn-Rhein-Sieg, " +
                "Fachbereich Informatik, Grantham-Allee 20, 53757 Sankt Augustin";
        Person eva = new Person(evaString);
        System.out.println(eva);


        assertEquals("Frau", eva.getAnrede());
        assertEquals("Dr.", eva.getTitel());
        assertEquals("Eva", eva.getVorname());
        assertEquals("Müller", eva.getNachname());
        assertEquals("Hochschule Bonn-Rhein-Sieg", eva.getOrganisation());
        assertEquals("Fachbereich Informatik", eva.getAbteilung());
        assertEquals("Grantham-Allee", eva.getStrasse());
        assertEquals("20", eva.getHausnummer());
        assertEquals("53757", eva.getPLZ());
        assertEquals("Sankt Augustin", eva.getStadt());
    }
}