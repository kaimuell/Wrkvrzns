package adressbook.controller;

import adressbook.view.ABViewer;
import adressbook.model.ABModel;
import adressbook.model.Person;
import adressbook.model.PersonEntry;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ABControllerImplementation implements ABController {
    private ABModel model;
    private PersonEntry selectedPerson;
    private List<ABViewer> views;



    public ABControllerImplementation(ABModel model) {
        this.model = model;
        this.selectedPerson = null;
        this.views = new ArrayList<>();
    }



    @Override
    public void selectPerson(PersonEntry p) {
        this.selectedPerson = p;
        updateViews();
    }

    @Override
    public void deleteSelectedPerson() {
        if (selectedPerson != null) {
            if (selectedPerson.getLinks() == 0) {
                model.getPersonList().remove(this.selectedPerson);
            } else {
                int confirmation = JOptionPane.showConfirmDialog(null, "Dieser Eintrag ist extern verlinkt. Wirklich löschen?");
                if (confirmation == JOptionPane.OK_OPTION){
                    model.getPersonList().remove(this.selectedPerson);
                }
            }
            updateViews();
        }
    }


    @Override
    public void addPerson(Person person) {
        model.getPersonList().add(new PersonEntry(idCreator(), person));
        updateViews();
    }

    private void updateViews() {
        for (ABViewer view: views) {
            view.refreshView();
        }
    }

    private int idCreator() {
        int id =model.getPersonList().size();
        while (isIDAllreadyInList(id)) {
            id++;
        }
        return id;
    }

    private boolean isIDAllreadyInList(int id){
        for (PersonEntry p : model.getPersonList()) {
            if (p.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void modifyPerson(int id, Person person) {
        PersonEntry personEntry = model.getPersonWithID(id);
        if (personEntry != null){
            personEntry.setFirstName(person.getFirstName());
            personEntry.setFamilyName(person.getFamilyName());
            personEntry.setTel(person.getTel());
            personEntry.seteMail(person.geteMail());
            personEntry.setAdress(person.getAdress());
            updateViews();
        }
    }

    @Override
    public void addViewAsListener(ABViewer view) {
        views.add(view);
    }

    @Override
    public PersonEntry getSelectedPerson() {
        return selectedPerson;
    }
}
