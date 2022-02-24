package model;

import exhibitions.ExhibitionType;
import exhibitions.entities.Exhibition;
import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;
import org.junit.jupiter.api.Test;
import tools.PictureTools;

public class ArtpieceExhibitionRelationTests {

    @Test
    void relationFunctionalityTest(){
        ArtPieceExhibitionRelationContainer container = new ArtPieceExhibitionRelationContainer();
        ArtPieceEntry arttpiece1 = new ArtPieceEntry(0, new ArtPiece("bla", "bla", ArtworkType.PAINTING,0, 0, 0, 0, 0, 0, 0, ""),PictureTools.defaultEmptyImage());
        Exhibition exhibition1 = new Exhibition(0, ExhibitionType.SOLO, "", "1", "", "", "", 0);
        assert (container.getAllRelations().isEmpty());
        container.addRelation(new ArtpieceExhibitionRelation( arttpiece1, exhibition1));
        assert (!container.getAllRelations().isEmpty());
        assert (container.getExhibitionsOfArtpiece(arttpiece1).get(0).equals(exhibition1));
        assert (container.getArtpieceEntriesOfExhibition(exhibition1).get(0).equals(arttpiece1));
    }

}
