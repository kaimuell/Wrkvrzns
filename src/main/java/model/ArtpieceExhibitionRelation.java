package model;

import exhibitions.entities.Exhibition;
import model.elements.ArtPieceEntry;

public class ArtpieceExhibitionRelation {
    private ArtPieceEntry artPieceEntry;
    private Exhibition exhibition;

    public ArtpieceExhibitionRelation(ArtPieceEntry artPieceEntry, Exhibition exhibition) {
        this.artPieceEntry = artPieceEntry;
        this.exhibition = exhibition;
    }

    public ArtPieceEntry getArtPieceEntry() {
        return artPieceEntry;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }
}
