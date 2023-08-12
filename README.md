# aqi-project-backend

## Glossaire

* INSEE : Institut National de la Statistique et des Études Économiques ;
* Code INSEE communal (`insee`) : code à 5 chiffres décimaux dont les deux
premiers correspondent au code INSEE de la région circonscrite.
* Code INSEE départemental : code à 3 chiffres hexadécimaux.

NB: Le code INSEE communal n’est pas le code postal !

## Architecture

```
                       ________________________________
                      |  ________________              |
 ________     ________|_|_     _____    _V_________   _V____ 
| Config |   | Controller |-->| DTO |  | Exception | | Util |
|________|   |____________|   |     |  |           | |      |
               |              |     |  |           | |      |
 ________     _V_______       |     |  |           | |      |
| Entity |<--| Service |----->|     |  |           | |      |
|        |   |_________|      |_____|  |___________| |______|
|        |     |   | |___________________A             A
|        |     |   |___________________________________|
|        |    _V__________
|        |<--| Repository |
|________|   |____________|
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
curl --verbose 'http://127.0.0.1:8080/user-account/1'
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
''' -H 'Content-Type: application/json' 'http://127.0.0.1:8080/user-account'
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
''' -H 'Content-Type: application/json' -X PUT 'http://127.0.0.1:8080/user-account/1'
```

### Suppression DELETE

```bash
#!/bin/bash
curl --verbose -H 'Content-Type: application/json' -X DELETE 'http://127.0.0.1:8080/user-account/1'
```

### Listage GET

```bash
#!/bin/bash
curl --verbose 'http://127.0.0.1:8080/user-accounts'
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

Rendre les listeurs paginables.

Gérer les suppressions de données dépendantes : nouvelle exception ou
suppressions en cascade.

Implémenter la couche de sécurité via Spring Security.

## Liste des entités à implémenter

- [ ] `entities` :
  - [x]  `account` :
    - [x]   `Address`,
    - [x]   `UserAccount`,
    - [x]   `UserStatus` ;
  - [ ]  `forum` :
    - [x]   `Message`,
    - [ ]   `Reaction`,
    - [x]   `Thread`,
    - [ ]   `Topic` ;
  - [x]  `map` :
    - [x]   `AirQualityReport`,
    - [x]   `AirQualityStation`,
    - [x]   `Bookmark`,
    - [x]   `City`,
    - [x]   `Department`,
    - [x]   `ForecastType`,
    - [x]   `Population`,
    - [x]   `Region`,
    - [x]   `ReportDate`,
    - [x]   `WeatherReport`,
    - [x]   `WeatherStation`.
