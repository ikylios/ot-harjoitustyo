# Arkkitehtuurikuvaus

## Rakenne

![Rakenne](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/pakkausrakenne.png)

UI-paketti sisältää käyttöliittymän luokat, domain sisältää sovelluslogiikan ja muut toiminnallisuuden luokat, dao-paketti sisältää pysyväistalletuksen luokat.

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

IngredientLibraryn ainekset ovat tallessa ingredients.txt-tiedostossa ja potionLibraryn rohdot sijaitsevat potions.txt-tiedostossa. Tiedostojen lukeminen tapahtuu PotionsHandler- ja IngredientsHandler -luokissa (nykyhetkellä osa tapahtuu potionLibraryssa ja ingredientLibraryssa.) Muutokset tiedostoihin tallennetaan VAIN päävalikon exit-nappulaa klikatessa. Siis ikkunan oikean yläkulman napista sulkeminen ei tallenna muutoksia.


Ainekset ovat tallennettuna muodossa aineeNimi;Yksikkö.
Rohdot ovat tallennettuna rohdonNimi;rohdonTyyppi;rohdonEfekti;aineksenNimi;aineksenMäärä;aineksenMittayksikkö;aineksenNimi;aineksenMäärä jne jokaisen aineksen mukaan.



## Päätoiminnallisuudet

Sekvenssikaavio aineksen lisäämisestä rohtoon:
![Sekvenssi Add Ingredient To Potion](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/addingredientsequence.jpg)


Käyttäjä on aluksi Create A Potion -näkymässä. Tässä näkymässä hän klikkaa Add Ingredient (ingredient library) -nappia, joka ohjaa hänet ingredientLibrary-näkymään. 


Tämän jälkeen käyttäjä klikkaa jotakin ingredientLibraryn listassa olevista aineksista ja painaa sitten Add To Potion -nappia. Tämän jälkeen aukeaa addIngredientToPotion-näkymä, jossa käyttäjältä kysytään kuinka paljon ainetta kuuluu reseptiin, esim. 7 ml, 3 kpl. Tässä näkymässä käytetään käyttäjän mukavuudeksi tekstiä, josta tulee ilmi aineksen yksikkö. Tätä varten kutsutaan ingredientLibrarysta ainesta nimellä, ja saadaan takaisin aineksen mittayksikkö, esimerkiksi "g". 


Käyttäjä antaa halutun arvon amount-kenttään ja painaa confirm-nappulaa. AppLogicin metodia addToTempPotion kutsutaan. 


AppLogic ensin pyytää ingredient-olion ingredientLibrarylta ja sitten antaa tämän aineksen parametrinä tempPotionin metodille addToPotion. Tämä metodi palauttaa merkkijonon, joka kertoo operaation onnistuneen. (Epäonnistuminen palauttaa merkkijonon riippuen epäonnistumisen syystä.) Käyttäjän näkymään ilmestyy status-teksti, joka varmistaa käyttäjälle operaation onnistuneen: "Ingredient added."


## Ohjelman rakenteeseen jääneet heikkoudet


### Käyttöliittymä

Näkymät ovat omissa metodeissaan, mutta nämä metodit silti sijaitsevat yhdessä samassa luokassa, joka on aivan massiivinen.


Stage-olion heitteleminen metodien välillä tuntuu huonolta menetelmältä.

### Rakenne

Jokaisella Potion-luokalla on oma Magic-luokka jossa toistuvat samat listat. Lienee järkevämpää ja etenkin jatkokehitysmielessä mielekkäämpää, että magic-luokka olisi myös omanlainen kirjastonsa, johon voi lisätä uusia taikoja ja tyyppejä.
