# springboot-rickAndMortyApi
Consumo de la api de rick and morthy con solo un metodo get by id

-----------
-----------
### **GET**
## api/v1/character/
## URL: localhost:8080/api/v1/character/

## Descripción

**Sin pasarle un id** se creo este metodo por si no le pasan un id, controllar como que si quisieran llamar a un getall.


## Documentación



### Request

_Parámetros a enviar en la URL_

Este no contiene parametros, ya el el ultimi / lo quita el framework y lo toma como un get all, este retorna un error
500 con un mensaje

-----------
-----------
### **GET**
## api/v1/character/${idCharacter}

## URL: localhost:8080/api/v1/character/1

## Descripción

**Metodo de consulta por id** se le pasa el id del caracter (numerico) y retorna la data solicitada


## Documentación



### Request

_Parámetros a enviar en el la url_

| Parámetro     | Tipo de dato    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| Constraints &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
| --------|---------|-------|
| idCharacter  | Long   | Obligatorio. es numerico   |


# Swagger
El proyecto esta andando en el puesto 8080 por ende la ruta es
```http://localhost:8080/swagger-ui.html#```
##Gerson Aponte