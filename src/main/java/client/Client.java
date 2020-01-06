package client;

import interfaces.FileDTO;
import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Stack;

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
        String username = "";
        System.out.println("Welcome!");
        while (running){
            if(loggedIn) System.out.println("Logged in as " + username);
            if(loggedIn) controller.printFilesOnServer();
            if(loggedIn) controller.printNotifications(username);
            System.out.println("Available commands: register, login, upload, download, quit");
            String userChoice = getUserInput();
            switch (userChoice){
                case "register":
                    if(loggedIn) break;
                    System.out.println("Username: ");
                    String regUsr = getUserInput();
                    System.out.println("Password: ");
                    String regPass = getUserInput();
                    controller.registerUser(regUsr, regPass);
                    break;
                case "login":
                    if(loggedIn) break;
                    System.out.println("Username: ");
                    String loginUsr = getUserInput();
                    username = loginUsr;
                    System.out.println("Password: ");
                    String loginPass = getUserInput();
                    if(controller.loginUser(loginUsr, loginPass)) loggedIn = true;
                    else username = "";
                    break;
                case "upload":
                    if(!loggedIn) break;
                    System.out.println("Filename to upload: ./FilesToUpload/");
                    String uploadName = getUserInput();
                    controller.uploadFile(uploadName, username);
                    break;
                case "download":
                    if(!loggedIn) break;
                    System.out.println("Filename to download: ");
                    String downloadName = getUserInput();
                    controller.downloadFile(downloadName, username);
                    break;
                case "quit":
                    System.out.println("Bye!");
                    System.exit(0);
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

    public void fileWasAccessed(FileDTO file, String username){
        System.out.println("User " + username + " downloaded your file " + file.getName());
    }
}
