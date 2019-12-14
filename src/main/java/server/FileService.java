package server;

import client.Client;
import client.ClientController;
import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import server.db.FileEntity;
import server.db.FileRepository;

import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;

public class FileService extends UnicastRemoteObject implements FileRemoteAPI {
    FileRepository fileRepo;
    HashMap<Integer, RemoteRef> fileUploaders;

    protected FileService() throws RemoteException {
        super();
        fileRepo = new FileRepository();
        fileUploaders = RMIHandler.fileUploaders;
    }

    @Override
    public void uploadFile(FileDTO file) throws RemoteException {
        System.out.println("Got file: " + file.getName());
        Integer id = fileRepo.addFile(file);
        fileUploaders.put(id, this.ref);
    }

    @Override
    public boolean deleteFile(int fid, int userId) throws RemoteException {
        return false;
    }

    @Override
    public FileDTO[] listAllFiles() throws RemoteException {
        ArrayList<FileEntity> dbFiles = fileRepo.getAllFiles();
        FileDTO[] files = new FileDTO[dbFiles.size()];
        int i = 0;
        for(FileEntity fe : dbFiles){
            files[i] = FileEntity.getFileDTO(fe);
            i++;
        }
        return files;
    }

    @Override
    public FileDTO getFile(String filename, String username) throws RemoteException {
        FileEntity dbFile = fileRepo.getFile(filename);
        FileDTO file = FileEntity.getFileDTO(dbFile);
        RemoteRef ref = fileUploaders.get(dbFile.getId());
        System.out.println("Downloaded file: " + file.toString());
        return file;
    }
}