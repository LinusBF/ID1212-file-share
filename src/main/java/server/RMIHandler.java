package server;

import client.Client;
import client.ClientController;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.util.HashMap;

public class RMIHandler {
    public static HashMap<Integer, RemoteRef> fileUploaders = new HashMap<>();
    public static void initialize() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(4200);
        registry.bind("login", new LoginService());
        registry.bind("catalog", new FileService());
        System.out.println("Server is running on port 4200");
    }
}
