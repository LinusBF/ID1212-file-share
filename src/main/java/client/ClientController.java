package client;

import errors.UsernameTakenException;
import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;

import java.rmi.RemoteException;

public class ClientController {
    private LoginRemoteAPI loginService;
    private FileRemoteAPI fileService;

    public ClientController(LoginRemoteAPI loginService, FileRemoteAPI fileService) {
        this.loginService = loginService;
        this.fileService = fileService;
    }

    void printFilesOnServer(){

    }

    void registerUser(String username, String password) throws RemoteException {
        try{
            if(loginService.registerUser(username, password)){
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

    void downloadFile(String filename){

    }

    void uploadFile(String filename){

    }
}
