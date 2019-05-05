# RohtoAppi
Sovellus taianomaisten taikarohtojen luomiseen ja tallentamiseen.

# Linkit

## Dokumentaatio

[Käyttöohje](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/ikylios/ot-harjoitustyo/blob/master/dokumentointi/tyotunnit.md)

## Releaset
[Viikko 5](https://github.com/ikylios/ot-harjoitustyo/releases)

[Loppupalautus](https://github.com/ikylios/ot-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla `mvn test`.

Testikattavuusraportti luodaan komennolla `mvn jacoco:report`.

Checkstyleraportti tehdään komennolla `mvn jxr:jxr checkstyle:checkstyle`

Suoritettavan jar-tiedoston luominen tehdään komennolla `mvn package`. Paketin nimi on RohtoAppi-1.0-SNAPSHOT.jar.

JavaDoc generoidaan komennolla `mvn javadoc:javadoc`.
