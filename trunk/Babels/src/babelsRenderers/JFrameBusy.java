
package babelsRenderers;

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;

public class JFrameBusy extends JDialog {
    
    public JFrameBusy(){
        JDialog frame = new JDialog();

        // add the panel to this frame
        frame.add(doInit());

        // when you close the frame, the app exits
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // center the frame and show it
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * creates a JXLabel and attaches a painter to it.
     */
    public void doClose(){
    this.dispose();
    }
    private Component doInit() {
        JXPanel panel = new JXPanel();
        panel.setLayout(new BorderLayout());

        // create a busylabel
        final JXBusyLabel busylabel1 = createSimpleBusyLabel();
        busylabel1.setEnabled(false);

        // create a label
        final JXLabel label = createLabel();
        
        busylabel1.setEnabled(true);
        busylabel1.setBusy(true);
        // create a button
      //  JButton button = new JButton("start/stop");
       /* button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!busylabel1.isEnabled()) {
                    busylabel1.setEnabled(true);
                }

                if (busylabel1.isBusy()) {
                    label.setText("BusyLabel stopped");
                    busylabel1.setBusy(false);
                } else {
                    label.setText("BusyLabel started");
                    busylabel1.setBusy(true);
                }
            }
        }); */

        // set the transparency of the JXPanel to 50% transparent
        panel.setAlpha(0.7f);

        // add the label, busylables, and button to the panel
       // panel.add(label, BorderLayout.NORTH);
        JXPanel busylabels = new JXPanel(new FlowLayout(FlowLayout.CENTER, 40, 5));
        busylabels.add(busylabel1);
        panel.add(busylabels, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(250, 125));

        return panel;
    }

    public JXBusyLabel createSimpleBusyLabel() {
        JXBusyLabel label = new JXBusyLabel();
        label.setToolTipText("simple busy label");
        return label;
    }

    public JXLabel createLabel() {
        JXLabel label = new JXLabel();
       // label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        //label.setText("<html>BusyLabel Example...<br>click start/stop button</html>");
        //label.setIcon(Imagenes.NetworkDisconnected.getIcon(40, 40));
        label.setHorizontalAlignment(JXLabel.LEFT);
        label.setBackgroundPainter(getPainter());
        return label;
    }

    /**
     * this painter draws a gradient fill
     */
    public Painter getPainter() {
        int width = 100;
        int height = 100;
        Color color1 = Colors.White.color(0.5f);
        Color color2 = Colors.Gray.color(0.5f);

        LinearGradientPaint gradientPaint =
                new LinearGradientPaint(0.0f, 0.0f, width, height,
                new float[]{0.0f, 1.0f},
                new Color[]{color1, color2});
        MattePainter mattePainter = new MattePainter(gradientPaint);
        return mattePainter;
    }
}
