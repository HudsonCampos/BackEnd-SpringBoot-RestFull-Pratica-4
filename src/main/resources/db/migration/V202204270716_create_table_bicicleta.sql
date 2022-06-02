CREATE TABLE IF NOT EXISTS t_bicicleta (
    id_bicicleta int8 NOT NULL AUTO_INCREMENT,
    cor VARCHAR (255) NOT NULL,
    modelo VARCHAR (255) NOT NULL,
    foto LongBlob,
    PRIMARY KEY (id_bicicleta)
)