### Testausdokumentti

**Testauksen puutteet**

- Ainoastaan logiikkaa on testattu ja siinä määrin kuin olen katsonut tarpeelliseksi. Manuaalisesti olen testaillut erilaisilla syötteillä sekä tietokanta- että gui-pakkausta. Lisäksi ulkoasua olen testaillut manuaalisesti.

**Bugeja**

Ohjelmasta löysin ainakin kaksi bugia, jotka molemmat liittyvät Henkilo-olion poistamiseen.

- Mikäli perhe kirjautuu ensin sisään ja sitten ulos ja tämän jälkeen joku perheeseen kuuluvista henkilöistä kirjautuu sisään ja tahtoo poistaa profiilinsa (syöte 7), se ei onnistu, vaikka ohjelma niin ilmoittaakin. Poisto onnistuu vain, jos ohjelman käynnistyksen ja henkilön poistoyrityksen yksikään perhe, johon henkilö kuuluu, ei ole ollut kirjautuneena.

- Mikäli henkilö saadaan poistettua ja perhe kirjautuu sisään, perhe ei enää noteeraa henkilöä eli henkilö poistuu myös perheestä. Mutta ehkä vain vähäksi aikaa: jos luomme uuden henkilön, jonka käyttäjätunnus on sama kuin poistamamme käyttäjän, tämä uusi henkilö ilmestyy taas samaan perheeseen, johon poistamamme käyttäjä oli perhettä luodessa lisättynä.