# aqi-project-backend

## À propos des requêtes CRUD en HTTP

J’exploite un client HTTP : cURL dans le Bourn Again SHell (Bash).

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

### Code de retour OK

```json
{
  "code" : 200,
  "message" : "OK"
}
```