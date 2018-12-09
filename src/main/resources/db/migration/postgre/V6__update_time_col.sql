ALTER TABLE time
DROP constraint fk_modalidade_time;

DELETE FROM jogador;
DELETE FROM partida;
DELETE FROM time;

ALTER TABLE time
RENAME modalidade TO evento_modalidade;

ALTER TABLE time
ADD constraint fk_time_evento_modalidade
        FOREIGN KEY (evento_modalidade)
REFERENCES evento_modalidade;
