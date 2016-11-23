package car;

import java.util.List;

/**
 * Created by Hebo on 08.11.2016.
 */
public class View {
    dbconnect db=new dbconnect();

    public void getCars(){
        List<Cars> Listcar ;
Listcar=db.selectCars();
//db.closeConnection();
viewCars(Listcar);

    }

    public void viewCars(List<Cars> Listcar){

        for (Cars cars:Listcar) {
            System.out.println(cars.getId_cars());
            System.out.println(cars.getName());
            System.out.println(cars.getYear());
            System.out.println(cars.getCourse());
            System.out.println(cars.getWarnings());
        }
    } public void getRun(){
        List<Run> ListRun ;
        ListRun=db.selectRun();
   //     db.closeConnection();
        viewRun(ListRun);

    }public void getCarRun(int Car_id){

        List<Run> CarRun ;

           CarRun =db.selectRunWithCar(Car_id);

       // db.closeConnection();
        viewRun(CarRun);
    }

    public void viewRun(List<Run> ListRun){

        for (Run run:ListRun) {
            System.out.println(run.getId_run());
            System.out.println(run.getName());
            System.out.println(run.getDistance());
            System.out.println(run.getPrice());
            System.out.println(run.getWarnings());
            System.out.println(run.getCars_id());
        }
    }
}
