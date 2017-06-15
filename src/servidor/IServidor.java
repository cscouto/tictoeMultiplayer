/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.Remote;
import cliente.ICliente;
import java.rmi.RemoteException;

/**
 *
 * @author Tiago
 */
public interface IServidor extends Remote {
    
    public void realizaJogada(int i, int j, ICliente clienteObj, 
            ICliente oponente) 
            throws RemoteException;
    
    public void registerForCallback(
            ICliente clienteObj
    ) throws java.rmi.RemoteException;

    public void unregisterForCallback(
            ICliente clienteObj)
            throws java.rmi.RemoteException;
    
    public void unregisterForGame(
            ICliente clienteObj)
            throws java.rmi.RemoteException;

    public void sendMessage(String text, ICliente clienteObj) 
            throws RemoteException;

    public void iniciaPartida(ICliente clienteObj, ICliente oponente) 
            throws RemoteException;

    public void aceitaPartida(ICliente clienteObj, ICliente oponente) 
            throws RemoteException;

    public void novaPartida(ICliente oponente) throws RemoteException;

    public void confirmaNovaPartida(ICliente oponente) throws RemoteException;

    public void sendMessageFromGame(String text, ICliente clienteObj, 
            ICliente oponente) throws RemoteException;

    public void avisaEncerramento(ICliente oponente) throws RemoteException;
}
