# marvelCharacter

### Como compilar, rodar e instalar a aplicação:

### Para compilar o projeto utilize o comando:
- utilize o comando mvn clean package

### Para instalar e rodar a aplicação utilze o comando:
- mvn clean spring-boot:run


## chamadas CURL

### Recupera todos os registros
efetuar uma chamada GET
curl http://localhost:8090/marvelCharacter/rest/

### Recupera um personagem dado um ID
efetuar uma chamada GET
http://localhost:8090/marvelCharacter/rest/{id}

### exemplo:
curl http://localhost:8090/marvelCharacter/rest/1011334

### Inserir um novo personagem
efetuar uma chamada POST
curl -H 'Content-Type: application/json' -X POST -d '{"id":123456,"name":"Personagem Teste","description":"Alguma descrição","modified":1398795497000,"thumbnail":{"id":301,"path":"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784","extension":"jpg"},"comics":null,"resourceURI":null,"urls":null,"stories":null,"events":null,"series":null}'' http://localhost:8090/marvelCharacter/rest/

### Atualizar os dados de um personagem
efetuar uma chamada PUT
curl -H 'Content-Type: application/json' -X PUT -d '{"id":123456,"name":"Personagem Teste","description":"Alguma descrição","modified":1398795497000,"thumbnail":{"id":301,"path":"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784","extension":"jpg"},"comics":null,"resourceURI":null,"urls":null,"stories":null,"events":null,"series":null}'' http://localhost:8090/marvelCharacter/rest/

### Remover um personagem
efetuar uma chamada DELETE
http://localhost:8090/marvelCharacter/rest/{id}

### exemplo:
curl -X DELETE http://localhost:8090/marvelCharacter/rest/123456
