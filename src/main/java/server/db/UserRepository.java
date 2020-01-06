package server.db;
import java.util.List;
import java.util.Iterator;

import errors.IncorrectCredentialsException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import server.Server;

import javax.persistence.NoResultException;

public class UserRepository {
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

    public UserEntity getUser(String username) throws IncorrectCredentialsException {
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        UserEntity user = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity user WHERE user.username=:name");
            user = (UserEntity) query
                    .setParameter("name", username)
                    .getSingleResult();
            tx.commit();
        } catch (NoResultException e) {
            if (tx!=null) tx.rollback();
            session.close();
            throw new IncorrectCredentialsException();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
