/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import servidor.IServidor;

/**
 *
 * @author Tiago
 */
public class TelaMensagem extends javax.swing.JFrame {

    IServidor servidor;
    ICliente clienteObj;
    Vector names;
    Vector clienteVector;
    TelaJogo jogo;

    public TelaMensagem() {
        initComponents();
        names = new Vector();
    }

    public String getHost() {
        return JOptionPane.showInputDialog(jPanel1,
                "Selecione o endereco de ip: ", "Informe seu Ip", JOptionPane.PLAIN_MESSAGE);
    }

    public String getNome() {
        return JOptionPane.showInputDialog(jPanel1,
                "Entre com seu nome: ", "Informe seu nome", JOptionPane.PLAIN_MESSAGE);
    }

    public void atualizaContatos(Vector clientList) throws RemoteException {
        liContatos.clearSelection();
        names.clear();
        for (int i = 0; i < clientList.size(); i++) {
            names.add(((ICliente) clientList.elementAt(i)).getNome());
        }
        clientList.remove(names.indexOf(clienteObj.getNome()));

        names.remove(clienteObj.getNome());

        clienteVector = clientList;
        liContatos.setListData(names);
    }

    public void sendMessage(String text, ICliente clienteObj) throws RemoteException {
        taMensagens.append(clienteObj.getNome() + ": " + text + "\n");
    }

    public void conviteJogo(ICliente oponente) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int click = JOptionPane.showConfirmDialog(jPanel1, oponente.getNome()
                            + " Convidou voce para jogar", "Jogar", JOptionPane.YES_NO_OPTION);
                    if (click == 0) {
                        TelaMensagem.super.setVisible(false);
                        jogo.setVisible(true);
                        jogo.clienteObj = clienteObj;
                        jogo.oponente = oponente;
                        servidor.aceitaPartida(clienteObj, oponente);
                    }else{
                        servidor.unregisterForGame(oponente);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaMensagem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void aceitouPartida(ICliente oponente) {
        super.setVisible(false);
        jogo.setVisible(true);
        jogo.clienteObj = clienteObj;
        jogo.oponente = oponente;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        TelaMensagem mensagem = new TelaMensagem();
        mensagem.setVisible(true);
        String host = mensagem.getHost();
        String nome = mensagem.getNome();

        Registry registro = LocateRegistry.getRegistry(host);

        mensagem.servidor = (IServidor) Naming.
                lookup("rmi://" + host + ":1999/callback");

        mensagem.clienteObj = new ImplCliente(mensagem, nome);
        mensagem.servidor.registerForCallback(mensagem.clienteObj);
        mensagem.jogo = new TelaJogo(mensagem.servidor, mensagem);
        mensagem.clienteObj.setJogo(mensagem.jogo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liContatos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMensagens = new javax.swing.JTextArea();
        tfMensagem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbNome = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Jogo Da Velha");
        setBackground(new java.awt.Color(78, 50, 50));
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(78, 50, 50));

        liContatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        liContatos.setSelectedIndex(0);
        liContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                liContatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(liContatos);

        taMensagens.setEditable(false);
        taMensagens.setColumns(20);
        taMensagens.setRows(5);
        jScrollPane2.setViewportView(taMensagens);

        tfMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMensagemActionPerformed(evt);
            }
        });

        jButton1.setText("Iniciar Partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbNome.setFont(new java.awt.Font("AR DELANEY", 0, 24)); // NOI18N
        lbNome.setForeground(new java.awt.Color(255, 255, 0));

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMensagemActionPerformed
        try {
            servidor.sendMessage(tfMensagem.getText(), clienteObj);
            tfMensagem.setText("");
        } catch (RemoteException ex) {
            Logger.getLogger(TelaMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfMensagemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (liContatos.getSelectedIndex() >= 0) {
            ICliente oponente = (ICliente) clienteVector.get(liContatos.
                    getSelectedIndex());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        servidor.iniciaPartida(clienteObj, oponente);
                    } catch (RemoteException ex) {
                        Logger.getLogger(TelaMensagem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void liContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_liContatosMouseClicked
        if (liContatos.getSelectedIndex() >= 0) {
            lbNome.setText(liContatos.getSelectedValue());
        }
    }//GEN-LAST:event_liContatosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            servidor.unregisterForCallback(clienteObj);
            System.exit(0);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNome;
    private javax.swing.JList<String> liContatos;
    private javax.swing.JTextArea taMensagens;
    private javax.swing.JTextField tfMensagem;
    // End of variables declaration//GEN-END:variables

}
