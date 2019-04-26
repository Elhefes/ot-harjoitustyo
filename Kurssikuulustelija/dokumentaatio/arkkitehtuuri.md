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

Sovelluksen loogisen datamallin muodostaa _kurssikuulustelija.domain_ -pakkauksesta löytyvät luokat [User](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/User.java), [Exercise](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/Exercise.java) sekä [Point](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/Point.java).

Sovelluksen toiminnallisuudesta pitää huolen lähinnä _kurssikuulustelija.dao_ -pakkauksen dao-luokat. Niiden avulla tietokantaan lisätään käyttäjiä, tehtäviä sekä pisteitä tehdyistä tehtävistä. Lisäksi _GUI_ -luokassa on jonkin verran sovelluslogiikkaa, mutta pyrin erottamaan sitä uuteen luokkaan kun kerkiän.

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/classDiagram.png">

# Tietojen talletus

Pakkauksessa _kurssikuulustelija.dao_ olevat Dao-luokat pitävät huolen tietojen pysyväistalletuksesta. Ne noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia käyttämällä [JDBC-rajapintaa](https://www.javatpoint.com/java-jdbc) sekä SQL-kieltä tallettaakseen tiedot tietokantoihin.
