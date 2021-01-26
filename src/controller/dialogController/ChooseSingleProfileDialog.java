package controller.dialogController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

class ChooseSingleProfileDialog extends JFileChooser {
     ChooseSingleProfileDialog(File homeDirectory){
         super(homeDirectory);
        setFileSelectionMode(FILES_ONLY);
         addChoosableFileFilter(new FileNameExtensionFilter("Werkverzeichnis Profile", "wz"));
         setAcceptAllFileFilterUsed(false);
         setMultiSelectionEnabled(false);
         setVisible(true);

     }
}
