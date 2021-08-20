package gui.elements;

import model.elements.ArtworkType;

import java.awt.*;

import static model.elements.ArtworkType.*;

public class ArtworkTypeChoice extends Choice {

    public ArtworkTypeChoice() {
        add("PAINTING");
        add("GRAFIK");
        add("SCULPTURE");
        add("INSTALLATION");
        add("VIDEO");
    }

    public ArtworkType getSelectedArtworkType(){
        switch (getSelectedItem()){
            case "PAINTING"     : return PAINTING;
            case "GRAFIK"       : return GRAFIK;
            case "SCULPTURE"    : return SCULPTURE;
            case "INSTALLATION" : return INSTALLATION;
            case "VIDEO"        : return VIDEO;
        }
        return null;
    }
}
