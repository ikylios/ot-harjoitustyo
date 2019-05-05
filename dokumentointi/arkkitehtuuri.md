# Arkkitehtuurikuvaus

## Rakenne

![Rakenne](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/pakkausrakenne.png)

rohtoappi.ui-paketti sisältää käyttöliittymän luokat, rohtoappi.domain sisältää sovelluslogiikan ja muut toiminnallisuuden luokat, rohtoappi.dao-paketti sisältää pysyväistalletuksen luokat.

## Käyttöliittymä

Käyttöliittymässä on 7 päänäkymää ja lisäksi 2 niinsanottua apunäkymää, jotka sisältävät vain esim. tekstikentän.

* Main Menu
* Create A Potion
* Ingredient List
* Add Ingredient to Potion (apunäkymä)
* Generated Potion
* Ingredient Library
* New Ingredient (apunäkymä)
* Potion Library
* View Potion

Jokainen näkymä on metodi, joka kokoaa Scene-olion. Metodit sijaitsevat AppUI-luokassa, mutta joidenkin toistuvien ja hieman monimutkaisempien elementtien kokoamismetodit sijaitsevat UIBuilder-luokassa. Mainissa alustetaan sovellus luomalla uusi AppLogic- ja AppUI-luokka. Sovelluksen alustusmetodissa asetetaan sovellukselle tiedostosta lukemiseen tarvittavat oliot, jotka injektoidaan AppLogic-luokalle. Sovelluksen käynnistämishetkellä luodaan myös uusi Potion-olio tempPotionille.


Lisäksi mainissa sijaitseva start-metodi saa parametrinään Stage-olion, ja tämä Stage-olio annetaan eteenpäin AppUI-luokan start-metodille. AppUI-luokan Scene-metodit antavat toisilleen parametrinä tätä stage-oliota.


## Sovelluslogiikka

AppLogic-luokalla on käytössään oliomuuttujina ingredientLibrary- ja potionLibrary-luokka. Lisäksi sillä on oliomuuttujana instanssi luokasta Potion nimeltä tempPotion, joka on rohto, jota käyttäjä on työstämässä. AppLogic käsittelee näiden kolmen luokan sisäisiä sekä keskenäisiä toimintoja.

Potionin metodeja ovat mm.
* addToPotion(Ingredient ingredient) lisää aineksen rohtoon
* getIngredientByName(String name)
* removeFromPotion(String name)

PotionLibraryn metodeja ovat mm.
* addPotion(Potion potion) lisää/tallentaa tempPotion-rohdon kirjastoon
* deletePotion(String name)
* getPotionByName(String name)

IngredientLibraryn metodeja ovat mm.
* addIngredient(String name, String measuringUnit) luo uuden aineksen ja lisää aineksen kirjastoon
* removeIngredient(String name) poistaa aineksen kirjastosta
* getIngredientByName(String name)
* getRandomIngredient()

Ingredient-luokka kuvaa yksittäistä ainesta. Potion-luokka kuvaa yksittäistä rohtoa. 


Magic-luokka tarjoaa rohdolle tyypin (esimerkiksi tee, rasva, tahna) ja taianomaisen efektin (esim. kauneus, terveys, kirous).
Luokka sisältää kaksi listaa ja metodin joka valitsee satunnaisesti molemmista listoista yhden objektin.

![Luokkakaavio](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/luokkakaavio.png)


## Pysyväistalletus

Sovelluksen tiedostojen nimet määritellään juurikansiossa olevassa config.properties -tiedostossa.

IngredientLibraryn ainekset ovat tallessa ingredients.txt-tiedostossa ja potionLibraryn rohdot sijaitsevat potions.txt-tiedostossa. Tiedostojen lukeminen tapahtuu PotionsHandler- ja IngredientsHandler -luokissa, jotka antavat IngredientLibrary- ja PotionLibrary-olioille tiedostojen rivit ArrayListinä. Kirjastot muuntavat nämä rivit sitten aineksiksi ja rohdoiksi. Tiedostot luetaan sovelluksen käynnistyshetkellä ja muutokset tallennetaan sovelluksen sulkemishetkellä.


Ainekset ovat tallennettuna muodossa aineenNimi;Yksikkö.
```
carrot;g
coal;kg
dragon scale; mg
```


Rohdot ovat tallennettuna rohdonNimi;rohdonTyyppi;rohdonEfekti;aineksenNimi;aineksenMäärä;aineksenMittayksikkö;aineksenNimi;aineksenMäärä jne jokaisen aineksen mukaan.

```
Healing Potion;Potion;Healing;carrot;20;g;coal;10;kg
Tea of Deep Doubt;Tea;Deep Doubt;ivy leaf;10;g
```

## Päätoiminnallisuuksista

Sekvenssikaavio aineksen lisäämisestä rohtoon:
![Sekvenssi Add Ingredient To Potion](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/addingredientsequence.jpg)


Käyttäjä on aluksi Create A Potion -näkymässä. Tässä näkymässä hän klikkaa Add Ingredient -nappia, joka ohjaa hänet ingredientList-näkymään. 


Tämän jälkeen käyttäjä klikkaa jotakin ingredientListin aineksista ja painaa sitten Add To Potion -nappia. Tämän jälkeen aukeaa addIngredientToPotion-näkymä, jossa käyttäjältä kysytään kuinka paljon ainetta kuuluu reseptiin, esim. 7 g. Tässä näkymässä käytetään käyttäjän mukavuudeksi tekstiä, josta tulee ilmi aineksen yksikkö. Tätä varten kutsutaan ingredientLibrarysta ainesta nimellä, ja saadaan takaisin aineksen mittayksikkö, esimerkiksi "g". 


Käyttäjä antaa halutun arvon amount-kenttään ja painaa confirm-nappulaa. AppLogicin metodia addToTempPotion kutsutaan. AppLogic pyytää ensin ingredient-olion ingredientLibrarylta nimen perusteella ja sitten antaa tämän aineksen parametrinä tempPotionin metodille addToPotion. Tämä metodi palauttaa merkkijonon, joka kertoo, onnistuiko operaatio. Käyttäjän näkymään ilmestyy status-teksti, joka varmistaa käyttäjälle operaation onnistuneen tai epäonnistuneen. Onnistumisen yhteydessä status saa tekstikseen "Ingredient added."




Sekvenssikaavio rohdon generoimisesta:
![RandomisePotion](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/randomisePotionSequence.jpg)


Käyttäjä klikkaa Randomise Potion -nappia. AppLogic pyytää Random-oliolta satunnaista lukua 1-9 välillä (ei kuvattu). Tämä määrä kertoo kuinka monta ainesta on tarkoitus lisätä rohtoon. 


AppLogic hakee Ingredient Librarylta tiedon kuinka monta ainesta kirjastossa on. Jos aiemmin arvottu luku on suurempi kuin mitä aineksia kirjastossa on, ainesten määräksi asetetaan kirjastossa olevien aineksien määrä. Tämän jälkeen alkaa silmukka (kuvassa rajattu alue), jota toistetaan kunnes ainekset rohtoon on valittu.


Silmukassa AppLogic kutsuu Ingredient Libraryn getRandomIngredient()-metodia, joka palauttaa satunnaisesti aineksen AppLogicille. AppLogic tarkistaa tempPotionilta, onko arvottua ainesta rohdossa jo. Jos ei, ainekselle arvotaan ainekselle määrä välillä 1-40, aines lisätään rohtoon ja silmukan laskuri kasvaa yhdellä. Jos rohdossa on ainesta, ainesta ei lisätä ja laskuri ei kasva. Arvotaan seuraava aines Ingredient Librarysta.


Lopuksi AppUI kutsuu createAPotion()-metodia, jossa näkymä luodaan uudestaan eli päivittyy. Käyttäjälle näkyy generoitu lista rohdon sisällöstä.


Muiden toiminnallisuuksien toiminta noudattelee samaa periaatetta: sovelluslogiikka kutsuu ingredientLibrarya tai potionLibrarya tarvittavan toiminnallisuuden suorittamiseksi, ja käyttöliittymä välittää käyttäjälle tiedon operaation tuloksesta.

## Ohjelman rakenteeseen jääneet heikkoudet


### Käyttöliittymä

Näkymät ovat omissa metodeissaan, mutta nämä metodit silti sijaitsevat yhdessä samassa luokassa, joka on aivan massiivinen.


Stage-olion heitteleminen metodien välillä tuntuu huonolta menetelmältä.

### Rakenne

Jokaisella Potion-luokalla on oma Magic-luokka jossa toistuvat samat listat. Lienee järkevämpää ja etenkin jatkokehitysmielessä mielekkäämpää, että Magic-luokka olisi myös omanlainen kirjastonsa, johon voi lisätä uusia taikoja ja tyyppejä.


Rohtotiedostoon tallennetaan aineksen yksikkö. Hienompaa olisi, jos yksikön voisi hakea aineskirjastosta turhan toiston välttämiseksi.


Koodikatselmoinnissa mainittu switch ja enumit -toteutus operaatioiden onnistumiselle on kiintoisa. Perusversiota varten sitä ei valitettavasti ehditty toteuttaa.


Monet oliomuuttujista ja metodeista ovat jätetty publiceiksi testauksen helpottamiseksi.


FXML-toteutus on vieras, mutta projektia tehdessä tähän törmäsi usein. Vaikuttaa siltä, että tämä toteutus olisi erittäin kätevä sovellukseen.
