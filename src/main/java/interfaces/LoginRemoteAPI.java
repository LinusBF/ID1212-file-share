package interfaces;

import errors.UsernameTakenException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginRemoteAPI extends Remote {
    Integer registerUser(String username, String password) throws RemoteException, UsernameTakenException;
    boolean login(String username, String password) throws RemoteException;
}
