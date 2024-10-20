Tecnologias utilizadas:
+ Java 17
+ Spring Boot
+ MongoDB
+ Mockito
+ JUnit
+ Log4j
+ Lombok

+ Para armazenar as informações, foi criado o banco de dados "votacao_db" e as Collections "pautas" e "votos".

+ Endpoints:

Criação de Pauta:

Endpoint -> curl --location --request POST 'http://localhost:8080/api/pauta' \
--header 'Content-Type: application/json' \
--data-raw '{
    "titulo": "Aprovação 3",
    "descricao": "Aprovação da Pauta Y."
}'

![criarPauta](https://github.com/user-attachments/assets/f7931461-df39-44b9-9da9-9fa91d7c86af)

Abrir sessão:

Endpoint -> curl --location --request POST 'http://localhost:8080/api/pauta/:pautaId/abrir-sessao'

![abrirSessao](https://github.com/user-attachments/assets/9d0e7118-b3b0-47ce-af0e-9a76077fda70)

Votação:

Endpoint -> curl --location --request POST 'http://localhost:8080/api/voto/pautaId/:pautaId/associadoId/:associadoId' \
--header 'Content-Type: application/json' \
--data-raw '{
    "voto": "NAO"
}'

![votar](https://github.com/user-attachments/assets/6a5ecc11-aeca-4adf-a1b7-af8b1ce4f501)

Obter pautas:

Endpoint -> curl --location --request GET 'http://localhost:8080/api/pauta'

![pautas](https://github.com/user-attachments/assets/7d732102-63f4-4e93-839e-978aab93eabf)


Resultado:

Endpoint -> curl --location --request GET 'http://localhost:8080/api/pauta/resultado/:pautaId'

![resultado](https://github.com/user-attachments/assets/703d1b6a-d21a-4d12-a9e1-18d32355bd2c)

