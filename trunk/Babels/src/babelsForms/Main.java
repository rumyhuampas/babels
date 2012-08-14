package babelsForms;

import babelsManagers.MainManager;
import babelsManagers.ReportManager;
import babelsObjects.FormsFactory;
import java.awt.image.ImageObserver;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

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
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        mbarMain = new javax.swing.JMenuBar();
        mitemFile = new javax.swing.JMenu();
        mitemFileExit = new javax.swing.JMenuItem();
        mitemAdmin = new javax.swing.JMenu();
        mitemAdminUser = new javax.swing.JMenu();
        mitemAdminUserChangePass = new javax.swing.JMenuItem();
        mitemUsers = new javax.swing.JMenu();
        mitemUsersAdmin = new javax.swing.JMenuItem();
        mitemCaja = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mitemCliente = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mitemProductos = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        mitemAdminProducts = new javax.swing.JMenuItem();
        mitemCombos = new javax.swing.JMenu();
        jmitemNuevoProd = new javax.swing.JMenuItem();
        mitemAdminCombos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Babels");
        setName("frmMain"); // NOI18N

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/FONDO SISTEMA 1.jpg"))); // NOI18N

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

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addContainerGap(701, Short.MAX_VALUE)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mbarMain.setPreferredSize(new java.awt.Dimension(173, 38));

        mitemFile.setText("Archivo");
        mitemFile.setPreferredSize(new java.awt.Dimension(59, 19));

        mitemFileExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/power_black (1) - copia.png"))); // NOI18N
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

        mitemAdminUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/User48 - copia.png"))); // NOI18N
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

        mitemUsersAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/Users48.png"))); // NOI18N
        mitemUsersAdmin.setText("Administrar usuarios...");
        mitemUsersAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemUsersAdminActionPerformed(evt);
            }
        });
        mitemUsers.add(mitemUsersAdmin);

        mbarMain.add(mitemUsers);

        mitemCaja.setText("Caja");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/currency_black_dollar (2) - copia.png"))); // NOI18N
        jMenuItem1.setText("Caja");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mitemCaja.add(jMenuItem1);

        mbarMain.add(mitemCaja);

        mitemCliente.setText("   Cliente");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/Add_user48.png"))); // NOI18N
        jMenuItem2.setText("Nuevo Cliente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mitemCliente.add(jMenuItem2);

        mbarMain.add(mitemCliente);

        mitemProductos.setText("   Productos");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/Add48.png"))); // NOI18N
        jMenuItem3.setText("   Nuevo Producto");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mitemProductos.add(jMenuItem3);

        mitemAdminProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/foodpng.png"))); // NOI18N
        mitemAdminProducts.setText("   Productos");
        mitemAdminProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemAdminProductsActionPerformed(evt);
            }
        });
        mitemProductos.add(mitemAdminProducts);

        mbarMain.add(mitemProductos);

        mitemCombos.setText("   Combos");

        jmitemNuevoProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/Add48.png"))); // NOI18N
        jmitemNuevoProd.setText("   Nuevo Combo");
        jmitemNuevoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmitemNuevoProdActionPerformed(evt);
            }
        });
        mitemCombos.add(jmitemNuevoProd);

        mitemAdminCombos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/foodpng.png"))); // NOI18N
        mitemAdminCombos.setText("   Combos");
        mitemAdminCombos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemAdminCombosActionPerformed(evt);
            }
        });
        mitemCombos.add(mitemAdminCombos);

        mbarMain.add(mitemCombos);

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

    private void mitemAdminProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminProductsActionPerformed
        FormsFactory.GetDialogForm("babelsForms.Products", true, null, null);
    }//GEN-LAST:event_mitemAdminProductsActionPerformed

    private void mitemAdminUserChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminUserChangePassActionPerformed
        FormsFactory.GetDialogForm("babelsForms.ChangePassword", true, null, null);
    }//GEN-LAST:event_mitemAdminUserChangePassActionPerformed

private void mitemAdminCombosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemAdminCombosActionPerformed
         FormsFactory.GetDialogForm("babelsForms.Combos", true, null, null);
        
}//GEN-LAST:event_mitemAdminCombosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FormsFactory.GetDialogForm("babelsForms.CashRegister", true, null, null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       FormsFactory.GetDialogForm("babelsForms.NewClient", true, null, null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FormsFactory.GetDialogForm("babelsForms.NewProduct", true, null, null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jmitemNuevoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmitemNuevoProdActionPerformed
       FormsFactory.GetDialogForm("babelsForms.NewCombo", true, null, null);
    }//GEN-LAST:event_jmitemNuevoProdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jmitemNuevoProd;
    private javax.swing.JMenuBar mbarMain;
    private javax.swing.JMenu mitemAdmin;
    private javax.swing.JMenuItem mitemAdminCombos;
    private javax.swing.JMenuItem mitemAdminProducts;
    private javax.swing.JMenu mitemAdminUser;
    private javax.swing.JMenuItem mitemAdminUserChangePass;
    private javax.swing.JMenu mitemCaja;
    private javax.swing.JMenu mitemCliente;
    private javax.swing.JMenu mitemCombos;
    private javax.swing.JMenu mitemFile;
    private javax.swing.JMenuItem mitemFileExit;
    private javax.swing.JMenu mitemProductos;
    private javax.swing.JMenu mitemUsers;
    private javax.swing.JMenuItem mitemUsersAdmin;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    // End of variables declaration//GEN-END:variables
}
