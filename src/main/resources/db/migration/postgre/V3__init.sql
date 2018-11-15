    alter table aluno
        drop constraint fk_turma_aluno;
    alter table aluno
        drop constraint FKiop74xch54oyh5ia7b2ixs8sm;
    alter table curso
        drop constraint fk_instituicao_curso;
    alter table endereco
        drop constraint fk_entidade_comum_endereco;
    alter table endereco
        drop constraint fk_municipio_endereco;
    alter table equipe
        drop constraint fk_aluno_equipe;
    alter table equipe
        drop constraint fk_evento_equipe;
    alter table equipe
        drop constraint fk_imagem_equipe;
    alter table equipe_aluno
        drop constraint FK99tf8gf54riibblmmhn4wf9qb;
    alter table equipe_aluno
        drop constraint FK7y1nd0qf88tvpp3vm71k7xre7;
    alter table evento
        drop constraint fk_servidor_evento;
    alter table evento
        drop constraint FKkuspvqdojeaq5rua4ejggjdh9;
    alter table evento_modalidade
        drop constraint fk_evento_evento_modalidade;
    alter table evento_modalidade
        drop constraint fk_modalidade_evento_modalidade;
    alter table imagem
        drop constraint fk_entidade_comum_imagem;
    alter table informacao_evento
        drop constraint fk_evento_informacao_evento;
    alter table informacao_evento_imagem
        drop constraint FKbbvqn2dcty2gdangks365uo8;
    alter table informacao_evento_imagem
        drop constraint FK3tngvot4j5mtkcpc4a01hg6m0;
    alter table instituicao
        drop constraint FKdq4pobfjdyijv7rwq5luplajj;
    alter table instituicao_cargo
        drop constraint FKt4kftn4ih9d43pt2ujw1mtgny;
    alter table instituicao_cargo
        drop constraint FKpgilwg1rw8chpymiy43cjvcn1;
    alter table jogador
        drop constraint fk_aluno_jogador;
    alter table jogador
        drop constraint fk_posicao_jogador;
    alter table jogador
        drop constraint fk_time_jogador;
    alter table modalidade_penalidade
        drop constraint FKrt6ixw60x4grxib0irforwuel;
    alter table modalidade_penalidade
        drop constraint FKixwx9ua9vaav6qc3d2hekxowh;
    alter table modalidade_posicao
        drop constraint FKa1jibdug2rmed19htp1rlrfq7;
    alter table modalidade_posicao
        drop constraint FK507dddf4nj2d00aa9iernkr2e;
    alter table modalidade_tipo_ponto
        drop constraint FKddy5g47ulrl1xd139u9n2vq7f;
    alter table modalidade_tipo_ponto
        drop constraint FKnf0v0bnpu7wft3yj3lhh9y7cf;
    alter table municipio
        drop constraint fk_estado_municipio;
    alter table partida
        drop constraint fk_evento_partida;
    alter table partida
        drop constraint fk_juiz_partida;
    alter table partida
        drop constraint fk_evento_modalidade;
    alter table partida
        drop constraint fk_time_casa_partida;
    alter table partida
        drop constraint fk_time_visita_partida;
    alter table partida
        drop constraint FK1err5ewem4kmry5v9u1vuow7b;
    alter table partida_penalidade
        drop constraint fk_jogador_partida_penalidade;
    alter table partida_penalidade
        drop constraint fk_partida_partida_penalidade;
    alter table partida_penalidade
        drop constraint fk_penalidade_partida_penalidade;
    alter table partida_ponto
        drop constraint fk_jogador_partida_ponto;
    alter table partida_ponto
        drop constraint fk_turma_partida_ponto;
    alter table partida_ponto
        drop constraint fk_tipo_ponto_partida_ponto;
    alter table pessoa
        drop constraint FKlrkary62spgrtobc8dbty9n2a;
    alter table servidor
        drop constraint fk_cargo_servidor;
    alter table servidor
        drop constraint FKc0jy9mpfbp2se8p0yurxvrcss;
    alter table time
        drop constraint fk_equipe_time;
    alter table time
        drop constraint fk_modalidade_time;
    alter table turma
        drop constraint fk_curso_turma;
    drop table if exists aluno cascade;
    drop table if exists cargo cascade;
    drop table if exists curso cascade;
    drop table if exists endereco cascade;
    drop table if exists entidade_comum cascade;
    drop table if exists equipe cascade;
    drop table if exists equipe_aluno cascade;
    drop table if exists estado cascade;
    drop table if exists evento cascade;
    drop table if exists evento_modalidade cascade;
    drop table if exists imagem cascade;
    drop table if exists informacao_evento cascade;
    drop table if exists informacao_evento_imagem cascade;
    drop table if exists instituicao cascade;
    drop table if exists instituicao_cargo cascade;
    drop table if exists jogador cascade;
    drop table if exists modalidade cascade;
    drop table if exists modalidade_penalidade cascade;
    drop table if exists modalidade_posicao cascade;
    drop table if exists modalidade_tipo_ponto cascade;
    drop table if exists municipio cascade;
    drop table if exists partida cascade;
    drop table if exists partida_penalidade cascade;
    drop table if exists partida_ponto cascade;
    drop table if exists penalidade cascade;
    drop table if exists pessoa cascade;
    drop table if exists posicao cascade;
    drop table if exists servidor cascade;
    drop table if exists time cascade;
    drop table if exists tipo_ponto cascade;
    drop table if exists turma cascade;
    drop sequence hibernate_sequence;

    create sequence hibernate_sequence start 5 increment 1;
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
        instituicao int8,
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
        add constraint fk_pessoa_instituicao
        foreign key (instituicao)
        references instituicao;
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
        references curso