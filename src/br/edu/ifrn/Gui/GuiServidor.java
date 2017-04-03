package br.edu.ifrn.Gui;

import br.edu.ifrn.Servidor.SocketServidor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GuiServidor extends javax.swing.JFrame {

    //Threads da aplicação
    private Thread tMonitorSocket;
    private SocketServidor servidor;
    private String msSocket;

    public GuiServidor() {
        initComponents();
        setLocationRelativeTo( null );
        habilitaComponentes();
    }    
    
    private void desabilitaComponentes(){
        btnIniciar.setEnabled(false);
        btnParar.setEnabled(true);
        Txt_Porta.setEnabled(false);
        lblStatus.setText("ON-Line");
        lblStatus.setForeground(Color.GREEN);
    }
    
    private void habilitaComponentes(){
        btnIniciar.setEnabled(true);
        btnParar.setEnabled(false);
        Txt_Porta.setEnabled(true);
        lblStatus.setText("OFF-Line");
        lblStatus.setForeground(Color.RED);
    }

    private void mensagem(String msg){
        txtMensagem.append(msg+"\n");
    }
    
    //Thred do socket
    private void monitorSocket(int porta){
        //Socket 
        tMonitorSocket = new Thread(new Runnable() {
        @Override
        public synchronized void run() {
                servidor = null;
                try{
                    if(porta == 0){
                        servidor = new SocketServidor(2003);
                    }else{
                        servidor = new SocketServidor(porta);
                    }
                 }catch(Exception ex){
                     JOptionPane.showMessageDialog(null,"Já existe uma instancia do aplicativo em execução!","Informação", JOptionPane.INFORMATION_MESSAGE);
                     System.exit(0);
                 }
                 while(true){
                    try{
                        if(servidor.testeServidor()){
                            try{
                                servidor = new SocketServidor(porta);
                                
                            }catch(Exception ex){}
                        }
                        mensagem("--------------------------");
                        mensagem("Aguardando Cliente...");
                        servidor.recebeCliente();
                        //Seta timeout em segundos
                        //servidor.setTimeout(10*1000);
                        mensagem("Cliente conectado. IP: "+servidor.getIpCliente());
                        msSocket = servidor.receberMsg();
                        servidor.enviarMsg(msSocket);
                        mensagem("Mensagem : "+msSocket);
                        mensagem("--------------------------");
                    }catch(Exception ex){
                        mensagem("Mensagem não recebida "+ex);
                        try {
                            servidor.fecharServidor();
                        } catch (IOException ex1) {
                            System.err.println(ex1);
                        }
                   }
                }
           }});
        tMonitorSocket.setPriority(Thread.MAX_PRIORITY);
        tMonitorSocket.start();

    }
    
    
    
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PanelMensagem = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Txt_Porta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Servidor Socket");

        txtMensagem.setEditable(false);
        txtMensagem.setColumns(20);
        txtMensagem.setLineWrap(true);
        txtMensagem.setRows(5);
        txtMensagem.setWrapStyleWord(true);
        txtMensagem.setEnabled(false);
        PanelMensagem.setViewportView(txtMensagem);

        jLabel2.setText("Clientes Conectados:");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnParar.setText("Parar");
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        jLabel3.setText("Status :");

        lblStatus.setForeground(new java.awt.Color(0, 255, 51));
        lblStatus.setText("Online");

        jLabel5.setText("Porta :");

        Txt_Porta.setText("8000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelMensagem))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnParar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Porta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnParar)
                    .addComponent(jLabel3)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Txt_Porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {
            
            monitorSocket(Integer.parseInt(Txt_Porta.getText()));
            desabilitaComponentes();
            
            
        } catch (Exception ex) {
            Logger.getLogger(GuiServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        try {
            tMonitorSocket.stop();
            servidor.fecharServidor();
            servidor.closeServer();
            habilitaComponentes();
        } catch (IOException ex) {
            Logger.getLogger(GuiServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnPararActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (servidor.testeServidor()) {
            tMonitorSocket.stop();
            try {
                servidor.fecharServidor();
            } catch (IOException ex) {
                Logger.getLogger(GuiServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            servidor.closeServer();  
        }
    }//GEN-LAST:event_formWindowClosed
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiServidor().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane PanelMensagem;
    private javax.swing.JTextField Txt_Porta;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnParar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables
}
