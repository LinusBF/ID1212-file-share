package interfaces;
import client.ClientController;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Stack;

public interface FileRemoteAPI extends Remote{
    void uploadFile(FileDTO file) throws RemoteException;
    boolean deleteFile(int fid, int userId) throws RemoteException;
    FileDTO[] listAllFiles() throws RemoteException;
    HashMap<FileDTO, Stack<String>> listNotifications(String owner) throws RemoteException;
    FileDTO getFile(String filename, String username) throws RemoteException;
}
