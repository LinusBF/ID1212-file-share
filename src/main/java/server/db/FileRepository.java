package server.db;

import interfaces.FileDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import server.Server;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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

    public FileEntity getFile(String filename){
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        FileEntity file = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM FileEntity file WHERE file.filename=:name");
            file = (FileEntity) query
                    .setParameter("name", filename)
                    .getSingleResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return file;
    }

    public ArrayList<FileEntity> getAllFiles(){
        Session session = Server.sessionFactory.openSession();
        Transaction tx = null;
        ArrayList<FileEntity> files = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            String hql = "SELECT F FROM FileEntity F";
            Query query = session.createQuery(hql);
            List<Object> fs = query.list();
            if(fs.size() > 0){
                System.out.println(fs.get(0));
                for(Object o : fs){
                    FileEntity fe = (FileEntity) o;
                    files.add(fe);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return files;
    }
}
