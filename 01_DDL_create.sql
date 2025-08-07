-- 테이블 생성
-- 동일DB(동일계정)에 동일명 테이블이 있으면 안됨
-- 기본키 제약조건
-- 1.속성 설정 시 기본키 지정
CREATE TABLE product(
    prdNo VARCHAR2(10) NOT NULL PRIMARY KEY,
    prdNAME VARCHAR2(30) NOT NULL,
    prdPRICE NUMBER(8),
    prdCOMPANY VARCHAR2(30)
);

-- 2. 기본키 따로 설정 : primary key(기본키 필드명)
CREATE TABLE product(
    prdNo VARCHAR2(10) NOT NULL,
    prdNAME VARCHAR2(30) NOT NULL,
    prdPRICE NUMBER(8),
    prdCOMPANY VARCHAR2(30),
    PRIMARY KEY(prdNo)
);

-- 3. 제약 이름과 같이 설정 : 제약 변경이나 삭제 시 유용함 -> CONSTRAINT PK_product_prdNo PRIMARY KEY
CREATE TABLE product(
    prdNo VARCHAR2(10) NOT NULL CONSTRAINT PK_product_prdNo PRIMARY KEY,
    prdNAME VARCHAR2(30) NOT NULL,
    prdPRICE NUMBER(8),
    prdCOMPANY VARCHAR2(30)
);

-- 4. 따로 설정 제약명 추가
CREATE TABLE product2(
    prdNo VARCHAR2(10) NOT NULL,
    prdNAME VARCHAR2(30) NOT NULL,
    prdPRICE NUMBER(8),
    prdCOMPANY VARCHAR2(30),
    CONSTRAINT PK_product_prdNo PRIMARY KEY(prdNo)
);

----------------------------------------------------------------------------
-- 출판사 테이블 먼저 생성하고 도서 테이블 생성(외래키 참조필드)
-- 외래키 필드에 입력되는 값은 참조테이블의 기본키로서 값과 동일해야 함
-- 외래키 필드의 도메인과 참조테이블의 기본키 도메인은 동일해야 함
-- 테이블 삭제시에는 생성과 반대로 참조하는 book을 먼저 삭제하고 참조되는 publisher를 삭제

/* 출판사 테이블 생성(출판사 번호, 출판사명)
제약조건
- 기본키 not null
*/

CREATE TABLE publisher(
    pubNo VARCHAR2(10) NOT NULL PRIMARY KEY,
    pubNAME VARCHAR2(30) NOT NULL
);

/*
도서 테이블 생성(도서번호, 도서명, 가격, 발행일, 출판사번호)
기본키
외래키
기본값 체크조건
*/

-- 외래키 필드는 참조테이블에서는 기본키여야 함
CREATE TABLE book(
    bookNO VARCHAR2(10) NOT NULL PRIMARY KEY,
    bookNAME VARCHAR2(20) NOT NULL,
    bookPRICE NUMBER(8) DEFAULT 10000 CHECK(bookPRICE > 1000),
    bookDATE DATE,
    pubNO VARCHAR2(10) NOT NULL,
    CONSTRAINT FK_book_publisher FOREIGN KEY (pubNO) REFERENCES publisher(pubNo)
);

-- 테이블 CREATE 연습문제
-- 학과(department) 테이블 생성
CREATE TABLE department(
    dept_ID VARCHAR2(10) NOT NULL PRIMARY KEY,
    dept_NAME VARCHAR2(50) NOT NULL
);

-- 학생(student) 테이블 생성
CREATE TABLE student(
    student_ID VARCHAR2(20) NOT NULL PRIMARY KEY,
    student_NAME VARCHAR2(30) NOT NULL,
    grade NUMBER(10) DEFAULT 4 CHECK (grade BETWEEN 1 AND 4),
    dept_ID VARCHAR2(10) NOT NULL,
    CONSTRAINT FK_student_department FOREIGN KEY (dept_ID) REFERENCES department(dept_ID)
);

-- 교수 테이블
create table professor (
	profNo varchar2(10) not null primary key,
    profName varchar2(30) not null,
    profPosition varchar2(30),
    profTel varchar2(13),
    dptNo varchar2(10) not null,
    foreign key (dptNo) references department(dptNo)
);

-- 과목 테이블
create table course (
	courseId varchar(10) not null primary key,
    courseName varchar(30) not null,
    courseCredit int,
    profNo varchar(10) not null,
    foreign key (profNo) references professor(profNo)
);

CREATE TABLE SCORES(
    STDNO VARCHAR2(10) NOT NULL,
    COURSEID VARCHAR2(10) NOT NULL,
    SCORE NUMBER(3),
    GRADE VARCHAR2(2),
    CONSTRAINT PK_SCORES_STDNO_COURSEID PRIMARY KEY(STDNO, COURSEID),
    CONSTRAINT FK_SCORES_STUDENT FOREIGN KEY(STDNO) REFERENCES STUDENT(STDNO),
    CONSTRAINT FK_SCORES_COURSE FOREIGN KEY(COURSEID) REFERENCES COURSE(COURSEID)
);
