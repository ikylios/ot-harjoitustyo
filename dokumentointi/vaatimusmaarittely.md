# Rohtosovelluksen vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus toimii käyttäjän sähköisenä taikarohtoreseptikirjana ja -hakemistona. Sovelluksen avulla luodaan, talletetaan ja muokataan rohtoja. Lisäksi voidaan generoida uusia ja uniikkeja rohtoja.

## Käyttäjät
Alkuvaiheessa vain yksi käyttäjä, myöhemmin saatetaan lisätä enemmillä oikeuksilla varustettu pääkäyttäjä.



## Perusversion tarjoama toiminnallisuus
### Päävalikko
* pääsy rohdonluomisnäkymään (tyhjä rohdonmuokkausnäkymä.)
* pääsy rohtojenselaamisnäkymään

### Rohtohakemisto
* listaa kaikki talletetut rohdot
* rohtoja pääsee tarkastelemaan ja poistamaan
![Potion Library](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/potionLibrary.jpeg)

## Rohdon tarkastelunäkymä
* listaa rohdon ainekset
* kertoo rohdon kuvauksen (nimi, käyttötarkoitus, käyttötapa)
* sisältää muokkausnappulan, josta pääsee rohdon muokkausnäkymään
* alkeellinen kuva rohdosta

### Muokkausnäkymä
* käyttäjä voi muokata kaikkia rohdon ominaisuuksia, jotka ovat tarkastelunäkymässä
* voidaan lisätä tai poistaa aineksia rohdosta
* nappula josta generoida satunnaiset ainekset rohtoon
![Muokkaus ja luonti](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/createApotion.jpeg)

## Aineskirjasto (ingredientLibrary)
* sisältää kaikki ainekset, joita rohtoon voidaan laittaa.
* käyttäjä voi luoda uusia aineksia tai poistaa olemassa olevia aineksia (tämä poistaa kaikki rohdot, joissa ainesta on.)

## Jatkokehitysideoita
* tallennettujen rohtojen muokkaustoiminnallisuus
* taikaefektien syöttäminen eli jonkinlainen efektikirjasto
* rohtotyyppien syöttäminen eli jonkinlainen tyyppikirjasto

* rohtojen arvostelutoiminnallisuus, esim. 1-5 tähteä, kommentteja...
* rohtojen listaaminen hakemistossa muussa kuin luomisjärjestyksessä
* rohdon generoidun kuvan parempi vastaavuus reseptin ainesosien kanssa, esim. sammakonkoivet ovat näkyviä
* muiden käyttäjien lisääminen sovellukseen
* rohtojen etsiminen yhden ainesosan tai monen ainesosan perusteella
* rohtojen etsiminen käyttötarkoituksen perusteella
* rohdon arvioidun hinnan laskeminen
* erillinen ainesostoslista
* sovelluksella on parempi ymmärrys siitä, että kalliimmat ja harvinaisemmat ainesosat tuottavat kalliimpia ja harvinaisempia rohtoja
* mahdollisuus itse valita rohdon taikaefekti ja rohtotyyppi
