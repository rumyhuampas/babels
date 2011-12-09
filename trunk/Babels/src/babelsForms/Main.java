package babelsForms;

import babelsObjects.FormsFactory;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
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
        setName("frmMain"); // NOI18N

        mitemFile.setText("Archivo");

        mitemFileExit.setText("Salir");
        mitemFile.add(mitemFileExit);

        mbarMain.add(mitemFile);

        mitemUsers.setText("Usuarios");

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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mitemUsersAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemUsersAdminActionPerformed
        FormsFactory.GetDialogForm("babelsForms.Users", true);
    }//GEN-LAST:event_mitemUsersAdminActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mbarMain;
    private javax.swing.JMenu mitemFile;
    private javax.swing.JMenuItem mitemFileExit;
    private javax.swing.JMenu mitemUsers;
    private javax.swing.JMenuItem mitemUsersAdmin;
    // End of variables declaration//GEN-END:variables
}
