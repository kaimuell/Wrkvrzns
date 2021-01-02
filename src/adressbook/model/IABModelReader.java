package adressbook.model;

import java.util.Iterator;

public interface IABModelReader {
    Iterator<PersonEntry> personIterator();
    int getNumberOfEntries();
}
