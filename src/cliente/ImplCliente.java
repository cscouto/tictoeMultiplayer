/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author Tiago
 */
public class ImplCliente extends UnicastRemoteObject implements ICliente {
    TelaJogo jogo;
    TelaMensagem mensagem;
    String nome;
    
    public ImplCliente(TelaMensagem mensagem, String nome) throws RemoteException {
        this.mensagem  = mensagem;
        this.nome = nome;
    }
    
    public void setJogo(TelaJogo jogo) throws RemoteException{
        this.jogo = jogo;
    }
    

    @Override
    public void notificaJogada(int x, int y) throws RemoteException {
        jogo.liberaPartida(true);
        jogo.realizaJogada(x, y);
    }

    @Override
    public void LiberaPartida() {
        jogo.liberaPartida(true);
    }

    @Override
    public void atualizaContatos(Vector clientList) throws RemoteException {
        mensagem.atualizaContatos(clientList);
    }

    @Override
    public String getNome() throws RemoteException {
        return this.nome;
    }

    @Override
    public void sendMessage(String text, ICliente clienteObj) throws RemoteException {
        mensagem.sendMessage(text, clienteObj);
    }

    @Override
    public void convidaJogo(ICliente clienteObj) throws RemoteException {
        mensagem.conviteJogo(clienteObj);
    }

    @Override
    public void aceitouPartida(ICliente clienteObj) throws RemoteException {
        mensagem.aceitouPartida(clienteObj);
    }

    @Override
    public void novaPartida() throws RemoteException {
        jogo.novaPartida();
    }

    @Override
    public void confirmaNovaPartida() throws RemoteException {
        jogo.confirmaNovaPartida();
    }

    @Override
    public void sendMessageFromGame(String text, ICliente clienteObj) 
            throws RemoteException {
        jogo.sendMessage(text, clienteObj);
    }

    @Override
    public void avisaEncerramento() throws RemoteException {
        jogo.avisaEncerramento();
    }

}
