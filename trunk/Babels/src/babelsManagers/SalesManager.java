/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babelsManagers;

import babelsForms.Sales;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 *
 * @author BGH
 * 
 */
public class SalesManager {
public void SetWindowSettings (Sales WindowSales) {
    
        WindowSales.setLocationRelativeTo(null);
        WindowSales.setExtendedState(Frame.MAXIMIZED_BOTH);
   
        }
public void SetTopTabbedPane (Sales WindowSales){
        JTabbedPane tp = new JTabbedPane();
     
        tp.add("Combos", new JLabel("En la pestaña uno"));
        tp.add("Unitarios", new JLabel("En la pestaña dos"));
      
        // Los iconos
        tp.setIconAt(0, new ImageIcon("cuscus.jpg"));
        tp.setIconAt(1, new ImageIcon("cuscus.jpg"));
       
        // Los colores de fondo
        tp.setBackgroundAt(0, Color.black);
        tp.setBackgroundAt(1, Color.red);
        
        // Se visualiza todo
        WindowSales.getContentPane().add(tp);
       

}

}
