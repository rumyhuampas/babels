package babelsForms;

import babels.Babels;
import babelsObjects.UsersAdmin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Users extends javax.swing.JDialog {

    public Users(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        UsersAdmin userAdm = new UsersAdmin(tblUsers);
        tblUsers.getModel().addTableModelListener(userAdm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Admin", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUsers);

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            RefreshUsersTable();
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void RefreshUsersTable() throws SQLException {
        ClearUsersTable();
        LoadUsers();
    }

    private void ClearUsersTable() {
        DefaultTableModel tm = (DefaultTableModel) tblUsers.getModel();
        tm.getDataVector().removeAllElements();
    }

    private void LoadUsers() throws SQLException {
        Babels.mysql.Open();
        Object[] rows = UsersAdmin.GetAllUsers(Babels.mysql.Conn);
        Object[] row = null;
        DefaultTableModel tm = (DefaultTableModel) tblUsers.getModel();
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            row = (Object[]) rows[rowIdx];
            tm.addRow(row);
        }
        Babels.mysql.Close();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}
