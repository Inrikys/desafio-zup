# Desafio Zup

## Sobre o projeto

O projeto é uma aplicação WEB desenvolvida para a segunda etapa do processo seletivo da <a href="https://www.zup.com.br/">Zup</a> <3

A aplicação consiste em cadastrar usuários que podem ter uma coleção de Comics (diretos da API da Marvel). O preço da Comic resgatada deve estar de acordo com a regra de descontos imposta pelo desafio.

Obs: O banco inicialmente foi populado por Comics da DC com ISBN fictício para realizar os testes de regras de negócio, pois os Comics da Marvel não estavam retornando ISBN.

## Modelo conceitual

![Modelo conceitual](https://i.imgur.com/2p9l5af.png)

## Tecnologias utilizadas

- Java Spring Boot
- Maven
- JPA / Hibernate
- H2
- Spring Cloud Feign

## Estrutura

![Estrutura](https://i.imgur.com/6WXLc6f.png)

## Rotas
[POST]
<b>/users</b>

Exemplo:<br>
Headers<br>
Content-Type = application/json<br><br>
Body
``` json
{
"name": "Henrique", 
"email":"henrique.pereira@zup.com.br", 
"cpf": "446.430.250-50", 
"dob": "1995-08-11T00:00:00Z"
}
```
Retorno<br>
Status: 201 Created
``` json
{
    "id": 4,
    "name": "Henrique",
    "email": "henrique.pereira@zup.com.br",
    "cpf": "446.430.250-50",
    "dob": "1995-08-11T00:00:00Z",
    "comics": []
}
```


[GET]
<b>/comics/register/{comicId}/{userId}</b> <br>

Exemplo:<br>
/comics/register/93669/4 <br>
Retorno <br>
Status: 200 OK
``` json
{
    "id": 4,
    "name": "Henrique",
    "email": "henrique.pereira@zup.com.br",
    "cpf": "446.430.250-50",
    "dob": "1995-08-11T00:00:00Z",
    "comics": [
        {
            "id": 93669,
            "name": "Sinister War (2021) #1",
            "price": 4.99,
            "isbn": "",
            "discountDay": null,
            "discountActive": false,
            "description": "DOCTOR OCTOPUS IS BACK! AND THE SINISTER WAR HAS BEGUN! Ock’s got a new Sinister Six and if you think he’s thought big in the past, think again. What Ock DOESN’T know is that the VULTURE has a sextet of his own: THE SAVAGE SIX! It’s an all-out WAR between two of the greatest villains in the Marvel Universe, and the only person they hate more than each other is SPIDER-MAN! Spidey’s in deep trouble with the toughest battle that he’s ever faced. Nick Spencer and Mark Bagley team up for this epic Spider-Man story guaranteed to shock readers everywhere! ",
            "creators": [
                {
                    "id": 87,
                    "name": "Mark Bagley",
                    "role": "penciler"
                },
                {
                    "id": 339,
                    "name": "Bryan Hitch",
                    "role": "inker (cover)"
                },
                {
                    "id": 548,
                    "name": "Andrew Hennessy",
                    "role": "inker"
                },
                {
                    "id": 582,
                    "name": "Brian Reber",
                    "role": "colorist"
                },
                {
                    "id": 4300,
                    "name": "Nick Lowe",
                    "role": "editor"
                },
                {
                    "id": 359,
                    "name": "John Dell",
                    "role": "inker"
                },
                {
                    "id": 11434,
                    "name": "Nick Spencer",
                    "role": "writer"
                },
                {
                    "id": 5251,
                    "name": "Vc Joe Caramagna",
                    "role": "letterer"
                },
                {
                    "id": 479,
                    "name": "Paul Mounts",
                    "role": "colorist (cover)"
                }
            ]
        }
    ]
}
```


[GET]
<b>/user/{userId}</b> <br>

Exemplo:<br>
/user/4 <br>
Retorno<br>
Status: 200 OK
``` json
{
    "id": 4,
    "name": "Henrique",
    "email": "henrique.pereira@zup.com.br",
    "cpf": "446.430.250-50",
    "dob": "1995-08-11T00:00:00Z",
    "comics": [
        {
            "id": 93669,
            "name": "Sinister War (2021) #1",
            "price": 4.99,
            "isbn": "",
            "discountDay": null,
            "discountActive": false,
            "description": "DOCTOR OCTOPUS IS BACK! AND THE SINISTER WAR HAS BEGUN! Ock’s got a new Sinister Six and if you think he’s thought big in the past, think again. What Ock DOESN’T know is that the VULTURE has a sextet of his own: THE SAVAGE SIX! It’s an all-out WAR between two of the greatest villains in the Marvel Universe, and the only person they hate more than each other is SPIDER-MAN! Spidey’s in deep trouble with the toughest battle that he’s ever faced. Nick Spencer and Mark Bagley team up for this epic Spider-Man story guaranteed to shock readers everywhere! ",
            "creators": [
                {
                    "id": 359,
                    "name": "John Dell",
                    "role": "inker"
                },
                {
                    "id": 4300,
                    "name": "Nick Lowe",
                    "role": "editor"
                },
                {
                    "id": 339,
                    "name": "Bryan Hitch",
                    "role": "inker (cover)"
                },
                {
                    "id": 5251,
                    "name": "Vc Joe Caramagna",
                    "role": "letterer"
                },
                {
                    "id": 479,
                    "name": "Paul Mounts",
                    "role": "colorist (cover)"
                },
                {
                    "id": 582,
                    "name": "Brian Reber",
                    "role": "colorist"
                },
                {
                    "id": 11434,
                    "name": "Nick Spencer",
                    "role": "writer"
                },
                {
                    "id": 548,
                    "name": "Andrew Hennessy",
                    "role": "inker"
                },
                {
                    "id": 87,
                    "name": "Mark Bagley",
                    "role": "penciler"
                }
            ]
        }
    ]
}
```
## Códigos de erros

- <b>404 Not Found</b>: Recurso não encontrado.
- <b>400 Bad Request</b>: Argumentos inválidos. 



## Autor
Henrique Pereira de Sousa

https://www.linkedin.com/in/henrique-sousa-a80210173/
