package client;

import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    private static Boolean running;
    private static ClientController controller;

    public static void main(String[] args) throws RemoteException {
        running = true;
        LoginRemoteAPI loginService = RMIHandler.lookupLoginAPI();
        FileRemoteAPI fileService = RMIHandler.lookupFileAPI();
        if(loginService == null || fileService == null) System.exit(1);

        controller = new ClientController(loginService, fileService);
        mainLoop();
    }

    private static void mainLoop() throws RemoteException {
        boolean loggedIn = false;
        while (running){
            System.out.println("Welcome!");
            controller.printFilesOnServer();
            System.out.println("Available commands: register, login, upload, download, quit");
            String userChoice = getUserInput();
            switch (userChoice.toLowerCase()){
                case "register":
                    if(loggedIn) break;
                    System.out.print("Username: ");
                    String regUsr = getUserInput();
                    System.out.print("Password: ");
                    String regPass = getUserInput();
                    controller.registerUser(regUsr, regPass);
                    break;
                case "login":
                    if(loggedIn) break;
                    System.out.print("Username: ");
                    String loginUsr = getUserInput();
                    System.out.print("Password: ");
                    String loginPass = getUserInput();
                    if(controller.loginUser(loginUsr, loginPass)) loggedIn = true;
                    break;
                case "upload":
                    if(!loggedIn) break;
                    System.out.println("Filename to upload: ./FilesToUpload/");
                    String uploadName = getUserInput();
                    controller.uploadFile(uploadName);
                    break;
                case "download":
                    if(!loggedIn) break;
                    System.out.println("Filename to download: ");
                    String downloadName = getUserInput();
                    controller.downloadFile(downloadName);
                    break;
                default:
                    System.out.println("Please type a valid command");
                    break;
            }
        }
    }

    private static String getUserInput(){
        Scanner in = new Scanner(System.in);
        if (in.hasNext()) {
            return in.nextLine();
        } else return getUserInput();
    }
}
