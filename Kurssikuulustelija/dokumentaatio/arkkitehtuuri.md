# Arkkitehtuurikuvaus
## Rakenne
Sovelluksen rakenne noudattaa seuraavaa pakkausrakennetta:

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/pakkausrakenne.png">
Pakkaus _kurssikuulustelija.ui_ sisältää käyttöliittymän luovan koodin, _kurssikuulustelija.domain_ siältää sovelluslogiikan koodin ja _kurssikuulustelija.dao_ sisältää tiedon talletukseen tarvittavan koodin.

## Käyttöliittymä

Sovelluksen käyttöliittymä sisältää viisi erilaista "Sceneä" eli näkymää. Nämä ovat

- kirjautuminen
- kurssin valitseminen
- kurssin päänäkymä
- harjoittelunäkymä
- tehtävien luontinäkymä

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostaa _kurssikuulustelija.domain_ -pakkauksesta löytyvät luokat [User](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/User.java), [Exercise](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/Exercise.java) sekä [Point](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/domain/Point.java).

Sovelluksen toiminnallisuudesta pitää huolen lähinnä _kurssikuulustelija.dao_ -pakkauksen dao-luokat. Niiden avulla tietokantaan lisätään käyttäjiä, tehtäviä sekä pisteitä tehdyistä tehtävistä. Lisäksi [GUI](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/ui/GUI.java) -luokassa on jonkin verran sovelluslogiikkaa, joka olisi varmaan kannattanut pyrkiä erottamaan omaan luokkaansa.

<img src="https://raw.githubusercontent.com/henripalin/ot-harjoitustyo/master/Kurssikuulustelija/dokumentaatio/kuvat/classDiagram.png">

_kurssikuulustelija.ui_-pakkauksessa _GUI_-luokan lisäksi on [JavaFxSpringService](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/ui/JavaFxSpringService.java)-luokka. Tämä luokka vaadittiin, jotta voidaan käyttää Springiä JavaFx:n kanssa ja käyttää @Autowired-notaatiota kun haluamme määrittää Dao-luokat.

## Tietojen talletus

Pakkauksessa _kurssikuulustelija.dao_ olevat Dao-luokat pitävät huolen tietojen pysyväistalletuksesta. Ne noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia käyttämällä [JDBC-rajapintaa](https://www.javatpoint.com/java-jdbc) sekä SQL-kieltä tallettaakseen tiedot tietokantoihin.

## Päätoiminnallisuudet

### Käyttäjän kirjautuminen

Käyttäjän kirjautuminen toteutetaan suurimmaksi osaksi _GUI_-luokassa. Kun käyttäjä syöttää tiedot kirjautumiskenttiin, voidaan tarkastaa SQL-tietokannasta, onko kyseinen käyttäjä jo rekisteröitynyt. Tämä toteutetaan antamalla _User_-olio _UserDao_-luokan checkCredentialsAndReturnUser()-metodille, joka palauttaa henkilön tiedot _GUI_-luokalle mikäli kirjautumistiedot ovat oikein.

### Käyttäjän rekisteröityminen

Rekisteröitymisessä tietokantatalusta tiedon hakemisen sijaan sinne lisätään tietoa _UserDao_-luokan _create_-metodilla, jolle annetaan parametriksi henkilö-olio, joka halutaan luoda.

### Tehtävien käsittely

Sovellukseen luodaan uusi tehtävä käyttämällä _ExerciseDao_-luokan _create_-metodia. Sen jälkeen tietokannasta voidaan hakea esimerkiksi satunnainen tehtävä käyttämällä _getRandom()_-metodia. Se palauttaa _GUI_-luokalle aktiivisena olevasta kurssista satunnaisen harjoitustehtävän.

Kun käyttäjä painaa _Tarkista_-nappia, ohjelma kutsuu _ExerciseDao_:n metodia _getAnswer()_, joka palauttaa merkkijonona tehtävän oikean vastauksen. Tämän avulla _GUI_-luokassa voidaan joko ilmoittaa käyttäjälle väärästä vastauksesta tai siirtyä seuraavaan tehtäävään.

### Pisteiden käsittely

Kun käyttäjä vastaa oikein tehtävään, hän saa siitä pisteen. Tämän toiminnallisuus hoidetaan jälleen hyödyntämällä _SQL_-tietokantaa [PointDao](https://github.com/henripalin/ot-harjoitustyo/blob/master/Kurssikuulustelija/src/main/java/kurssikuulustelija/dao/PointDao.java)-luokassa. Käyttäjän vastatessa oikein _GUI_-luokka kutsuu _PointDao_:n metodia _create_, jolla jälleen luodaan tietokantatauluun uutta tietoa. Sinne lisätään pisteen saanut käyttäjä, tehtävän id sekä kurssin nimi.

Kun käyttäjä poistaa tehtävän, kutsutaan sekä _ExerciseDao_-luokkaa, että _PointDao_-luokkaa. Metodit joita kutsutaan ovat _deleteExercise()_, joka poistaa kyseisen tehtävän tietokannasta, ja sen jälkeen kutsutaan _deleteExercisePoints()_-metodia jotta saadaan poistettua kaikkien käyttäjien pisteet jo poistetusta tehtävästä.

## Ohjelman rakenteen ongelmat

### Sovelluslogiikka

Jälkeenpäin miettien ohjelma olisi kannattanut toteuttaa enemmän siten, että sovelluslogiikka hoidettaisiin omassa luokassaan, eikä niin että se hoidetaan suurimmaksi osakseen _GUI_-luokassa. Tämä olisi helpottanut koodin muokkausta ja lisännyt jatkokehitysmahdollisuuksia. 

## Dao-luokat

Ohjelman _Dao_-luokkia olisi voinut parantaa vähentämällä toisteista koodia, jota sovelluksessa on paljon ohjelmassa. Tässä olisi voinut hyödyntää esimerkiksi _Dao-interfacea_.
