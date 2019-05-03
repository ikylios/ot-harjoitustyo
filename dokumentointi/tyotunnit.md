# Työaikakirjanpito

| Päivä | Aika | Mitä tein |
| --- | --- | --- |
|20.3.|1 | Projektin alustaminen |
|     |2 | Määrittelydokumentin kirjoittamista ja sovelluksen suunnittelua |
|     |1 | Dokumentoinnin kirjoittamista ja ulkoasun hiomista |
|21.3.|4 | Sovelluksen visuaalisen ilmeen ja toiminnallisuuden tarkempaa kartoittamista |
|30.3.|3 | Päävalikon ja rohdonluonnin graafinen ilme kasassa, ingredientLibraryn näkymä luotu. Näiden välinen siirtyminen tehty.
|31.3.|2 | Ingredient- ja potion-luokkien rakentamista, ingredientLibraryn käyttöliittymän ja toiminnallisuuden kirjoittamista
|1.4.|1 | Javafx:n kanssa kamppailua. ListView ja ObservableList tutuiksi.
|2.4.|3 | Javafx:n kanssa kamppailua (tarkemmin init-metodi).
|    |3 | IngredientLibraryn päätoiminnallisuus toteutettu. Käyttäjä voi nyt myös lisätä uusia aineksia jo olemassa olevien default-ainesten rinnalle. Ohjelma osaa myös tarkistaa, ettei uusiksi aineksiksi huolita tyhjiä kenttiä tai duplikaatteja jo olemassa olevista aineksista.
|    |1 | Ensimmäiset kolme testiä tehty. Tarkistaa ingredientLibraryn toimintaa. Muokkasin koodia testausystävällisemmäksi.
|    |1 | Mavenin konfiguraatiota. Jacoco-homma lisätty. Lisättiin ingredientLibrarysta aineksien poistotoiminnallisuus. Lisättiin testejä tälle toiminnallisuudelle.
|7.4.|1 | Checkstyle lisätty. Jättää käyttöliittymän huomiotta. Lisätty Jacoco-raporttiin jättää käyttöliittymän huomiotta.
|8.4.|3 | Lisättiin toiminnallisuus, jossa aineksia voidaan lisätä rohtoon ja varautuu virhellisiin syötteisiin. Käyttöliittymä luo ruudukon, jossa rohdon ainekset näkyvät.
|9.4.|3 | Refaktorointia, jaettiin sovelluksen luokkia paremmin pakkauksiin, eriytettiin ingredientLibraryn sekä Create A Potionin testit omiksi luokikseen, lisättiin UIBuilder-luokka UI:n boilerplaten vähentämiseksi ja kaunistettiin käyttöliittymää.
|    |2 | Lisättiin toiminnallisuus ainesten poistamiselle rohdosta. Kirjoitettiin testit ainesten lisäämiselle ja poistamiselle.
|    |2 | Lisää refaktorointia, muutettiin mm. Type-luokan nimeksi Magic. Lisättiin näkymä, jossa näkyy valmis rohto, generatedPotion. Tästä näkymästä päästään tulevaisuudessa tallentamaan rohto PotionLibraryyn. Näkymän toiminnallisuuteen kuuluu rohdon sattumanvaraisen nimen generointi: tämäkin toiminnallisuus lisätty.
|12.4.|1 | Checkstyle korjattu.
|     |4 | Lisätty toiminnallisuus, jossa ingredientLibraryn elementit tallentuvat tiedostoon ingredients.txt.
|     |1 | IngredientLibraryn ainekset listataan nyt aakkosjärjestyksessä. Kohennettiin testejä.
|13.4.|1 | Paranneltiin tiedostoon kirjoittamista (tallentaa tiedostoihin tehdyt muutokset sovelluksen sulkemisen yhteydessä). Lisättiin toiminnallisuus, jossa ingredientLibrarysta poistetut ainekset poistetaan myös tiedostosta (pysyväismuistista).
|     |1 | Pieniä korjauksia koodiin ja käyttöliittymään. Käyttöohjeen kirjoittamista. Muokattiin ainesten poisto ingredientLibrarysta toimivammaksi listView:n kanssa - ei erillistä sceneä, jossa kysytään aineksen nimi.
|15.4.|2 | CreateAPotionTest-luokan korjaamista. Kirjoitettiin lisää testejä.
|     |1 | Maven-testien kanssa sekoilua. Päivitettiin työkone java 8:aan.
|     |2 | Lisättiin PotionLibraryn näkymä ja runkotoiminnallisuutta. Rohtoja voi nyt tallentaa rohdot potionLibraryyn, mutta ei pysyväismuistiin (vielä). Lisättiin PotionLibrarylle testejä. Lisäksi muita pienempiä fiksejä, kuten clear-nappula joka tyhjentää työn alla olevan rohdon kaikista aineksista. Rohto ei enää myöskään häviä, jos palaa päävalikkoon Create A Potionista.
|     |1 | Lisättiin rivikattavuutta nostavia testejä.
|16.4.|2 | Arkkitehtuurikuvaus kuntoon.
|22.4.|2 | Muutettiin ingredient-tiedostosta lukeminen ja tiedostoon kirjoittaminen hyödyntämään properties-oliota.
|     |2 | Työstettiin rohtojen pysyväismuistitallennusta.
|23.4.|3 | Toteutettiin toiminnallisuus rohtojen pysyväistallentamiseen.
|24.4.|2 | JavaDocin kirjoittamista.
|     |1 | Lisättiin toiminnallisuus, jossa voidaan poistaa rohtoja potionLibrarysta. Lisättiin kivempi ulkoasu käyttöliittymään.
|25.4.|4 | Arkkitehtuurikuvauksen kirjoittamista ja muokkaamista. Luotiin uudet kuvat. Lisättiin myös toiminnallisuus, joka sattumanvaraisesti luo rohdon.
|     |2 | Muokattiin createAPotion käyttämään listView:ta kätevyyden takia.
|26.4.|4 | Muokattiin createAPotionin remove ingredient yhteensopivaksi listView:n kanssa. Lisättiin createAPotioniin toiminnallisuus jossa voi muuttaa jo lisätyn aineksen määrää.
|     |1 | Työstettiin testejä.
|30.4.|1 | Jaettiin ingredientLibrary kahteen näkymään, jossa toisessa hallinnoidaan ingredientLibraryn aineksia (pääsy päävalikosta) ja toinen jossa valitaan listasta aineksia rohtoon. Hieman ulkoasun hiomista.
|     |3 | Korjattiin bugi, jossa randomise-nappulan luoman rohdon viimeinen aines oli eri arvo listassa kuin "oikeasti". Siistittiin removeIngredientin koodia samoin kuin editIngrentin koodia.
|     |1 | Lisättiin näkymä, jossa käyttäjä voi asettaa rohdolle nimen.
|3.5.|2 | Korjattiin bugi jossa rohtoon pystyi lisäämään aineksen, jonka määrä on 0. Siistittiin tiedostonlukemista koskevia metodeja.
|    |6 | Työstettiin tiedostonlukemisen testejä ja paranneltiin muita testejä.


Summa: 83
