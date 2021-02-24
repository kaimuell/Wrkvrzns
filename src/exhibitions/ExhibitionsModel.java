package exhibitions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExhibitionsModel {
    private List<Exhibition> exhibitions;

    public ExhibitionsModel(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions == null ? new ArrayList<>() : exhibitions;
    }

    public Iterator<Exhibition> getExhibitonIterator (){
        return exhibitions.iterator();
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }


}
