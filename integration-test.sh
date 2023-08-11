#!/bin/env bash

# Création de la région
curl --verbose --data '''
{
  "name" : "Here"
}
''' -H 'Content-Type: application/json' \
'http://127.0.0.1:8080/region'

# Création du département
curl --verbose --data '''
{
  "code" : 1,
  "name" : "StrangeZone",
  "regionId" : 1
}
''' -H 'Content-Type: application/json' \
'http://127.0.0.1:8080/department'

# Création de la commune
curl --verbose --data '''
{
  "insee" : 99001,
  "name" : "SomeWhere",
  "postcode" : 999,
  "latitude" : 90.00,
  "longitude" : 90.01,
  "departmentId" : 1
}
''' -H 'Content-Type: application/json' \
'http://127.0.0.1:8080/city'


# Création de l’adresse
curl --verbose --data '''
{
  "addressLine1" : "SomeWhere",
  "addressLine2" : "",
  "cityId" : 99001
}
''' -H 'Content-Type: application/json' \
'http://127.0.0.1:8080/address'

# Création du compte utilisateur
curl --verbose --data '''
{
  "firstName" : "Firstname",
  "lastName" : "Lastname",
  "email" : "thing@any.uk",
  "password" : "Password",
  "userStatusIds" : [1],
  "addressId" : 1,
  "bookmarkIds" : [],
  "role" : "USER",
  "topicIds" : [],
  "threadIds" : [],
  "messageIds" : [],
  "reactionIds" : []
}
''' -H 'Content-Type: application/json' \
'http://127.0.0.1:8080/user-account'

# Actualisation du compte utilisateur
curl --verbose --data '''
{
  "firstName" : "MyName",
  "lastName" : "JustThat",
  "email" : "me@that.none",
  "password" : "01234",
  "userStatusIds" : [1],
  "addressId" : 1,
  "bookmarkIds" : [],
  "role" : "USER",
  "topicIds" : [],
  "threadIds" : [],
  "messageIds" : [],
  "reactionIds" : []
}
''' -H 'Content-Type: application/json' \
-X PUT 'http://127.0.0.1:8080/user-account/1'

# Lecture du compte utilisateur
curl --verbose 'http://127.0.0.1:8080/user-account/1'

# Suppression du compte utilisateur
curl --verbose -H 'Content-Type: application/json' \
-X DELETE 'http://127.0.0.1:8080/user-account/1'