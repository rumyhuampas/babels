package babelsManagers;

import babelsFilters.ImagesFilter;
import babelsForms.NewProduct;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class NewProductManager {

    public Icon ChooseProductImage(NewProduct newProductWindow) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Elija la imagen del producto");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new ImagesFilter());
        if (fc.showOpenDialog(newProductWindow) == JFileChooser.APPROVE_OPTION) {
            return createImageIcon(fc.getSelectedFile());
        } else {
            return null;
        }
    }

    private Icon createImageIcon(File chosenFile) {
        return new ImageIcon(chosenFile.getPath());
    }
}
