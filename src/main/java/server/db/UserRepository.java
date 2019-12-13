package server.db;
import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import server.Server;

public class UserRepository {
    private static SessionFactory factory;

    public Integer addUser(String username, String password){
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        Integer userId = null;

        try {
            tx = session.beginTransaction();
            UserEntity userEntity = new UserEntity(username, password);
            userId = (Integer) session.save(userEntity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userId;
    }

    public UserEntity getUser(String username){
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        UserEntity user = null;

        try {
            tx = session.beginTransaction();
            user = (UserEntity) session.createQuery("FROM user WHERE username=" + username).getSingleResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
