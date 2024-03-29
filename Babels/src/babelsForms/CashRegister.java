package babelsForms;

import babelsManagers.CashRegisterManager;
import babelsManagers.ReportManager;
import babelsObjects.FormsFactory;
import babelsObjects.GarbageCollector;
import babelsRenderers.JFrameBusy;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.jdesktop.swingx.JXBusyLabel;

public class CashRegister extends javax.swing.JDialog {

    private CashRegisterManager Manager;
    public boolean Refresh = true;
    private JXBusyLabel busylabel1;

    public CashRegister(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.pnlBusy1.setLayout(new BorderLayout());
        Manager = new CashRegisterManager(this.tblMovements, this.lblTotal, this.chbOpenClose, this.chbSales, this.chbInOut);
        Date begin = new Date();
        Date end = new Date();
        Calendar cal = new GregorianCalendar();
        Calendar calfin = new GregorianCalendar();
        cal.setTime(begin);
        calfin.setTime(end);
        //cal.add(Calendar.HOUR);
        //calfin.add(Calendar.HOUR);
        Date Begining = cal.getTime();
        Date Final = calfin.getTime();
        // JOptionPane.showMessageDialog(null, Begining);
        //JOptionPane.showMessageDialog(null, Final);
        busylabel1 = createSimpleBusyLabel();
        busylabel1.setEnabled(false);
        this.setModalityType(ModalityType.MODELESS);
        dcFrom.setDate(Begining);
        dcTo.setDate(Final);
        this.Refresh = true;
        this.btnSearch.doClick();
    }

    public JXBusyLabel createSimpleBusyLabel() {
        JXBusyLabel label = new JXBusyLabel();
        label.setToolTipText("Cargando...");
        return label;
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
        pnlBusy1 = new org.jdesktop.swingx.JXPanel();
        btnExport = new org.edisoncor.gui.button.ButtonTask();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuOperations = new javax.swing.JMenu();
        mItemOpenCash = new javax.swing.JMenuItem();
        menuClose = new javax.swing.JMenu();
        mItemPartialClose = new javax.swing.JMenuItem();
        mItemFinalClose = new javax.swing.JMenuItem();
        mItemCashOut = new javax.swing.JMenuItem();
        mItemCashIn = new javax.swing.JMenuItem();
        mItemTotals = new javax.swing.JMenuItem();

        mItemDetalle.setText("Detalle");
        mItemDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemDetalleActionPerformed(evt);
            }
        });
        popUpTableMovement.add(mItemDetalle);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblMovements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Movimiento", "Fecha", "Monto", "CancellerId", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        if (tblMovements.getColumnModel().getColumnCount() > 0) {
            tblMovements.getColumnModel().getColumn(4).setMinWidth(0);
            tblMovements.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblMovements.getColumnModel().getColumn(4).setMaxWidth(0);
        }

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
        lblTotal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblTotal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblTotalTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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

        javax.swing.GroupLayout pnlBusy1Layout = new javax.swing.GroupLayout(pnlBusy1);
        pnlBusy1.setLayout(pnlBusy1Layout);
        pnlBusy1Layout.setHorizontalGroup(
            pnlBusy1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );
        pnlBusy1Layout.setVerticalGroup(
            pnlBusy1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/babelsImages/57-download.png"))); // NOI18N
        btnExport.setText("Exportar");
        btnExport.setDescription("Exportar Tabla Seleccionada");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
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

        mItemTotals.setText("Ver Totales");
        mItemTotals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemTotalsActionPerformed(evt);
            }
        });
        MenuOperations.add(mItemTotals);

        jMenuBar1.add(MenuOperations);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbOpenClose)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbSales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbInOut))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(dcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(dcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlBusy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dcTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addComponent(btnSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbInOut)
                            .addComponent(chbOpenClose, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chbSales, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlBusy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTotalTitle)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
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
            //busylabel1.setEnabled(true);
            //busylabel1.setBusy(true);    
            //this.pnlBusy1.add(busylabel1, BorderLayout.CENTER);
            //this.pnlBusy1.setAlpha(0.7f);
            // JFrameBusy fb = new JFrameBusy(); 
            Manager.RefreshTable(dcFrom.getDate(), dcTo.getDate());
            tblMovements.repaint();
            //GarbageCollector.getSolicitaGarbageColector();   

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

    private void tblMovementsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovementsMousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (evt.getClickCount() == 1) {
                this.tblMovements.changeSelection(tblMovements.getSelectedRow(), tblMovements.getSelectedColumn(), false, false);
                this.popUpTableMovement.show(this.tblMovements, evt.getX(), evt.getY());
            }
        } else {
            if (evt.getClickCount() == 2) {
                try {
                    Manager.getDetail();
                } catch (SQLException ex) {
                    Logger.getLogger(CashRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tblMovementsMousePressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (Refresh == true) {
            this.btnSearch.doClick();
            Refresh = false;
        }

    }//GEN-LAST:event_formWindowActivated

    private void mItemDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemDetalleActionPerformed
        try {
            Manager.getDetail();
        } catch (SQLException ex) {
            Logger.getLogger(CashRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mItemDetalleActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        ReportManager repManager = new ReportManager(this.tblMovements);
        try {
            try {
                this.setModal(false);
                repManager.print();
                this.setModal(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void mItemTotalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemTotalsActionPerformed
        FormsFactory.GetDialogForm("babelsForms.CashRegisterTotals", true, null, null);
    }//GEN-LAST:event_mItemTotalsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuOperations;
    private org.edisoncor.gui.button.ButtonTask btnExport;
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
    private javax.swing.JMenuItem mItemTotals;
    private javax.swing.JMenu menuClose;
    private org.jdesktop.swingx.JXPanel pnlBusy1;
    private javax.swing.JPopupMenu popUpTableMovement;
    private javax.swing.JTable tblMovements;
    // End of variables declaration//GEN-END:variables
}
