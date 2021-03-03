package exhibitions.gui;

import exhibitions.model.ExhibitionType;

import java.awt.*;

class ExhibitionTypeChoice extends Choice {

    ExhibitionTypeChoice()  {
        add("Einzelausstellung");
        add("Gruppenausstellung");
    }

    ExhibitionTypeChoice(ExhibitionType type){
        add("Einzelausstellung");
        add("Gruppenausstellung");
        setSelection(type);
    }


    public ExhibitionType getSelectedExhibitionType(){
        switch (this.getSelectedItem()){
            case "Einzelausstellung" : return ExhibitionType.SOLO;
            case "Gruppenausstellung" : return ExhibitionType.GROUP;
            default: return ExhibitionType.SOLO;
        }
    }

    public void setSelection(ExhibitionType type){
        switch (type){
            case SOLO:
                this.select("Einzelausstellung");
                break;

            case GROUP:
                this.select("Gruppenausstellung");
                break;
        }
    }

}
