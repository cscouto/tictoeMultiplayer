/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Tiago
 */
public interface ICliente extends Remote {

    public void notificaJogada(int x, int y) throws RemoteException;

    public void LiberaPartida() throws RemoteException;

    public void atualizaContatos(Vector clientList) throws RemoteException;
    
    public void setJogo(TelaJogo jogo) throws RemoteException;
    
    public String getNome() throws RemoteException;

    public void sendMessage(String text, ICliente clienteObj) throws RemoteException;

    public void convidaJogo(ICliente clienteObj) throws RemoteException;

    public void aceitouPartida(ICliente clienteObj) throws RemoteException;

    public void novaPartida() throws RemoteException;

    public void confirmaNovaPartida() throws RemoteException;

    public void sendMessageFromGame(String text, ICliente clienteObj)
            throws RemoteException;

    public void avisaEncerramento() throws RemoteException;

}
