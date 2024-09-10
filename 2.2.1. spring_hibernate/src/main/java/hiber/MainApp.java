package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = (new User("User21", "Lastname1", "user1@mail.ru"));
        User user2 = (new User("User2", "Lastname2", "user2@mail.ru"));
        User user3 = (new User("User3", "Lastname3", "user3@mail.ru"));
        User user4 = (new User("User4", "Lastname4", "user4@mail.ru"));

        Car car1 = new Car("model1", 1);
        Car car2 = new Car("model2", 2);
        Car car3 = new Car("model3", 3);
        Car car4 = new Car("model4", 4);

        user1.setUserCar(car1);
        userService.add(user1);

        user2.setUserCar(car2);
        userService.add(user2);

        user3.setUserCar(car3);
        userService.add(user3);

        user4.setUserCar(car4);
        userService.add(user4);

        // вытаскиваем обратно.
        List<User> users = userService.listUsers();
        for (User user : users) {

            System.out.println("USER" + user.getId() + " = " + user.getFirstName()
                    + " " + user.getLastName() + " " + user.getEmail() + "    авто= " + user.getUserCar());
            System.out.println() ;
        }

        // Получение пользователя по авто
        Car car = car2;
        User fineUserByCar = userService.getUserByCar(car);

        System.out.println();
        System.out.println("Пльзователь авто: " + car + " ->" +
                fineUserByCar.getFirstName() + " " + fineUserByCar.getLastName() + " " +
                fineUserByCar.getEmail());

        context.close();
    }
}

