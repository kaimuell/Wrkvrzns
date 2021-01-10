package controller.DialogController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Realisiert einen JFileChooser um ein einzelnes JPEG auszuwählen.
 */


class chooseSingleJPEGDialog extends JFileChooser{

    chooseSingleJPEGDialog()

    {
        setFileSelectionMode(FILES_AND_DIRECTORIES);
        addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg"));
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
        setVisible(true);
    }
}
