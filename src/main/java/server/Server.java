package server;

import errors.IncorrectCredentialsException;
import interfaces.FileDTO;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import server.db.FileEntity;
import server.db.UserEntity;
import server.db.UserRepository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Stack;

public class Server {
    static HashMap<FileDTO, Stack<String>> downloadTracker = new HashMap<>();
    public static SessionFactory sessionFactory = new Configuration().
            configure().
            addAnnotatedClass(UserEntity.class).
            addAnnotatedClass(FileEntity.class).
            buildSessionFactory();

    public static void main(String[] args) {
        try {
            UserRepository userRepo = new UserRepository();
            userRepo.addUser("test", "test2019");
            userRepo.addUser("test2", "test2019");
            UserEntity user = userRepo.getUser("test");
            System.out.println(user.getId());
            RMIHandler.initialize();
        } catch (RemoteException | AlreadyBoundException | IncorrectCredentialsException e) {
            System.out.println("Failed to run RMI handler!");
            e.printStackTrace();
        }
    }
}
