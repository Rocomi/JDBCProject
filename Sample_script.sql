-- JDBC용 계정 :  C##JDBC / JDBC

CREATE USER C##JDBC IDENTIFIED BY JDBC;

GRANT CONNECT, RESOURCE TO C##JDBC;

-- * 테이블 스페이스 설정
ALTER USER C##JDBC DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

-- 관리자 계정으로 위의 내용 진행--------------------------------------------------

-- 회원 정보를 저장할 테이블 (MEMBER)
DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    USERNO NUMBER PRIMARY KEY,              -- 회원번호
    USERID VARCHAR2(20) NOT NULL UNIQUE,    -- 회원아이디
    USERPW VARCHAR2(20) NOT NULL,           -- 회원 비밀번호
    USERNAME VARCHAR2(20) NOT NULL,        -- 회원 이름
    GENDER  CHAR(1) CHECK(GENDER IN ('M', 'F')), -- 성별
    AGE NUMBER,                             -- 나이
    EMAIL VARCHAR2(30),                     -- 이메일
    ADDRESS VARCHAR2 (100),                 -- 주소
    PHONE VARCHAR2(13),                     -- 연락처
    HOBBY VARCHAR2(50),                     -- 취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
);

DROP SEQUENCE SEQ_USERNO;

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

-- * 샘플 테이터 2개 추가
INSERT INTO MEMBER
    VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 20, 'admin@kh.or.kr', '서울', '010-1010-0101', null, '2020-07-30');

INSERT INTO MEMBER
    VALUES(SEQ_USERNO.NEXTVAL, 'sujin', '1234', '임수진', 'F', 20, 'sjlim@kh.or.kr', null, '010-9090-0101', null, default);

COMMIT;

SELECT * FROM MEMBER;

--------------------------------------------------------------------------------

-- 테스트용 테이블 (test)
CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(30),
    TDATE DATE
);

SELECT * FROM TEST;

INSERT INTO TEST VALUES (1, '기다운', SYSDATE);

COMMIT;

--------------------------------------------------------------------------------
INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'kauli','1234','엄희윤','M',0,'null','null','null','null',SYSDATE);











