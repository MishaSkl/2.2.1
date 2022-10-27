package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("volga", 2110);
      Car car2 = new Car("bmv", 2220);
      Car car3 = new Car("mers", 2330);
      Car car4 = new Car("tesla", 2440);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model " + user.getCarUser().getModel());
         System.out.println("Model " + user.getCarUser().getSeries());
         System.out.println(user.getCarUser());
         System.out.println("----------------");
      }

      User carUser = userService.getUserByCar("volga", 2110);
      System.out.println(carUser.toString());


      context.close();
   }
}


/*
* CREATE TABLE mydbtest.users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  last_name varchar(15),
  email varchar(25),
  car_id int, PRIMARY KEY (id)
);

CREATE TABLE mydbtest.car (
  id int NOT NULL AUTO_INCREMENT,
  model varchar(15),
  series int,
  car_id int,
  PRIMARY KEY (id)
, FOREIGN KEY (car_id) REFERENCES mydbtest.users(id));
*
* */