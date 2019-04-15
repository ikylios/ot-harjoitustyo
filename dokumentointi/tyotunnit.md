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

Summa: 30
