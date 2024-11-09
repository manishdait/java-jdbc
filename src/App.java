import java.sql.SQLException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws SQLException {
    Scanner scan = new Scanner(System.in);
    AlienService alienService = new AlienService();

    while (true) {
      System.out.println("------ Alien Dashboard ------");
      System.out.println("Options:");
      System.out.println("l : List all aliens.");
      System.out.println("s : Get alien by id.");
      System.out.println("i : Add new alien.");
      System.out.println("u : Update alien.");
      System.out.println("d : remove alien");
      System.out.println("q : Quit.");

      System.out.print("Enter Option: ");
      char ch = scan.next().charAt(0);

      if (ch == 'l') {
        System.out.println("\n" + alienService.findAll());
      }

      if (ch == 's') {
        System.out.print("Enter id: ");
        int id = scan.nextInt();

        System.out.println("\n" + alienService.findById(id));
      }

      if (ch == 'i') {
        scan.nextLine();
        System.out.print("Enter name: ");
        String name = scan.nextLine();

        System.out.print("Enter tech: ");
        String tech = scan.nextLine();

        Alien alien = new Alien(0, name, Tech.valueOf(tech));

        System.out.println("\n" + alienService.save(alien));
      }

      if (ch == 'u') {
        System.out.print("Enter id: ");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter name: ");
        String name = scan.nextLine();

        System.out.print("Enter tech: ");
        String tech = scan.nextLine();

        Alien alien = new Alien(id, name, Tech.valueOf(tech));

        System.out.println("\n" + alienService.update(id, alien));
      }

      if (ch == 'd') {
        System.out.print("Enter id: ");
        int id = scan.nextInt();

        System.out.println("\n" + alienService.delete(id));
      }

      if (ch == 'q') {
        break;
      }
    }

    scan.close();
  }
}
