-- 연습문제_2

-- 저자 중 성이 '손'인 모든 저자 출력
SELECT BOOKAUTHOR
FROM BOOK
WHERE BOOKAUTHOR LIKE '손%';

-- 저자 중에서 같은 성을 가진 사람이 몇 명이나 되는지 알아보기 위해 성(姓)별로 그룹 지어 인원수 출력
SELECT SUBSTR(BOOKAUTHOR, 1, 1) AS 성, COUNT(*) AS 인원수
FROM BOOK
GROUP BY BOOKAUTHOR
ORDER BY "성";

-------------------------------------------------------------------------------------------------------------------

-- 연습문제_3
-- 테이블을 생성하고 CUBE, ROLLUP, GROUPING SETS를 적용 시켜 결과 설명

-- sales 테이블 생성
CREATE TABLE sales (
prdName VARCHAR2(20),
salesDate VARCHAR2(10),
prdCompany VARCHAR2(10),
salesAmount NUMBER(8));

-- sales 데이터 추가
INSERT INTO sales VALUES ('노트북', '2021.01', '삼성', 10000);
INSERT INTO sales VALUES ('노트북', '2021.03', '삼성', 20000);
INSERT INTO sales VALUES ('냉장고', '2021.01', 'LG', 12000);
INSERT INTO sales VALUES ('냉장고', '2021.03', 'LG', 20000);
INSERT INTO sales VALUES ('프린터', '2021.01', 'HP', 3000);
INSERT INTO sales VALUES ('프린터', '2021.03', 'HP', 1000);

-- sales 테이블 확인
SELECT * FROM sales;

-- CUBE 적용 : 모든 가능한 조합에 대한 소계 생성
SELECT PRDNAME, PRDCOMPANY, SUM(SALESAMOUNT) AS "총매출"
FROM SALES
GROUP BY CUBE(PRDNAME, PRDCOMPANY);

-- ROLLUP 적용: 계층 구조에 따른 소계 생성
SELECT PRDNAME, PRDCOMPANY, SUM(SALESAMOUNT) AS "총매출"
FROM SALES
GROUP BY ROLLUP(PRDNAME, PRDCOMPANY);

-- GROUPING SETS 적용: 지정된 그룹 조합에 대한 소계 생성
SELECT PRDNAME, PRDCOMPANY, SUM(SALESAMOUNT) AS "총매출"
FROM SALES
GROUP BY GROUPING SETS(PRDNAME, PRDCOMPANY);

-- 주문일에 7일을 더한 날을 배송일로 계산하여 출력
SELECT BOOKSALE.BSDATE AS "주문일", BOOKSALE.BSDATE + 7 AS "배송일"
FROM BOOKSALE;

-- 도서 테이블에서 도서명과 출판연도 출력 // EXTRACT() 함수 사용
SELECT BOOKNAME AS "도서명",    
       EXTRACT(YEAR FROM BOOKDATE) AS "출판연도"
FROM BOOK;