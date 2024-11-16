## Connexion au backend Quiz Platform

### Configuration PostgreSQL
- **Host** : `my_postgres`
- **Port** : `5432`
- **User** : `postgres`
- **Password** : `pwd`

### Backend
L'application backend est accessible sur :
http://localhost:8080
### Instructions pour démarrer :
1. Créez un réseau Docker :
   ```bash
   docker network create quiz_network

2. Lancez PostgreSQL :
 ```bash
docker run --network=quiz_network --name my_postgres -e POSTGRES_PASSWORD=pwd -p 5432:5432 -d postgres


3. Construisez et exécutez le backend :
 ```bash

docker build -t quizzmaster:latest .
docker run --name quizz_platform -p 8080:8080 --network quiz_network quizzmaster:latest

