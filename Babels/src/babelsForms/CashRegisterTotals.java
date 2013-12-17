package babelsForms;

import babelsInterfaces.IBabelsDialog;
import babelsManagers.CashRegisterTotalsManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashRegisterTotals extends javax.swing.JDialog implements IBabelsDialog {

    private CashRegisterTotalsManager Manager;
    
    public CashRegisterTotals(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Manager = new CashRegisterTotalsManager();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDay = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtABSalesDay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtXSalesDay = new javax.swing.JTextField();
        txtTotalSalesDay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlMonth = new javax.swing.JPanel();
        txtXSalesMonth = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtABSalesMonth = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotalSalesMonth = new javax.swing.JTextField();
        pnlYear = new javax.swing.JPanel();
        txtXSalesYear = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtABSalesYear = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotalSalesYear = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Babel's Totales");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlDay.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales del dia"));

        jLabel3.setText("Ventas A/B:");

        txtABSalesDay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtABSalesDay.setEnabled(false);

        jLabel4.setText("Ventas X:");

        txtXSalesDay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtXSalesDay.setEnabled(false);

        txtTotalSalesDay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotalSalesDay.setEnabled(false);

        jLabel9.setText("Ventas Totales:");

        javax.swing.GroupLayout pnlDayLayout = new javax.swing.GroupLayout(pnlDay);
        pnlDay.setLayout(pnlDayLayout);
        pnlDayLayout.setHorizontalGroup(
            pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDayLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtABSalesDay, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(pnlDayLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtXSalesDay, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(pnlDayLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalSalesDay, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDayLayout.setVerticalGroup(
            pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtABSalesDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXSalesDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalSalesDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMonth.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales del mes"));

        txtXSalesMonth.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtXSalesMonth.setEnabled(false);

        jLabel5.setText("Ventas X:");

        txtABSalesMonth.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtABSalesMonth.setEnabled(false);

        jLabel6.setText("Ventas A/B:");

        jLabel10.setText("Ventas Totales:");

        txtTotalSalesMonth.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotalSalesMonth.setEnabled(false);

        javax.swing.GroupLayout pnlMonthLayout = new javax.swing.GroupLayout(pnlMonth);
        pnlMonth.setLayout(pnlMonthLayout);
        pnlMonthLayout.setHorizontalGroup(
            pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMonthLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtABSalesMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(pnlMonthLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtXSalesMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(pnlMonthLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalSalesMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMonthLayout.setVerticalGroup(
            pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtABSalesMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXSalesMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalSalesMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlYear.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales del año"));

        txtXSalesYear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtXSalesYear.setEnabled(false);

        jLabel7.setText("Ventas X:");

        txtABSalesYear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtABSalesYear.setEnabled(false);

        jLabel8.setText("Ventas A/B:");

        jLabel11.setText("Ventas Totales:");

        txtTotalSalesYear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotalSalesYear.setEnabled(false);

        javax.swing.GroupLayout pnlYearLayout = new javax.swing.GroupLayout(pnlYear);
        pnlYear.setLayout(pnlYearLayout);
        pnlYearLayout.setHorizontalGroup(
            pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlYearLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtABSalesYear, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(pnlYearLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtXSalesYear, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlYearLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalSalesYear, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlYearLayout.setVerticalGroup(
            pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtABSalesYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXSalesYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalSalesYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Object[] info = Manager.getInfo();
            
            Object[] dayInfo = (Object[]) info[0];
            Object abTotalDay = dayInfo[0];
            txtABSalesDay.setText(abTotalDay.toString());
            Object xTotalDay = dayInfo[1];
            txtXSalesDay.setText(xTotalDay.toString());
            Object TotalDay = dayInfo[2];
            txtTotalSalesDay.setText(TotalDay.toString());
            
            Object[] monthInfo = (Object[]) info[1];
            Object abTotalMonth = monthInfo[0];
            txtABSalesMonth.setText(abTotalMonth.toString());
            Object xTotalMonth = monthInfo[1];
            txtXSalesMonth.setText(xTotalDay.toString());
            Object TotalMonth = monthInfo[2];
            txtTotalSalesMonth.setText(TotalMonth.toString());
            
            Object[] yearInfo = (Object[]) info[2];
            Object abTotalYear = yearInfo[0];
            txtABSalesYear.setText(abTotalYear.toString());
            Object xTotalYear = yearInfo[1];
            txtXSalesYear.setText(xTotalYear.toString());
            Object TotalYear = yearInfo[2];
            txtTotalSalesYear.setText(TotalYear.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CashRegisterTotals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    @Override
    public void ClickOKButton() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ClickCancelButton() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnlDay;
    private javax.swing.JPanel pnlMonth;
    private javax.swing.JPanel pnlYear;
    private javax.swing.JTextField txtABSalesDay;
    private javax.swing.JTextField txtABSalesMonth;
    private javax.swing.JTextField txtABSalesYear;
    private javax.swing.JTextField txtTotalSalesDay;
    private javax.swing.JTextField txtTotalSalesMonth;
    private javax.swing.JTextField txtTotalSalesYear;
    private javax.swing.JTextField txtXSalesDay;
    private javax.swing.JTextField txtXSalesMonth;
    private javax.swing.JTextField txtXSalesYear;
    // End of variables declaration//GEN-END:variables
}
