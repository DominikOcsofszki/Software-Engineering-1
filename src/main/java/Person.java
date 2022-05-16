import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Person {
    //"0_Frau Dr. Eva Müller, 1_Hochschule Bonn-Rhein-Sieg,
    // 2_Fachbereich Informatik, 3_Grantham-Allee 20, 4_53757 Sankt Augustin"
    private String Anrede;
    private String Titel;
    private String Vorname;
    private String Nachname;
    private String Organisation;
    private String Abteilung;
    private String Strasse;
    private String Hausnummer;
    private String PLZ;
    private String Stadt = "";
    public Person(String input) {
        String[] params = input.split("\\s*,\\s*");
        System.out.println(Arrays.toString(params));

        String[] names = params[0].split(" ");
        this.Anrede = names[0];
        this.Titel = names[1];
        this.Vorname = names[2];
        this.Nachname = names[3];
        this.Organisation = params[1];
        this.Abteilung = params[2];
        String[] strasse_nr = params[3].split(" ");
        this.Strasse = strasse_nr[0]; //
        this.Hausnummer = strasse_nr[1];
        String[] plzStadt = params[4].split(" ");
        System.out.println(Arrays.toString(plzStadt));
        this.PLZ = plzStadt[0];
//        this.Stadt = plzStadt[1]+" "+ plzStadt[2] ; // TODO Hardcoded...
        for (int i = 1; i < plzStadt.length; i++) {
            if(i != 1) this.Stadt += " ";
            this.Stadt += plzStadt[i];
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "Anrede='" + Anrede + '\'' +
                ", Titel='" + Titel + '\'' +
                ", Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", \nOrganisation='" + Organisation + '\'' +
                ", Abteilung='" + Abteilung + '\'' +
                ", \nStraße='" + Strasse + '\'' +
                ", Hausnummer='" + Hausnummer + '\'' +
                ", PLZ='" + PLZ + '\'' +
                ", Stadt='" + Stadt + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String evaString = "Frau Dr. Eva Müller, Hochschule Bonn-Rhein-Sieg, " +
                "Fachbereich Informatik, Grantham-Allee 20, 53757 Sankt Augustin";
        Person eva = new Person(evaString);
        System.out.println(eva);
    }

    public String getAnrede() {
        return Anrede;
    }

    public String getTitel() {
        return Titel;
    }

    public String getVorname() {
        return Vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public String getOrganisation() {
        return Organisation;
    }

    public String getAbteilung() {
        return Abteilung;
    }

    public String getStrasse() {
        return Strasse;
    }

    public String getHausnummer() {
        return Hausnummer;
    }

    public String getPLZ() {
        return PLZ;
    }

    public String getStadt() {
        return Stadt;
    }

}
