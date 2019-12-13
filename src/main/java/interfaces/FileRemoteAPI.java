package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface FileRemoteAPI extends Remote{
    void uploadFile(FileDTO file) throws RemoteException;
    boolean deleteFile(int fid, int userId) throws RemoteException;
    FileDTO[] listAllFiles() throws RemoteException;
    HashMap getFile(int fid, int user) throws RemoteException;
}
