package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    void add(Car car);

    List<Car> listCar();

    List<User> findAllByUserCar(Car car);

    List<User> findUsersByCar(Car car);

    User findUserByCar(Car car);

}
