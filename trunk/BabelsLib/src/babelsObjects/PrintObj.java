package babelsObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintObj implements Printable {

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        //Creamos un objeto 2D para dibujar en el
      Graphics2D g2 = (Graphics2D) graphics;
      //Este código imprime 2 páginas una con un cuadrado o marco
      //y una segunda con un circulo en la esquina superior izquierda

      //Creamos el rectángulo
      //getImagebleX() coge la parte de la hoja donde podemos 
      //imprimir quitando los bordes. Si no hiciesemos 
      //esto así y tuviesemos bordes definidos en la impresión 
      //lo que dibujasemos fuera de los bordes no lo 
      //imprimiría aunque cupiese en la hoja físicamente.
      Rectangle2D rect = new Rectangle2D.Double(pageFormat.getImageableX(),
                                                pageFormat.getImageableY(),
                                                pageFormat.getImageableWidth(),
                                                pageFormat.getImageableHeight());

      //Creamos la circunferencia
      Ellipse2D circle = new Ellipse2D.Double(100,100,100,100);

      //pageIndex indica el número de la página que se imprime
      //cuando es 0 primera página a imprimir, es un rectángulo
      //cuando es 1 segunda página a imprimir, es una circunferencia
      //En otro caso se devulve que no hay más páginas a imprimir
      switch (pageIndex)
      {
         case 0 : //Página 1: Dibujamos sobre g y luego lo pasamos a g2
                  graphics.setColor(Color.black);
                  graphics.fillRect(110,120,30,5);
                  graphics.setColor(Color.pink);
                  graphics.drawLine(0,0,200,200);
                  g2 = (Graphics2D) graphics;
                  return PAGE_EXISTS; //La página 1 existe y se imprimirá
         case 1 : //Página 2: Circunferencia y rectángulo
                  g2.setColor(Color.red);
                  g2.draw(circle);
                  g2.draw(rect);
                  return PAGE_EXISTS;  //La página 2 existe y se imprimirá
         default: return NO_SUCH_PAGE;        //No se imprimirán más páginas
      }
    }
    
}
