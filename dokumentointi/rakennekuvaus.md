Ohjelman rakenne koostuu seuraavista komponenteista:

#Logiikka#
* Hand
* Player
* Dealer
* Human
* Card
* Deck
* BlackJackDeck
* Seat
* Rank
* Suit
* HandValueHolder
* HandValueCalculator
* GameState
* BlackJackGameManager

Kyseisten luokkien nimet kertovat jo itsestään suoraan niiden roolin. Player on abstraktiluokka, josta Human ja Dealer periytyy. Player sisältää tiettyjä valmiita metodeita ja muuttujia joita sen perivät luokat voivat käyttää suoraan. HandValueCalculator liittyy Hand-luokkaan, sitä käytetään käden arvon laskemiseen ja käden arvon voidaan palauttaa suoraan Hand-luokalta itseltään.
BlackJackDeck perii abstraktin luokan Deck, joka sisältää korttipakalle tyypilliset ominaisuudet. Kortin nostamisen, sekoittamisen yms. BlackJackDeckille voidaan määrittää parametreista kuinka monta 52 kortin "korttipakkaa" siihen ladataan. Suit ja Rank ovat Enum-luokkia, jotka liittyvät Card-luokkaan. BlackJackGameManager on itse pelin "sydän". Siihen liittyy Tuoleja, Jakaja, Korttipakka ja GameState.

#Käyttöliittymä#
* BlackJackUI
* GamePanel
* HandPanel
* SeatPanel
* MenuPanel

BlackJackUI on koko pelin käyttöliittymän runko joka perii JFramen. BlackJackUI käyttää yhtä GamePanelia, jonka rooli on pitää käyttöliittymä kasassaa. GamePaneliin liittyy SeatPaneleita ja HandPaneleita sen mukaan, kuinka monta paikkaa pöytään on konfiguroitu. SeatPanel sisältää aina yhden tuolin ja tuolin kautta pelaajan. HandPanel sisältää myös tuolin ja sitä käyttä pelaajan ja pelaajan kortit. ManuPanel on pelin valikon paneeli joka sisältää pelin valikon napit. MenuPanel ja SeatPanel saavat GamePanelilta BlackJackGameManager-luokan, jota voivat käyttää.


