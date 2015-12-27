package flashcardapp.gui;

import flashcardapp.database.TiedostoTietokanta;
import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import flashcardapp.database.Tietokanta;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Testing
        Tietokanta db = new TiedostoTietokanta();
        Pakka pakka = new Pakka("Englantia");

        pakka.lisaaKortti(new Kortti("kyllä", "yes"));
        pakka.lisaaKortti(new Kortti("ei", "no"));
        pakka.lisaaKortti(new Kortti("ehkä", "maybe"));
        pakka.lisaaKortti(new Kortti("viihdyttää", "entertain"));
        pakka.lisaaKortti(new Kortti("opettaa", "teach"));
        
        List<Pakka> pakat = new ArrayList<>();
        pakat.add(pakka);
        
        db.tallennaPakat(pakat);
        //Testataan toimiiko tallennus
        List<Pakka> ladattu = db.lataaPakat();

        System.out.println("LADATTU");

        for (Kortti kortti : ladattu.get(0)) {
            System.out.println(kortti.getEtupuoli() + " - " + kortti.getKaantopuoli());
        }
    }
}
