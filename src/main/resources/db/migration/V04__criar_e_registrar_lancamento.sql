CREATE TABLE lancamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL, 
	data_pagamento DATE NOT NULL, 
	valor DECIMAL (10,2) NOT NULL,
	observacao VARCHAR(200),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo) 

) ENGINE=innoDB DEFAULT CHARSET=utf8;