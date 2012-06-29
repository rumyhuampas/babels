package babelsForms;

import babelsInterfaces.IBabelsDialog;
import babelsManagers.CashRegisterManager;
import babelsManagers.CashRegisterWindowManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CashRegisterWindow extends javax.swing.JDialog implements IBabelsDialog {

    private int Type;

    public CashRegisterWindow(java.awt.Frame parent, boolean modal, int type) {
        super(parent, modal);
        initComponents();
        CashRegisterWindowManager.SetFieldsListeners(this.txtAmount, this);
        Type = type;
        if (Type == 1) {
            this.setTitle("Apertura de Caja");
        }
        if (Type == 2) {
            this.setTitle("Cierre Parcial de Caja");
            this.lblAmount.setText("Ingrese el monto real");
        }
        if (Type == 3) {
            this.setTitle("Cierre Final de Caja");
             this.lblAmount.setText("Ingrese el monto real");
        }
        if (Type == 4) {
            this.setTitle("Extraccion");
        }
        if (Type == 5) {
            this.setTitle("Deposito");
        }
    }

    @Override
    public void ClickOKButton() {
        this.btnOk.doClick();
    }

    @Override
    public void ClickCancelButton() {
        this.btnCancel.doClick();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAmount = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        lblAmount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOk.setText("Aceptar");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblAmount.setText("Monto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAmount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAmount))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            if (Type == 1) {
                CashRegisterWindowManager.doCashOpen(Float.parseFloat(txtAmount.getText()));
                this.dispose();
            }
            if (Type == 2) {
                CashRegisterWindowManager.doCashClose(Float.parseFloat(txtAmount.getText()), true);
                this.dispose();
            }
            if (Type == 3) {
                CashRegisterWindowManager.doCashClose(Float.parseFloat(txtAmount.getText()), false);
                this.dispose();
            }
            if (Type == 4) {
                CashRegisterWindowManager.doCashMove(Float.parseFloat(txtAmount.getText()), false);
                this.dispose();
            }
            if (Type == 5) {
                CashRegisterWindowManager.doCashMove(Float.parseFloat(txtAmount.getText()), true);
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
