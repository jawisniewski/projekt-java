import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        dbconnect d=new dbconnect();

        Cars cars1 = new Cars("MAN TGA","2010",200000,"brak");

         Run run1 = new Run("gdansk-torun","Brak", 200, 4000,1);
      // d.insertCars(cars1);

     //  d.insertRun(run1);
      //  d.closeConnection();
    //   d.deleteRun( "3" );
        d.selectCars();
    }




}
