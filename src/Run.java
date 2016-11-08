
public class Run {
  private int id_run;
  private String name;
  private String warnings;
  private Double price;
  private Double distance;
  private int cars_id;
public Run(String name,String warnings,double price, double distance,int cars_id){
  super();
  this.name=name;
  this.warnings=warnings;
  this.price=price;
  this.distance=distance;
  this.cars_id=cars_id;
}
  public int getId_run() {
    return id_run;
  }

  public void setId_run(int id_run) {
    this.id_run = id_run;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWarnings() {
    return warnings;
  }

  public void setWarnings(String warnings) {
    this.warnings = warnings;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public int getCars_id() {
    return cars_id;
  }

  public void setCars_id(int cars_id) {
    this.cars_id = cars_id;
  }
}
