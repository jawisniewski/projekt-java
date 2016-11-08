import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hebo on 07.11.2016.
 */
public class dbconnect {


    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/firma_transportowa";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection Conn;
    private Statement Stat;
    public  String columnName ;
    private PreparedStatement insertCars;

    private PreparedStatement insertRun;
    private PreparedStatement deleteRun;

    private PreparedStatement deleteCars;
    private PreparedStatement ViewCars;

    private PreparedStatement ViewRun;

    public dbconnect() {
        try {
            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Conn=DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
            Stat=Conn.createStatement();
            createTable();
            insertRun= Conn.prepareStatement("insert INTO run(name, distance, price, warnings, cars_id) VALUES (?,?,?,?,?);");
            insertCars= Conn.prepareStatement("insert INTO cars(name, year,  course, warnings) VALUES (?,?,?,?);");
            deleteCars=Conn.prepareStatement("DELETE FROM cars WHERE  '?'=?");
            deleteRun=Conn.prepareStatement("DELETE FROM  run WHERE   id_run =?");
            ViewCars = Conn.prepareStatement("Select * FROM cars");
             ViewRun= Conn.prepareStatement("Select * FROM run");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

        public void createTable(){
            String createCars="CREATE TABLE IF NOT EXISTS cars(" +
                    "id_cars  INT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                    "name VARCHAR(255) NOT NULL," +
                    "Course int NOT NULL, " +
                    "year year NOT NULL, " +
                    "warnings VARCHAR(255))";
            String createRun="CREATE TABLE IF NOT EXISTS run(" +
                    "id_run  INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price double NOT NULL, " +
                    "cars_id int NOT NULL," +
                    "distance double NOT NULL," +
                    "warnings VARCHAR(255))";
            String alterTableRun= "ALTER TABLE run ADD FOREIGN KEY (cars_id) REFERENCES cars(id_cars)";
            try {
                Stat.execute(createCars);
                Stat.execute(createRun);
                Stat.execute(alterTableRun);
            } catch (SQLException e) {
                System.err.println("Blad przy tworzeniu tabeli");
                e.printStackTrace();
            }

        }
    public void insertCars(Cars cars){
        try {
           // String name,String year, double course,String warnings

            insertCars.setString(1, cars.getName());
            insertCars.setString(2, cars.getYear());
            insertCars.setDouble(3, cars.getCourse());

            insertCars.setString(4, cars.getWarnings());

            insertCars.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void insertRun(Run run) {
        try {
      //      insert.setString(1,"run");
        //    insert.setString(2,"name, distance, price, warnings, cars_id");
            insertRun.setString(1, run.getName());
            insertRun.setDouble(2, run.getDistance());
            insertRun.setDouble(3, run.getPrice());
            insertRun.setString(4, run.getWarnings());

            insertRun.setInt(5, run.getCars_id());
            insertRun.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRun(String parameter) {
        try {

     //   deleteRun.setString(1,column);
           // deleteRun.setString(1,column);
       // columnName=column;
        deleteRun.setString(1,parameter);
        deleteRun.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Cars> CarsSelect(){

        List<Cars> Car=new ArrayList<Cars>();
        try {
            ResultSet resultSet = ViewCars.executeQuery();
            while (resultSet.next()) {
                Cars auto=new Cars();
               // auto.setId_cars(resultSet.getInt("id_car"));
                auto.setName(resultSet.getString("name"));

                auto.setYear(resultSet.getString("year"));
                auto.setCourse(resultSet.getDouble("course"));
                auto.setWarnings(resultSet.getString("warnings"));


                Car.add(auto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Car;
    }
    public List<Run> Run(){

        List<Run> Runs=new ArrayList<Run>();
        try {
            ResultSet resultSet = ViewRun.executeQuery();
            while (resultSet.next()) {
                Run run=new Run();
                run.setId_run(resultSet.getInt("id_run"));
                run.setName(resultSet.getString("name"));
                run.setDistance(resultSet.getDouble("distance"));
                run.setPrice(resultSet.getDouble("price"));
                run.setWarnings(resultSet.getString("warnings"));
                run.setCars_id(resultSet.getInt("cars_id"));


                Runs.add(run);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Runs;
    }

    }



