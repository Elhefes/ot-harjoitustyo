# Käyttöohje

Lataa tiedosto [täältä](https://github.com/henripalin/ot-harjoitustyo/releases/tag/Viikko7)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Kurssikuulustelija.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kuvat/kirjauduSisaan.png?raw=true">

Kirjautuminen onnistuu kirjoittamalla olemassaolevan käyttäjän tunnus ja salasana niitä vastaaviin kenttiin ja painamalla _Kirjaudu_. Sovellus ilmoittaa jos kirjautuminen ei onnistunut.

## Uuden käyttäjän luominen

Uusi käyttäjä luodaan samasta näkymästä, kuin kirjautuminen. Voit luoda uuden käyttäjän kirojittamalla haluamasi käyttäjätunnuksen ja salasanan, ja painamalla _Rekisteröidy_.

Sovellus ilmoittaa tekstillä jos rekisteröinnissä on tapahtunut jonkinlainen virhe.

## Kurssin valinta

Kirjautumisen jälkeen sovelluksessa avautuu seuraava näkymä

<img src="https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kuvat/kurssinValinta.png?raw=true" width="600">

Valitse haluamasi kurssi painamalla näkymän keskellä olevia nappeja. Jos painat väärää nappia, niin voit mennä kaikissa seuraavissa näkymissä takaisinpäin painamalla vasemmassa yläkulmassa olevaa _Takaisin_-nappia.

Painamalla vasemman ylänurkan painiketta _Kirjaudu ulos_, voit kirjautua ulos sovelluksesta ja sovellus palata takaisin kirjaantumisnäkymään.

## Kurssinäkymä

Kun olet valinnut kurssin, sinulle aukeaa kurssinäkymä, joka näyttää tältä:

<img src="https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kuvat/kurssiNakyma.png?raw=true" width="600">

Tästä näkymästä voit valita, halutko lisätä kurssille lisää harjoitustehtäviä, vai harjoitella itse omatekemiä ja muiden tekemiä tehtäviä. Painamalla _Harjoittele tehtäviä_-nappia, pääset harjoittelemaan valitun kurssin tehtäviä. Painamalla taas _Luo uusia tehtäviä_ pääset kirjoittamaan uusia tehtäviä kurssiin.

Näkymästä näät lisäksi kuinka monta tehtävää olet jo suorittanut kyseisestä kurssista.

## Harjoittele tehtäviä

Tehtävien harjoittelunäkymä näyttää tältä:

<img src="https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kuvat/vastaaKysymykseen.png?raw=true" width="600">

Jos tehtäviä ei ole vielä luotu kurssille ollenkaan, sovellus ilmoittaa siitä kuvan mukaisesti. Muuten tehtävänanto olisi vastausalueen yläpuolella. Kirjoittamalla vastauksen vastausalueelle ja painamalla _Tarkasta_ sovellus kertoo, menikö vastaus oikein vai väärin. Jos vastaus menee oikein, sovellus tallettaa pisteen käyttäjälle kyseisestä tehtävästä. Voit myös halutessasi painaa _Näytä vastaus_, jolloin sovellus antaa suoraan oikean vastauksen.  Tällöin sovellus ei talleta pistettä kyseisestä tehtävästä. Vastauksen tultua sinulla on mahdollisuus jatkaa seuraavaan kysymykseen painamalla ilmestynyttä _Seuraava_-nappia.
## Luo uusia tehtäviä

Tehtävien luomisnäkymä näyttää tältä:

<img src="https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/dokumentaatio/kuvat/luoUusiTehtava.png?raw=true" width="600">

Tehtävän voi luoda kirjoittamalla sille tehtävänannon sekä oikean vastauksen ja painamalla _Luo kysymys!_-nappia. Vastauksessa isoilla kirjaimilla ei ole väliä ja tehtävästä saa pisteen, vaikka ei kirjoittaisi isoja ja pieniä kirjaimia oikein, mutta kunhan vastaus on muuten oikein.
