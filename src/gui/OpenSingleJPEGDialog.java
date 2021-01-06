package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Realisiert einen JFileChooser um ein einzelnes JPEG auszuwählen.
 */


public class OpenSingleJPEGDialog extends JFileChooser{

    public OpenSingleJPEGDialog()

    {
        setFileSelectionMode(FILES_AND_DIRECTORIES);
        addChoosableFileFilter(new FileNameExtensionFilter("Petrinet File", "pnml"));
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
        setVisible(true);
    }
}
