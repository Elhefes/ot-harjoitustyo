# Arkkitehtuurikuvaus
## Rakenne
Sovelluksen rakenne noudattaa seuraavaa pakkausrakennetta:

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/pakkausrakenne.png">
Pakkaus _kurssikuulustelija.ui_ sisältää käyttöliittymän luovan koodin, _kurssikuulustelija.domain_ siältää sovelluslogiikan koodin ja _kurssikuulustelija.dao_ sisältää tiedon talletukseen tarvittavan koodin.

# Käyttöliittymä

Sovelluksen käyttöliittymä sisältää viisi erilaista "Sceneä" eli näkymää. Nämä ovat

- kirjautuminen
- kurssin valitseminen
- kurssin päänäkymä
- harjoittelunäkymä
- tehtävien luontinäkymä

# Sovelluslogiikka

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/classDiagram.png">

# Tietojen talletus

Pakkauksessa _kurssikuulustelija.dao_ olevat Dao-luokat pitävät huolen tietojen pysyväistalletuksesta. Ne noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia käyttämällä [JDBC-rajapintaa](https://www.javatpoint.com/java-jdbc) sekä SQL-kieltä tallettaakseen tiedot tietokantoihin.
