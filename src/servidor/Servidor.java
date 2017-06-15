/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Tiago
 */
public class Servidor {
    	public static void main(String args[]) {
		try {
			startRegistry(1999);
			
			ImplServidor exportedObj = new ImplServidor();
			
			Naming.rebind("rmi://localhost:1999/callback", exportedObj);
			System.out.println("Callback Server ready.");
		}
		catch (Exception re) {
			System.out.println("Exception in HelloServer.main: " + re);
		} 
	} 

	private static void startRegistry(int RMIPortNum) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			registry.list();

		} catch (RemoteException e) {

			Registry registry = LocateRegistry.createRegistry(RMIPortNum);
		}
	} 
}
