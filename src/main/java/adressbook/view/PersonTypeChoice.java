package adressbook.view;

import adressbook.model.PersonType;

import java.awt.*;

public class PersonTypeChoice extends Choice {

    PersonTypeChoice(){
        add("undefiniert");
        add("Sammler");
        add("Galerist");
        add("Museum");
    }

    public PersonType getSelection(){
        switch (getSelectedItem()){
            case "undefiniert" : return PersonType.UNDEFINED;
            case "Sammler" : return PersonType.COLLECTOR;
            case "Galerist" : return PersonType.GALLERY;
            case "Museum" : return PersonType.MUSEUM;
            default: return PersonType.UNDEFINED;
        }
    }

    public void setSelection(PersonType type){
        String selection = "";
                switch(type){
                    case UNDEFINED: selection = "undefiniert"; break;
                    case COLLECTOR: selection = "Sammler"; break;
                    case MUSEUM: selection = "Galerist"; break;
                    case GALLERY: selection = "Museum"; break;
                    default: selection = "undefiniert";
                }
       this.select(selection);

    }


}
