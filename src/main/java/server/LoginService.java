package server;

import errors.UsernameTakenException;
import interfaces.LoginRemoteAPI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginService extends UnicastRemoteObject implements LoginRemoteAPI {

    protected LoginService() throws RemoteException {
        super();
    }

    @Override
    public boolean registerUser(String username, String password) throws RemoteException, UsernameTakenException {
        System.out.println("Got login request from " + username);
        return false;
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        System.out.println("Got login request from " + username);
        return false;
    }
}
