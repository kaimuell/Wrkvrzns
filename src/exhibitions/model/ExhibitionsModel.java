package exhibitions.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExhibitionsModel {
    private List<Exhibition> exhibitions;
    private Exhibition selectedExhibition;

    public ExhibitionsModel(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions == null ? new ArrayList<>() : exhibitions;
    }

    public Iterator<Exhibition> getExhibitonIterator (){
        return exhibitions.iterator();
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }


    public boolean containsId(int id) {
        for (Exhibition e: exhibitions) {
            if (e.getId() == id){
                return true;
            }
        }
        return false;
    }

    public Exhibition getSelectedExhibition(){
        return this.selectedExhibition;
    }

    public void setSelectedExhibition(Exhibition selectedExhibition) {
        this.selectedExhibition = selectedExhibition;
    }

    public List<Exhibition> getExhibitionsWithIDs(List<Integer> exhibitionIds) {
        List<Exhibition> exhibitions = new ArrayList<>();
        for (Exhibition exhibition : this.exhibitions) {
            if (exhibitionIds.contains(exhibition.getId())){
                exhibitions.add(exhibition);
            }

        }

        return exhibitions;

    }
}
