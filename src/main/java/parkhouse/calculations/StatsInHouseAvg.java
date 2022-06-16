package parkhouse.calculations;

public class StatsInHouseAvg extends StatsInHouseSum{

    @Override
    long calcExtra(int sizeCarsList, long sum) {
        return sum/sizeCarsList;
    }
}
