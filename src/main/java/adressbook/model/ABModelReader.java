package adressbook.model;

import java.util.Iterator;

/**
 * Gewährt Lesezugriff auf das Model
 */
public interface ABModelReader {
    /**
     * Gibt einen Iterator über die Einträge des Models zurück
     * @return der Iterator
     */
    Iterator<PersonEntry> personIterator();

    /**
     * Gibt die Zahl der Einträge im Model zurück
     * @return die Einträge
     */
    int getNumberOfEntries();
}
