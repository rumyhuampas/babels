package babelsForms;

import babelsComponents.ImageManagement;
import babelsInterfaces.IBabelsDialog;
import babelsManagers.NewProductManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class NewProduct extends javax.swing.JDialog implements IBabelsDialog {

    private NewProductManager Manager;
    private int ProdId = -1;
    private boolean ImageChanged = false;

    public NewProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Manager = new NewProductManager();
        this.Manager.SetFieldsListeners(this.txtName, this.txtPrice, this.txtaDesc, this.txtCostPrice, this.txtPricePackaging, this.txtIva, this);
        this.txtName.requestFocus();
        AutoCompleteDecorator.decorate(this.comboKitchen);
        AutoCompleteDecorator.decorate(this.comboCategories);
        this.txtIva.setText("21");
    }

    public NewProduct(java.awt.Frame parent, boolean modal, int prodId) {
        super(parent, modal);
        initComponents();
        AutoCompleteDecorator.decorate(this.comboKitchen);
        AutoCompleteDecorator.decorate(this.comboCategories);
        this.txtName.requestFocus();
        this.Manager = new NewProductManager();
        this.Manager.SetFieldsListeners(this.txtName, this.txtPrice, this.txtaDesc, this.txtCostPrice, this.txtPricePackaging, this.txtIva, this);
        this.txtIva.setText("21");
        if (prodId != -1) {
            try {
                this.ProdId = prodId;
                this.Manager.LoadProduct(prodId, this.txtName, this.txtaDesc, this.txtPrice, this.lblImg, this.txtCostPrice, this.txtIva, this.txtPricePackaging, this.comboCategories, this.comboKitchen);
            } catch (SQLException ex) {
                Logger.getLogger(NewProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

        lblImg = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        spnlDesc = new javax.swing.JScrollPane();
        txtaDesc = new javax.swing.JTextArea();
        txtPrice = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtCostPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPricePackaging = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboKitchen = new javax.swing.JComboBox();
        comboCategories = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Babels - Nuevo Producto");
        setName("frmNewProduct"); // NOI18N
        setResizable(false);

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImg.setText("<html>Click para cargar imágen<br>\n(no superiores a 1 MB)\n</html>");
        lblImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgMouseClicked(evt);
            }
        });

        lblName.setText("Nombre:");

        lblDesc.setText("Descripción:");

        txtaDesc.setColumns(20);
        txtaDesc.setRows(5);
        spnlDesc.setViewportView(txtaDesc);

        lblPrice.setText("Precio Venta:");

        btnOK.setText("Aceptar");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Precio Costo:");

        jLabel2.setText("Precio Costo Packaging:");

        txtIva.setText("21");

        jLabel3.setText("<html>Carga Impositiva: <br>\n(Debito fiscal  <br>\ndel producto)<html>");

        comboKitchen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", " " }));

        comboCategories.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Comida", "Bebidas S/A", "Bebidas C/A", "Acompañamiento", "Helados", "Tortas", "Tartas" }));
        comboCategories.setNextFocusableComponent(btnOK);
        comboCategories.setOpaque(true);

        jLabel4.setText("Cocina:");

        jLabel5.setText("Categoria:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(168, 168, 168)))
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(61, 61, 61)
                        .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(txtPricePackaging, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33)
                        .addComponent(comboKitchen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnOK)
                                .addGap(6, 6, 6)
                                .addComponent(btnCancel)))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblName)
                                .addGap(21, 21, 21)
                                .addComponent(lblDesc))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(spnlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPricePackaging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboKitchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOK)
                            .addComponent(btnCancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (this.Manager.CheckFields(this.txtName, this.txtPrice, this.txtCostPrice, this.txtPricePackaging, this.txtIva) == true) {
            try {
                if (this.Manager.SaveProduct(this.ProdId, this.txtName.getText(), this.txtaDesc.getText(),
                        this.txtPrice.getText(), this.txtCostPrice.getText(), this.txtPricePackaging.getText(),
                        this.txtIva.getText(), this.comboKitchen.getSelectedIndex(), this.comboCategories.getSelectedIndex(),
                        this.lblImg.getToolTipText(), this.ImageChanged) == true) {
                    if (ProdId == -1) {
                        if (JOptionPane.showConfirmDialog(this, "Desea agregar otro producto?",
                                "Nuevo Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            this.txtName.setText("");
                            this.txtName.requestFocus();
                            this.txtPrice.setText("");
                            this.txtaDesc.setText("");
                            this.lblImg.setText("<html>Click para cargar imágen<br>(no superiores a 1 MB)</html>");
                            this.lblImg.setIcon(null);
                            this.lblImg.setToolTipText("");
                            this.ImageChanged = false;
                            this.txtCostPrice.setText("");
                            this.txtIva.setText("21");
                            this.txtPricePackaging.setText("");
                            this.comboCategories.setSelectedIndex(0);
                            this.comboKitchen.setSelectedIndex(0);
                        } else {
                            this.dispose();
                        }
                    } else {
                        this.dispose();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOKActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox comboCategories;
    private javax.swing.JComboBox comboKitchen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JScrollPane spnlDesc;
    private javax.swing.JTextField txtCostPrice;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPricePackaging;
    private javax.swing.JTextArea txtaDesc;
    // End of variables declaration//GEN-END:variables
}
