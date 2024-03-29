package model;

import adressbook.model.ABModel;
import exhibitions.model.ExhibitionsModel;
import model.elements.ArtPieceEntry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Serializable, ModelViewAccess {
    private ABModel adressbook;
    private ExhibitionsModel exhibitions;
    private List<ArtPieceEntry> pieces;
    private ArtPieceExhibitionRelationContainer artpieceExhibitionRelations;

    private List<ArtPieceEntry> filtertPieces;

    public Model(ABModel adressbuch, ExhibitionsModel exhibitions, ArtPieceExhibitionRelationContainer artpieceExhibitionRelations) {
        this.setAdressbook(adressbuch);
        this.exhibitions = exhibitions;
        this.pieces = new ArrayList<>();
        this.filtertPieces = new ArrayList<>();
        this.artpieceExhibitionRelations = artpieceExhibitionRelations;
    }

    public List<ArtPieceEntry> getFiltertPieces() {
        return filtertPieces;
    }

    public List<ArtPieceEntry> getPieces() {
        return pieces;
    }

    @Override
    public int getNumberOfEntries() {
        return pieces.size();
    }

    @Override
    public Iterator<ArtPieceEntry> artPiecesToView() {
        if(filtertPieces.size() == 0) {
            return pieces.iterator();
        }else{
            return filtertPieces.iterator();
        }
    }


    public ArtPieceEntry getEntryWithId(int id) {
        for (ArtPieceEntry entry : pieces) {
            if(entry.getId() == id){
                return entry;
            }
        }
        return null;
    }

    public ExhibitionsModel getExhibitions() {
        return exhibitions;
    }

    public void resetFilteredPieces() {
        this.filtertPieces = new ArrayList<>();
    }

    public ABModel getAdressbook() {
        return adressbook;
    }

    public void setAdressbook(ABModel adressbook) {
        this.adressbook = adressbook;
    }

    public ArtPieceExhibitionRelationContainer getArtpieceExhibitionRelations() {
        return artpieceExhibitionRelations;
    }
}
