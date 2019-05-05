# Käyttöohje

## Sovelluksen päävalikko
![Main Menu](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/mainMenu.png)

Tarjoaa pääsyn uuden rohdon luomisnäkymään, tallennettujen rohtojen näkymään ja sovelluksen sulkemisnapin.

## Create A Potion
![Create A Potion](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/createAPotion.png)

Create A Potionissa luodaan rohto. Aineslista on aluksi tyhjä, mutta aineksia voidaan lisätä Add Ingredient -nappulalla, joka johtaa näkymään, jossa on listattuna kaikki Ingredient Libraryn ainekset. Käyttäjä syöttää aineksen määrän sovellukselle lisänäkymässä. Sovellus ei hyväksy negatiivisia lukuja tai nollaa. Korkeintaan yhdeksän merkkiä pitkät luvut hyväksytään.

Käyttäjä voi poistaa aineksen rohdosta lisäyksen jälkeen valitsemalla aineksen listasta ja klikkaamalla Remove Ingredient -nappia. Käyttäjä voi muokata aineen määrää Edit Amount -napilla. Ohjelma hyväksyy jälleen vain positiiviset, korkeintaan yhdeksän merkkiä pitkät luvut.

Randomize-nappi arpoo satunnaiset ainesosat ja näille satunnaiset määrät.

Clear Potion -nappi tyhjentää rohdon kaikista aineksista.

Kun rohtoon halutut ainesosat ovat lisätty, rohtoon generoidaan "taikaa" painamalla Confirm Potion -nappulaa. Tämä vie käyttäjän uuteen Generated Potion -näkymään.


![Aineslista](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/ingredientList.png)


Rohtoon lisätään aines klikkaamalla ainesta listasta ja painamalla Add to Potion -nappia.

## Generated Potion
![GeneratedPotion](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/generatedPotion.png)

Näkymässä näkyy valmis rohto aineksineen. Rohdolle on satunnaisesti generoitu nimi, eli tyyppi ja vaikutus. Generoidun rohdon voi tallentaa rohtokirjastoon.

Menemällä takaisin Create A Potion -näkymään rohdon sisältöä voi vielä muokata. Vaikka käyttäjä ei muokkaisi rohdon sisältöä, Confirm Potion -nappulaa painaessa rohdolle generoidaan uusi tyyppi ja vaikutus. Tämä on tarkoituksenmukaista.



## Ingredient Library
![IngredientLibrary](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/ingredientLibrary.png)

Ingredient Library listaa rohtoihin lisättävät ainekset.

Käyttäjä voi itse lisätä uusia aineksia New Ingredient -napilla. Tällöin avautuu uusi näkymä, jossa annetaan aineksen nimi sekä mittayksikkö (grams/g, milligrams/mg, pieces, jne). On käyttäjän vastuulla pitää mittayksiköt ovat samassa muodossa: sovellus ei hajoa jos eri aineksilla on yksiköt mg ja milligrams, mutta rasittanee käyttäjää.

Tallennettuja aineksia voi poistaa kirjastosta Remove Ingredient from Library -napilla.

## Potion Library
![Potion Library](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/potionLibrary.png)

Potion Library listaa kaikki talletetut rohdot. Tallennettuja rohtoja voidaan tarkastella View Potion -nappulalla ja poistaa Delete Potion -nappulalla.


## Yleisiä huomioita:
Vaikka käyttäjä vaihtaisi näkymäänsä Create A Potionista muihin näkymiin, työn alla oleva rohto ei häviä. Vain painamalla Clear potion -nappia kaikki ainekset häviävät ja käyttäjä aloittaa puhtaalta pöydältä. Rohto häviää myös jos sovellus suljetaan.


Jotta uudet ainekset ja rohdot tallentuvat Ingredient Libraryyn ja Potion Libraryyn, sovellus **täytyy** sulkea päävalikon Exit-nappulasta. Muutokset eivät muuten tallennu tiedostoihin.


# Asennus

Lataa tiedosto *rohtoappi.jar*. Luo uusi hakemisto, johon sijoitat rohtoappi.jar:in.

## Konfigurointi

Luo tiedosto nimeltä *config.properties*. Kopioi tiedoston sisällöksi

```
ingredientFile=ingredients.txt
potionsFile=potions.txt
```

Sijoita *config.properties* samaan hakemistoon kuin missä rohtoappi.jar on.
Sovellus käyttää näitä tiedostoja aineksien ja rohtojen tallentamiseen. Tiedostot ingredients.txt ja potions.txt luodaan kun ohjelma havaitsee, ettei näitä tiedostoja ole vielä olemassa. Sovellus luo nämä tiedostot.


## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
```
java -jar rohtoappi.jar
```
suoritushakemistossa.
