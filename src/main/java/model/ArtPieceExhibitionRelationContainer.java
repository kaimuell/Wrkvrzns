package model;

import exhibitions.entities.Exhibition;
import model.elements.ArtPieceEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
A Container Class to hold the Relations between Artpieces and Exhibitions
 */
public class ArtPieceExhibitionRelationContainer {

    private List<ArtpieceExhibitionRelation> relationList;

    public ArtPieceExhibitionRelationContainer(List<ArtpieceExhibitionRelation> relationList) {
        this.relationList = relationList;
    }

    public ArtPieceExhibitionRelationContainer() {
        this.relationList = new ArrayList<>();
    }

    public void addRelation(ArtpieceExhibitionRelation relation){
        relationList.add(relation);
    }

    public List<ArtpieceExhibitionRelation> getAllRelations() {
        return relationList;
    }

    public List<Exhibition> getExhibitionsOfArtpiece(ArtPieceEntry artPieceEntry){
        List<Exhibition> exhibitions = new ArrayList<>();
        for (ArtpieceExhibitionRelation relation : relationList){
            if (relation.getArtPieceEntry().getId() == artPieceEntry.getId()){
                exhibitions.add(relation.getExhibition());
            }
        }
        return exhibitions;
    }

    public List<ArtPieceEntry> getArtpieceEntriesOfExhibition(Exhibition exhibition){
        List<ArtPieceEntry> entries = new ArrayList<>();
        for (ArtpieceExhibitionRelation relation : relationList){
            if(relation.getExhibition().getId() == exhibition.getId()){
                entries.add(relation.getArtPieceEntry());
            };
        }
        return entries;
    }

    public void removeRelationsOfExhibition(Exhibition exhibition){

        for (Iterator<ArtpieceExhibitionRelation> it = relationList.iterator(); it.hasNext(); ) {
            ArtpieceExhibitionRelation relation = it.next();
            if (relation.getExhibition().getId() == exhibition.getId()){
                relationList.remove(relation);
            }
        }
    }

    public void removeRelationsOfArtpiece(ArtPieceEntry artPieceEntry){

        for (Iterator<ArtpieceExhibitionRelation> it = relationList.iterator(); it.hasNext(); ) {
            ArtpieceExhibitionRelation relation = it.next();
            if (relation.getArtPieceEntry().getId() == artPieceEntry.getId()){
                relationList.remove(relation);
            }
        }
    }

    public void removeRelation(ArtPieceEntry artPieceEntry, Exhibition exhibition){
        //TODO Entfernt Element und zerstört die Liste über die er iteriert.
        ArtpieceExhibitionRelation foundRelation = null;
        for (Iterator<ArtpieceExhibitionRelation> it = relationList.iterator(); it.hasNext(); ) {
            if (it.hasNext()) {
                ArtpieceExhibitionRelation relation = it.next();
                if (relation.getArtPieceEntry().getId() == artPieceEntry.getId()
                        && relation.getExhibition().getId() == exhibition.getId()) {
                foundRelation = relation;
                }
            }
        }
        if (foundRelation != null) {
           relationList.remove(foundRelation);
        }
    }


    public void removeRelation(ArtpieceExhibitionRelation relation){
        relationList.remove(relation);
        }

}
