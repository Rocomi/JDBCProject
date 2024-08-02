-- JDBC�� ���� :  C##JDBC / JDBC

CREATE USER C##JDBC IDENTIFIED BY JDBC;

GRANT CONNECT, RESOURCE TO C##JDBC;

-- * ���̺� �����̽� ����
ALTER USER C##JDBC DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

-- ������ �������� ���� ���� ����--------------------------------------------------

-- ȸ�� ������ ������ ���̺� (MEMBER)
DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    USERNO NUMBER PRIMARY KEY,              -- ȸ����ȣ
    USERID VARCHAR2(20) NOT NULL UNIQUE,    -- ȸ�����̵�
    USERPW VARCHAR2(20) NOT NULL,           -- ȸ�� ��й�ȣ
    USERNAME VARCHAR2(20) NOT NULL,        -- ȸ�� �̸�
    GENDER  CHAR(1) CHECK(GENDER IN ('M', 'F')), -- ����
    AGE NUMBER,                             -- ����
    EMAIL VARCHAR2(30),                     -- �̸���
    ADDRESS VARCHAR2 (100),                 -- �ּ�
    PHONE VARCHAR2(13),                     -- ����ó
    HOBBY VARCHAR2(50),                     -- ���
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
);

DROP SEQUENCE SEQ_USERNO;

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

-- * ���� ������ 2�� �߰�
INSERT INTO MEMBER
    VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '������', 'M', 20, 'admin@kh.or.kr', '����', '010-1010-0101', null, '2020-07-30');

INSERT INTO MEMBER
    VALUES(SEQ_USERNO.NEXTVAL, 'sujin', '1234', '�Ӽ���', 'F', 20, 'sjlim@kh.or.kr', null, '010-9090-0101', null, default);

COMMIT;

SELECT * FROM MEMBER;

--------------------------------------------------------------------------------

-- �׽�Ʈ�� ���̺� (test)
CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(30),
    TDATE DATE
);

SELECT * FROM TEST;

INSERT INTO TEST VALUES (1, '��ٿ�', SYSDATE);

COMMIT;

--------------------------------------------------------------------------------
INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'kauli','1234','������','M',0,'null','null','null','null',SYSDATE);











