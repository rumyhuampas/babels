package babelsForms;

import babelsManagers.CashRegisterManager;
import babelsObjects.FormsFactory;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashRegister extends javax.swing.JDialog {

    private CashRegisterManager Manager;

    public CashRegister(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Manager = new CashRegisterManager(this.tblMovements, this.lblTotal, this.chbOpenClose, this.chbSales, this.chbInOut);
        Date begin = new Date();
        Date end = new Date();
        dcFrom.setDate(begin);
        dcTo.setDate(end);
        btnSearch.doClick();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovements = new javax.swing.JTable();
        dcTo = new com.toedter.calendar.JDateChooser();
        dcFrom = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalTitle = new javax.swing.JLabel();
        chbOpenClose = new javax.swing.JCheckBox();
        chbSales = new javax.swing.JCheckBox();
        chbInOut = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuOperations = new javax.swing.JMenu();
        mItemOpenCash = new javax.swing.JMenuItem();
        menuClose = new javax.swing.JMenu();
        mItemPartialClose = new javax.swing.JMenuItem();
        mItemFinalClose = new javax.swing.JMenuItem();
        mItemCashOut = new javax.swing.JMenuItem();
        mItemCashIn = new javax.swing.JMenuItem();
        MenuReports = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblMovements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Movimiento", "Fecha", "Total", "CancellerId", "Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMovements);
        tblMovements.getColumnModel().getColumn(3).setMinWidth(0);
        tblMovements.getColumnModel().getColumn(3).setPreferredWidth(0);
        tblMovements.getColumnModel().getColumn(3).setMaxWidth(0);
        tblMovements.getColumnModel().getColumn(4).setMinWidth(0);
        tblMovements.getColumnModel().getColumn(4).setPreferredWidth(0);
        tblMovements.getColumnModel().getColumn(4).setMaxWidth(0);

        jLabel1.setText("Hasta:");

        jLabel2.setText("Desde:");

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("David", 1, 36)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(153, 0, 0));
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblTotalTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalTitle.setText("Total:");

        chbOpenClose.setText("Aperturas/Cierres");

        chbSales.setText("Ventas");

        chbInOut.setText("Depositos/Extracciones");

        MenuOperations.setText("Operaciones");

        mItemOpenCash.setText("Apertura Caja");
        mItemOpenCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemOpenCashActionPerformed(evt);
            }
        });
        MenuOperations.add(mItemOpenCash);

        menuClose.setText("Cierre");

        mItemPartialClose.setText("Cierre Parcial");
        menuClose.add(mItemPartialClose);

        mItemFinalClose.setText("Cierre Final");
        menuClose.add(mItemFinalClose);

        MenuOperations.add(menuClose);

        mItemCashOut.setText("Extraccion");
        MenuOperations.add(mItemCashOut);

        mItemCashIn.setText("Deposito");
        mItemCashIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCashInActionPerformed(evt);
            }
        });
        MenuOperations.add(mItemCashIn);

        jMenuBar1.add(MenuOperations);

        MenuReports.setText("Reportes");
        jMenuBar1.add(MenuReports);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbOpenClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbSales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbInOut)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 399, Short.MAX_VALUE)
                                .addComponent(lblTotalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(dcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(dcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dcTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chbOpenClose)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chbSales)
                        .addGap(340, 340, 340))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbInOut)
                        .addGap(340, 340, 340)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTotalTitle)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mItemOpenCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemOpenCashActionPerformed
        Class[] classParam = new Class[3];
        classParam[0] = java.awt.Frame.class;
        classParam[1] = boolean.class;
        classParam[2] = int.class;
        Object[] objectParam = new Object[3];
        objectParam[0] = new javax.swing.JFrame();
        objectParam[1] = true;
        objectParam[2] = 1;
        FormsFactory.GetDialogForm("babelsForms.CashRegisterWindow", true, classParam, objectParam);
        btnSearch.doClick();
    }//GEN-LAST:event_mItemOpenCashActionPerformed

    private void mItemCashInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCashInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mItemCashInActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            Manager.RefreshTable(dcFrom.getDate(), dcTo.getDate());
            tblMovements.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(CashRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CashRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CashRegister dialog = new CashRegister(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuOperations;
    private javax.swing.JMenu MenuReports;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chbInOut;
    private javax.swing.JCheckBox chbOpenClose;
    private javax.swing.JCheckBox chbSales;
    private com.toedter.calendar.JDateChooser dcFrom;
    private com.toedter.calendar.JDateChooser dcTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTitle;
    private javax.swing.JMenuItem mItemCashIn;
    private javax.swing.JMenuItem mItemCashOut;
    private javax.swing.JMenuItem mItemFinalClose;
    private javax.swing.JMenuItem mItemOpenCash;
    private javax.swing.JMenuItem mItemPartialClose;
    private javax.swing.JMenu menuClose;
    private javax.swing.JTable tblMovements;
    // End of variables declaration//GEN-END:variables
}
