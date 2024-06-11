CREATE TABLE contas(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC,
    descricao TEXT,
    situacao TEXT
);