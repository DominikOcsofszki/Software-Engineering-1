
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
