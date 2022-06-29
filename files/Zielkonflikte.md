
## Zielkonflikte

Während des Projekts kamen folgende, signifikate Probleme auf:

**Keine einheitlichen Daten für Tests**\
*Lösung: Implementierung einer zentralen Klasse für das Lesen und Bereitstellen von Testdaten.*

**Unklarheit über Preisberechnung und Zeitbeschleunigung in der Simulation**\
*Lösung: Systematische Analyse der Parkhaus JavaScript API.*

**Implemention eine Content Securty Policy wegen vielen inline-Scripts problematisch**\
*Lösung: Verwendung von Hashes über Java Script Libaries für ausnahmen (aufgrund von hoher komplexität verworfen).*

**Daten gehen nach einem Server Neustart verloren**\
*Lösung: Implementierung einer persistenten Speicherung im Dateisystem.*

**String Parameter des Autos können potentiell schädlichen Java Script Code enthalten**\
*Lösung: Einsatz einer Filter Funktion für das desinfizieren von String Parametern vor der Ausgabe.*

**Unsere Stats-Methoden in über das Template Methoden Pattern und das Multiton Pattern umgewandelt.\
Am Ende wurde die Pattern zwar als Möglichkeit gesehen, aber es hatte keinen Mehrwert für die Evolvierbarkeit**\
*Lösung: Pattern zurück in Stats-Klasse umgewandelt.*

**Controller als Singleton Problem: Evolvierbarkeit wäre dadurch beschränkt worden, da mehrere Kontroller \
verwendet werden müssten. Somit Singleton nicht anwendbar.**\
*Lösung: Entfernen des Singletons.*