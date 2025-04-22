Integrantes: Luann Mariano - RM558548  //  Henzo Puchetti - RM555179

HEADER: Content-Type:application/json

CRIAR PERSONAGENS: POST http://localhost:8080/personagens
{
        "nome": "LuannZeiro",
        "classe": "guerreiro",
        "nivel": 50,
        "moedas": 150
    }

LISTAR PERSONAGENS: GET http://localhost:8080/personagens

LISTAR MAGOS: http://localhost:8080/personagens?classe=mago

LISTAR GUERREIROS: http://localhost:8080/personagens?classe=guerreiro

