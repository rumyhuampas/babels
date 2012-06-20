package babelsForms;

import babelsManagers.ProductsManager;
import babelsObjects.FormsFactory;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Products extends javax.swing.JDialog {

    private ProductsManager Manager;
    public boolean Refresh = true;

    public Products(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Manager = new ProductsManager(tblProducts);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmenuTblProducts = new javax.swing.JPopupMenu();
        pmitemEdit = new javax.swing.JMenuItem();
        pmitemDelete = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        lblImg = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnNewproduct = new javax.swing.JButton();

        pmenuTblProducts.setToolTipText("");

        pmitemEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pmitemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/edit.png"))); // NOI18N
        pmitemEdit.setText("Editar");
        pmitemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmitemEditActionPerformed(evt);
            }
        });
        pmenuTblProducts.add(pmitemEdit);

        pmitemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/delete.png"))); // NOI18N
        pmitemDelete.setText("Eliminar");
        pmitemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmitemDeleteActionPerformed(evt);
            }
        });
        pmenuTblProducts.add(pmitemDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Babels - Administrador de Productos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProductsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);
        tblProducts.getColumnModel().getColumn(0).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProducts.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProducts.getColumnModel().getColumn(2).setMinWidth(100);
        tblProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblProducts.getColumnModel().getColumn(2).setMaxWidth(100);

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnNewproduct.setText("Nuevo Producto");
        btnNewproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewproductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNewproduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClose)
                            .addComponent(btnNewproduct))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (Refresh == true) {
            try {
                this.Manager.RefreshTable();
                if (this.tblProducts.getRowCount() > 0) {
                    //this.tblProducts.setRowSelectionInterval(0, 0);
                    this.tblProducts.changeSelection(0, 0, false, false);
                    try {
                        this.lblImg.setIcon(this.Manager.GetProductImage(lblImg.getWidth(), lblImg.getHeight()));
                    } catch (SQLException ex) {
                        Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Refresh = false;
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnNewproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewproductActionPerformed
        FormsFactory.GetDialogForm("babelsForms.NewProduct", true, null, null);
        this.Refresh = true;
    }//GEN-LAST:event_btnNewproductActionPerformed

    private void pmitemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmitemEditActionPerformed
        this.Manager.EditProduct();
        this.Refresh = true;
    }//GEN-LAST:event_pmitemEditActionPerformed

    private void pmitemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmitemDeleteActionPerformed
        int row = this.tblProducts.getSelectedRow();
        String prodName = (String) this.tblProducts.getModel().getValueAt(row, 1);
        if (JOptionPane.showConfirmDialog(null, "¿Eliminar el producto " + prodName + "?",
                "¿Eliminar?", JOptionPane.YES_NO_OPTION) == 0) {
            try {
                this.Manager.DeleteProduct();
                this.Refresh = true;
            } catch (SQLException ex) {
                Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pmitemDeleteActionPerformed

private void tblProductsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMousePressed
    if (evt.getButton() == MouseEvent.BUTTON1) {
        if (evt.getClickCount() == 1) {
            try {
                this.lblImg.setIcon(this.Manager.GetProductImage(lblImg.getWidth(), lblImg.getHeight()));
            } catch (SQLException ex) {
                Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.Manager.EditProduct();
            this.Refresh = true;
        }
    } else {
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int row = this.tblProducts.rowAtPoint(evt.getPoint());
            this.tblProducts.changeSelection(row, 0, false, false);
            this.pmenuTblProducts.show(this.tblProducts, evt.getX(), evt.getY());
        }
    }
}//GEN-LAST:event_tblProductsMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewproduct;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImg;
    private javax.swing.JPopupMenu pmenuTblProducts;
    private javax.swing.JMenuItem pmitemDelete;
    private javax.swing.JMenuItem pmitemEdit;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}
