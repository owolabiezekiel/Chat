/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 *
 * @author TOBILOBA
 */
public class ChatServer extends javax.swing.JFrame {

    /**
     * Creates new form ChatServer
     */
    LocalDateTime dt = LocalDateTime.now();
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    public ChatServer() {
        initComponents();
        this.setTitle("Server");
        msgArea.setText("Chat with Client Started on: " + dt.getHour() + ":"+dt.getMinute()+ (dt.getHour() >= 12 ? " PM" : " AM" ));
        msgText.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgArea = new javax.swing.JTextArea();
        msgText = new javax.swing.JTextField();
        serverSend = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msgArea.setColumns(20);
        msgArea.setRows(5);
        jScrollPane1.setViewportView(msgArea);

        msgText.setText("jTextField1");

        serverSend.setText("Send");
        serverSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msgText, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(serverSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgText)
                    .addComponent(serverSend, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serverSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverSendActionPerformed
        try{
            String msgOut = "";
            String me = "Me:\t";
            msgOut = msgText.getText().trim();
            dout.writeUTF(msgOut);
            dout.flush();
            msgArea.setText(msgArea.getText() + "\n" + (me + msgOut));
            msgText.setText("");
        } catch(IOException e) {
            // exception handling code here
        }
    }//GEN-LAST:event_serverSendActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatServer().setVisible(true);
            }
        });
        
        String msgin = "";
        try{
            ss = new ServerSocket(1200);
            s = ss.accept();
            
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while(!msgin.equals("exit")){
                msgin = din.readUTF();
                msgArea.setText(msgArea.getText().trim() + "\nClient: \t" + msgin);
            }
        } catch(Exception e) {
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private static javax.swing.JTextArea msgArea;
    private javax.swing.JTextField msgText;
    private javax.swing.JButton serverSend;
    // End of variables declaration//GEN-END:variables
}
