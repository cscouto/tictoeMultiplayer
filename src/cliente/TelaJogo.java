/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.sun.corba.se.impl.corba.AsynchInvoke;
import com.sun.javafx.runtime.async.AsyncOperation;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.SwingUtilities;
import javax.swing.text.AsyncBoxView;
import servidor.IServidor;

/**
 *
 * @author Tiago
 */
public class TelaJogo extends javax.swing.JFrame {

    String jogo[][];
    ICliente clienteObj;
    ICliente oponente;
    IServidor servidor;
    TelaMensagem mensagem;
    Icon x;
    Icon o;

    public TelaJogo(IServidor servidor, TelaMensagem mensagem) {

        initComponents();
        this.servidor = servidor;
        this.mensagem = mensagem;
        x = new ImageIcon(getClass().getResource("x.png"));
        o = new ImageIcon(getClass().getResource("o.png"));
        jogo = new String[3][3];
        inicializaMatriz();
    }

    public void realizaJogada(int i, int j) {
        jogo[i][j] = "o";
        atualizaTela(i, j, o);
        verificaSeVenceu();
    }

    public void atualizaTela(int i, int j, Icon c) {
        if (i == 0) {
            if (j == 0) {
                btA1.setIcon(c);
            }
            if (j == 1) {
                btA2.setIcon(c);
            }
            if (j == 2) {
                btA3.setIcon(c);
            }
        }
        if (i == 1) {
            if (j == 0) {
                btB1.setIcon(c);
            }
            if (j == 1) {
                btB2.setIcon(c);
            }
            if (j == 2) {
                btB3.setIcon(c);
            }
        }
        if (i == 2) {
            if (j == 0) {
                btC1.setIcon(c);
            }
            if (j == 1) {
                btC2.setIcon(c);
            }
            if (j == 2) {
                btC3.setIcon(c);
            }
        }
    }

    public void inicializaMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogo[i][j] = "0";
            }
        }
    }

    public void liberaPartida(boolean t) {
        btA1.setEnabled(t);
        btA2.setEnabled(t);
        btA3.setEnabled(t);
        btB1.setEnabled(t);
        btB2.setEnabled(t);
        btB3.setEnabled(t);
        btC1.setEnabled(t);
        btC2.setEnabled(t);
        btC3.setEnabled(t);
    }

    public void novaPartida() throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int click = JOptionPane.showConfirmDialog(jPanel1, oponente.getNome()
                            + " Convidou voce para jogar novamente", "Jogar", JOptionPane.YES_NO_OPTION);
                    if (click == 0) {
                        limpaTela();
                        servidor.confirmaNovaPartida(oponente);
                        liberaPartida(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void confirmaNovaPartida() {
        liberaPartida(true);
    }

    public void sendMessage(String text, ICliente clienteObj) throws RemoteException {
        taMsg.append(clienteObj.getNome() + ": " + text + "\n");
    }

    public void avisaEncerramento() throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Adversario abandonou a partida.");
            }
        });
        servidor.unregisterForGame(clienteObj);
        limpaTela();
        super.setVisible(false);
        mensagem.setVisible(true);
    }

    public void limpaTela() {
        inicializaMatriz();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                atualizaTela(i, j, null);
            }
        }
        btNovoJogo.setEnabled(false);
        lbWin.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btC1 = new javax.swing.JButton();
        btB1 = new javax.swing.JButton();
        btA1 = new javax.swing.JButton();
        btA2 = new javax.swing.JButton();
        btB2 = new javax.swing.JButton();
        btC2 = new javax.swing.JButton();
        btC3 = new javax.swing.JButton();
        btB3 = new javax.swing.JButton();
        btA3 = new javax.swing.JButton();
        btNovoJogo = new javax.swing.JButton();
        btLobby = new javax.swing.JButton();
        btsair = new javax.swing.JButton();
        tfMsg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMsg = new javax.swing.JTextArea();
        lbWin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Jogo Da Velha");
        setBackground(new java.awt.Color(78, 50, 50));
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(78, 50, 50));

        btC1.setBackground(new java.awt.Color(255, 255, 255));
        btC1.setEnabled(false);
        btC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btC1ActionPerformed(evt);
            }
        });

        btB1.setBackground(new java.awt.Color(255, 255, 255));
        btB1.setEnabled(false);
        btB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btB1ActionPerformed(evt);
            }
        });

        btA1.setBackground(new java.awt.Color(255, 255, 255));
        btA1.setEnabled(false);
        btA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btA1ActionPerformed(evt);
            }
        });

        btA2.setBackground(new java.awt.Color(255, 255, 255));
        btA2.setEnabled(false);
        btA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btA2ActionPerformed(evt);
            }
        });

        btB2.setBackground(new java.awt.Color(255, 255, 255));
        btB2.setEnabled(false);
        btB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btB2ActionPerformed(evt);
            }
        });

        btC2.setBackground(new java.awt.Color(255, 255, 255));
        btC2.setEnabled(false);
        btC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btC2ActionPerformed(evt);
            }
        });

        btC3.setBackground(new java.awt.Color(255, 255, 255));
        btC3.setEnabled(false);
        btC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btC3ActionPerformed(evt);
            }
        });

        btB3.setBackground(new java.awt.Color(255, 255, 255));
        btB3.setToolTipText("");
        btB3.setEnabled(false);
        btB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btB3ActionPerformed(evt);
            }
        });

        btA3.setBackground(new java.awt.Color(255, 255, 255));
        btA3.setEnabled(false);
        btA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btA3ActionPerformed(evt);
            }
        });

        btNovoJogo.setText("NOVO JOGO");
        btNovoJogo.setEnabled(false);
        btNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoJogoActionPerformed(evt);
            }
        });

        btLobby.setText("LOBBY");
        btLobby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLobbyActionPerformed(evt);
            }
        });

        btsair.setText("SAIR");
        btsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsairActionPerformed(evt);
            }
        });

        tfMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMsgActionPerformed(evt);
            }
        });

        taMsg.setEditable(false);
        taMsg.setColumns(20);
        taMsg.setRows(5);
        jScrollPane1.setViewportView(taMsg);

        lbWin.setFont(new java.awt.Font("AR DELANEY", 1, 48)); // NOI18N
        lbWin.setForeground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLobby, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btsair, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btA3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btA2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btA1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btB1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btB2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btB3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btC1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btC2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btC3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfMsg, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbWin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btB1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btC1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btA1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btC2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btB2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btA2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btA3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btB3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btC3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbWin, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLobby, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btA1ActionPerformed
        try {
            if (jogo[0][0].equals("0")) {
                jogo[0][0] = "x";
                servidor.realizaJogada(0, 0, clienteObj, oponente);
                atualizaTela(0, 0, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btA1ActionPerformed

    private void btA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btA2ActionPerformed
        try {
            if (jogo[0][1].equals("0")) {
                jogo[0][1] = "x";
                servidor.realizaJogada(0, 1, clienteObj, oponente);
                atualizaTela(0, 1, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btA2ActionPerformed

    private void btA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btA3ActionPerformed
        try {
            if (jogo[0][2].equals("0")) {
                jogo[0][2] = "x";
                servidor.realizaJogada(0, 2, clienteObj, oponente);
                atualizaTela(0, 2, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btA3ActionPerformed

    private void btB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btB1ActionPerformed
        try {
            if (jogo[1][0].equals("0")) {
                jogo[1][0] = "x";
                servidor.realizaJogada(1, 0, clienteObj, oponente);
                atualizaTela(1, 0, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btB1ActionPerformed

    private void btB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btB2ActionPerformed
        try {
            if (jogo[1][1].equals("0")) {
                jogo[1][1] = "x";
                servidor.realizaJogada(1, 1, clienteObj, oponente);
                atualizaTela(1, 1, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btB2ActionPerformed

    private void btB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btB3ActionPerformed
        try {
            if (jogo[1][2].equals("0")) {
                jogo[1][2] = "x";
                servidor.realizaJogada(1, 2, clienteObj, oponente);
                atualizaTela(1, 2, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btB3ActionPerformed

    private void btC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btC1ActionPerformed
        try {
            if (jogo[2][0].equals("0")) {
                jogo[2][0] = "x";
                servidor.realizaJogada(2, 0, clienteObj, oponente);
                atualizaTela(2, 0, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btC1ActionPerformed

    private void btC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btC2ActionPerformed
        try {
            if (jogo[2][1].equals("0")) {
                jogo[2][1] = "x";
                servidor.realizaJogada(2, 1, clienteObj, oponente);
                atualizaTela(2, 1, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btC2ActionPerformed

    private void btC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btC3ActionPerformed
        try {
            if (jogo[2][2].equals("0")) {
                jogo[2][2] = "x";
                servidor.realizaJogada(2, 2, clienteObj, oponente);
                atualizaTela(2, 2, x);
                liberaPartida(false);
                verificaSeVenceu();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btC3ActionPerformed

    private void btNovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoJogoActionPerformed
        try {
            limpaTela();
            servidor.novaPartida(oponente);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btNovoJogoActionPerformed

    private void btLobbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLobbyActionPerformed
        try {
            servidor.unregisterForGame(clienteObj);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        servidor.avisaEncerramento(oponente);
                    } catch (RemoteException ex) {
                        Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            super.setVisible(false);
            mensagem.setVisible(true);
            limpaTela();
        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLobbyActionPerformed

    private void btsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairActionPerformed
        try {
            servidor.unregisterForGame(clienteObj);
            servidor.unregisterForCallback(clienteObj);
            servidor.avisaEncerramento(oponente);
            System.exit(0);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btsairActionPerformed

    private void tfMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMsgActionPerformed
        try {
            servidor.sendMessageFromGame(tfMsg.getText(), clienteObj, oponente);
            tfMsg.setText("");
        } catch (RemoteException ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfMsgActionPerformed

    public void verificaSeVenceu() {
        boolean velha = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(jogo[i][j].equals("0")){
                    velha = false;
                }
            }
        }
        if(velha){
            lbWin.setText("Velha!!");
            btNovoJogo.setEnabled(true);
            liberaPartida(false);
        }
        if ((jogo[0][0].equals("x")) && (jogo[0][0].equals(jogo[1][0]))
                && (jogo[0][0].equals(jogo[2][0]))) {
            lbWin.setText("Voce venceu!");
            btNovoJogo.setEnabled(true);
            liberaPartida(false);
        }
        if ((jogo[0][1].equals("x")) && (jogo[0][1].equals(jogo[1][1]))
                && (jogo[0][1].equals(jogo[2][1]))) {
            lbWin.setText("Voce venceu!");
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
        }
        if ((jogo[0][2].equals("x")) && (jogo[0][2].equals(jogo[1][2]))
                && (jogo[0][2].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }

        if ((jogo[0][0].equals("x")) && (jogo[0][0].equals(jogo[0][1]))
                && (jogo[0][0].equals(jogo[0][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }
        if ((jogo[1][0].equals("x")) && (jogo[1][0].equals(jogo[1][1]))
                && (jogo[1][0].equals(jogo[1][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }
        if ((jogo[2][0].equals("x")) && (jogo[2][0].equals(jogo[2][1]))
                && (jogo[2][0].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }
        if ((jogo[0][0].equals("x")) && (jogo[0][0].equals(jogo[1][1]))
                && (jogo[0][0].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }
        if ((jogo[2][0].equals("x")) && (jogo[2][0].equals(jogo[1][1]))
                && (jogo[2][0].equals(jogo[0][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce venceu!");
        }
        if ((jogo[0][0].equals("o")) && (jogo[0][0].equals(jogo[1][0]))
                && (jogo[0][0].equals(jogo[2][0]))) {
            lbWin.setText("Voce perdeu!");
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
        }
        if ((jogo[0][1].equals("o")) && (jogo[0][1].equals(jogo[1][1]))
                && (jogo[0][1].equals(jogo[2][1]))) {
            lbWin.setText("Voce perdeu!");
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
        }
        if ((jogo[0][2].equals("o")) && (jogo[0][2].equals(jogo[1][2]))
                && (jogo[0][2].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }

        if ((jogo[0][0].equals("o")) && (jogo[0][0].equals(jogo[0][1]))
                && (jogo[0][0].equals(jogo[0][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }
        if ((jogo[1][0].equals("o")) && (jogo[1][0].equals(jogo[1][1]))
                && (jogo[1][0].equals(jogo[1][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }
        if ((jogo[2][0].equals("o")) && (jogo[2][0].equals(jogo[2][1]))
                && (jogo[2][0].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }
        if ((jogo[0][0].equals("o")) && (jogo[0][0].equals(jogo[1][1]))
                && (jogo[0][0].equals(jogo[2][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }
        if ((jogo[2][0].equals("o")) && (jogo[2][0].equals(jogo[1][1]))
                && (jogo[2][0].equals(jogo[0][2]))) {
            liberaPartida(false);
            btNovoJogo.setEnabled(true);
            lbWin.setText("Voce perdeu!");
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btA1;
    private javax.swing.JButton btA2;
    private javax.swing.JButton btA3;
    private javax.swing.JButton btB1;
    private javax.swing.JButton btB2;
    private javax.swing.JButton btB3;
    private javax.swing.JButton btC1;
    private javax.swing.JButton btC2;
    private javax.swing.JButton btC3;
    private javax.swing.JButton btLobby;
    private javax.swing.JButton btNovoJogo;
    private javax.swing.JButton btsair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbWin;
    private javax.swing.JTextArea taMsg;
    private javax.swing.JTextField tfMsg;
    // End of variables declaration//GEN-END:variables

}
