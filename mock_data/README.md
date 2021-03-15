## How to upload mock-data

Du trenger kun å gjøre 1 & 2 første gang. 

### Gjør bare første gang
1. Legg til private key i directoriet. Den ligger i `.gitignore` så alle må gjøre dette selv. 
2. Kjør `npm install` i `mock_data`-mappen.

### Alltid gjør dette
3. Slett eksisterende data manuelt i firestore. Slik som på bildet her: 
   
   ![](https://i.imgur.com/5u12R2x.png) 

4. Gå ett nivå opp fra `/mock_data` og kjør `node mock_data`. 
