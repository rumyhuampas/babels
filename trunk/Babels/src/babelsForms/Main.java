package babelsForms;

import babelsManagers.MainManager;
import babelsObjects.FormsFactory;

public class Main extends javax.swing.JFrame {
    
    private MainManager Manager;

    public Main() {
        initComponents();
        this.Manager = new MainManager();
        this.Manager.SetWindowBasicSettings(this);
        this.Manager.HideUsersIfNotAdmin(this.mitemUsers);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbarMain = new javax.swing.JMenuBar();
        mitemFile = new javax.swing.JMenu();
        mitemFileExit = new javax.swing.JMenuItem();
        mitemUsers = new javax.swing.JMenu();
        mitemUsersAdmin = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Babels");
        setName("frmMain"); // NOI18N

        mitemFile.setText("Archivo");

        mitemFileExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/exit.png"))); // NOI18N
        mitemFileExit.setText("Salir");
        mitemFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemFileExitActionPerformed(evt);
            }
        });
        mitemFile.add(mitemFileExit);

        mbarMain.add(mitemFile);

        mitemUsers.setText("Usuarios");

        mitemUsersAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/users.png"))); // NOI18N
        mitemUsersAdmin.setText("Administrar usuarios");
        mitemUsersAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemUsersAdminActionPerformed(evt);
            }
        });
        mitemUsers.add(mitemUsersAdmin);

        mbarMain.add(mitemUsers);

        setJMenuBar(mbarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mitemUsersAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemUsersAdminActionPerformed
        FormsFactory.GetDialogForm("babelsForms.Users", true, null, null);
    }//GEN-LAST:event_mitemUsersAdminActionPerformed

    private void mitemFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemFileExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mitemFileExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mbarMain;
    private javax.swing.JMenu mitemFile;
    private javax.swing.JMenuItem mitemFileExit;
    private javax.swing.JMenu mitemUsers;
    private javax.swing.JMenuItem mitemUsersAdmin;
    // End of variables declaration//GEN-END:variables
}
