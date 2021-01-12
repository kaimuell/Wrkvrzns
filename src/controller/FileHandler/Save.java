package controller.FileHandler;

import adressbook.model.ABModel;
import model.Model;
import model.elements.ArtPieceEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Save implements Serializable {
    List<ArtpieceSave> artpieces;
    ABModel adressbook;


    Save (Model model){
        this.adressbook = model.adressbook;
        this.artpieces = convertArtpieceEntries(model.getPieces());
    }

    private List<ArtpieceSave> convertArtpieceEntries(List<ArtPieceEntry> listToConvert) {
        List<ArtpieceSave> artpiecesTOSave = new ArrayList<>();
        for (ArtPieceEntry p: listToConvert) {
            artpiecesTOSave.add(new ArtpieceSave(p));
        }
        return artpiecesTOSave;
    }

}
