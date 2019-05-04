# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan testiluokat testaavat pitkälti oman vastaavan luokkansa toiminnallisuuksia. Toisin sanoen PotionLibraryTest testaa Potion Libraryn toiminnallisuuksia, IngredientLibraryTest testaa Ingredient Libraryn toiminnallisuuksia, jne. ComponentTest testaa toisaalta kaikkien rohtoappi.domain.components-luokkien toiminnallisuutta, sillä nämä ovat lähinnä gettereitä, settereitä ja muita pieniä metodeja.

Potion Library sekä Ingredient Library suorittavat testinsä sijoittamalla testeissä luotuja ja käytettyjä aineksia ja rohtoja suoraan sovelluslogiikkaan.

### DAO-luokat

HandlerTest-luokka testaa tiedostosta lukemista sekä pysyväistalletusta käyttäen TemporaryFolderia.


### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 10% ja haarautumakattavuus 20%. Testauksessa ei oteta huomioon käyttöliittymäluokkia.

[JacocoReport]()


