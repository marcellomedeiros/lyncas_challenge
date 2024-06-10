CREATE TABLE contas(
    id SERIAL PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC,
    descricao TEXT,
    situacao TEXT
);