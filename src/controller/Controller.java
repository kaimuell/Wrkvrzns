package controller;

import model.ArtPieceEntry;
import model.Model;

public interface Controller {
    boolean isASelectedElement(ArtPieceEntry artPiece);
    void addSelectedElement(ArtPieceEntry artPieceEntry);
    void setSelectedElementTo(ArtPieceEntry artPieceEntry);

    void modifyEntry(ArtPieceEntry artPieceEntry);

    void addEntry(ArtPieceEntry artPieceEntry);

    Model getModel();
}
