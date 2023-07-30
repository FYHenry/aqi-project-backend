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
  "firstName" : "PRENOM",
  "lastName" : "NOM",
  "email" : "truc@much.fr",
  "password" : "MOT_DE_PASSE",
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

Soon…

### Suppression DELETE

Soon…