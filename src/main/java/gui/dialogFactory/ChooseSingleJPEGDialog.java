package gui.dialogFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Realisiert einen JFileChooser um ein einzelnes JPEG auszuwählen.
 */


class ChooseSingleJPEGDialog extends JFileChooser{

    ChooseSingleJPEGDialog(File homeDirectory)

    {
        super(homeDirectory);
        setFileSelectionMode(FILES_ONLY);
        addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg"));
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
        setVisible(true);
    }
}
