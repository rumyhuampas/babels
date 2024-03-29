/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Combos.java
 *
 * Created on 13/03/2012, 17:52:03
 */
package babelsForms;

import babelsManagers.CombosManager;
import babelsObjects.FormsFactory;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BGH
 */
public class Combos extends javax.swing.JDialog {

    /**
     * Creates new form Combos
     */
    private Boolean Refresh = true;
    private CombosManager Manager;

    public Combos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Manager = new CombosManager(tblCombos, tblProducts);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmMenuTblCombos = new javax.swing.JPopupMenu();
        jmItemEdit = new javax.swing.JMenuItem();
        jmItemDelete = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCombos = new javax.swing.JTable();
        btnNewCombo = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jmItemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/edit.png.png"))); // NOI18N
        jmItemEdit.setText("Editar");
        jmItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmItemEditActionPerformed(evt);
            }
        });
        jmMenuTblCombos.add(jmItemEdit);

        jmItemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/Delete.png"))); // NOI18N
        jmItemDelete.setText("Eliminar");
        jmItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmItemDeleteActionPerformed(evt);
            }
        });
        jmMenuTblCombos.add(jmItemDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Babels - Administrador de Combos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblCombos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Descripcion", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCombos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCombosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCombos);
        tblCombos.getColumnModel().getColumn(0).setMinWidth(0);
        tblCombos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblCombos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblCombos.getColumnModel().getColumn(1).setMinWidth(190);
        tblCombos.getColumnModel().getColumn(1).setPreferredWidth(190);
        tblCombos.getColumnModel().getColumn(1).setMaxWidth(190);
        tblCombos.getColumnModel().getColumn(2).setMinWidth(0);
        tblCombos.getColumnModel().getColumn(2).setPreferredWidth(0);
        tblCombos.getColumnModel().getColumn(2).setMaxWidth(0);

        btnNewCombo.setText("Nuevo Combo");
        btnNewCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewComboActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio", "Imagen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProducts);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Combos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Productos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNewCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewCombo)
                    .addComponent(btnCerrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
// TODO add your handling code here:
    if (Refresh == true) {
        try {
            this.Manager.RefreshTableCombos();
            if (this.tblCombos.getRowCount() > 0) {
                //this.tblProducts.setRowSelectionInterval(0, 0);
                this.tblCombos.changeSelection(0, 0, false, false);
                this.Manager.GetTableProducts();
            }
            Refresh = false;
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}//GEN-LAST:event_formWindowActivated

private void tblCombosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCombosMousePressed
    if (evt.getButton() == MouseEvent.BUTTON1) {
        try {

            this.Manager.GetTableProducts();
            if (this.tblProducts.getRowCount() > 0) {
                //this.tblProducts.setRowSelectionInterval(0, 0);
                this.tblProducts.changeSelection(0, 0, false, false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int row = this.tblCombos.rowAtPoint(evt.getPoint());
            this.tblCombos.changeSelection(row, 0, false, false);
            this.jmMenuTblCombos.show(this.tblCombos, evt.getX(), evt.getY());
        }
    }
}//GEN-LAST:event_tblCombosMousePressed

private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
    this.dispose();
}//GEN-LAST:event_btnCerrarActionPerformed

private void btnNewComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewComboActionPerformed
    FormsFactory.GetDialogForm("babelsForms.NewCombo", true, null, null);
    this.Refresh = true;
}//GEN-LAST:event_btnNewComboActionPerformed

private void jmItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmItemDeleteActionPerformed
    int row = this.tblCombos.getSelectedRow();
    String combName = (String) this.tblCombos.getModel().getValueAt(row, 1);
    if (JOptionPane.showConfirmDialog(null, "¿Eliminar el Combo " + combName + "?",
            "¿Eliminar?", JOptionPane.YES_NO_OPTION) == 0) {
        try {
            this.Manager.DeleteCombo();
            this.Refresh = true;
        } catch (SQLException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}//GEN-LAST:event_jmItemDeleteActionPerformed

private void jmItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmItemEditActionPerformed
    Manager.EditCombo();
    this.Refresh = true;
}//GEN-LAST:event_jmItemEditActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnNewCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem jmItemDelete;
    private javax.swing.JMenuItem jmItemEdit;
    private javax.swing.JPopupMenu jmMenuTblCombos;
    private javax.swing.JTable tblCombos;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}
