package flashcardapp.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class stores a state of a List as an ArrayList.
 */
public class ListMemento<E> implements Serializable {
    private final List<E> tila;
    
    public ListMemento(List<E> tallennettava) {
        tila = new ArrayList<>(tallennettava);
    }
    
    public List<E> getAlkutila() {
        return new ArrayList<>(tila);
    }
}
