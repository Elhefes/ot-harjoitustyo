# Kurssikuulustelija <h1>
Tällä hetkellä sovelluksessa on lähes valmis käyttöliittymä. Perusominaisuudet löytyy myös. Ohjelmassa voi luoda uuden käyttäjän, kirjautua sisään, luoda uusia kysymyksiä sekä katsella kysymyksiä.

### Releaset
[Viikko5](https://github.com/henripalin/ot-harjoitustyo/releases/tag/viikko5)

### Dokumentaatio
[Vaatimuusmäärittely](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/vaatimuusmaarittely.md)
  
[Työaikakirjanpito](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/arkkitehtuuri.md)

### Testaus

Repositorion kloonauksen jälkeen testit voidaan suorittaa komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
Kattavuusraporttia löytyy sijainnista _target/site/jacoco/index.html_

Checkstyle-dokumentti saadaan luotua komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
Komennon jälkeen html-dokumentti löytyy sijainnista _target/site/checkstyle.html


### Suoritettavan jarin generointi

```
mvn package
```

löytyy hakemistosta _target
