package hiber;

/*
Задание:
1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
3. Добавьте этот класс в настройки hibernate.
4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
 */

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(new Car("BMW", 8));

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(new Car("Audi", 32));

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(new Car("Cadillac", 16));

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCar(new Car("Chevrolet", 777));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("Cadillac", 16).toString());

        context.close();
    }
}
