package parkhouse.calculations;

import parkhouse.controller.IParkingController;

public class StatsSum extends AbstractStats {



    @Override
    double calcExtra(IParkingController controller, double sum) {
        return sum;
    }
    void optionalPrintSthOrSo(IParkingController controller, double sum){
//        System.out.println("In Sum, yes in avg not used--I could print sth or give to out.println-<JS");
    }
}
