package hiber;

/*
Условие:
Скачайте заготовку проекта по ссылке.
С работой ядра Спринг мы разобрались, теперь самое время подключить к нему пару модулей для комфортной работы.
Начнем с ORM.
Для работы с hibernate нам понадобится зависимость hibernate-core,
корректным взаимодействием со Спрингом озаботится зависимость spring-orm.
Как вы можете видеть, зависимость spring-core пропала, это произошло из-за того,
что она является транзитной для всех модулей Спринга и дублировать ее смысла нет.
У нас появились пакеты model, service, теперь сервисы и DAO объявлены бинами с помощью аннотаций @Repository и @Service.
В методе main будет происходить тестирование работоспособности нашего приложения.
Класс Car аннотирован как стандартная сущность hibernate.
В AppConfig теперь присутствует базовая настройка hibernate,
берущая данные из файла db.properties.
Обратите внимание, что для ее работы используется аннотация @PropertySource("classpath:db.properties"),
обращающаяся к папке ресурсов.
На этом настройка приложения окончена.

Задание:
1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
3. Добавьте этот класс в настройки hibernate.
4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
 */
import hiber.config.AppConfig;
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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
