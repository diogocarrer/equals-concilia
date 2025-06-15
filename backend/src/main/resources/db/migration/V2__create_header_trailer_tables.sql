CREATE TABLE header (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_registro VARCHAR(5),
    numero_estabelecimento VARCHAR(50),
    data_geracao DATE,
    periodo_inicial DATE,
    periodo_final DATE,
    tipo_extrato VARCHAR(10),
    empresa_adquirente VARCHAR(50),
    versao_layout VARCHAR(10),
    versao_release VARCHAR(10)
);

CREATE TABLE trailer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_registro VARCHAR(5),
    total_registros BIGINT,
    reservado TEXT
);