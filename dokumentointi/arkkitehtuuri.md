# Arkkitehtuurikuvaus

## Rakenne


## Käyttöliittymä

Käyttöliittymässä on 5 näkymää ja lisäksi 3 niinsanottua apunäkymää, jotka sisältävät esim. tekstikentän. Sovelluksen kehittyessä joistakin apunäkymistä voidaan luopua.

* Main Menu
* Create A Potion
* Add Ingredient to Potion (apunäkymä)
* Remove from Potion (apunäkymä)
* Generated Potion
* Ingredient Library
* New Ingredient (apunäkymä)
* Potion Library

Jokainen näkymä on metodi, joka kokoaa Scene-olion. Metodit sijaitsevat (vielä) yhdessä AppUI-luokassa. Mainissa alustetaan sovellus luomalla uusi AppLogic- ja AppUI-luokka. Lisäksi mainissa sijaitseva start-metodi saa parametrinään Stage-olion, ja tämä Stage-olio annetaan eteenpäin AppUI-luokan start-metodille. AppUI-luokan Scene-metodit antavat toisilleen parametrinä tätä stage-oliota.

## Sovelluslogiikka
AppLogic-luokalla on käytössään oliomuuttujina ingredientLibrary- ja potionLibraryluokka. Lisäksi sillä on luokka tempPotion, joka on rohto, jota käyttäjä on työstämässä. AppLogic pääsee näin käsiksi kaikkiin mahdollisiin aineksiin ja rohtoihin. IngredientLibrary hoitaa yksittäisiin aineksiin liittyviä toimintoja, ja potionLibraryyn on tallennettu aikaisempia tempPotion-olioita.

Luokkakaavio:
![arkkitehtuuri](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.jpg)

## Pysyväistalletus
IngredientLibraryn ainekset ovat tallessa ingredients.txt-tiedostossa. Tiedoston lukemis- ja kirjoittamisoperaatiot sijaitsevat vielä tällä hetkellä ingredientLibraryssa, mutta potionLibraryn kasvaessa tarkoitus on eriyttää tiedosto-operaatiot omaksi luokakseen. Muutokset ainestietoihin tallennetaan tiedostoon VAIN päävalikon exit-nappulaa klikattaessa. Siis ikkunan oikean yläkulman napista sulkeminen ei tallenna muutoksia.
Ainekset ovat tallennettuna ingredients.txt-tiedostoon muodossa aineennimi;yksikkö.

## Päätoiminnallisuudet

Sekvenssikaavio aineksen lisäämisestä rohtoon:
![sekvenssi1](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/addingredientsequence.jpg)
Käyttäjä on aluksi Create A Potion -näkymässä. Tässä näkymässä hän klikkaa Add Ingredient (ingredient library) -nappia, joka ohjaa hänet ingredientLibrary-näkymään. Tämän jälkeen käyttäjä klikkaa jotakin ingredientLibraryn listassa olevista aineksista ja painaa sitten Add To Potion -nappia. Tämän jälkeen aukeaa addIngredientToPotion-näkymä, jossa käyttäjältä kysytään kuinka paljon ainetta kuuluu reseptiin, esim. 7 ml, 3 kpl. Tässä näkymässä käytetään käyttäjän mukavuudeksi tekstiä, josta tulee ilmi aineksen yksikkö. Tätä varten kutsutaan ingredientLibrarysta ainesta nimellä, ja saadaan takaisin aineksen mittayksikkö, esimerkiksi "g". Käyttäjä antaa halutun arvon amount-kenttään ja painaa confirm-nappulaa. AppLogicin metodia addToTempPotion kutsutaan. AppLogic ensin pyytää ingredient-olion ingredientLibrarylta ja sitten antaa tämän aineksen parametrinä tempPotionin metodille addToPotion. Tämä metodi palauttaa merkkijonon, joka kertoo operaation onnistuneen. (Epäonnistuminen palauttaa merkkijonon riippuen epäonnistumisen syystä.) Näkymään ilmestyy status-teksti, joka varmistaa kertoo käyttäjälle operaation onnistuneen: "Ingredient added."

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä
Näkymät ovat omissa metodeissaan, mutta nämä metodit silti sijaitsevat yhdessä samassa luokassa, joka on aivan massiivinen.
