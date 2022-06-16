package parkhouse.calculations;

public class StatsRemovedAvg extends StatsRemovedSum{

    @Override
    long calcExtra(int sizeCarsList, long sum) {
        return sum/sizeCarsList;
    }
}
