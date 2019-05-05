# Kurssikuulustelija
Kurssikuulustelija on sovellus, jolla käyttäjä voi luoda tiettyyn kurssiin harjoitustehtäviä, sekä harjoitella niitä. Sovellukseen on mahdollisuus tehdä monia eri käyttäjiä, ja sovellus pitää tietoa siitä, mitä tehtäviä kukin käyttäjä on jo ratkaissut.

### Releaset
[Viikko7](https://github.com/henripalin/ot-harjoitustyo/releases/tag/Viikko7)

[Viikko6](https://github.com/henripalin/ot-harjoitustyo/releases/tag/viikko6)

[Viikko5](https://github.com/henripalin/ot-harjoitustyo/releases/tag/viikko5)

### Dokumentaatio
[Käyttöohje](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kayttoohje.md)

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

### Javadocin generointi

Javadocin saat generoitua komennolla

```
mvn javadoc:javadoc
```
jonka jälkeen javadoc-dokumentti löytyy polusta _target/site/apidocs
