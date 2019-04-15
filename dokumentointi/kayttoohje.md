# Käyttöohje

## Create A Potion
![Create A Potion]()

Create A Potionissa luodaan rohto. Aineslista on aluksi tyhjä, mutta aineksia voidaan lisätä Add ingredient -nappulalla, joka johtaa ingredientLibraryyn. Aineksia voidaan poistaa Remove ingredient -napilla. Käyttäjä syöttää aineksen määrän sovellukselle. Sovellus ei hyväksy negatiivisia lukuja tai nollaa. Ohjelma ei myöskään hyväksy kohtuuttoman pitkiä lukuja. Korkeintaan yhdeksän merkkiä pitkät luvut hyväksytään.

## IngredientLibrary

![IngredientLibrary](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/ingredientLibrary.png)

IngredientLibrary listaa rohtoon lisättävät ainekset. Rohtoon lisätään aines klikkaamalla listan ainesta ja painamalla Add Ingredient to Potion -nappia. (ei toiminnassa vielä.)

Käyttäjä voi myös itse lisätä uusia aineksia New Ingredient -napilla. Tällöin avautuu uusi näkymä, jossa annetaan aineksen nimi sekä mittayksikkö (grams/g, milligrams/mg, pieces, jne). On käyttäjän vastuulla että mittayksiköt ovat samaa muotoa: ohjelma ei hajoa jos eri aineksilla on yksiköt mg ja milligrams, mutta tämä saattaa rasittaa käyttäjän silmää. Uudet ainekset näkyvät ingredientLibraryssa. Jos käyttäjä lisää uusia aineksia tai poistaa aineksia ingredientLibrarysta, käyttäjän täytyy sulkea ohjelma menemällä takaisin päävalikkoon ja suljettava ohjelma exit-nappulalla. Muuten tehdyt muutokset eivät tallennu tiedostoon.
