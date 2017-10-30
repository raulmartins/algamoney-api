create table categoria (
		codigo BIGINT(20) primary key AUTO_INCREMENT,
		nome varchar(50) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO CATEGORIA (nome) value ('Lazer');
INSERT INTO CATEGORIA (nome) value ('Alimentacao');
INSERT INTO CATEGORIA (nome) value ('Jogos');
INSERT INTO CATEGORIA (nome) value ('Dividas');
