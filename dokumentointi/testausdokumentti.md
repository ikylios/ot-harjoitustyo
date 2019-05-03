# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan testiluokat testaavat pitkälti oman vastaavan luokkansa toiminnallisuuksia. Toisin sanoen PotionLibraryTest testaa Potion Libraryn toiminnallisuuksia, IngredientLibraryTest testaa Ingredient Libraryn toiminnallisuuksia, jne. ComponentTest testaa toisaalta kaikkien rohtoappi.domain.components-luokkien toiminnallisuutta, sillä nämä ovat lähinnä gettereitä, settereitä ja muita pieniä metodeja.

Potion Library sekä Ingredient Library suorittavat testinsä sijoittamalla testeissä luotuja ja käytettyjä aineksia ja rohtoja suoraan sovelluslogiikkaan.
