Pelissä käytetään .properties-tiedostoja, joilla peliä voi joiltakin osin konfata haluamakseen. Conffeissa voi esimerkiksi
määrittää kuinka monta tuolia pelissä on, rajoitus tuoleille on kuitenkin max. 4.

Kun peli käynnistetään aukeaa peli-ikkuna, jossa pelaaja voi istua haluamilleen tuoleille, jolloin peli kysyy tuolille istuvan
pelaajan nimeä. Jos yhdelläkin tuolilla on pelaaja voidaan peli aloittaa. Tuolilta poistuminen tapahtuu hiiren 2. painikkeella,
silloin kuin kierros ei ole käynnissä. Kun kieros aloitetaan jaetaan mukana oleville pelaajille kaksi korttia ja jakajalle yksi.
Kieroksen vuorot kiertävät myötäpäivää oikealta vasemmalle. Pelaaja voi pelitilanteen mukaan, joko ottaa lisää kortteja, tuplata,
jakaa, tai jäädä. Kun pelaaja jää siirtyy vuoro seuraavalle pelaajalle jne. kunnes viimeinenkin pelaaja on jäänyt. Kun viimeinen
pelaaja on jäänyt jaetaan jakajalle kortteja niin kauan kunnes hänellä on vähintään 17. Kun jakajan kortit on jaettu peli katsoo
ketkä kaikki voittavat ja häviävät. Voittaja on se, jolla on parempi käsi kuin jakajalla, muuta alle 22. Jos jakaja menee yli 21,
niin kaikki pelaajat jotka eivät ole menneet yli voittavat. Jos pelaaja menee yli hän häviää automaattisesti. Kun kierros on
päättynyt voi uuden kieroksen aloittaa.
