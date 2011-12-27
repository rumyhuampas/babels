package babelsFilters;

import java.io.File;

public class ImagesFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory()
                || f.getName().toLowerCase().endsWith(".png")
                || f.getName().toLowerCase().endsWith(".bmp")
                || f.getName().toLowerCase().endsWith(".jpg")
                || f.getName().toLowerCase().endsWith(".jpeg")
                || f.getName().toLowerCase().endsWith(".gif")
                || f.getName().toLowerCase().endsWith(".ico");
    }

    @Override
    public String getDescription() {
        return "Archivos de Imágen";
    }
}
