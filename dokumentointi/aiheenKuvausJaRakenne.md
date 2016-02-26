###Aiheen kuvaus ja rakenne


**Aihe:** Budjettikirjanpito

Toteutetaan ohjelma, jonne on mahdollista asettaa k�ytt�j�n palkka, sy�tt�� menoja ja seurata, miten raha riitt��. Ohjelmasta pystyisi my�s n�kem��n, kuinka paljon rahaa menee mihinkin. Normaalien menojen lis�ksi budjettiin saa lis�tty� lainan tai aloittaa s��st�n jotain kohdetta varten. Ohjelman tarkoitus on saada k�ytt�j� kohtaamaan kaikki omat menonsa, ja siksi ohjelmaa suositellaan erityisesti t�rs�ilij�ille. Soveltuu niin yksitt�isen henkil�n, suuren kotitalouden kuin yrityksenkin k�ytt��n.

###### Rakenne

Ohjelma jakautuu tietokanta- ja k�ytt�liittym�pakkaukseen sek� logiikkapakkauksiin.

Logiikka jakautuu k�ytt�ji� sek� tapahtumia (rahaliikenne) koskeviin pakkauksiin. K�ytt�j�-pakkauksessa on luokka K�ytt�j� sek� t�m�n aliluokat Henkilo, Perhe ja Yritys. Tapahtuma-pakkauksessa on luokka Tapahtuma sek� sen aliluokat Ostos, Velka, Tulo ja Saasto.

K�ytt�liittym�pakkauksessa (gui) on P��ohjelma, josta ajetaan K�ytt�liittym�-luokan metodi k�ynnist�(). Lis�ksi K�ytt�liittym�-luokka pit�� hallussaan v�liaikaismuistia (kayttajat ja current) ja kutsuu muita pakkauksen luokkia sen mukaan kuin on tarve. Ohjelmassa on tekstik�ytt�liittym�, joka on aiheittain jaettu eri luokkiin. Varsinaisia attribuutteja ei n�iss� luokissa juuri ole, vaan ne on sit� varten, ettei K�ytt�liittym�-luokan koko olisi 2000 rivi� (niin kuin se alun perin oli). Esim. jos ohjelman k�ytt�j� haluaa lis�t� uuden k�ytt�j�n, kutsuu K�ytt�liittym� luokan Kayttajanlisays metodia uudenKayttajanLisays. N�m� luokat sis�lt�v�t paljon tulostusta (muodostavat yhdess� tekstik�ytt�liittym�n) ja k�ytt�v�t K�ytt�liittym�n scanneria.

Database-kansiossa on Database-luokka, joka muodostaa Kayttaja-olioista ser-tiedostoja ja nime�� ne olion k�ytt�j�tunnuksen mukaan. T�m� mahdollistaa, ett� ohjelman suljettuakin k�ytt�j�n tiedot s�ilyv�t. Luotaessa uusi k�ytt�j�, luodaan uusi ser-tiedostokin ja mik�li k�ytt�j� poistetaan, ser-tiedostokin poistetaan.

**K�ytt�j�t:** Henkil�, perhe tai yritys

**K�ytt�j�n toiminnot:** 


* Uuden k�ytt�j�tunnuksen luonti
* Oman k�ytt�j�tunnuksen poisto
* Sis��n ja ulos kirjautuminen
* Ostosten, tulojen, velkojen sek� s��st�jen lis��minen ja poistaminen
* Rahatilanteen seuraaminen
* Maksu- ja s��st�suunnitelman seuraaminen
* Tietojen talletus


![Luokkakaavio](Luokkakaavio.png)
![Kirjautuminen](Kirjautuminen.png)
![Uuden Henkil�n lis�ys](Uuden henkil�n lis�ys.png)
![Tapahtuman poisto](Tapahtuman poisto.png)