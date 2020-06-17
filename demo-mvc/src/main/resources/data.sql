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
VALUES( 'Rua das Oliveiras', 200, 'Centro', 'Fundos', '97050015', 'Porto Alegre', 'RS'),
        ('Av. dos Palmares', 320, 'Ribeirão', 'Casa C', '98050908', 'Canoas', 'RS' ),
        ('Rua Senador Cassiano do Nascimento', 135, 'Noroeste','AP. 106', '90798099','Porto Alegre','RS'),
        ('Av. Maracanã', 309,'Maracanã','AP. 108', '91050980', 'Rio de Janeiro','RJ'),
        ('Rua Gomes Carneiro', 909,'Centro', 'Esquina', '98080999', 'Porto Alegre','RS');

INSERT INTO funcionarios(nome, salario, cargo_id_fk, endereco_id_fk, data_entrada, data_saida, departamento_id_fk, cpf_cnpj)
VALUES('Jorge da Silva', 3500.99, 4, 1, '2014-10-01',null, 2, null),
	('Ana Maria Lindova',3560.70,2, 2,  '2014-08-01', '2015-03-02', 2, null),
	('Juan Perez', 14060.99, 2, 3,  '2015-05-01', null, 2, null),
	('Marcio Silva', 2890.99, 1, 4, '2013-10-18', '2015-09-06', 1, null),
	('Marco Silva', 2890.99, 1, 4, '2013-10-18', '2015-09-06', 1, null),
	('Joana Prado', 3804.00, 4, 5, '2015-02-01', null, 1, null);


INSERT INTO area_atividade(descricao)
VALUES('Financeira'),
		('Comércio'),
		('Industrial'),
		('Pecuária'),
		('Química'),
		('Tecnologia da Inf');
		
INSERT INTO empresa( cnpj, data_abertura, razao_social, situacao, area_atividade, endereco_id, faturamento_mensal)
VALUES  ('01800019000185', '2015-02-01', 'PortoCred Financeira', 'A', 1, 1,100000.00),
		('02879250001302','2010-01-01', 'Empresário Cobrança','A', 2, 2, 250000.00),
		('01171591000122','1996-01-01', 'Nova Quest Financeira', 'A', 1,1, 180000.00),
		('05550662000159', '2003-06-03', 'Walar Desenv de Sist IT LTDA', 'A', 6, 4, 1000000.00),
		('60856820002660', '1985-05-27', 'Brasimet Proc Termico LTDA', 'A', 5, 4, 780000.00),
		('60856820000373', '1945-06-01', 'Bodycote Brasimet Proc Termico Ltda', 'A', 3, 4, 50000.00);
		
 		
		