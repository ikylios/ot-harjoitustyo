# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus toimii käyttäjän sähköisenä taikarohtoreseptikirjana ja -hakemistona. Sovelluksen avulla luodaan, talletetaan ja muokataan rohtoja. Lisäksi voidaan generoida uusia ja uniikkeja rohtoja.

## Käyttäjät
Vain yksi käyttäjä.


## Perusversion tarjoama toiminnallisuus
### Päävalikko
* pääsy rohdonluomisnäkymään (tyhjä rohdonmuokkausnäkymä)
* pääsy rohtojenselaamisnäkymään
* pääsy aineskirjastoon

### Rohtohakemisto (Potion Library)
* listaa kaikki talletetut rohdot
* rohtoja pääsee tarkastelemaan ja poistamaan

## Rohdon tarkastelunäkymä
* listaa rohdon ainekset
* kertoo rohdon kuvauksen (nimi, käyttötarkoitus, käyttötapa)

### Muokkausnäkymä
* voidaan lisätä tai poistaa aineksia rohdosta
* nappi, josta generoida satunnaiset ainekset rohtoon


Alustava luonnos:
![Muokkaus ja luonti](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/createApotion.jpeg)

## Aineskirjasto (Ingredient Library)
* sisältää kaikki ainekset, joita rohtoon voidaan laittaa
* käyttäjä voi luoda uusia aineksia tai poistaa olemassa olevia aineksia

## Jatkokehitysideoita
* tallennettujen rohtojen muokkaustoiminnallisuus
* taikaefektien syöttäminen eli jonkinlainen efektikirjasto
* rohtotyyppien syöttäminen eli jonkinlainen tyyppikirjasto
* alkeellinen kuva rohdosta generated potion -näkymässä
* rohtojen listaaminen hakemistossa muussa kuin aakkosjärjestyksessä
* rohtojen etsiminen yhden ainesosan tai monen ainesosan perusteella
* rohtojen etsiminen käyttötarkoituksen perusteella
* rohdon arvioidun hinnan laskeminen
* erillinen ainesostoslista
* sovelluksella on hienompi ymmärrys aineksista ja taioista; harvinaisemmat ainekset randomisoitaisiin pienemmillä annoksilla kuin yleisemmät ainekset, yksinkertaiset ainesosat luovat yksinkertaista taikaa, yms.
* mahdollisuus itse valita rohdon taikaefekti, rohtotyyppi
