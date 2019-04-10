
-------------------------------------CONTROLE--------------------------
TSOC_CTR_ERRO
TSOC_CTR_PERIODO
TSOC_CTR_PERIODO_DET
TSOC_CTR_PROCESSO
TSOC_CTR_ERRO_PROCESSO
tsoc_ctr_lote
----------------------------------PARAMETRIZAÇÃO------------------------
TSOC_PAR_AMBIENTE
TSOC_PAR_CODIGO
TSOC_PAR_COND_PROGRAMA
TSOC_PAR_DEFINICAO
TSOC_PAR_ERRO
TSOC_PAR_ESTRUTURAS_XML
TSOC_PAR_EVENTO
TSOC_PAR_EVENTOS_XML
TSOC_PAR_ORIGEM
TSOC_PAR_PROCESSO
TSOC_PAR_PROC_SERVIDOR
TSOC_PAR_PROGRAMA
TSOC_PAR_SIGEPREV_ESOCIAL
TSOC_PAR_SQL_XML
---------------------------CADASTRO---------------------
TSOC_CAD_BENEFICIARIO
TSOC_CAD_BENEFICIO
TSOC_CAD_DEP
TSOC_CAD_EMPREGADOR
TSOC_CAD_EXCLUSAO_EVENTO
TSOC_CAD_REAB_EVENTO_PER
--------------------------HISTORICO---------------------
TSOC_HCAD_BENEFICIARIO
TSOC_HCAD_BENEFICIO
TSOC_HCAD_DEP
TSOC_HCAD_EMPREGADOR
TSOC_HCAD_REAB_EVENTO_PER
----------------------------EVENTO----------------------
TSOC_1000_EMPREGADOR
TSOC_1010_RUBRICA
TSOC_1030_CARGO
TSOC_1035_CARREIRA
TSOC_1298_REAB_EVENTO_PER
TSOC_1299_FECHAMENTO_EVT_PER
TSOC_2400_BENEFICIARIO_INI
TSOC_2400_DEP_INI
TSOC_2405_BENEFICIARIO_ALT
TSOC_2410_BENEFICIO_INI
TSOC_2416_BENEFICIO_ALT
TSOC_2420_BENEFICIO_TERMINO
TSOC_3000_EXCLUSAO_EVENTO
TSOC_5002_IR_RET_FONTE





Segue resumo de estrutura de banco de dados para tela de monitoramento. 


1.TABELAS DE EVENTOS DISPONÍVEIS
TSOC_2400_BENEFICIARIO_INI
TSOC_2405_BENEFICIARIO_ALT
TSOC_2410_BENEFICIO_INI
TSOC_2416_BENEFICIO_ALT
TSOC_2420_BENEFICIO_TERMINO
TSOC_1000_EMPREGADOR
TSOC_1010_RUBRICA

2.COLUNA UTILIZADA PARA IDENTIFICAR SITUAÇÃO DE CADA EVENTO
Coluna: CTR_FLG_STATUS

3.VALORES POSSÍVEIS:
EV - Erro de Validação
AX - Aguardando geração de XML
EX - Erro na geração de XML
AA - Aguardando Assinatura
EA - Erro na Assinatura
AL - Aguardando Geração do Lote
EL - Erro na Geração do Lote
AE - Aguardando Envio
EE - Erro no Envio
EN - Enviado
RE - Retornado com Erro
FN - Finalizado

4.TABELA DE DETALHE DE ERROS GERADAS EM CADA PROCESSO, PARA CADA EVENTO:

TSOC_CTR_ERRO_PROCESSO 

COLUNA: TIPO_EVENTO identifica o tipo de evento relacionado, (2400,2405,1210 etc)
COLUNA: ID_CTR_PROCESSO identifica o processo agendado, e executado que gerou o erro (item 5)
COLUNA: ID_CAD identifica o evento relacionado nas tabelas do item 1. Caso seja um erro que impossibilitou a geração do evento, esta coluna apresenta NULO. 

5.TABELA AONDE SÃO AGENDADOS OS PROCESSOS PARA EXECUÇÃO
TSOC_CTR_PROCESSO 

FLG_STATUS: A - Agendado, P- Processando, E-Erro 

Qualquer dúvida estou à disposição. 
Att,











drop table tb_player;
drop sequence seq_player;

CREATE TABLE tb_player(
  id number,
  nick VARCHAR2(100),
  idade NUMBER(5)
);

CREATE SEQUENCE SEQ_PLAYER
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

/*********************************************************/


drop table TB_ESOCIAL_USER;
drop table TB_ESOCIAL_ROLE;

CREATE TABLE TB_ESOCIAL_USER(
   user_id varchar2(20) NOT NULL,
   email varchar2(50) NOT NULL,
    password varchar2(200) NOT NULL,
  name varchar2(20) NOT NULL,
  last_name varchar2(20) NOT NULL,
  active number

);

create table TB_ESOCIAL_ROLE (
  role_id number,
  role varchar(20) NOT NULL
);


create table TB_ESOCIAL_USER_ROLE (
  user_id number,
  role_id number
);

CREATE SEQUENCE SEQ_ESOCIAL_USER
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;


CREATE SEQUENCE SEQ_ESOCIAL_ROLE
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;




insert into tb_esocial_user_role
values (-46,1);

insert into TB_ESOCIAL_ROLE
values(1, 'ADMIN');
