package client;

import interfaces.FileRemoteAPI;
import interfaces.LoginRemoteAPI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIHandler {
    public static LoginRemoteAPI lookupLoginAPI() {
        try {
            return (LoginRemoteAPI) Naming.lookup("rmi://localhost:4200/login");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Could not find Login service!!!");
            e.printStackTrace();
        }
        return null;
    }

    public static FileRemoteAPI lookupFileAPI() {
        try {
            return (FileRemoteAPI) Naming.lookup("rmi://localhost:4200/catalog");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Could not find File service!!!");
            e.printStackTrace();
        }
        return null;
    }
}
