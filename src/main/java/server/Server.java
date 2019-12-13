package server;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import server.db.FileEntity;
import server.db.UserEntity;
import server.db.UserRepository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Server {
    public static SessionFactory sessionFactory = new Configuration().
            configure().
            addAnnotatedClass(UserEntity.class).
            addAnnotatedClass(FileEntity.class).
            buildSessionFactory();

    public static void main(String[] args) {
        try {
            UserRepository userRepo = new UserRepository();
            userRepo.addUser("test", "test2019");
            UserEntity user = userRepo.getUser("test");
            System.out.println(user.getId());
            RMIHandler.initialize();
        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println("Failed to run RMI handler!");
            e.printStackTrace();
        }
    }
}
