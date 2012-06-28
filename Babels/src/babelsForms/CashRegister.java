package babelsForms;

import babelsManagers.CashRegisterManager;
import babelsObjects.FormsFactory;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashRegister extends javax.swing.JDialog {

    private CashRegisterManager Manager;
    public boolean Refresh = true;

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

        popUpTableMovement = new javax.swing.JPopupMenu();
        mItemDetalle = new javax.swing.JMenuItem();
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
        mitemPrint = new javax.swing.JMenuItem();

        mItemDetalle.setText("jMenuItem1");
        popUpTableMovement.add(mItemDetalle);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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
                "Id", "Movimiento", "Fecha", "Total", "CancellerId"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
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
        tblMovements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMovementsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblMovements);
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
        chbOpenClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbOpenCloseActionPerformed(evt);
            }
        });

        chbSales.setText("Ventas");
        chbSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSalesActionPerformed(evt);
            }
        });

        chbInOut.setText("Depositos/Extracciones");
        chbInOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInOutActionPerformed(evt);
            }
        });

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
        mItemPartialClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemPartialCloseActionPerformed(evt);
            }
        });
        menuClose.add(mItemPartialClose);

        mItemFinalClose.setText("Cierre Final");
        mItemFinalClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemFinalCloseActionPerformed(evt);
            }
        });
        menuClose.add(mItemFinalClose);

        MenuOperations.add(menuClose);

        mItemCashOut.setText("Extraccion");
        mItemCashOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCashOutActionPerformed(evt);
            }
        });
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

        mitemPrint.setText("Imprimir");
        mitemPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemPrintActionPerformed(evt);
            }
        });
        MenuReports.add(mitemPrint);

        jMenuBar1.add(MenuReports);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbOpenClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbSales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbInOut)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTotalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(dcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(chbInOut)
                    .addComponent(chbOpenClose, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbSales, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        Manager.doOperation(1);
        this.Refresh = true;
    }//GEN-LAST:event_mItemOpenCashActionPerformed

    private void mItemCashInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCashInActionPerformed
        Manager.doOperation(5);
       this.Refresh = true;
    }//GEN-LAST:event_mItemCashInActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            Manager.RefreshTable(dcFrom.getDate(), dcTo.getDate());
            tblMovements.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(CashRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void mItemPartialCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemPartialCloseActionPerformed
        Manager.doOperation(2);
        this.Refresh = true;
    }//GEN-LAST:event_mItemPartialCloseActionPerformed

    private void mItemFinalCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemFinalCloseActionPerformed
        Manager.doOperation(3);
        this.Refresh = true;
    }//GEN-LAST:event_mItemFinalCloseActionPerformed

    private void mItemCashOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCashOutActionPerformed
        Manager.doOperation(4);
        this.Refresh = true;
    }//GEN-LAST:event_mItemCashOutActionPerformed

    private void chbOpenCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbOpenCloseActionPerformed
        btnSearch.doClick();
    }//GEN-LAST:event_chbOpenCloseActionPerformed

    private void chbSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSalesActionPerformed
        btnSearch.doClick();
    }//GEN-LAST:event_chbSalesActionPerformed

    private void chbInOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInOutActionPerformed
        btnSearch.doClick();
    }//GEN-LAST:event_chbInOutActionPerformed

    private void mitemPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemPrintActionPerformed
        try {
            Manager.doPrint();
        } catch (SQLException ex) {
            Logger.getLogger(CashRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mitemPrintActionPerformed

    private void tblMovementsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovementsMousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (evt.getClickCount() == 1) {
                this.tblMovements.changeSelection(tblMovements.getSelectedRow(), tblMovements.getSelectedColumn(), false, false);
                        
                this.popUpTableMovement.show(this.tblMovements, evt.getX(), evt.getY());

            } else {
                // this.Manager.EditProduct();
                // this.Refresh = true;
            }
        }
    }//GEN-LAST:event_tblMovementsMousePressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
          if (Refresh == true) {
           this.btnSearch.doClick();
           Refresh = false;
          }
                                     
    }//GEN-LAST:event_formWindowActivated

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
    private javax.swing.JMenuItem mItemDetalle;
    private javax.swing.JMenuItem mItemFinalClose;
    private javax.swing.JMenuItem mItemOpenCash;
    private javax.swing.JMenuItem mItemPartialClose;
    private javax.swing.JMenu menuClose;
    private javax.swing.JMenuItem mitemPrint;
    private javax.swing.JPopupMenu popUpTableMovement;
    private javax.swing.JTable tblMovements;
    // End of variables declaration//GEN-END:variables
}
