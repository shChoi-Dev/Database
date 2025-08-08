-- INSERT 연습문제_1
-- INSERT 문을 사용하여 STUDENT 테이블에 다음과 같이 행 삽입하고 SELECT문으로 조회

-- 속성과 값을 모두 나열하는 방법
INSERT INTO student (STDNO, STDNAME, STDBIRTHDAY, STDYEAR, DPTNO)
VALUES ('2016001', '홍길동', '4', '1997-01-01', '1');

-- 값만 나열하는 방법
INSERT INTO student
VALUES ('2015002', '성춘향', '3', '1996-12-10', '3');

-- 전체 한번에 저장
INSERT ALL
    INTO student VALUES ('2014004', '이몽룡', '2', '1996-03-03', '2')
    INTO student VALUES ('2016002', '변학도', '4', '1996-05-07', '1')
    INTO student VALUES ('2015003', '손흥민', '3', '1997-11-11', '2')
SELECT * FROM DUAL;

-- student 테이블 내용 조회
SELECT * FROM student;

