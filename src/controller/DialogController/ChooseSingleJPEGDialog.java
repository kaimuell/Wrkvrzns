package controller.DialogController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Realisiert einen JFileChooser um ein einzelnes JPEG auszuwählen.
 */


class ChooseSingleJPEGDialog extends JFileChooser{

    ChooseSingleJPEGDialog()

    {
        setFileSelectionMode(FILES_AND_DIRECTORIES);
        addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg"));
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
        setCurrentDirectory(new File (System.getProperty("user.home")));
        setVisible(true);
    }
}
