# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan testiluokat testaavat pitkälti oman vastaavan luokkansa toiminnallisuuksia. Toisin sanoen PotionLibraryTest testaa Potion Libraryn toiminnallisuuksia, IngredientLibraryTest testaa Ingredient Libraryn toiminnallisuuksia, jne. ComponentTest testaa toisaalta kaikkien rohtoappi.domain.components-luokkien toiminnallisuutta, sillä nämä ovat lähinnä gettereitä, settereitä ja muita pieniä metodeja.

Potion Library sekä Ingredient Library suorittavat testinsä sijoittamalla testeissä luotuja ja käytettyjä aineksia ja rohtoja suoraan sovelluslogiikkaan.

### DAO-luokat

HandlerTest-luokka testaa tiedostosta lukemista sekä pysyväistalletusta käyttäen TemporaryFolderia.


### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 87% ja haarautumakattavuus 79%. Testauksessa ei oteta huomioon käyttöliittymäluokkia. En osannut erottaa main-luokkaa testauksesta.

![JacocoReport](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/jacocoresults.png)


## Järjestelmätestaus

## Asennus ja konfigurointi

Sovellus on ladattu ja testattu käyttöohjeiden mukaisesti Linux-ympäristössä.

## Toiminnallisuudet

Ohjelma on varautunut epäkelpoihin syötteisiin.
