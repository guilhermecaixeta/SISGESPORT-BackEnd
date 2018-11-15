INSERT INTO public.entidade_comum(id)
    VALUES (1);

INSERT INTO public.instituicao(descricao, nome, id)
    VALUES ('Instituto Federal de Goiás - Campus Luziânia', 'IFG - Campus Luziânia', 1);

INSERT INTO public.curso(id, flg_ativo, nome, instituicao)
    VALUES (1, true, 'TADS', 1);

INSERT INTO public.turma(id, data_inicial, data_limite, flg_ativo, nome, curso)
 VALUES (1, '2018-01-01', '2022-01-01', true, 'TADS2018/1', 1);

INSERT INTO public.cargo(id, descricao, nome)
    VALUES (1, 'Lecionar aulas de tecnologia', 'Professor de Informática');

INSERT INTO public.instituicao_cargo(instituicao, cargo)
    VALUES (1,1);

INSERT INTO public.entidade_comum(id)
    VALUES (2);
INSERT INTO public.pessoa
(data_nasc, email, matricula, nome, perfil, senha, sexo, id, instituicao)
VALUES('1990-01-01', 'admin_sistema@gmail.com', '20122080010047', 'Admin Sistema', 'ROLE_ADMIN', '$2a$10$gTn11stUFPXhLS1pC4DYhekdmbL3ngMTMrU58SyVgsjPO70bWnWxS', 'M', 2, 1);
INSERT INTO public.servidor
(id, cargo)
VALUES(2, 1);


INSERT INTO public.entidade_comum(id)
    VALUES (3);
INSERT INTO public.pessoa
(data_nasc, email, matricula, nome, perfil, senha, sexo, id, instituicao)
VALUES('1990-01-01', 'admin_sistema_2@gmail.com', '20122080010048', 'Guilherme Caixeta', 'ROLE_ADMIN', '$2a$10$gTn11stUFPXhLS1pC4DYhekdmbL3ngMTMrU58SyVgsjPO70bWnWxS', 'M', 3, 1);
INSERT INTO public.servidor
(id, cargo)
VALUES(3, 1);


Insert into estado (id,nome, uf) values (1,'Acre', 'AC');

Insert into estado (id,nome, uf) values (2,'Alagoas', 'AL');

Insert into estado (id,nome, uf) values (3,'Amapá', 'AP');

Insert into estado (id,nome, uf) values (4,'Amazonas', 'AM');

Insert into estado (id,nome, uf) values (5,'Bahia', 'BA');

Insert into estado (id,nome, uf) values (6,'Ceará', 'CE');

Insert into estado (id,nome, uf) values (7,'Distrito Federal', 'DF');

Insert into estado (id,nome, uf) values (8,'Espírito Santo', 'ES');

Insert into estado (id,nome, uf) values (9,'Goiás', 'GO');

Insert into estado (id,nome, uf) values (10,'Maranhão', 'MA');

Insert into estado (id,nome, uf) values (11,'Mato Grosso', 'MT');

Insert into estado (id,nome, uf) values (12,'Mato Grosso do Sul', 'MS');

Insert into estado (id,nome, uf) values (13,'Minas Gerais', 'MG');

Insert into estado (id,nome, uf) values (14,'Pará', 'PA');

Insert into estado (id,nome, uf) values (15,'Paraíba', 'PB');

Insert into estado (id,nome, uf) values (16,'Paraná', 'PR');

Insert into estado (id,nome, uf) values (17,'Pernambuco', 'PE');

Insert into estado (id,nome, uf) values (18,'Piauí', 'PI');

Insert into estado (id,nome, uf) values (19,'Rio de Janeiro', 'RJ');

Insert into estado (id,nome, uf) values (20,'Rio Grande do Norte', 'RN');

Insert into estado (id,nome, uf) values (21,'Rio Grande do Sul', 'RS');

Insert into estado (id,nome, uf) values (22,'Rondônia', 'RO');

Insert into estado (id,nome, uf) values (23,'Roraima', 'RR');

Insert into estado (id,nome, uf) values (24,'Santa Catarina', 'SC');

Insert into estado (id,nome, uf) values (25,'São Paulo', 'SP');

Insert into estado (id,nome, uf) values (26,'Sergipe', 'SE');

Insert into estado (id,nome, uf) values (27,'Tocantins', 'TO');





Insert into municipio (id,nome, estado)values(1,'Abadia de Goiás',(SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(2,'Abadiânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(3,'Acreúna',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(4,'Adelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(5,'Água Fria de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(6,'Água Limpa',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(7,'Águas Lindas de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(8,'Alexânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(9,'Aloândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(10,'Alto Horizonte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(11,'Alto Paraíso de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(12,'Alvorada do Norte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(13,'Amaralina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(14,'Americano do Brasil',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(15,'Amorinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(16,'Anápolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(17,'Anhanguera',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(18,'Anicuns',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(19,'Aparecida de Goiânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(20,'Aparecida do Rio Doce',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(21,'Aporé',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(22,'Araçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(23,'Aragarças',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(24,'Aragoiânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(25,'Araguapaz',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(26,'Arenópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(27,'Aruanã',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(28,'Aurilândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(29,'Avelinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(30,'Baliza',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(31,'Barro Alto',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(32,'Bela Vista de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(33,'Bom Jardim de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(34,'Bom Jesus de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(35,'Bonfinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(36,'Bonópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(37,'Brazabrantes',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(38,'Britânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(39,'Buriti Alegre',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(40,'Buriti de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(41,'Buritinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(42,'Cabeceiras',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(43,'Cachoeira Alta',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(44,'Cachoeira de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(45,'Cachoeira Dourada',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(46,'Caçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(47,'Caiapônia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(48,'Caldas Novas',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(49,'Caldazinha',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(50,'Campestre de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(51,'Campinaçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(52,'Campinorte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(53,'Campo Alegre de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(54,'Campo Limpo de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(55,'Campos Belos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(56,'Campos Verdes',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(57,'Carmo do Rio Verde',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(58,'Castelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(59,'Catalão',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(60,'Caturaí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(61,'Cavalcante',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(62,'Ceres',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(63,'Cezarina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(64,'Chapadão do Céu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(65,'Cidade Ocidental',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(66,'Cocalzinho de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(67,'Colinas do Sul',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(68,'Córrego do Ouro',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(69,'Corumbá de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(70,'Corumbaíba',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(71,'Cristalina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(72,'Cristianópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(73,'Crixás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(74,'Cromínia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(75,'Cumari',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(76,'Damianópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(77,'Damolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(78,'Davinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(79,'Diorama',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(80,'Doverlândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(81,'Edealina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(82,'Edéia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(83,'Estrela do Norte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(84,'Faina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(85,'Fazenda Nova',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(86,'Firminópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(87,'Flores de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(88,'Formosa',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(89,'Formoso',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(90,'Gameleira de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(91,'Divinópolis de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(92,'Goianápolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(93,'Goiandira',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(94,'Goianésia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(95,'Goiânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(96,'Goianira',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(97,'Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(98,'Goiatuba',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(99,'Gouvelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(100,'Guapó',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(101,'Guaraíta',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(102,'Guarani de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(103,'Guarinos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(104,'Heitoraí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(105,'Hidrolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(106,'Hidrolina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(107,'Iaciara',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(108,'Inaciolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(109,'Indiara',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(110,'Inhumas',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(111,'Ipameri',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(112,'Ipiranga de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(113,'Iporá',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(114,'Israelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(115,'Itaberaí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(116,'Itaguari',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(117,'Itaguaru',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(118,'Itajá',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(119,'Itapaci',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(120,'Itapirapuã',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(121,'Itapuranga',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(122,'Itarumã',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(123,'Itauçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(124,'Itumbiara',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(125,'Ivolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(126,'Jandaia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(127,'Jaraguá',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(128,'Jataí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(129,'Jaupaci',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(130,'Jesúpolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(131,'Joviânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(132,'Jussara',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(133,'Lagoa Santa',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(134,'Leopoldo de Bulhões',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(135,'Luziânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(136,'Mairipotaba',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(137,'Mambaí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(138,'Mara Rosa',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(139,'Marzagão',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(140,'Matrinchã',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(141,'Maurilândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(142,'Mimoso de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(143,'Minaçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(144,'Mineiros',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(145,'Moiporá',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(146,'Monte Alegre de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(147,'Montes Claros de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(148,'Montividiu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(149,'Montividiu do Norte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(150,'Morrinhos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(151,'Morro Agudo de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(152,'Mossâmedes',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(153,'Mozarlândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(154,'Mundo Novo',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(155,'Mutunópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(156,'Nazário',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(157,'Nerópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(158,'Niquelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(159,'Nova América',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(160,'Nova Aurora',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(161,'Nova Crixás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(162,'Nova Glória',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(163,'Nova Iguaçu de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(164,'Nova Roma',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(165,'Nova Veneza',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(166,'Novo Brasil',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(167,'Novo Gama',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(168,'Novo Planalto',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(169,'Orizona',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(170,'Ouro Verde de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(171,'Ouvidor',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(172,'Padre Bernardo',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(173,'Palestina de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(174,'Palmeiras de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(175,'Palmelo',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(176,'Palminópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(177,'Panamá',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(178,'Paranaiguara',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(179,'Paraúna',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(180,'Perolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(181,'Petrolina de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(182,'Pilar de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(183,'Piracanjuba',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(184,'Piranhas',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(185,'Pirenópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(186,'Pires do Rio',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(187,'Planaltina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(188,'Pontalina',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(189,'Porangatu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(190,'Porteirão',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(191,'Portelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(192,'Posse',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(193,'Professor Jamil',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(194,'Quirinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(195,'Rialma',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(196,'Rianápolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(197,'Rio Quente',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(198,'Rio Verde',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(199,'Rubiataba',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(200,'Sanclerlândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(201,'Santa Bárbara de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(202,'Santa Cruz de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(203,'Santa Fé de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(204,'Santa Helena de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(205,'Santa Isabel',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(206,'Santa Rita do Araguaia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(207,'Santa Rita do Novo Destino',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(208,'Santa Rosa de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(209,'Santa Tereza de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(210,'Santa Terezinha de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(211,'Santo Antônio da Barra',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(212,'Santo Antônio de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(213,'Santo Antônio do Descoberto',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(214,'São Domingos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(215,'São Francisco de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(216,'São João D''Aliança',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(217,'São João da Paraúna',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(218,'São Luís de Montes Belos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(219,'São Luíz do Norte',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(220,'São Miguel do Araguaia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(221,'São Miguel do Passa Quatro',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(222,'São Patrício',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(223,'São Simão',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(224,'Senador Canedo',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(225,'Serranópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(226,'Silvânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(227,'Simolândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(228,'Sítio D''Abadia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(229,'Taquaral de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(230,'Teresina de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(231,'Terezópolis de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(232,'Três Ranchos',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(233,'Trindade',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(234,'Trombas',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(235,'Turvânia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(236,'Turvelândia',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(237,'Uirapuru',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(238,'Uruaçu',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(239,'Uruana',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(240,'Urutaí',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(241,'Valparaíso de Goiás',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(242,'Varjão',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(243,'Vianópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(244,'Vicentinópolis',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(245,'Vila Boa',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(246,'Vila Propício',( SELECT id FROM estado WHERE uf ='GO'));

Insert into municipio (id,nome, estado)values(247,'Brasília',( SELECT id FROM estado WHERE uf = 'DF'));

