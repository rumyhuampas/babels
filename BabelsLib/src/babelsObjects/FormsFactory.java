package babelsObjects;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

public class FormsFactory {

    public static void GetDialogForm(final String formName, final boolean disposeOnExit,
            final Class[] classPrm, final Object[] objectPrm) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Class[] classParam = classPrm;
                if (classParam == null) {
                    classParam = new Class[2];
                    classParam[0] = java.awt.Frame.class;
                    classParam[1] = boolean.class;
                }
                Object[] objectParam = objectPrm;
                if (objectParam == null) {
                    objectParam = new Object[2];
                    objectParam[0] = new javax.swing.JFrame();
                    objectParam[1] = true;
                }
                Class cl;
                try {
                    cl = Class.forName(formName);
                    java.lang.reflect.Constructor co;
                    try {
                        co = cl.getConstructor(classParam);
                        try {
                            final JDialog dialog = (JDialog) co.newInstance(objectParam);
                            dialog.setLocationRelativeTo(null);
                            dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                                @Override
                                public void windowClosing(java.awt.event.WindowEvent e) {
                                    if (disposeOnExit == true) {
                                        dialog.dispose();
                                    } else {
                                        System.exit(0);
                                    }
                                }
                            });
                            dialog.setVisible(true);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FormsFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
