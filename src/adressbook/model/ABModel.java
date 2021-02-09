package adressbook.model;


import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Das Model des Adressbuchs
 */

public class ABModel implements Serializable, ABModelReader {
    List<PersonEntry> personList;
    List<PersonEntry> filteredList;

    public ABModel() {
        this.personList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public ABModel(List<Person> buyers) {
        this.personList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        ABController controller = new ABControllerImplementation(this);
        for (Person person : buyers) {
            controller.addPerson(person);
        }
    }

    public List<PersonEntry> getPersonList() {
        return personList;
    }

    @Override
    public Iterator<PersonEntry> personIterator() {
        if(filteredList.isEmpty()) {
            return personList.iterator();
        } else {
            return filteredList.iterator();
        }
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

    public List<PersonEntry> getFilteredList(){
        return this.filteredList;
    }

    public void resetFilteredList(){
        this.filteredList = new ArrayList<>();
    }
}
