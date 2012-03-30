package babelsForms;

import babelsManagers.MainManager;
import babelsObjects.FormsFactory;
import java.awt.image.ImageObserver;

public class Main extends javax.swing.JFrame {

    private MainManager Manager;

    public Main() {
        initComponents();
        this.Manager = new MainManager();
        this.Manager.SetWindowBasicSettings(this);
        this.Manager.HideUsersIfNotAdmin(this.mitemUsers);
        org.edisoncor.gui.panel.PanelImage PanelImage= new org.edisoncor.gui.panel.PanelImage();
        PanelImage.setBounds(0, 0,HEIGHT , WIDTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        mbarMain = new javax.swing.JMenuBar();
        mitemFile = new javax.swing.JMenu();
        mitemFileNew = new javax.swing.JMenu();
        mitemFileNewProd = new javax.swing.JMenuItem();
        mitemFileNewCombo = new javax.swing.JMenuItem();
        mitemFileExit = new javax.swing.JMenuItem();
        mitemAdmin = new javax.swing.JMenu();
        mitemAdminProducts = new javax.swing.JMenuItem();
        mitemAdminCombos = new javax.swing.JMenuItem();
        mitemAdminUser = new javax.swing.JMenu();
        mitemAdminUserChangePass = new javax.swing.JMenuItem();
        mitemUsers = new javax.swing.JMenu();
        mitemUsersAdmin = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Babels");
        setName("frmMain"); // NOI18N

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/background.png"))); // NOI18N

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/title.png"))); // NOI18N

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        mbarMain.setPreferredSize(new java.awt.Dimension(173, 38));

        mitemFile.setText("Archivo");
        mitemFile.setPreferredSize(new java.awt.Dimension(59, 19));

        mitemFileNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/new.png"))); // NOI18N
        mitemFileNew.setText("Nuevo");

        mitemFileNewProd.setText("Producto...");
        mitemFileNewProd.setPreferredSize(new java.awt.Dimension(101, 38));
        mitemFileNewProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemFileNewProdActionPerformed(evt);
            }
        });
        mitemFileNew.add(mitemFileNewProd);

        mitemFileNewCombo.setLabel("Combo...");
        mitemFileNewCombo.setPreferredSize(new java.awt.Dimension(107, 38));
        mitemFileNewCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemFileNewComboActionPerformed(evt);
            }
        });
        mitemFileNew.add(mitemFileNewCombo);

        mitemFile.add(mitemFileNew);

        mitemFileExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/exit.png"))); // NOI18N
        mitemFileExit.setText("Salir");
        mitemFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemFileExitActionPerformed(evt);
            }
        });
        mitemFile.add(mitemFileExit);

        mbarMain.add(mitemFile);

        mitemAdmin.setText("Administrar");
        mitemAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mitemAdmin.setPreferredSize(new java.awt.Dimension(81, 19));

        mitemAdminProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/food.png"))); // NOI18N
        mitemAdminProducts.setText("Productos...");
        mitemAdminProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemAdminProductsActionPerformed(evt);
            }
        });
        mitemAdmin.add(mitemAdminProducts);

        mitemAdminCombos.setText("Combos");
        mitemAdminCombos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemAdminCombosActionPerformed(evt);
            }
        });
        mitemAdmin.add(mitemAdminCombos);

        mitemAdminUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/user.png"))); // NOI18N
        mitemAdminUser.setText("Cuenta de usuario");

        mitemAdminUserChangePass.setText("Cambiar password...");
        mitemAdminUserChangePass.setPreferredSize(new java.awt.Dimension(151, 38));
        mitemAdminUserChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemAdminUserChangePassActionPerformed(evt);
            }
        });
        mitemAdminUser.add(mitemAdminUserChangePass);

        mitemAdmin.add(mitemAdminUser);

        mbarMain.add(mitemAdmin);

        mitemUsers.setText("Usuarios");
        mitemUsers.setPreferredSize(new java.awt.Dimension(63, 19));

        mitemUsersAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/users.png"))); // NOI18N
        mitemUsersAdmin.setText("Administrar usuarios...");
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
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mitemUsersAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemUsersAdminActionPerformed
        FormsFactory.GetDialogForm("babelsForms.Users", true, null, null);
    }//GEN-LAST:event_mitemUsersAdminActionPerformed

    private void mitemFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemFileExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mitemFileExitActionPerformed

    private void mitemFileNewProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemFileNewProdActionPerformed
        FormsFactory.GetDialogForm("babelsForms.NewProduct", true, null, null);
    }//GEN-LAST:event_mitemFileNewProdActionPerformed

    private void mitemAdminProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminProductsActionPerformed
        FormsFactory.GetDialogForm("babelsForms.Products", true, null, null);
    }//GEN-LAST:event_mitemAdminProductsActionPerformed

    private void mitemAdminUserChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminUserChangePassActionPerformed
        FormsFactory.GetDialogForm("babelsForms.ChangePassword", true, null, null);
    }//GEN-LAST:event_mitemAdminUserChangePassActionPerformed

    private void mitemFileNewComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemFileNewComboActionPerformed
        FormsFactory.GetDialogForm("babelsForms.NewCombo", true, null, null);
    }//GEN-LAST:event_mitemFileNewComboActionPerformed

private void mitemAdminCombosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminCombosActionPerformed
         FormsFactory.GetDialogForm("babelsForms.Combos", true, null, null);
    
    // TODO add your handling code here:
}//GEN-LAST:event_mitemAdminCombosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mbarMain;
    private javax.swing.JMenu mitemAdmin;
    private javax.swing.JMenuItem mitemAdminCombos;
    private javax.swing.JMenuItem mitemAdminProducts;
    private javax.swing.JMenu mitemAdminUser;
    private javax.swing.JMenuItem mitemAdminUserChangePass;
    private javax.swing.JMenu mitemFile;
    private javax.swing.JMenuItem mitemFileExit;
    private javax.swing.JMenu mitemFileNew;
    private javax.swing.JMenuItem mitemFileNewCombo;
    private javax.swing.JMenuItem mitemFileNewProd;
    private javax.swing.JMenu mitemUsers;
    private javax.swing.JMenuItem mitemUsersAdmin;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    // End of variables declaration//GEN-END:variables
}
