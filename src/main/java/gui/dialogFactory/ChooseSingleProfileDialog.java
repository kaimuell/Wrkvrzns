package gui.dialogFactory;

import languagePack.LanguagePackContainer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

class ChooseSingleProfileDialog extends JFileChooser {
     ChooseSingleProfileDialog(File homeDirectory){
         super(homeDirectory);
        setFileSelectionMode(FILES_ONLY);
         addChoosableFileFilter(new FileNameExtensionFilter(LanguagePackContainer.getLanguagePack().getChooseSingleProfileDialogDescription(), "wz"));
         setAcceptAllFileFilterUsed(false);
         setMultiSelectionEnabled(false);
         setVisible(true);

     }
}
