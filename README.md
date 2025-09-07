# GestioneLibreria
Progetto  ingegneria del software a.a.2024/25 dell'università della Calabria.

## Descrizione
Il progetto sviluppato in questo repository si inserisce in questo panorama
proponendo una soluzione intermedia: un gestore di catalogo librario pensato
per scopi didattici, che mantiene semplicità d’uso e leggerezza ma al tempo
stesso integra funzionalità tipiche dei software più evoluti, come la ricerca,
l’ordinamento e la persistenza dei dati su file. Inoltre, sono stati adottati pattern
software che rendono l’architettura estensibile e più vicina alle buone pratiche
ingegneristiche rispetto alle soluzioni più basilari.
Per garantire una gestione semplificata ed efficiente all’utente viene fornita una
GUI(Graphic User Interface) con la quale si possa interagire con l’applicazione e
le sue funzionalitá in maniera intuitiva ed efficiente.

## Pattern utilizzati:funzioni che svolgono
1. Builder: Costruzione oggetto Libro
2. Facade: per riunire tutte le operazioni(complessità)→nascondere logica interna
3. Singleton: Istanza unica per Libreria
4. Observer: notifica aggiunta/rimozione libro
5. Memento: operazioni di undo/redo,persistenza libreria
6. Command: funzione di modifica, aggiunta e rimozione specifica
7. Strategy: gestione caricamento e salvataggio file JSON/CSV, ordinamento per filtri
8. Strategy+Chain of Responsability: per la ricerca con filtri multipli


## Prerequisiti

- [Java JDK](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) 23 o superiore
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## Installazione

1. Clona il repository:
    ```bash
    [gh repo clone mariaritaombres/GestoreLibreria](https://github.com/mariaritaombres/GestoreLibreria.git)
    ```
2. Apri IntelliJ IDEA.
3. Seleziona `File` -> `Open` e scegli la cartella del progetto clonata.
4. Assicurati che il JDK sia configurato correttamente:
    - Vai su `File` -> `Project Structure` -> `Project` e verifica che il JDK corretto sia selezionato.
5. Costruisci il progetto usando `Build` -> `Build Project`.

## Esecuzione

1. Seleziona ed apri la cartella 'AppGUI'.
2. Trova la classe `App` .
3. Fai clic con il tasto destro del mouse sulla classe `App` e seleziona `Run 'Main'`.


## Creato da 
Maria Rita Ombres
   
