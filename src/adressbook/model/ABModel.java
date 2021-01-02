package adressbook.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ABModel implements Serializable, IABModelReader {
    List<PersonEntry> personList;

    public ABModel() {
        this.personList = new ArrayList<>();
    }

    public List<PersonEntry> getPersonList() {
        return personList;
    }

    @Override
    public Iterator<PersonEntry> personIterator() {
        return personList.iterator();
    }

    @Override
    public int getNumberOfEntries() {
        return personList.size();
    }

    public PersonEntry getPersonWithID(int id) {
        for (PersonEntry pe : personList) {
            if(pe.getId() == id){
                return pe;
            }
        }
        return null;
    }
}
