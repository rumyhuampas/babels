/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babelsRenderers;

/**
 *
 * @author BGH
 */
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
 
/**
 *
 * @author EdisonCor
 */
public class LabelImage extends JLabel{
 
    private float tran= 0.5f;
    private Image image=null;
    private Icon icono;
 
    public float getTran() {
        return tran;
    }
 
    public void setTran(float tran) {
        this.tran = tran;
    }
 
    public Image getImage() {
        return image;
    }
 
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
 
    public Icon getIcono() {
        return icono;
    }
 
    public void setIcono(Icon icon){
        this.icono=icon;
        setImage(((ImageIcon)icon).getImage());
    }
 
    @Override
    protected void paintComponent(Graphics g) {
          Graphics2D g2 = (Graphics2D) g;
 
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        Composite oldComposite = g2.getComposite();
        AlphaComposite newComposite =
	    AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,getTran());
        g2.setComposite(newComposite);
        if(getImage()!=null)
            g2.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null);
        g2.setComposite(oldComposite);
        super.paintComponent(g);
    }
 
}