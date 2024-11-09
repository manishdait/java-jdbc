public class Alien {
  private int id;
  private String name;
  private Tech tech;

  public Alien() {}
  
  public Alien(int id, String name, Tech tech) {
    this.id = id;
    this.name = name;
    this.tech = tech;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tech getTech() {
    return tech;
  }

  public void setTech(Tech tech) {
    this.tech = tech;
  }

  @Override
  public String toString() {
    return "Alien [id=" + id + ", name=" + name + ", tech=" + tech + "]";
  }
}
