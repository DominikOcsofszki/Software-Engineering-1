
## Eigenleistungen

Eigenleistungen der einzelnen Teammitglieder.

### Team

- ParkhouseServlet
- MainServlet
- Config
- index.jsp
- pom.xml
- style.css

---

### Dominik Ocsofszki

**Interfaces:**

- IParkingModel

**Klassen:**

- Locator
- ParkingModel
  - addRemovedCar
  - earningHelper
  - dailyEarnings
  - weeklyEarnings
  - getCarList
- Finder
- Saver

**Tests:**

- ParkingControllerTest
- SaverTest
- CurrentCostsViewTest

---

### Jakob Stühn

**Interfaces:**

- ICar
- IParkingController
- IObservable
- IParkingModel
- IObserver

**Klassen:**

- Locator
- Price
- Car
- CarDecorator
- CarLeaveCommand
- Commander
- ParkingController
- ParkingModel
  - registerObserver
  - removeObserver
  - notifyObservers
  - addCar
  - removeCar
  - currentCost
  - getAllCars
- SanitizedCar
- SecurityCORSFilter
- Finder
- Jsonify
- Saver
- Tableize
- Time
  - now
  - simTime
  - simNow
- ClientCategoriesView
- CurrentCostsView
- DailyEarningsView
- DurationView
- EarningsByCategoriesView
- VehicleTypesView
- WeeklyEarningsView
- Data

**Tests:**

- LocatorTest
- PriceTest
- CarTest
- FinderTest
- JsonifyTest
- SaverTest
- TableizeTest
- ClientCategoriesViewTest
- DurationViewTest
- EarningsByCategoriesViewTest
- VehicleTypesTest

---

### Sam Taheri

**Klassen:**

- CarTypes

**Tests:**

- CarTypesTest
- TimeTest
- WeeklyEarningsViewTest

---

### Tobias Papen

**Interfaces:**

- ICommand
- IParkingModel

**Klassen:**

- Stats
- CarEnterCommand
- ParkingModel
  - deleteCar
  - getRemovedCarList
- Time
  - diff

**Tests:**

- StatsTest
- CarEnterLeaveCommandTest
- CommanderTest
- ParkingModelTest
- DailyEarningsViewTest

---

### Lines-Of-Code

Aufgrund von Bedenken wegen ungenauer Ergebnisse durch Cloc, wurde zusätzlich eine manuelle Zählung durchgeführt.\
Als Zeilenanzahl einer Klasse wurde die Ausgabe von SonarQube verwendet.

|             | Dominik Ocsofszki | Jakob Stühn | Sam Taheri | Tobias Papen |
|-------------|-------------------|-------------|------------|--------------|
| **Cloc**    | 0                 | 0           | 0          | 0            |
| **Manuell** | 0                 | 0           | 0          | 0            |