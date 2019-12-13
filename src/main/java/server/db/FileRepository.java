package server.db;

import interfaces.FileDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.Server;

import java.util.Iterator;
import java.util.List;

public class FileRepository {
    private static SessionFactory factory;

    public Integer addFile(FileDTO file){
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        Integer fileId = null;

        try {
            tx = session.beginTransaction();
            FileEntity fileEntity = new FileEntity(file);
            fileId = (Integer) session.save(fileEntity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return fileId;
    }
}
