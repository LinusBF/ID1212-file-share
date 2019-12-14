package client;

import errors.UsernameTakenException;
import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;
import server.FileService;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ClientController implements Serializable {
    private LoginRemoteAPI loginService;
    private FileRemoteAPI fileService;

    public ClientController(LoginRemoteAPI loginService, FileRemoteAPI fileService) {
        this.loginService = loginService;
        this.fileService = fileService;
    }

    void printFilesOnServer() throws RemoteException {
        for(FileDTO file : this.fileService.listAllFiles()){
            System.out.println(file);
        }
    }

    void registerUser(String username, String password) throws RemoteException {
        try{
            if(loginService.registerUser(username, password) > 0){
                System.out.println("Successfully registered");
            } else {
                System.out.println("Failed to register!!!");
            }
        } catch (UsernameTakenException e){
            System.out.println("Username is already taken, please try another!");
        }
    }

    boolean loginUser(String username, String password) throws RemoteException {
        if(loginService.login(username, password)){
            return true;
        } else {
            System.out.println("Wrong credentials!");
            return false;
        }
    }

    void downloadFile(String filename, String username) throws RemoteException {
        this.fileService.getFile(filename, username);
    }

    void uploadFile(String filename, String username){
        int size = 0;
        System.out.println(System.getProperty("user.dir"));
        try {
            File file = new File(filename);
            String fname = file.getName();
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            size = (int) attr.size();
            FileDTO fileDTO = new FileDTO(fname, username, size);
            this.fileService.uploadFile(fileDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWasAccessed(FileDTO file, String username){
        System.out.println("User " + username + " downloaded your file " + file.getName());
    }
}
