package client;

import errors.UsernameTakenException;
import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.rmi.RemoteException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Stack;

public class ClientController implements Serializable {
    private LoginRemoteAPI loginService;
    private FileRemoteAPI fileService;

    ClientController(LoginRemoteAPI loginService, FileRemoteAPI fileService) {
        this.loginService = loginService;
        this.fileService = fileService;
    }

    void printFilesOnServer() throws RemoteException {
        System.out.println("\nFiles on server:");
        for(FileDTO file : this.fileService.listAllFiles()){
            System.out.println(file);
        }
        System.out.println("");
    }

    void printNotifications(String username) throws RemoteException {
        HashMap<FileDTO, Stack<String>> uploadedFiles = this.fileService.listNotifications(username);
        for(FileDTO uploadedFile : uploadedFiles.keySet()){
            Stack<String> usersThatHaveDownloaded = uploadedFiles.get(uploadedFile);
            for(String user : usersThatHaveDownloaded){
                System.out.println("User " + user + " downloaded your file " + uploadedFile.getName());
            }
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
        FileDTO file = this.fileService.getFile(filename, username);
        System.out.println("Downloaded the following file:");
        System.out.println(file);
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
}
