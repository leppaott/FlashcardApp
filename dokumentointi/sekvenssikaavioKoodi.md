title Pakan lisääminen

ActionListener->MenuBar: lisaaPakka(ActionEvent ae)
MenuBar->+JOptionPane: showInputDialog(...)
JOptionPane-->-MenuBar: pakanNimi
MenuBar->FlashcardApp: lisaaPakka(pakanNimi)
MenuBar->Kayttoliittyma: paivita()

title Pakan harjoitteleminen



MouseListener->PakkaPaneeli

MouseListener->PakkaPaneeli: mouseClicked()

PakkaPaneeli->Kayttoliittyma: harjoitaPakkaa(pakanNimi)

Kayttoliittyma->+FlashcardApp: haePakka(pakanNimi)

FlashcardApp-->-Kayttoliittyma: pakka

Kayttoliittyma->PakkaHarjoittaja: alusta(pakka)

Kayttoliittyma->JFrame: setContentPane(pakkaHarjoittaja)

Kayttoliittyma->Kayttoliittyma: paivitaFrame()
