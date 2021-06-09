package exhibitions.model;

import exhibitions.entities.Exhibition;

import java.util.*;

/**
 * Model zur verwaltung von Ausstellungen
 */

public class ExhibitionsModel {
    private HashMap<Integer, Exhibition> exhibitions;
    private Exhibition selectedExhibition;

    public ExhibitionsModel(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions == null ? new HashMap() : createHashMap(exhibitions);
    }

    private HashMap<Integer, Exhibition> createHashMap(List<Exhibition> exhibitions) {
        HashMap<Integer, Exhibition> map = new HashMap<>();
        for (Exhibition e : exhibitions) {
            map.put(e.getId(), e);
        }

        return map;
    }

    public void removeExhibition(Exhibition exhibition){
        exhibitions.remove(exhibition.getId(), exhibition);
    }

    public Iterator<Exhibition> getExhibitonIterator (){
        return exhibitions.values().iterator();
    }

    public Iterator<Integer> getKeysIterator (){
        return exhibitions.keySet().iterator();
    }

    public Integer getNumberOfEntries(){
        return exhibitions.keySet().size();
    }

    public void addExhibition(Exhibition e){
        exhibitions.put(e.getId(), e);
    }

    public boolean containsId(int id) {
        return exhibitions.containsKey(id);
    }

    public Exhibition getSelectedExhibition(){
        return this.selectedExhibition;
    }

    public void setSelectedExhibition(Exhibition selectedExhibition) {
        this.selectedExhibition = selectedExhibition;
    }

    public List<Exhibition> getExhibitionsWithIDs(List<Integer> exhibitionIds) {
        List<Exhibition> exhibitions = new ArrayList<>();
        for (Iterator<Exhibition> it = this.exhibitions.values().iterator(); it.hasNext(); ) {
            Exhibition exhibition = it.next();
            if (exhibitionIds.contains(exhibition.getId())){
                exhibitions.add(exhibition);
            }
        }
        return exhibitions;

    }
}
