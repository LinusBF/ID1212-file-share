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
import java.util.Stack;
import java.util.logging.FileHandler;

public class FileService extends UnicastRemoteObject implements FileRemoteAPI {
    private FileRepository fileRepo;

    FileService() throws RemoteException {
        super();
        fileRepo = new FileRepository();
    }

    @Override
    public void uploadFile(FileDTO file) throws RemoteException {
        System.out.println("Got file: " + file.getName());
        fileRepo.addFile(file);
        Server.downloadTracker.put(file, new Stack<>());
        System.out.println(Server.downloadTracker);
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
    public HashMap<FileDTO, Stack<String>> listNotifications(String owner) throws RemoteException {
        ArrayList<FileDTO> ownedFiles = new ArrayList<>();
        HashMap<FileDTO, Stack<String>> ownedFilesDownloaded = new HashMap<>();
        for(FileDTO file : Server.downloadTracker.keySet()){
            if(file.getOwner().equals(owner)) ownedFiles.add(file);
        }
        for(FileDTO ownedFile : ownedFiles){
            ownedFilesDownloaded.put(ownedFile, Server.downloadTracker.get(ownedFile));
            Server.downloadTracker.put(ownedFile, new Stack<>());
        }
        return ownedFilesDownloaded;
    }

    @Override
    public FileDTO getFile(String filename, String username) throws RemoteException {
        FileEntity dbFile = fileRepo.getFile(filename);
        FileDTO file = FileEntity.getFileDTO(dbFile);
        Server.downloadTracker.get(file).push(username);
        System.out.println("Downloaded file: " + file.toString());
        return file;
    }
}