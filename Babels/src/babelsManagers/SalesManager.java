/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babelsManagers;

import babelsForms.Sales;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
/**
 *
 * @author BGH
 * 
 */
public class SalesManager {
    
        private		JTabbedPane tabbedPane;
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
        
        
public void SetWindowSettings (Sales WindowSales) {
    
        WindowSales.setLocationRelativeTo(null);
        WindowSales.setExtendedState(Frame.MAXIMIZED_BOTH);
        WindowSales.setLayout(new BorderLayout());
   
        }
public void SetTopTabbedPane (Sales WindowSales){
       
                JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		WindowSales.getContentPane().add( topPanel );
               
                createPage1();
                tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "prueba",new ImageIcon("lock.png"), panel1 );
		tabbedPane.setBackgroundAt(0, Color.black);
                topPanel.add( tabbedPane, BorderLayout.CENTER );
             
}
public void createPage1()
	{
		panel1 = new JPanel();
		panel1.setLayout( null );

		JLabel label1 = new JLabel( "Username:" );
		label1.setBounds( 10, 15, 150, 20 );
		panel1.add( label1 );

		JTextField field = new JTextField();
		field.setBounds( 10, 35, 150, 20 );
		panel1.add( field );

		

		
	}

}
