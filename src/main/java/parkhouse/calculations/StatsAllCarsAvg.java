package parkhouse.calculations;

public class StatsAllCarsAvg extends StatsAllCarsSum {

    @Override
    long calcExtra(int sizeCarsList, long sum) {
        return sum/sizeCarsList;
    }

}



