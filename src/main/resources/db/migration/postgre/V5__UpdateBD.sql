ALTER TABLE partida
DROP constraint fk_evento_modalidade;

DELETE FROM partida;

ALTER TABLE partida 
RENAME modalidade TO evento_modalidade;

ALTER TABLE partida
        ADD constraint fk_partida_evento_modalidade
        FOREIGN KEY (evento_modalidade)
REFERENCES evento_modalidade;
