# Testausdokumentti

Testasin ohjelmaa hyödyntämällä JUnittia sekä manuualisesti testaamalla ohjelmaa monella eri tietokoneella ja käyttöjärjestelmällä.

## Yksikkö- ja integraatiotestaus

### Sovellusrakenteiden testaus

JUnit-testi testaavat kurssikuulustelija.domain -paketin luokkia luomalla niistä esimerkkiolioita ja katsomalla, että tiedot ovat oikein assertEquals-komennoilla.

### Dao-luokat

Sovelluksen kolmen Dao-luokan toiminnallisuutta on testattu JUnitilla luomalla "valetietokanta", joka simuloi sovelluksen omaa tietokantaa.

### Testauskattavuus

Kuten kuvasta huomaa, sovelluksen rivikattavuus on 72% ja haarautumakattavuus 76%.

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/kattavuus.png" width="800">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu sekä Cubbli-käyttöjärjestelmällä että Windows 10-järjestelmällä.

### Ohjelman asennus

Ohjelma on ladattu ja asennettu [käyttöohjeen](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kayttoohje.md) mukaisesti.

Sovellus on todettu toimivaksi silloin, kuin tietokantatiedostot löytyy, mutta myös silloin kun ne eivät löydy.

### Toiminnallisuuksien testaus

[Vaatimuusmäärittelyn](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/vaatimuusmaarittely.md) perustoiminnallisuudet on testattu läpikotaisin, ja kaikki toimivat niin kuin pitääkin.
