package controller.FileHandler;

import adressbook.model.ABModel;
import adressbook.model.Adress;
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
            builder.append(entry.getId() + "\n");
            builder.append(entry.getName() + "\n");
            builder.append(entry.getTechnique() + "\n");
            builder.append(entry.getType().ordinal() + "\n");
            builder.append(entry.getHeight() + "\n");
            builder.append(entry.getWidth() + "\n");
            builder.append(entry.getDepth() + "\n");
            builder.append(entry.getLength() + "\n");
            builder.append(entry.getYear() + "\n");
            builder.append(entry.getPrice() + "\n");
            if (entry.getBuyer() == null) {
                builder.append("0\n");
            } else {
                builder.append("1\n");
                parsePerson(builder, entry.getBuyer());
            }
        }
        for (PersonEntry personEntry: model.adressbook.getPersonList()) {
            builder.append("#contact\n");
            builder.append(personEntry.getId());
            parsePerson(builder, personEntry);
        }
        return builder.toString();
    }

    private static void parsePerson(StringBuilder builder, Person person){
        builder.append(person.getFirstName() + "\n");
        builder.append(person.getFamilyName() + "\n");
        builder.append(person.geteMail() + "\n");
        builder.append(person.getTel() + "\n");
        builder.append(person.getAdress().getStreet() + "\n");
        builder.append(person.getAdress().getHouseNo() + "\n");
        builder.append(person.getAdress().getPostal() + "\n");
        builder.append(person.getAdress().getCity() + "\n");
        builder.append(person.getAdress().getCountry() + "\n");
    }

    public static Model parseFileInput(Iterator<String> lines) throws VersionControllException{
        Model model = new Model(new ABModel());
        String s = lines.next();
        if(s.equals("1.0")){
            while (lines.hasNext()){
                if(lines.next().equals("#artpiece")){
                    model.getPieces().add(
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
                                    lines.next().equals("1") ? createNewPerson (lines) : null,
                                    null
                            ));
                }else if (lines.next().equals("\"#contact")){
                    model.adressbook.getPersonList().add(new PersonEntry(
                            parseInt(lines.next()),
                           createNewPerson(lines)
                    ));
                }
            }
        }else{
            throw new VersionControllException();
        }
        return model;
    }

    private static Person createNewPerson(Iterator<String> lines) {
        Person p = new Person(
                lines.next(),
                lines.next(),
                lines.next(),
                lines.next(),
                new Adress(
                        lines.next(),
                        lines.next(),
                        lines.next(),
                        lines.next(),
                        lines.next()
                )
        );
        return p;
    }

}
