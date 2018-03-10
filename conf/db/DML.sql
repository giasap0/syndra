
INSERT INTO tuser_role (`ID_ROLE`, `code`, `description`) VALUES (NULL, 'WRNCONS', 'warning consumer');
INSERT INTO tuser_role (`ID_ROLE`, `code`, `description`) VALUES (NULL, 'WRNMSTR', 'warning master');

INSERT INTO TUSER
(ID_USER, USER_CODE, NAME, PASSWORD, TINSERT, IS_ENABLED)
VALUES(1, 'admin', 'admin', 'admin', CURRENT_TIMESTAMP, 1);


INSERT INTO REL_USER_ROLE
(ID_USER, ID_ROLE, TINSERT, TSTART)
VALUES(
	(select id_user from tuser where user_code = 'admin')
	, (select id_role from tuser_role where code = 'WRNMSTR')
	, CURRENT_TIMESTAMP
	, CURRENT_TIMESTAMP
);

INSERT INTO ttipiprodotto (`ID_TIPO_PROD`, `CODICE`, `DESCRIZIONE`) VALUES
    (NULL, 'DRYPRD', 'Secchi'),
    (NULL, 'FRSPRD', 'Freschi'),
    (NULL, 'ORTPRD', 'Ortofrutta'),
    (NULL, 'SRGPRD', 'Surgelati')
;
