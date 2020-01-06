package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class RMIHandler {
    static void initialize() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(4200);
        registry.bind("login", new LoginService());
        registry.bind("catalog", new FileService());
        registry.bind("notification", new FileService());
        System.out.println("Server is running on port 4200");
    }
}
