### Testausdokumentti

**Testauksen puutteet**

- Ainoastaan logiikkaa on testattu ja siin� m��rin kuin olen katsonut tarpeelliseksi. Manuaalisesti olen testaillut erilaisilla sy�tteill� sek� tietokanta- ett� gui-pakkausta. Lis�ksi ulkoasua olen testaillut manuaalisesti.

**Bugeja**

Ohjelmasta l�ysin ainakin kaksi bugia, jotka molemmat liittyv�t Henkilo-olion poistamiseen.

- Mik�li perhe kirjautuu ensin sis��n ja sitten ulos ja t�m�n j�lkeen joku perheeseen kuuluvista henkil�ist� kirjautuu sis��n ja tahtoo poistaa profiilinsa (sy�te 7), se ei onnistu, vaikka ohjelma niin ilmoittaakin. Poisto onnistuu vain, jos ohjelman k�ynnistyksen ja henkil�n poistoyrityksen yksik��n perhe, johon henkil� kuuluu, ei ole ollut kirjautuneena.

- Mik�li henkil� saadaan poistettua ja perhe kirjautuu sis��n, perhe ei en�� noteeraa henkil�� eli henkil� poistuu my�s perheest�. Mutta ehk� vain v�h�ksi aikaa: jos luomme uuden henkil�n, jonka k�ytt�j�tunnus on sama kuin poistamamme k�ytt�j�n, t�m� uusi henkil� ilmestyy taas samaan perheeseen, johon poistamamme k�ytt�j� oli perhett� luodessa lis�ttyn�.