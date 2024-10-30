-- Trigger para selecionar um motorista disponível antes de inserir uma nova coleta
CREATE OR REPLACE TRIGGER TRG_SELECIONAR_MOTORISTA
BEFORE INSERT ON TB_COLETA
FOR EACH ROW
DECLARE
    v_motorista_id VARCHAR2(20);
BEGIN
    -- Seleciona o primeiro motorista disponível
    SELECT ID_MOTORISTA INTO v_motorista_id FROM TB_MOTORISTA
    WHERE STATUS_MOTORISTA = 'DISPONIVEL' AND ROWNUM = 1
    FOR UPDATE OF STATUS_MOTORISTA;

    -- Se um motorista for encontrado, ele será associado à coleta e seu status será atualizado
    IF v_motorista_id IS NOT NULL THEN
        :NEW.ID_MOTORISTA := v_motorista_id;
        UPDATE TB_MOTORISTA SET STATUS_MOTORISTA = 'OCUPADO' WHERE ID_MOTORISTA = v_motorista_id;
    ELSE
        RAISE_APPLICATION_ERROR(-20009, 'Nenhum motorista disponível.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20010, 'Nenhum motorista disponível.');
END;
/

-- Trigger para verificar se o peso da coleta excede a capacidade do caminhão antes de inseri-la
CREATE OR REPLACE TRIGGER TRG_KG_CAMINHAO
BEFORE INSERT ON TB_COLETA
FOR EACH ROW
DECLARE
    v_max_capacity NUMBER;
BEGIN
    -- Verifica a capacidade máxima do caminhão associado à coleta
    SELECT KG_CAPACIDADE INTO v_max_capacity FROM TB_CAMINHAO WHERE ID_CAMINHAO = :NEW.ID_CAMINHAO;

    -- Se o peso da coleta exceder a capacidade do caminhão, lança um erro
    IF :NEW.KG_COLETA > v_max_capacity THEN
        RAISE_APPLICATION_ERROR(-20011, 'A coleta excede a capacidade máxima do caminhão.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20012, 'Caminhão não encontrado.');
END;
/

-- Trigger para checar conflitos de horário de agendamento antes de inserir um novo agendamento
CREATE OR REPLACE TRIGGER TRG_CHECAR_AGENDA
BEFORE INSERT ON TB_AGENDAMENTO
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    -- Verifica se já existe um agendamento no mesmo horário ou em um intervalo de 1 hora
    SELECT COUNT(*) INTO v_count
    FROM TB_AGENDAMENTO
    WHERE HR_AGENDA BETWEEN :NEW.HR_AGENDA - INTERVAL '1' HOUR AND :NEW.HR_AGENDA + INTERVAL '1' HOUR
      AND DT_AGENDA = :NEW.DT_AGENDA;

    -- Se houver um conflito de horário, lança um erro
    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Conflito de horário detectado.');
    END IF;
END;
/

-- Trigger para atualizar ou inserir uma rota associada a uma coleta após a inserção ou atualização da coleta
CREATE OR REPLACE TRIGGER TRG_ATUALIZAR_ROTA
AFTER INSERT OR UPDATE ON TB_COLETA
FOR EACH ROW
BEGIN
    -- Atualiza ou insere uma nova rota associada à coleta
    MERGE INTO TB_ROTA trg
    USING (SELECT :NEW.ID_COLETA AS ID_COLETA,
                  :NEW.END_ORIGEM AS END_ORIGEM,  -- Valores dinâmicos para origem e destino
                  :NEW.END_DESTINO AS END_DESTINO,
                  'Rota atualizada' AS DSC_ROTA,
                  :NEW.HR_COLETA AS HR_PREVISAO FROM DUAL) src
    ON (trg.ID_ROTA = src.ID_COLETA)
    WHEN MATCHED THEN
        UPDATE SET
            trg.END_ORIGEM = src.END_ORIGEM,
            trg.END_DESTINO = src.END_DESTINO,
            trg.DSC_ROTA = src.DSC_ROTA,
            trg.HR_PREVISAO = src.HR_PREVISAO
    WHEN NOT MATCHED THEN
        INSERT (ID_ROTA, END_ORIGEM, END_DESTINO, DSC_ROTA, HR_PREVISAO)
        VALUES (src.ID_COLETA, src.END_ORIGEM, src.END_DESTINO, src.DSC_ROTA, src.HR_PREVISAO);
END;
/
