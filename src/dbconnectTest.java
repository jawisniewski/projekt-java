import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hebo on 08.11.2016.
 */
class dbconnectTest {
    dbconnect db;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
     db= new dbconnect();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

            db.closeConnection();
    }
    @org.junit.jupiter.api.Test
    public void checkConnection(){
        assertNotNull(db);
    }

    @org.junit.jupiter.api.Test
    void createTable() throws Exception {
        assertEquals(true,db.createTable());
    }

    @org.junit.jupiter.api.Test
    void insertCars() {
        Cars car = new Cars("MAN", "2010",201,"");
       boolean test= db.insertCars(car);
       assertEquals(true,test);

    }

    @org.junit.jupiter.api.Test
    void insertRun() {
        Run run = new Run("Poland-England","",2000,2000,5);
        boolean test= db.insertRun(run);
        assertEquals(true,test);

    }

    @org.junit.jupiter.api.Test
    void deleteRun() {
        boolean test=  db.deleteRun( "3" );
        assertEquals(true,test);
    }

    @org.junit.jupiter.api.Test
    void selectCars() {
        Cars cars = new Cars();
        List<Cars> car = db.selectCars();
        if(car.size()==0) {
      //      assertEquals(0, car.size());

            fail("Brak samochodow w bazie danych");

        }
        else{
            assertNotNull(car.size());
        }
    }

    @org.junit.jupiter.api.Test
    void selectRun() {
Run run = new Run();
List<Run> runs = db.selectRun();
assertEquals("gdansk-torun",runs.get(0).getName());
    }

    @org.junit.jupiter.api.Test
    void selectRunWithCar() {
      Run run = new  Run();
      View view = new View();
      List<Run> carsRun= db.selectRunWithCar(2);


         assertNotNull( carsRun.size());
      }



    @org.junit.jupiter.api.Test
    void updateRun() {

    }

    @org.junit.jupiter.api.Test
    void updateRunDelete() {

    }

}