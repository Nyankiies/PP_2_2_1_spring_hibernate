package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car mazda = new Car("Mazda",6);
      Car uaz = new Car("Uaz", 3);
      Car lada = new Car("Lada", 1);
      Car bmw = new Car("BMW", 3);
      carService.add(mazda);
      carService.add(uaz);
      carService.add(lada);
      carService.add(bmw);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", mazda));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", uaz));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", lada));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", bmw));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car =" +user.getEmpCar());
         System.out.println();
      }
      System.out.println("\nUser by Car "+ mazda + " - " + userService.getUserByCar(mazda));

      context.close();
   }
}
