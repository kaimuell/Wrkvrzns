package tests;

import adressbook.model.ABModel;
import controller.FileHandler.FileHandler;
import controller.FileHandler.SaveFileParser;
import controller.FileHandler.VersionControllException;
import model.Model;
import model.elements.ArtPieceEntry;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class LoadSaveTest {
    public static void main (String[] args){


        FileHandler fileHandler = new FileHandler();
        Model model = new Model(new ABModel());
        model.getPieces().add(ArtPieceEntry.createEmptyArtPieceEntry());
        System.out.println("Elemente in Model : " + model.getPieces().size());
        try {
            fileHandler.save(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            model = fileHandler.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (VersionControllException e) {
            e.printStackTrace();
        }
        System.out.println("Elemente in Model : " + model.getPieces().size());
         /*
        Model model = new Model(new ABModel());
        model.getPieces().add(ArtPieceEntry.createEmptyArtPieceEntry());
        String s = SaveFileParser.parseFileOutput(model);
        Iterator<String> lines = s.lines().iterator();
        try {
            model = SaveFileParser.parseFileInput(lines);
            for (ArtPieceEntry entry : model.getPieces()) {
                System.out.println(entry.getId());
                System.out.println(entry.getName());
                System.out.println(entry.getTechnique());
                System.out.println(entry.getType());
                System.out.println(entry.getHeight());
                System.out.println(entry.getWidth());
                System.out.println(entry.getDepth());
                System.out.println(entry.getYear());
                System.out.println(entry.getPrice());
                System.out.println(entry.getBuyer() == null);
            }
        } catch (VersionControllException e) {
            System.out.println("Falsche Version");
        }

          */
    }

}
