package babelsForms;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbarMain = new javax.swing.JMenuBar();
        mitemFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mitemUsers = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mitemFile.setText("Archivo");

        jMenuItem1.setText("jMenuItem1");
        mitemFile.add(jMenuItem1);

        mbarMain.add(mitemFile);

        mitemUsers.setText("Usuarios");
        mbarMain.add(mitemUsers);

        setJMenuBar(mbarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar mbarMain;
    private javax.swing.JMenu mitemFile;
    private javax.swing.JMenu mitemUsers;
    // End of variables declaration//GEN-END:variables
}
