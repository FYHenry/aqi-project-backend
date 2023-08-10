# aqi-project-backend

## Architecture

```
                       ________________________________
                      |  ________________              |
 ________     ________|_|_     _____    _V_________   _V_____ 
| Config |   | Controller |   | DTO |  | Exception | | Utils |
|________|   |____________|-->|     |  |           | |       |
               |              |     |  |           | |       |
 ________     _V_______       |     |  |           | |       |
| Entity |   | Service |----->|     |  |           | |       |
|        |<--|_________|      |_____|  |___________| |_______|
|        |     |   | |___________________A             A
|        |     |   |___________________________________|
|        |    _V__________
|        |   | Repository |
|________|<--|____________|
```

## Profil Spring : `init`

Une fois activé dans `src/resources/application.properties`, le profil `init`
modifie le comportement du serveur.
La base de donné est alors purgée au démarrage et à l’arrêt du serveur.

## À propos des requêtes CRUD en HTTP

Est exploité un client HTTP : cURL dans Bash.
L’entité `UserAccount` est prise en exemple`.

### Lecture GET

```bash
#!/bin/bash
curl --verbose 'http://127.0.0.1:8080/user/1'
```

### Création POST

```bash
#!/bin/bash
curl --verbose --data '''
{
  "firstName" : "Firstname",
  "lastName" : "Lastname",
  "email" : "thing@any.uk",
  "password" : "Password",
  "userStatusIdList" : [],
  "addressId" : 1,
  "bookmarkIdList" : [],
  "role" : "USER",
  "topicIdList" : [],
  "threadIdList" : [],
  "messageIdList" : [],
  "reactionIdList" : []
}
''' -H 'Content-Type: application/json' 'http://127.0.0.1:8080/user'
```

### Actualisation PUT

```bash
#!/bin/bash
curl --verbose --data '''
{
  "firstName" : "PRÉNOM",
  "lastName" : "NOM",
  "email" : "truc@much.fr",
  "password" : "MOT_DE_PASSE",
  "userStatusIdList" : [],
  "addressId" : 1,
  "bookmarkIdList" : [],
  "role" : "ADMIN",
  "topicIdList" : [],
  "threadIdList" : [],
  "messageIdList" : [],
  "reactionIdList" : []
}
''' -H 'Content-Type: application/json' -X PUT 'http://127.0.0.1:8080/user/1'
```

### Suppression DELETE

```bash
#!/bin/bash
curl --verbose -H 'Content-Type: application/json' -X DELETE 'http://127.0.0.1:8080/user/1'
```

### Listage GET

```bash
#!/bin/bash
curl --verbose 'http://127.0.0.1:8080/users'
```

### Code de retour `OK`

```json
{
  "code" : 200,
  "message" : "OK"
}
```

### Code de retour `NOT_FOUND`

```json
{
  "code" : 404,
  "message" : "NOT_FOUND"
}
```

## Favicon

![AQI logotype](src/main/resources/static/favicon.ico "favicon.ico")

Une petite expérimentation est incluse par l’apparition d’une *favicon*,
petite image présentant le logotype du projet.

## À faire plus tard

Trouver comment créer :
* Une classe entité générique ;
* Une classe répertoire générique ;
* Une classe service générique ;
* Une classe controleur générique.

« Dois-je vraiment répliquer tout pour chaque entité ? »

~~Dessiner un logotype.~~

Ajouter des classes pour la phase *test*.

Ajouter une licence.

## Liste des entités à implémenter

[x] `entities` :

[x]  `account` :

[x]   `Address`,

[x]   `UserAccount`,

[x]   `UserStatus` ;

[ ]  `forum` :

[ ]   `Message`,

[ ]   `Reaction`,

[ ]   `Thead`,

[ ]   `Topic` ;

[ ]  `map` :

[ ]   `AirQualityReport`,

[ ]   `AirQualityStation`,

[ ]   `Bookmark`,

[x]   `City`,

[x]   `Department`,

[ ]   `ForecastType`,

[ ]   `Population`,

[x]   `Region`,

[ ]   `ReportDate`,

[ ]   `WeatherReport`,

[ ]   `WeatherStation`.
