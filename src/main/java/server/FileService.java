package server;

import interfaces.FileDTO;
import interfaces.FileRemoteAPI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class FileService extends UnicastRemoteObject implements FileRemoteAPI {

    protected FileService() throws RemoteException {
        super();
    }

    @Override
    public void uploadFile(FileDTO file) throws RemoteException {
        System.out.println("Got file: " + file.getName());
    }

    @Override
    public boolean deleteFile(int fid, int userId) throws RemoteException {
        return false;
    }

    @Override
    public FileDTO[] listAllFiles() throws RemoteException {
        return new FileDTO[0];
    }

    @Override
    public HashMap getFile(int fid, int user) throws RemoteException {
        return null;
    }
}