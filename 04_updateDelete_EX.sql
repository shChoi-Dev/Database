-- UPDATE & DELETE 연습문제_2
-- BOOK 테이블에 다음과 같이 행 삽입
INSERT ALL
    INTO BOOK VALUES  ('6', 'JAVA 프로그래밍', '30000', '2021-03-10', '서울출판사')
    INTO BOOK VALUES  ('7', '파이썬 데이터 과학', '24000', '2018-02-05', '도서출판 강남')
SELECT * FROM DUAL;

-- BOOK 테이블에서 도서명이 '데이터베이스'인 행의 가격을 22000으로 변경
UPDATE BOOK
SET BOOKPRICE = 22000
WHERE BOOKNAME = '데이터베이스';

-- BOOK 테이블에서 발행일이 2018년도인 행 삭제
DELETE FROM BOOK
WHERE EXTRACT(YEAR FROM BOOKDATE) = 2018;

----------------------------------------------------------------------------------

-- 종합 연습문제_3
/*
orderProduct와 customer 테이블에 적절한 관계 설정
다음과 같이 SQL 문 작성(모든 테이블 속성 문자열로 구성 크기는 적절하게)
1. 고객 테이블 (CUSTOMER) 생성
2. 주문 테이블 (orderProduct) 생성 (이미 생성된 상품(product) 테이블에 대한 주문)
3. 고객 테이블의 전화번호 열을 NOT NULL로 변경
4. 고객 테이블에 ‘성별', ‘나이' 열 추가
5. 고객, 주문 테이블에 데이터 삽입 (3개씩)
6. 주문 테이블에서 상품번호가 2인 행의 주문수량을 3으로 수정
*/

-- 1. 고객(customer) 테이블 생성
CREATE TABLE customer (
    CUSTNO      NUMBER(4)       PRIMARY KEY,
    CUSTNAME    VARCHAR2(30)    NOT NULL,
    CUSTPHONE   VARCHAR2(20),
    CUSTADDRESS VARCHAR2(100)
);

-- 2. 주문(orderProduct) 테이블 생성 (customer, product 테이블 참조)
CREATE TABLE orderProduct (
    ORDERNO   NUMBER(4)     PRIMARY KEY,
    ORDERDATE DATE,
    ORDERQTY  NUMBER(3),
    CUSTNO    NUMBER(4)     REFERENCES customer(CUSTNO),
    PRDNO     VARCHAR2(4)     REFERENCES product(PRDNO)
);

-- 3. 고객 테이블의 전화번호(CUSTPHONE) 열을 NOT NULL로 변경
ALTER TABLE customer MODIFY (CUSTPHONE NOT NULL);

-- 4. 고객 테이블에 '성별'(CUSTGENDER), '나이'(CUSTAGE) 열 추가
ALTER TABLE customer ADD (
    CUSTGENDER VARCHAR2(3),
    CUSTAGE    NUMBER(3)
);

-- 5. 고객, 주문 테이블에 데이터 삽입 (각 3개)
-- 고객 데이터 삽입
INSERT INTO customer (CUSTNO, CUSTNAME, CUSTPHONE, CUSTADDRESS, CUSTGENDER, CUSTAGE) VALUES (1001, '홍길동', '010-1111-1111', '강원도 평창', '남', 22);
INSERT INTO customer (CUSTNO, CUSTNAME, CUSTPHONE, CUSTADDRESS, CUSTGENDER, CUSTAGE) VALUES (1002, '이몽룡', '010-2222-2222', '서울시 종로구', '남', 23);
INSERT INTO customer (CUSTNO, CUSTNAME, CUSTPHONE, CUSTADDRESS, CUSTGENDER, CUSTAGE) VALUES (1003, '성춘향', '010-3333-3333', '서울시 강남구', '여', 22);

-- 주문 데이터 삽입
INSERT INTO orderProduct (ORDERNO, ORDERDATE, ORDERQTY, CUSTNO, PRDNO) VALUES (1, '2018-01-10', 3, 1003, 3);
INSERT INTO orderProduct (ORDERNO, ORDERDATE, ORDERQTY, CUSTNO, PRDNO) VALUES (2, '2018-03-03', 1, 1001, 7);
INSERT INTO orderProduct (ORDERNO, ORDERDATE, ORDERQTY, CUSTNO, PRDNO) VALUES (3, '2018-04-05', 1, 1002, 2);

-- 6. 주문 테이블에서 상품번호(PRDNO)가 2인 행의 주문수량(ORDERQTY)을 3으로 수정
UPDATE orderProduct
SET ORDERQTY = 3
WHERE PRDNO = 2;
