

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import car.dbconnect;

import car.dbconnect;
import car.View;
import car.View;
import db.Cars;
import db.Run;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Hebo on 08.11.2016.
 */
public class dbconnectTest {
    dbconnect db;
    @Before
    public void setUp() {
        db= new dbconnect();
    }

    @After
    public void tearDown() {

        db.closeConnection();
    }
    @Test
    public void checkConnection(){
        assertNotNull(db);
    }

    @Test
    public void createTable() throws Exception {
        db.dropTable();
        assertEquals(true,db.createTable());

    }
    @Test
    public void dropTable() throws Exception {
        assertEquals(true,db.dropTable());

    }

    @Test
    public void insertCars() {
        db.dropTable();
        db.createTable();
        Cars car = new Cars("MAN", "2010",20100,"engine broken");

        Cars car2 = new Cars("DAF", "2015",201,"");

        db.insertCars(car);

        db.insertCars(car2);
        List<Cars> cars = db.selectCars();
        assertEquals("2010-01-01",cars.get(0).getYear());
        assertEquals("MAN",cars.get(0).getName());
//        assertEquals(20100,cars.get(0).getCourse());
        assertEquals("engine broken",cars.get(0).getWarnings());
        assertEquals("2015-01-01",cars.get(1).getYear());
        assertEquals("DAF",cars.get(1).getName());
//        assertEquals(201,cars.get(1).getCourse());
        assertEquals("",cars.get(1).getWarnings());


    }

    @Test
    public void insertRun() {
        double price=2000,distance=2000;
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        Run run = new Run("Poland-England","",2000,2000,1);
        boolean test= db.insertRun(run);
        assertEquals(true,test);

        List<Run> runs = db.selectRun();
        assertEquals("Poland-England", runs.get(0).getName());
        assertEquals("", runs.get(0).getWarnings());
        assertEquals(1, runs.get(0).getCars_id());
    }

    @Test
    public void deleteRun() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");
        Cars cars2 = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        db.insertCars(cars2);
        Run run = new Run("Poland-England","",2000,2000,1);
        Run run2 = new Run("Poland-England","",2000,2000,1);
        Run run3= new Run("Poland-France","",2000,1500,2);
// sprawdzic konkretnie usuniety element
        db.insertRun(run);

        db.insertRun(run2);

        db.insertRun(run3);

        List<Run> runs = db.selectRun();
        assertEquals(3,runs.size());
        boolean test=  db.deleteRun( "1" );
        assertEquals(true,test);

        runs = db.selectRun();

        assertEquals(1,runs.size());

        assertEquals("Poland-France", runs.get(0).getName());
        assertEquals("", runs.get(0).getWarnings());
        assertEquals(2, runs.get(0).getCars_id());
    }

    @Test
    public void selectCars() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"engine broken");
        List<Cars> car2 = db.selectCars();
        assertEquals(0,car2.size());
        db.insertCars(cars);
        car2.addAll(db.selectCars());
        //  List<Cars> car = db.selectCars();

        assertNotEquals(0,car2.size());

        assertEquals("2010-01-01",car2.get(0).getYear());
        assertEquals("MAN TGA",car2.get(0).getName());
//        assertEquals(200000,car2.get(0).getCourse());
        assertEquals("engine broken",car2.get(0).getWarnings());

    }

    @Test
    public void selectRun() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        Run run = new Run("Poland-England","",2000,2000,1);
        db.insertRun(run);
        Run run2 = new Run("Poland-France","",1500,2000,1);
        db.insertRun(run2);

        List<Run> runs = db.selectRun();
        assertEquals(2,runs.size());
        assertEquals("Poland-England",runs.get(0).getName());

        assertEquals("Poland-France",runs.get(1).getName());

    }

    @Test
    public void selectRunWithCar() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");

        Cars cars2 = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        db.insertCars(cars2);
        Run run = new Run("Poland-England","",2000,2000,1);
        Run run2 = new Run("Poland-France","",2000,2000,1);
        Run run3 = new Run("Poland-France","",2000,2000,2);

        db.insertRun(run);

        db.insertRun(run2);

        db.insertRun(run3);


        List<Run> carsRun= db.selectRunWithCar(1);


        assertEquals(2, carsRun.size());


        assertEquals("Poland-England", carsRun.get(0).getName());

        //assertEquals(2000,carsRun.get(0).getPrice());

        assertEquals("", carsRun.get(0).getWarnings());
        assertEquals(1, carsRun.get(0).getCars_id());

        assertEquals("Poland-France", carsRun.get(1).getName());
        assertEquals("", carsRun.get(1).getWarnings());
        assertEquals(1, carsRun.get(1).getCars_id());
    }



    @Test
    public void updateRun() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        Cars cars2 = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars2);
        Run run = new Run("Poland-England","",2000,2000,1);
        db.insertRun(run);

        Boolean test=     db.updateRun(2,1);
        List<Run> runs = db.selectRun();
        assertEquals(2,runs.get(0).getCars_id());
        //konkretnie co zmienilo sprawdzic i czy nie zmienilo sie inna wartosc
    }

    @Test
    public void updateRunDelete() {
        db.dropTable();
        db.createTable();
        Cars cars = new Cars ("MAN TGA","2010",200000,"");
        db.insertCars(cars);
        Cars cars2 = new Cars ("MAN TGX","2015",200000,"");
        db.insertCars(cars2);
        Run run = new Run("Poland-England","",2000,2000,1);
        db.insertRun(run);


        List<Run> carsRun= db.selectRunWithCar(1);
        assertEquals(1,carsRun.size());
        db.updateRunDelete(1);

        carsRun= db.selectRunWithCar(1);
        assertEquals(0,carsRun.size());
        Run run2 = new Run("Poland-France","",2000,2000,1);
        db.insertRun(run2);
        Run run3 = new Run("Poland-Greecce","",2000,4000,1);
        db.insertRun(run3);
        Run run4 = new Run("Poland-Greecce","",2000,4000,2);
        db.insertRun(run4);

        carsRun= db.selectRunWithCar(1);
        assertEquals(2,carsRun.size());
        db.updateRunDelete(1);

        carsRun= db.selectRunWithCar(1);
        assertEquals(0,carsRun.size());

        carsRun= db.selectRunWithCar(2);
        assertEquals(1,carsRun.size());
    }

}