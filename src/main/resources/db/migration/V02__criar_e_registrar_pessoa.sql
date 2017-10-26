create table pessoa (
		codigo BIGINT(20) primary key AUTO_INCREMENT,
		nome varchar(50) not null,
        ativo boolean not null,
        logradouro varchar(50),
        numero varchar(10),
        complemento varchar(50),
        bairro varchar(30),
        cep varchar(15),
        cidade varchar(30),
        estado varchar(30)
        
        
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento, bairro, cep, cidade) 
	value ('Raul', true, 'Anita Garibaldi', '319','Proximo a Regional IV', 'Serrinha','60743-410','Fortaleza');
