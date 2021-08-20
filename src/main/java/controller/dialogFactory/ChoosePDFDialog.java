package controller.dialogFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ChoosePDFDialog extends JFileChooser {
    public ChoosePDFDialog(File file) {
        super(file);
        addChoosableFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        setMultiSelectionEnabled(false);
        setVisible(true);
    }
}
