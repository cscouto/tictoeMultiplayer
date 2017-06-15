/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import cliente.ICliente;

/**
 *
 * @author Tiago
 */
public class ImplServidor extends UnicastRemoteObject implements IServidor {

    private Vector clientList;
    private Vector clienteGame;

    public ImplServidor() throws RemoteException {
        clientList = new Vector();
        clienteGame = new Vector();
    }

    @Override
    public void registerForCallback(ICliente clienteObj) throws RemoteException {
        if (!(clientList.contains(clienteObj))) {
            clientList.addElement(clienteObj);
            atualizaContatos();
        }
    }

    @Override
    public void unregisterForCallback(ICliente clienteObj) throws RemoteException {
        clientList.removeElement(clienteObj);
        atualizaContatos();
    }

    @Override
    public void realizaJogada(int i, int j, ICliente clienteObj, 
            ICliente oponente)
            throws RemoteException {
                oponente.notificaJogada(i, j);
    }

    private void atualizaContatos() throws RemoteException {
        for (int k = 0; k < clientList.size(); k++) {
            ICliente nextClient = (ICliente) clientList.elementAt(k);
            nextClient.atualizaContatos(clientList);
        }
    }

    @Override
    public void sendMessage(String text, ICliente clienteObj)
            throws RemoteException {
        for (int k = 0; k < clientList.size(); k++) {
            ICliente nextClient = (ICliente) clientList.elementAt(k);
            nextClient.sendMessage(text, clienteObj);
        }
    }

    @Override
    public void iniciaPartida(ICliente clienteObj, ICliente oponente)
            throws RemoteException {

        if (!(clienteGame.contains(oponente))) {
            if (!(clienteGame.contains(clienteObj))) {
                clienteGame.addElement(clienteObj);
            }
            oponente.convidaJogo(clienteObj);
        }
    }

    @Override
    public void aceitaPartida(ICliente clienteObj, ICliente oponente)
            throws RemoteException {
        if (!(clienteGame.contains(clienteObj))) {
            clienteGame.addElement(clienteObj);
        }
        oponente.aceitouPartida(clienteObj);

        clienteObj.LiberaPartida();
        oponente.LiberaPartida();
    }

    @Override
    public void novaPartida(ICliente oponente) throws RemoteException {
        oponente.novaPartida();
    }

    @Override
    public void confirmaNovaPartida(ICliente oponente) throws RemoteException {
        oponente.confirmaNovaPartida();
    }

    @Override
    public void sendMessageFromGame(String text, ICliente clienteObj,
            ICliente oponente) throws RemoteException {
        oponente.sendMessageFromGame(text, clienteObj);
        clienteObj.sendMessageFromGame(text, clienteObj);
    }

    @Override
    public void unregisterForGame(ICliente clienteObj) throws RemoteException {
        clienteGame.removeElement(clienteObj);
    }

    @Override
    public void avisaEncerramento(ICliente oponente) throws RemoteException {
        oponente.avisaEncerramento();
    }

}
