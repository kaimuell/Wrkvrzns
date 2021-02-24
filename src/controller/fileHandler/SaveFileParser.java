package controller.fileHandler;

import adressbook.model.*;
import model.Model;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;

import java.util.Iterator;

import static java.lang.Integer.parseInt;


public class SaveFileParser {

    /**
     * Methode um aus einem Model einen String zum speichern zu erszeugen
     * @param model das Model
     * @return der String
     */
    public static String parseFileOutput(Model model) {
        StringBuilder builder = new StringBuilder();
        builder.append("1.0\n");
        writeAllArtpieces(model, builder);
        writeAdressbook(model, builder);
        return builder.toString();
    }

    private static void writeAdressbook(Model model, StringBuilder builder) {
        for (PersonEntry personEntry: model.adressbook.getPersonList()) {
            builder.append("#contact\n");
            builder.append(personEntry.getId()).append("\n");
            writePerson(builder, personEntry);
        }
    }

    private static void writeAllArtpieces(Model model, StringBuilder builder) {
        for (ArtPieceEntry entry : model.getPieces()) {
            writeArtpiece(builder, entry);
            writeBuyersOfArtpiece(builder, entry);
        }
    }

    private static void writeBuyersOfArtpiece(StringBuilder builder, ArtPieceEntry entry) {
        if (entry.getBuyers().isEmpty()) {
            builder.append("0\n");
        } else {
            builder.append(entry.getBuyers().size() + "\n");
            for (Person person : entry.getBuyers()) {
                writePerson(builder, person);
            }
        }
    }

    private static void writeArtpiece(StringBuilder builder, ArtPieceEntry entry) {
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
    }

    private static void writePerson(StringBuilder builder, Person person){
        builder.append(person.getFirstName()).append("\n");
        builder.append(person.getFamilyName()).append("\n");
        builder.append(person.geteMail()).append("\n");
        builder.append(person.getTel()).append("\n");
        builder.append(person.getAdress().getStreet()).append("\n");
        builder.append(person.getAdress().getHouseNo()).append("\n");
        builder.append(person.getAdress().getPostal()).append("\n");
        builder.append(person.getAdress().getCity()).append("\n");
        builder.append(person.getAdress().getCountry()).append("\n");
        builder.append(createPersonTypeRepresentation(person)).append("\n");
    }


    /**
     * Methode um eine Datei wieder in ein Datenmodell umzuwandeln
     * @param lines die Zeilen des Ausgelesenen Strings einer Datei
     * @return das Model
     * @throws VersionControlException die gespeicherte Version ist unbekannt.
     */

    public static Model parseFileInput(Iterator<String> lines) throws VersionControlException {
        Model model = new Model(new ABModel());
        String versionControlLine = lines.next();

        if(versionControlLine.equals("1.0")){
            parseVersion_1_0(lines, model);
        }else{
            throw new VersionControlException();
        }
        return model;
    }

    private static void parseVersion_1_0(Iterator<String> lines, Model model) {
        while (lines.hasNext()){
            String controlWord = lines.next();
            if(controlWord.equals("#artpiece")){
                createArtPiece(lines, model);
            }else if (controlWord.equals("#contact")){
                createAdressbookEntry(lines, model);
            }
        }
    }

    private static void createArtPiece(Iterator<String> lines, Model model) {
        ArtPieceEntry artPieceEntry =
                parseArtpieceEntry(lines);
        parseBuyersOfArtpiece(lines, artPieceEntry);
        model.getPieces().add(artPieceEntry);
    }

    private static void createAdressbookEntry(Iterator<String> lines, Model model) {
        model.adressbook.getPersonList().add(new PersonEntry(
               parseInt(lines.next()),
               createNewPerson(lines)
        ));
    }

    private static ArtPieceEntry parseArtpieceEntry(Iterator<String> lines) {
        return new ArtPieceEntry(
                parseInt(lines.next()), //Id
                lines.next(),           //Name
                lines.next(),           //Technique
                ArtworkType.values()[parseInt(lines.next())], //Ordinal of Artworktype
                parseInt(lines.next()), //Height
                parseInt(lines.next()), //Width
                parseInt(lines.next()), //Depth
                parseInt(lines.next()), //Length
                parseInt(lines.next()), //Year
                parseInt(lines.next()), //Price
                parseInt(lines.next()),//Edition
                null
        );
    }


    private static void parseBuyersOfArtpiece(Iterator<String> lines, ArtPieceEntry artPieceEntry) {
        int peopleInBuyersList = parseInt(lines.next());
        if (peopleInBuyersList > 0){
            for (int i = 0; i < peopleInBuyersList; i++) {
                artPieceEntry.addBuyer(createNewPerson(lines));
            }
        }
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
                ),
                selectType(lines.next())
        );
    }

    private static PersonType selectType(String next) {
        switch (next) {
            case "UNDEFINED" :
                return PersonType.UNDEFINED;
            case "COLLECTOR" :
                return PersonType.COLLECTOR;
            case "GALLERY" :
                return PersonType.GALLERY;
            case "MUSEUM" :
                return PersonType.MUSEUM;
            default: return PersonType.UNDEFINED;
        }
    }


    private static String createPersonTypeRepresentation(Person person) {
        switch (person.getType()){
            case UNDEFINED: return "UNDEFINED";
            case COLLECTOR: return "COLLECTOR";
            case GALLERY: return "GALLERY";
            case MUSEUM : return "MUSEUM";
            default: return "UNDEFINED";
        }
    }


}
