package flashcardapp.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListMemento<E> implements Serializable{
    private final List<E> tila;
    
    public ListMemento(List<E> tallennettava) {
        tila = new ArrayList<>(tallennettava);
    }
    
    public List<E> getAlkutila() {
        return new ArrayList<>(tila);
    }
}
