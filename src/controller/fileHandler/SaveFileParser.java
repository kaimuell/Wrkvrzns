package controller.fileHandler;

import adressbook.model.ABModel;
import adressbook.model.Address;
import adressbook.model.Person;
import adressbook.model.PersonEntry;
import model.Model;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;

import java.util.Iterator;

import static java.lang.Integer.parseInt;

public class SaveFileParser {

    public static String parseFileOutput(Model model) {
        StringBuilder builder = new StringBuilder();
        builder.append("1.0\n");
        for (ArtPieceEntry entry : model.getPieces()) {
            builder.append("#artpiece\n");
            builder.append(entry.getId()).append("\n");
            builder.append(entry.getName()).append("\n");
            builder.append(entry.getTechnique()).append("\n");
            builder.append(entry.getType().ordinal()).append("\n");
            builder.append(entry.getHeight()).append("\n");
            builder.append(entry.getWidth()).append("\n");
            builder.append(entry.getDepth()).append("\n");
            builder.append(entry.getLength()).append("\n");
            builder.append(entry.getYear()).append("\n");
            builder.append(entry.getPrice()).append("\n");
            builder.append(entry.getEdition()).append("\n");
            if (entry.getBuyers().isEmpty()) {
                builder.append("0\n");
            } else {
                builder.append(entry.getBuyers().size() + "\n");
                for (Person person : entry.getBuyers()) {
                    parsePerson(builder, person);
                }
            }
        }
        for (PersonEntry personEntry: model.adressbook.getPersonList()) {
            builder.append("#contact\n");
            builder.append(personEntry.getId()).append("\n");
            parsePerson(builder, personEntry);
        }
        return builder.toString();
    }

    private static void parsePerson(StringBuilder builder, Person person){
        builder.append(person.getFirstName()).append("\n");
        builder.append(person.getFamilyName()).append("\n");
        builder.append(person.geteMail()).append("\n");
        builder.append(person.getTel()).append("\n");
        builder.append(person.getAdress().getStreet()).append("\n");
        builder.append(person.getAdress().getHouseNo()).append("\n");
        builder.append(person.getAdress().getPostal()).append("\n");
        builder.append(person.getAdress().getCity()).append("\n");
        builder.append(person.getAdress().getCountry()).append("\n");
    }

    public static Model parseFileInput(Iterator<String> lines) throws VersionControlException {
        Model model = new Model(new ABModel());
        String s = lines.next();
        if(s.equals("1.0")){
            while (lines.hasNext()){
                String controllWord = lines.next();
                if(controllWord.equals("#artpiece")){
                    ArtPieceEntry artPieceEntry =
                            new ArtPieceEntry(
                                    parseInt(lines.next()), //Id
                                    lines.next(),           //Name
                                    lines.next(),           //Technique
                                    ArtworkType.values() [parseInt(lines.next())], //Ordinal of Artworktype
                                    parseInt(lines.next()), //Height
                                    parseInt(lines.next()), //Width
                                    parseInt(lines.next()), //Depth
                                    parseInt(lines.next()), //Length
                                    parseInt(lines.next()), //Year
                                    parseInt(lines.next()), //Price
                                    parseInt(lines.next()),//Edition
                                    null
                            );
                    //TODO ANPASSEN
                    int peopleInBuyersList = parseInt(lines.next());
                    if (peopleInBuyersList > 0){
                        for (int i = 0; i < peopleInBuyersList; i++) {
                            artPieceEntry.addBuyer(createNewPerson(lines));
                        }
                    }
                    model.getPieces().add(artPieceEntry);
                }else if (controllWord.equals("#contact")){
                    model.adressbook.getPersonList().add(new PersonEntry(
                            parseInt(lines.next()),
                           createNewPerson(lines)
                    ));
                }
            }
        }else{
            throw new VersionControlException();
        }
        return model;
    }

    private static Person createNewPerson(Iterator<String> lines) {
        return new Person(
                lines.next(),           //First name
                lines.next(),           //Family name
                lines.next(),           //eMail
                lines.next(),           //tel
                new Address(
                        lines.next(),   //Street
                        lines.next(),   //House No
                        lines.next(),   //Postal
                        lines.next(),   //City
                        lines.next()    //Country
                )
        );
    }

}
