package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {

        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userCar", User.class)
                .getResultList();
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findUserByCar(Car car) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u JOIN FETCH u.userCar WHERE u.userCar = :car", User.class)
                .setParameter("car", car)
                .uniqueResult();
    }
}
