package server;

import errors.IncorrectCredentialsException;
import errors.UsernameTakenException;
import interfaces.LoginRemoteAPI;
import server.db.UserEntity;
import server.db.UserRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginService extends UnicastRemoteObject implements LoginRemoteAPI {
    private UserRepository userRepo;

    LoginService() throws RemoteException {
        super();
        userRepo = new UserRepository();
    }

    @Override
    public Integer registerUser(String username, String password) throws RemoteException, UsernameTakenException {
        System.out.println("Got register request with username" + username);
        return this.userRepo.addUser(username, password);
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        System.out.println("Got login request from " + username);
        UserEntity user;
        try{
            user = this.userRepo.getUser(username);
        } catch (IncorrectCredentialsException e){
            return false;
        }
        return user != null && user.getPassword().equals(password);
    }
}
