[Kortti]*<-1[Pakka]
[<<Interface>>;Tietokanta]uses -.->[Pakka]
[<<Interface>>;Tietokanta]^-.-[TiedostoTietokanta]
[Kortti]^-[LaajennettuKortti]
[FlashcardApp{bg:green}]1-*>[Pakka]
[FlashcardApp{bg:green}]uses-.->[<<Interface>>;Tietokanta]
[Kayttoliittyma{bg:yellow}]1->1[FlashcardApp{bg:green}]
[Kayttoliittyma{bg:yellow}]1-1[MenuBar]
[MenuBar]1-1[FlashcardApp{bg:green}]
[Kayttoliittyma{bg:yellow}]1-1[PakkaPaneeli{bg:blue}]
[PakkaPaneeli{bg:blue}]1-*[PakkaNappi]
[Kayttoliittyma{bg:yellow}]1-1[PakkaHarjoittaja{bg:blue}]
[Pakka]1-1[PakkaHarjoittaja{bg:blue}]
[Kayttoliittyma{bg:yellow}]1-1[KorttiLisaaja]
[KorttiLisaaja{bg:blue}]1-1>[PakkaTable]
[KorttiLisaaja{bg:blue}]1-1>[FlashcardApp{bg:green}]
[PakkaPaneeli{bg:blue}]1-1>[PakkaPopupMenu]
[PakkaPopupMenu]1-*>[PakkaNappi]
[KorttiLisaaja{bg:blue}]uses -.->[Pakka]
[KorttiLisaaja{bg:blue}]uses -.->[Kortti]
[PakkaTable]uses -.->[Pakka]
[PakkaTable]uses -.->[Kortti]