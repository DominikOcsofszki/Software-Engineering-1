
## Eigenleistungen

Eigenleistungen der einzelnen Teammitglieder.

### Team

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

- ParkhouseServlet:
  - doGet
    - config
    - Sum
    - Avg
  - doPost
    - change_max
    - change_open_from
    - change_open_to
    - enter
    - leave
  - parkingController
- Locator
- ParkingModel
  - addRemovedCar
  - earningHelper
  - dailyEarnings
  - weeklyEarnings
  - getCarList
- Stats
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

- ParkhouseServlet:
  - doGet
    - cars
    - Min
    - Max
    - Types
    - Categories
    - Durations
    - Daily-Earnings
    - Weekly-Earnings
    - Current-Cost
    - Earnings-Categories
    - Reset
  - doPost
    - change_max
    - change_open_from
    - change_open_to
    - enter
    - leave
  - saver
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

- ParkhouseServlet:
  - doGet
    - Time
- CarTypes

**Tests:**

- CarTypesTest
- TimeTest
- WeeklyEarningsViewTest

---

### Tobias Papen

**Interfaces:**

- ParkhouseServlet:
  - doGet
    - Undo
    - default
  - doPost
    - invalid/occupied
    - default
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
- CurrentCostViewTest

---

### Lines-Of-Code

|             | Dominik Ocsofszki | Jakob Stühn | Sam Taheri | Tobias Papen |
|-------------|------------------|-------------|------------|--------------|
| **Cloc**    | 238                 | 2655           | 157          | 678            |

[Version](https://github.com/casperdcl/git-fame)

*git-fame --incl="java|xml|jsp|css"*
