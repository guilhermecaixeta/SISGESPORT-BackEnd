    create sequence hibernate_sequence start 1 increment 1;
 
    create table aluno (
        id int8 not null,
        turma int8 not null,
        primary key (id)
    );
 
    create table cargo (
        id int8 not null,
        descricao varchar(400) not null,
        nome varchar(255) not null,
        primary key (id)
    );
 
    create table curso (
        id int8 not null,
        flg_ativo boolean not null,
        nome varchar(255) not null,
        instituicao int8 not null,
        primary key (id)
    );
 
    create table endereco (
        id int8 not null,
        bairro varchar(255) not null,
        cep varchar(8) not null,
        complemento varchar(255) not null,
        logradouro varchar(255) not null,
        entidade_comum int8,
        municipio int8 not null,
        primary key (id)
    );
 
    create table entidade_comum (
        id int8 not null,
        primary key (id)
    );
 
    create table equipe (
        id int8 not null,
        codigo_equipe varchar(20) not null,
        cor varchar(255) not null,
        nome varchar(255) not null,
        aluno int8,
        evento int8 not null,
        imagem int8,
        primary key (id)
    );
 
    create table equipe_aluno (
        aluno int8 not null,
        equipe int8 not null
    );
 
    create table estado (
        id int8 not null,
        nome varchar(20) not null,
        uf varchar(2) not null,
        primary key (id)
    );
 
    create table evento (
        codigo_evento varchar(15) not null,
        data_criacao timestamp not null,
        data_fim timestamp not null,
        data_fim_inscricao timestamp not null,
        data_inicio timestamp not null,
        data_inicio_inscricao timestamp not null,
        descricao varchar(400) not null,
        nome varchar(255) not null,
        qnt_equipe int4 not null,
        id int8 not null,
        servidor int8,
        primary key (id)
    );
 
    create table evento_modalidade (
        id int8 not null,
        idade_maxima int4,
        sexo char(1) not null,
        evento int8 not null,
        modalidade int8 not null,
        primary key (id)
    );
 
    create table imagem (
        id int8 not null,
        data_imagem timestamp,
        descricao_imagem varchar(255),
        imagem oid not null,
        nome varchar(255),
        tamanho float8 check (tamanho>=0),
        entidade_comum int8,
        primary key (id)
    );
 
    create table informacao_evento (
        id int8 not null,
        data_postagem timestamp,
        descricao varchar(8000) not null,
        tipo_informacao char(1) not null,
        titulo varchar(255) not null,
        evento int8 not null,
        primary key (id)
    );
 
    create table informacao_evento_imagem (
        informacao_evento int8 not null,
        imagem int8 not null
    );
 
    create table instituicao (
        descricao varchar(255) not null,
        nome varchar(255) not null,
        id int8 not null,
        primary key (id)
    );
 
    create table instituicao_cargo (
        instituicao int8 not null,
        cargo int8 not null
    );
 
    create table jogador (
        id int8 not null,
        num_camisa int4,
        jogador int8 not null,
        posicao int8,
        time int8 not null,
        primary key (id)
    );
 
    create table modalidade (
        id int8 not null,
        descricao varchar(400),
        nome varchar(255) not null,
        num_max_jogador int4,
        num_min_jogador int4,
        primary key (id)
    );
 
    create table modalidade_penalidade (
        modalidade int8 not null,
        penalidade int8 not null
    );
 
    create table modalidade_posicao (
        posicao int8 not null,
        modalidade int8 not null
    );
 
    create table modalidade_tipo_ponto (
        tipo_ponto int8 not null,
        modalidade int8 not null
    );
 
    create table municipio (
        id int8 not null,
        nome varchar(255) not null,
        estado int8 not null,
        primary key (id)
    );
 
    create table partida (
        acrescimo float8,
        data_partida timestamp,
        duracao_partida float8,
        id int8 not null,
        evento int8 not null,
        juiz int8 not null,
        modalidade int8 not null,
        time_casa int8 not null,
        time_visita int8 not null,
        primary key (id)
    );
 
    create table partida_penalidade (
        id int8 not null,
        jogador int8 not null,
        partida int8 not null,
        penalidade int8 not null,
        primary key (id)
    );
 
    create table partida_ponto (
        id int8 not null,
        jogador int8 not null,
        partida int8 not null,
        tipo_ponto int8 not null,
        primary key (id)
    );
 
    create table penalidade (
        id int8 not null,
        descricao varchar(60) not null,
        nome varchar(30) not null,
        primary key (id)
    );
 
    create table pessoa (
        data_nasc timestamp not null,
        email varchar(255),
        matricula varchar(20) not null,
        nome varchar(255) not null,
        perfil varchar(255) not null,
        senha varchar(60) not null,
        sexo char(1) not null,
        id int8 not null,
        primary key (id)
    );
 
    create table posicao (
        id int8 not null,
        descricao varchar(30) not null,
        nome varchar(30) not null,
        num_max_jogador int4 not null,
        num_min_jogador int4 not null,
        primary key (id)
    );
 
    create table servidor (
        id int8 not null,
        cargo int8,
        primary key (id)
    );
 
    create table time (
        id int8 not null,
        num_derrota int4,
        num_empate int4,
        num_vitoria int4,
        pontuacao int4,
        equipe int8 not null,
        modalidade int8 not null,
        primary key (id)
    );
 
    create table tipo_ponto (
        id int8 not null,
        nome varchar(30) not null,
        valor int4 not null,
        primary key (id)
    );
 
    create table turma (
        id int8 not null,
        data_inicial timestamp not null,
        data_limite timestamp not null,
        flg_ativo boolean not null,
        nome varchar(20) not null,
        curso int8,
        primary key (id)
    );
 
    alter table cargo 
        add constraint UK_91fcfd99kg1af67rjv08heu1q unique (nome);
 
    alter table curso 
        add constraint UK_bdhliwglt8i7q1v80fb95vea9 unique (nome);
 
    alter table equipe 
        add constraint UK_9w9dcg4ucbp96pydqxvyom9we unique (codigo_equipe);
 
    alter table equipe_aluno 
        add constraint UKelnogyqvix9qlcb71dvypsiex unique (equipe, aluno);
 
    alter table estado 
        add constraint UK_gfot2y0318rs8hc74ppp0n87p unique (nome);
 
    alter table estado 
        add constraint UK_ixjses41lcs0vkr2givuw4eiw unique (uf);
 
    alter table evento 
        add constraint UK_men79nyohoxfr6uy7n0p1qrfe unique (codigo_evento);
 
    alter table informacao_evento_imagem 
        add constraint UK5ql9111aee2iv95qgeyuu7dgc unique (informacao_evento, imagem);
 
    alter table instituicao_cargo 
        add constraint UKm9l97w6adhrg7m2fc9tciwpon unique (instituicao, cargo);
 
    alter table modalidade_penalidade 
        add constraint UKtk21he9glnocrccvowiplmng7 unique (modalidade, penalidade);
 
    alter table modalidade_posicao 
        add constraint UKkwausuk34c67qgjfh98ntrms unique (modalidade, posicao);
 
    alter table modalidade_tipo_ponto 
        add constraint UK91x0jbwd3ng6iey2wsyammbm8 unique (modalidade, tipo_ponto);
 
    alter table municipio 
        add constraint UK_o1j5bixpbaupvkiae3mhsadth unique (nome);
 
    alter table pessoa 
        add constraint UK_mc87q8fpvldpdyfo9o5633o5l unique (email);
 
    alter table pessoa 
        add constraint UK_fthvu7gx1dgqwixgfy4qlbfvr unique (matricula);
 
    alter table posicao 
        add constraint UK_pfj73e1b1mmbyd6965hk6fghx unique (nome);
 
    alter table aluno 
        add constraint fk_turma_aluno 
        foreign key (turma) 
        references turma;
 
    alter table aluno 
        add constraint FKiop74xch54oyh5ia7b2ixs8sm 
        foreign key (id) 
        references pessoa;
 
    alter table curso 
        add constraint fk_instituicao_curso 
        foreign key (instituicao) 
        references instituicao;
 
    alter table endereco 
        add constraint fk_entidade_comum_endereco 
        foreign key (entidade_comum) 
        references entidade_comum;
 
    alter table endereco 
        add constraint fk_municipio_endereco 
        foreign key (municipio) 
        references municipio;
 
    alter table equipe 
        add constraint fk_aluno_equipe 
        foreign key (aluno) 
        references aluno;
 
    alter table equipe 
        add constraint fk_evento_equipe 
        foreign key (evento) 
        references evento;
 
    alter table equipe 
        add constraint fk_imagem_equipe 
        foreign key (imagem) 
        references imagem;
 
    alter table equipe_aluno 
        add constraint FK99tf8gf54riibblmmhn4wf9qb 
        foreign key (equipe) 
        references equipe;
 
    alter table equipe_aluno 
        add constraint FK7y1nd0qf88tvpp3vm71k7xre7 
        foreign key (aluno) 
        references aluno;
 
    alter table evento 
        add constraint fk_servidor_evento 
        foreign key (servidor) 
        references servidor;
 
    alter table evento 
        add constraint FKkuspvqdojeaq5rua4ejggjdh9 
        foreign key (id) 
        references entidade_comum;
 
    alter table evento_modalidade 
        add constraint fk_evento_evento_modalidade 
        foreign key (evento) 
        references evento;
 
    alter table evento_modalidade 
        add constraint fk_modalidade_evento_modalidade 
        foreign key (modalidade) 
        references modalidade;
 
    alter table imagem 
        add constraint fk_entidade_comum_imagem 
        foreign key (entidade_comum) 
        references entidade_comum;
 
    alter table informacao_evento 
        add constraint fk_evento_informacao_evento 
        foreign key (evento) 
        references evento;
 
    alter table informacao_evento_imagem 
        add constraint FKbbvqn2dcty2gdangks365uo8 
        foreign key (imagem) 
        references imagem;
 
    alter table informacao_evento_imagem 
        add constraint FK3tngvot4j5mtkcpc4a01hg6m0 
        foreign key (informacao_evento) 
        references informacao_evento;
 
    alter table instituicao 
        add constraint FKdq4pobfjdyijv7rwq5luplajj 
        foreign key (id) 
        references entidade_comum;
 
    alter table instituicao_cargo 
        add constraint FKt4kftn4ih9d43pt2ujw1mtgny 
        foreign key (cargo) 
        references cargo;
 
    alter table instituicao_cargo 
        add constraint FKpgilwg1rw8chpymiy43cjvcn1 
        foreign key (instituicao) 
        references instituicao;
 
    alter table jogador 
        add constraint fk_aluno_jogador 
        foreign key (jogador) 
        references aluno;
 
    alter table jogador 
        add constraint fk_posicao_jogador 
        foreign key (posicao) 
        references posicao;
 
    alter table jogador 
        add constraint fk_time_jogador 
        foreign key (time) 
        references time;
 
    alter table modalidade_penalidade 
        add constraint FKrt6ixw60x4grxib0irforwuel 
        foreign key (penalidade) 
        references penalidade;
 
    alter table modalidade_penalidade 
        add constraint FKixwx9ua9vaav6qc3d2hekxowh 
        foreign key (modalidade) 
        references modalidade;
 
    alter table modalidade_posicao 
        add constraint FKa1jibdug2rmed19htp1rlrfq7 
        foreign key (modalidade) 
        references modalidade;
 
    alter table modalidade_posicao 
        add constraint FK507dddf4nj2d00aa9iernkr2e 
        foreign key (posicao) 
        references posicao;
 
    alter table modalidade_tipo_ponto 
        add constraint FKddy5g47ulrl1xd139u9n2vq7f 
        foreign key (modalidade) 
        references modalidade;
 
    alter table modalidade_tipo_ponto 
        add constraint FKnf0v0bnpu7wft3yj3lhh9y7cf 
        foreign key (tipo_ponto) 
        references tipo_ponto;
 
    alter table municipio 
        add constraint fk_estado_municipio 
        foreign key (estado) 
        references estado;
 
    alter table partida 
        add constraint fk_evento_partida 
        foreign key (evento) 
        references evento;
 
    alter table partida 
        add constraint fk_juiz_partida 
        foreign key (juiz) 
        references pessoa;
 
    alter table partida 
        add constraint fk_evento_modalidade 
        foreign key (modalidade) 
        references modalidade;
 
    alter table partida 
        add constraint fk_time_casa_partida 
        foreign key (time_casa) 
        references time;
 
    alter table partida 
        add constraint fk_time_visita_partida 
        foreign key (time_visita) 
        references time;
 
    alter table partida 
        add constraint FK1err5ewem4kmry5v9u1vuow7b 
        foreign key (id) 
        references entidade_comum;
 
    alter table partida_penalidade 
        add constraint fk_jogador_partida_penalidade 
        foreign key (jogador) 
        references jogador;
 
    alter table partida_penalidade 
        add constraint fk_partida_partida_penalidade 
        foreign key (partida) 
        references partida;
 
    alter table partida_penalidade 
        add constraint fk_penalidade_partida_penalidade 
        foreign key (penalidade) 
        references penalidade;
 
    alter table partida_ponto 
        add constraint fk_jogador_partida_ponto 
        foreign key (jogador) 
        references jogador;
 
    alter table partida_ponto 
        add constraint fk_turma_partida_ponto 
        foreign key (partida) 
        references partida;
 
    alter table partida_ponto 
        add constraint fk_tipo_ponto_partida_ponto 
        foreign key (tipo_ponto) 
        references tipo_ponto;
 
    alter table pessoa 
        add constraint FKlrkary62spgrtobc8dbty9n2a 
        foreign key (id) 
        references entidade_comum;
 
    alter table servidor 
        add constraint fk_cargo_servidor 
        foreign key (cargo) 
        references cargo;
 
    alter table servidor 
        add constraint FKc0jy9mpfbp2se8p0yurxvrcss 
        foreign key (id) 
        references pessoa;
 
    alter table time 
        add constraint fk_equipe_time 
        foreign key (equipe) 
        references equipe;
 
    alter table time 
        add constraint fk_modalidade_time 
        foreign key (modalidade) 
        references modalidade;
 
    alter table turma 
        add constraint fk_curso_turma 
        foreign key (curso) 
        references curso;

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

