INSERT INTO departamentos( nome ) 
VALUES( 'Contabilidade' ),
        ('Administração'),
        ('Tecnologia da Informação'),
        ('Recursos Humanos');


INSERT INTO cargos( nome, id_departamento_fk ) 
VALUES('Auxiliar Contábil',1),
        ('Auxiliar Administrativo',2),	
        ('Contador',1),		 
        ('Administrador',2),		 
        ('Programado PHP',3),		 
        ('Desenvolvedor Java Jr.',3),	
        ('Desenvolvedor Java Pleno',3),
        ('Desenvolvedor Java Senior',3),
        ('Auxiliar de RH',4),		 
        ('Coordenador de RH',4),	
        ('Psicologo',4);

INSERT INTO enderecos( logradouro, numero, bairro, complemento, cep, cidade, uf )
VALUES( 'Rua das Oliveiras', 200, 'Centro', 'Fundos', '97050-015', 'Porto Alegre', 'RS'),
        ('Av. dos Palmares', 320, 'Ribeirão', 'Casa C', '98050-908', 'Canoas', 'RS' ),
        ('Rua Senador Cassiano do Nascimento', 135, 'Noroeste','AP. 106', '90798-099','Porto Alegre','RS'),
        ('Av. Maracanã', 309,'Maracanã','AP. 108', '91050-980', 'Rio de Janeiro','RJ'),
        ('Rua Gomes Carneiro', 909,'Centro', 'Esquina', '98080-999', 'Porto Alegre','RS');

INSERT INTO funcionarios(nome, salario, cargo_id_fk, endereco_id_fk, data_entrada, data_saida, departamento_id_fk, cpf_cnpj)
VALUES('Jorge da Silva', 3500.99, 4, 1, '2014-10-01',null, 2, null),
	('Ana Maria Lindova',3560.70,2, 2,  '2014-08-01', '2015-03-02', 2, null),
	('Juan Perez', 14060.99, 2, 3,  '2015-05-01', null, 2, null),
	('Marcio Silva', 2890.99, 1, 4, '2013-10-18', '2015-09-06', 1, null),
	('Joana Prado', 3804.00, 4, 5, '2015-02-01', null, 1, null);


