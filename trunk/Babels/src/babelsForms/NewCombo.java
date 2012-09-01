package babelsForms;

import babelsComponents.ImageManagement;
import babelsComponents.TransferableProductPanel;
import babelsInterfaces.IBabelsDialog;
import babelsManagers.NewComboManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewCombo extends javax.swing.JDialog implements IBabelsDialog {

    private NewComboManager Manager;
    public boolean Refresh = true;
    private int ComboId = -1;
    private boolean ImageChanged = false;

    public NewCombo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Manager = new NewComboManager(this.tblProducts, this.pnlCombo);
        this.Manager.SetFieldsListeners(this.txtName, this.txtPrice, this.txtaDesc, this);
    }
    public NewCombo(java.awt.Frame parent, boolean modal, int IdCombo) {
        super(parent, modal);
        initComponents();
        this.Manager = new NewComboManager(this.tblProducts, this.pnlCombo);
        this.Manager.SetFieldsListeners(this.txtName, this.txtPrice, this.txtaDesc, this);
         if (IdCombo != -1) {
            try {
                this.ComboId = IdCombo;
                this.Manager.LoadCombo(ComboId, this.txtName, this.txtaDesc, this.txtPrice, this.lblImg);
            } catch (SQLException ex) {
                Logger.getLogger(NewProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private NewCombo() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void ClickOKButton() {
        this.btnOK.doClick();
    }

    @Override
    public void ClickCancelButton() {
        this.btnCancel.doClick();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblDesc = new javax.swing.JLabel();
        spnlDesc = new javax.swing.JScrollPane();
        txtaDesc = new javax.swing.JTextArea();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlCombo = new javax.swing.JPanel();
        lblHelp = new javax.swing.JLabel();
        lblImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Babels - Nuevo Combo");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio", "Imágen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
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
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblProducts);
        tblProducts.getColumnModel().getColumn(0).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProducts.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProducts.getColumnModel().getColumn(2).setMinWidth(50);
        tblProducts.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblProducts.getColumnModel().getColumn(2).setMaxWidth(50);

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOK.setText("Aceptar");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        lblName.setText("Nombre:");

        lblDesc.setText("Descripción:");

        txtaDesc.setColumns(20);
        txtaDesc.setRows(5);
        spnlDesc.setViewportView(txtaDesc);

        lblPrice.setText("Precio:");

        pnlCombo.setAutoscrolls(true);

        javax.swing.GroupLayout pnlComboLayout = new javax.swing.GroupLayout(pnlCombo);
        pnlCombo.setLayout(pnlComboLayout);
        pnlComboLayout.setHorizontalGroup(
            pnlComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
        pnlComboLayout.setVerticalGroup(
            pnlComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlCombo);

        lblHelp.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        lblHelp.setText("Arrastre los productos aqui para armar el combo");

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImg.setText("<html>Click para cargar imágen<br>\n(no superiores a 1 MB)\n</html>");
        lblImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDesc)
                                    .addComponent(lblName)
                                    .addComponent(lblPrice))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnlDesc)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHelp)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDesc)
                            .addComponent(spnlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice))))
                .addGap(6, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOK))
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
                }
                Refresh = false;
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (this.Manager.CheckFields(this.txtName, this.txtPrice) == true) {
            try {
                if (this.Manager.SaveCombo(this.ComboId, this.txtName.getText(), this.txtaDesc.getText(),
                        this.txtPrice.getText(),this.lblImg.getToolTipText(), this.Manager.GetComboProducts(), this.ImageChanged) == true) {
                    if (ComboId == -1) {    
                    if (JOptionPane.showConfirmDialog(this, "Desea agregar otro Combo?",
                                "Nuevo Combo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            this.txtName.setText("");
                            this.txtPrice.setText("");
                            this.txtaDesc.setText("");
                            this.ImageChanged = false;
                            this.lblImg.setText("<html>Click para cargar imágen<br>(no superiores a 1 MB)</html>");
                            this.lblImg.setIcon(null);
                            this.lblImg.setToolTipText("");
                            this.pnlCombo.removeAll();
                    } else {
                            this.dispose();
                        }
                    } else {
                        this.dispose();
                    }
                            
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    
    }//GEN-LAST:event_btnOKActionPerformed

    private void lblImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMouseClicked
        File image = this.Manager.ChooseProductImage(this);
        if (image != null) {

            Image image2 = Toolkit.getDefaultToolkit().getImage(image.getPath());
            ImageManagement gImg = new ImageManagement(image2);
            image2 = gImg.getImage();
            BufferedImage tnsImg = new BufferedImage(lblImg.getWidth(), lblImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = tnsImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image2, 0, 0, lblImg.getWidth(), lblImg.getHeight(), null);
            Icon icon = new ImageIcon(tnsImg);
            if (icon != null) {
                this.lblImg.setText("");
                this.lblImg.setIcon(icon);
                this.lblImg.setToolTipText(image.getPath());

                this.ImageChanged = true;
            }
        }
    }//GEN-LAST:event_lblImgMouseClicked
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewCombo().setVisible(true);
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JPanel pnlCombo;
    private javax.swing.JScrollPane spnlDesc;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextArea txtaDesc;
    // End of variables declaration//GEN-END:variables
}
