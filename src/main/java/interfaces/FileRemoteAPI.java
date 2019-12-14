package interfaces;
import client.ClientController;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileRemoteAPI extends Remote{
    void uploadFile(FileDTO file) throws RemoteException;
    boolean deleteFile(int fid, int userId) throws RemoteException;
    FileDTO[] listAllFiles() throws RemoteException;
    FileDTO getFile(String filename, String username) throws RemoteException;
}
