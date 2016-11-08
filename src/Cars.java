

public class Cars {
  private int id_cars;
  private String name;
  private String year;
  private double course;
  private String warnings;
public Cars(String name,String year, double course,String warnings){
  super();
  this.name=name;
  this.year=year;
  this.course=course;
  this.warnings=warnings;
}
  public int getId_cars() {
    return id_cars;
  }

  public void setId_cars(int id_cars) {
    this.id_cars = id_cars;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public double getCourse() {
    return course;
  }

  public void setCourse(double course) {
    this.course = course;
  }

  public String getWarnings() {
    return warnings;
  }

  public void setWarnings(String warnings) {
    this.warnings = warnings;
  }
}
