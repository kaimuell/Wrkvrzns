package controller.fileHandler;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;


import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static javax.swing.JFileChooser.APPROVE_OPTION;

//TODO TESTEN UND LÖSCHEN
public class ThunderbirdImportTest {
    public static void main (String[] args){
        ABModel model = new ABModel();
        JFileChooser filechooser = new JFileChooser();
        int returnval = filechooser.showOpenDialog(null);
        File file;
        if (returnval == APPROVE_OPTION){
            file = filechooser.getSelectedFile();
        } else{
            file = new File("./Gesammelte Adressen.csv");
            System.out.println("Adresse nicht ausgewählt.");
        }
        try {
            AddressBookImportParserForThunderbird parser = new AddressBookImportParserForThunderbird(file, model, false);
            parser.load();
            parser.parse();
            parser.copyToAdressbook();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
                e.printStackTrace();

        }

        for (PersonEntry entry : model.getPersonList()) {
            System.out.println(entry.getFirstName() + ", " + entry.getFamilyName() + " : " + entry.geteMail());
        }
    }
}
