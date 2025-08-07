-- 학과(department) 테이블 생성
CREATE TABLE department(
    dept_ID VARCHAR2(10) NOT NULL PRIMARY KEY,
    dept_NAME VARCHAR2(30) NOT NULL
);

-- 학생(student) 테이블 생성
CREATE TABLE student(
    student_ID VARCHAR2(10) NOT NULL PRIMARY KEY,
    student_NAME VARCHAR2(30) NOT NULL,
    grade NUMBER(2) DEFAULT 4 CHECK (grade BETWEEN 1 AND 4),
    dept_ID VARCHAR2(10) NOT NULL,
    CONSTRAINT FK_student_department FOREIGN KEY (dept_ID) REFERENCES department(dept_ID)
);

-- department 테이블 데이터 3개 입력
INSERT INTO department (dept_name) VALUES ('컴퓨터공학과');
INSERT INTO department (dept_name) VALUES ('심리학과');
INSERT INTO department (dept_name) VALUES ('연극영화과');

-- student 테이블 데이터 3개 입력
INSERT INTO student VALUES ('2021001', '서유진', '3', '100');
INSERT INTO student VALUES ('2024216', '이하늘', '1', '102');
INSERT INTO student VALUES ('2019465', '성춘향', '2', '101');

