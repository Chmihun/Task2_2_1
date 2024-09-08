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
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCar() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().
                createQuery("from Car", Car.class);
        return query.getResultList();
    }

    @Override
    public List<User> findAllByUserCar(Car car) {
//        TypedQuery<User> query = sessionFactory.getCurrentSession()
//                .createQuery("SELECT u FROM User u WHERE u.userCar = :car", User.class);
//        query.setParameter("car", car);
//        return query.getResultList();
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u JOIN FETCH u.userCar WHERE u.userCar = :car", User.class);
        query.setParameter("car", car);
        return query.getResultList();
    }

    @Override
    public List<User> findUsersByCar(Car car) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from User where userCar = :car", User.class)
//                .setParameter("car", car)
//                .list();
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u JOIN FETCH u.userCar WHERE u.userCar = :car", User.class)
                .setParameter("car", car)
                .getResultList();
    }

    @Override
    public User findUserByCar(Car car) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from User where userCar = :car", User.class)
//                .setParameter("car", car)
//                .uniqueResult();
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u JOIN FETCH u.userCar WHERE u.userCar = :car", User.class)
                .setParameter("car", car)
                .uniqueResult();
    }
}
