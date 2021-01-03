package controller;

import model.ArtPieceEntry;

public interface Controller {
    boolean isASelectedElement(ArtPieceEntry artPiece);
    void addSelectedElement(ArtPieceEntry artPieceEntry);
    void setSelectedElementTo(ArtPieceEntry artPieceEntry);

    void modifyEntry(ArtPieceEntry artPieceEntry);

    void addEntry(ArtPieceEntry artPieceEntry);
}
