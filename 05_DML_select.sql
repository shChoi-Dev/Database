-- SELECT
/*
SELECT [ALL | DISTINCT] 열이름 리스트
FROM 테이블명
[WHERE 검색조건(들)]
[GROUP BY 열이름]
[HAVING 검색조건(들)]
[ORDER BY 열이름 [ASC┃DESC]
*/

-- 서점 관계 테이블 IMPORT
SELECT * FROM BOOK;

-- 테이블에 제약 조건 설정

-- 참조 무결성 제약 조건
-- 참조되는 테이블의 기본키 먼저 구성 참조하는 테이블의 외래키 구성

-- 서점의 모든 도서는 거래하고 있는 출판사에서 구매된다 - 도서테이블(BOOK) 출판사데이터(PUBNO)는 출판사테이블(PUBLISHER)의 기본키 도메인의 범위에 국한된다
-- 서점의 모든 도서(BOOK)는 회원가입되어 있는 회원이 서점에 등록되어있는 도서를 구매할 수 있다 : 구매관계테이블(BOOKSALE)

-- 기본키 제약조건
ALTER TABLE PUBLISHER
    ADD CONSTRAINT PK_publisher_pubno
    PRIMARY KEY(PUBNO);
    
ALTER TABLE BOOK
    ADD CONSTRAINT PK_book_bookno
    PRIMARY KEY(BOOKNO);
    
ALTER TABLE client
    ADD CONSTRAINT PK_client_clientno
    PRIMARY KEY(CLIENTNO);
    
ALTER TABLE BOOKSALE
    ADD CONSTRAINT PK_booksale_bsno
    PRIMARY KEY(BSNO);
    
-- 외래키 제약조건 추가
ALTER TABLE BOOK
    ADD CONSTRAINT FK_book_publisher
    FOREIGN KEY (pubNo) REFERENCES PUBLISHER(pubNo);

ALTER TABLE BOOKSALE
    ADD CONSTRAINT FK_bookSale_client
    FOREIGN KEY (clientNo) REFERENCES CLIENT(clientNo);
    
ALTER TABLE BOOKSALE
    ADD CONSTRAINT FK_bookSale_book
    FOREIGN KEY (bookNo) REFERENCES BOOK(bookNo);
    
-------------------------------------------------------------------------------------------------

-- 특정 테이블의 모든 튜플을 반환 -> 반환결과 테이블
SELECT * FROM PUBLISHER;

SELECT * FROM BOOK;

SELECT * FROM CLIENT;

SELECT * FROM BOOKSALE;

-------------------------------------------------------------------------------------------------

-- BOOK 테이블에서 모든 행 검색하여 출력
-- (*) 모든
SELECT *
FROM BOOK;

-- BOOK 테이블에서 도서명, 도서가격만 출력
SELECT BOOKNAME, BOOKPRICE FROM BOOK;

-- BOOK테이블에서 저자 검색
SELECT BOOKAUTHOR FROM BOOK;

-- BOOK테이블에서 저자 검색(중복 튜블은 한번만 반환) : DISTINCT
SELECT DISTINCT BOOKAUTHOR FROM BOOK;

-------------------------------------------------------------------------------------------------

/*
WHERE 조건절 : 검색의 세분화
조건 사용 연산자
비교 : =, <, >, <=, >=, !=
범위 : BETWEEN
리스트에 포함 : IN, NOT IN 
NULL : IS NULL, IS NOT NULL
논리 : AND, OR
패턴 매칭 : LIKE
*/

-- 비교 : =, <, >, <=, >=, !=
-- 저자가 홍길동인 도서의 도서명, 저자 반환
SELECT BOOKNAME, BOOKAUTHOR FROM BOOK 
WHERE BOOKAUTHOR='홍길동';

-- 도서가가 30000원 이상인 도서의 도서명, 가격, 재고 반환
SELECT BOOKNAME, BOOKPRICE, BOOKSTOCK FROM BOOK
WHERE BOOKPRICE >= 30000;

-- 도서재고가 3권이상이고 5권 이하인 도서의 도서명과 재고 수량 반환
SELECT BOOKNAME, BOOKSTOCK FROM BOOK
WHERE BOOKSTOCK >= 3 AND BOOKSTOCK <= 5;

-- 범위 : BETWEEN
-- 도서재고가 3권이상이고 5권 이하인 도서의 도서명과 재고 수량 반환 - 위의 질의어와 같은 의미
SELECT BOOKNAME, BOOKSTOCK FROM BOOK
WHERE BOOKSTOCK BETWEEN 3 AND 5;

-- 리스트에 포함 : IN, NOT IN : (값1, 값2, ...) -> 리스트
-- 서울출판사('1')와 도서출판 강남('2')의 도서명과 출판사 번호 출력
SELECT BOOKNAME, PUBNO FROM BOOK
WHERE PUBNO IN('1', '2');

-- 서울출판사('1')와 도서출판 강남('2')의 도서가 아닌 도서의 도서명 출판사번호 반환
SELECT BOOKNAME, PUBNO FROM BOOK
WHERE PUBNO NOT IN('1', '2');

-- NULL : IS NULL, IS NOT NULL

-- 모든 클라이언트의 이름과 취미를 출력
SELECT CLIENTNAME, CLIENTHOBBY FROM CLIENT;

-- 취미정보가 NULL인 클라이언트의 이름과 취미를 반환
SELECT CLIENTNAME, CLIENTHOBBY FROM CLIENT
WHERE CLIENTHOBBY IS NULL;

-- 취미정보가 NULL이 아닌 클라이언트의 이름과 취미를 반환
SELECT CLIENTNAME, CLIENTHOBBY FROM CLIENT
WHERE CLIENTHOBBY IS NOT NULL;

-- 취미에 공백값이 저장되어 있는 클라이언트의 이름과 취미
SELECT CLIENTNAME, CLIENTHOBBY FROM CLIENT
WHERE CLIENTHOBBY = ' ';

-- 논리(AND, OR)

-- 저자가 홍길동 이면서 재고가 3권 이상인 도서의 정보 반환
SELECT * FROM BOOK
WHERE BOOKAUTHOR = '홍길동' AND BOOKSTOCK >= 3;

-- 저자가 홍길동 이거나 성춘향인 도서의 정보
SELECT * FROM BOOK
WHERE BOOKAUTHOR = '홍길동' OR BOOKAUTHOR = '성춘향'

-- IN
SELECT * FROM BOOK
WHERE BOOKAUTHOR IN('홍길동', '성춘향');

-- 패턴매칭 : LIKE
-- WHERE 컬럼명 LIKE '패턴'
-- % : 0개 이상의 문자를 가진 문자열, _ : 한개의 문자
-- '홍%' : 홍으로 시작하는 문자열 EX. 홍, 홍길, 홍길동
-- '%길%' : 길을 포함하는 문자열  EX. 길, 홍길, 길동, 홍길동
-- '%동' : 동으로 끝나는 문자열   EX. 동, 길동, 홍길동
-- ____ : 4개의 문자로 구성된 문자열 EX. 홍길동전, 홍길동(X)

-- 출판사 명에 출판사 문자열이 포함된 출판사 정보
SELECT * FROM PUBLISHER
WHERE PUBNAME LIKE '%출판사%';

-- 출생년도가 199N년대인 고객의 정보 반환
SELECT * FROM CLIENT
WHERE CLIENTBIRTH LIKE '199%';

-- 이름이 4글자인 고객의 정보 반환
SELECT * FROM CLIENT
WHERE CLIENTNAME LIKE '____';

-- NOT LIKE
-- 도서명에 안드로이드가 포함되지않은 도서의 정보
SELECT * FROM BOOK
WHERE BOOKNAME NOT LIKE '%안드로이드%';

-------------------------------------------------------------------------------------------------

-- ORDER BY
-- 특정 열의 값을 기준으로 질의 결과 정렬
-- 가장 마지막에 진행되는 연산 (ORDER BY 절은 마지막에 질의가 나옴)
-- 기준열을 두개이상 나열 가능 - 우선기준, 두번째 기준, 세번째 기준
-- ASC : 오름차순(생략가능)
-- DESC : 내림차순
-- 도서정보를 도서명 순으로 반환
SELECT * FROM BOOK
ORDER BY BOOKNAME;

-- 내림차순
SELECT * FROM BOOK
ORDER BY BOOKNAME DESC;

-- 조건절 뒤에 ORDER BY
SELECT BOOKNAME, BOOKAUTHOR, BOOKPRICE, BOOKSTOCK FROM BOOK
WHERE BOOKPRICE >= 20000
ORDER BY BOOKPRICE;

-- 정렬 조건 2개 이상일 경우
-- 도서재고를 기준으로 내림차순 정렬하고 재고가 동일한 튜플인 경우 저자를 기준으로 오름차순 정렬한 도서정보 반환
SELECT * FROM BOOK
ORDER BY BOOKSTOCK DESC, BOOKAUTHOR ASC;

-------------------------------------------------------------------------------------------------
-- 집계 함수
-- SUM()/AVG()/COUNT()/COUNT(*)/MAX()/MIN()

-- SUM()
-- 도서의 총 재고 수량 출력
SELECT SUM(BOOKSTOCK) FROM BOOK; -- 테이블반환 -> 컬럼명이 있음(SELECT 컬럼에 연산을 진행하면 연산식이 컬럼명으로 반환)

-- 모든 컬럼은 컬럼의 별명 생성 가능(SELECT문에서 AS를 활용해서)
-- 컬럼 AS "별명"
SELECT SUM(BOOKSTOCK) AS "SUM OF BOOKSTOCK" FROM BOOK;

-- 한글가능
SELECT SUM(BOOKSTOCK) AS "총재고량" FROM BOOK;

-- 2번 고객이 주문한 총 주문 도서 권수와 주문 도서 번호
-- as 통한 별명 "별명" 또는 별명
-- 별명에 공백이 있으면 반드시 "" 사용해야 함
SELECT SUM(BSQTY) AS 총주문수량 -- "총 주문 수량"
FROM BOOKSALE 
WHERE CLIENTNO='2';

-- 2번 고객이 주문한 총 주문 도서 권수와 주문 도서 번호
-- 총 주문수량은 1개의 튜플
-- 도서번호는 3개의 튜플
-- GROUP BY 절을 포함하고 있는 경우가 아니면 SELECT에 집계함수가 포함되면 다른 컬럼도 집계함수를 사용해서 포함되어야 함
/*
SELECT SUM(BSQTY) AS "총주문수량", BOOKNo AS "도서번호" -- ORA-00937: 단일 그룹의 그룹 함수가 아닙니다
FROM BOOKSALE 
WHERE CLIENTNO='2';
*/

SELECT SUM(BSQTY) AS "총주문수량", AVG(BSQTY) AS "평균주문수량"
FROM BOOKSALE 
WHERE CLIENTNO='2';

-- 도서판매 현황 중 주문 권수가 가장 많은 주문권수, 가장적은 주문권수
select max(bsQty) as 최대주문량, min(bsQty) as 최소주문량 from booksale;

-- 서점에 있는 도서의 전체 가격 총액, 평균가격, 최고가, 최저가 확인
select sum(bookPrice) as 가격총액, 
       avg(bookPrice) as 평균가격,
       max(bookPrice) as 최고가,
       min(bookPrice) as 최저가
from book;

-- 도서판매 테이블에서 도서 판매 건수 조회
-- bsDate가 null을 허용하거나 값이 중복되는 컬럼이라면 count가 원하는 목적과 같이 반환되지 않을 수도 있음
select count(bsDate) as 총판매건수 from bookSale;

-- 특정필드 값의 수가 아닌 튜플의 수를 세고자 하면 count(*)를 활용
select count(*) as 총판매건수 from bookSale;

-- 고객 테이블에서 총 취미의 개수 출력 : 취미를 제공한 고객 수
-- count(속성명): 속성값이 null인 경우는 제외하고 수를 센 결과 반환
select count(clientHobby) as "취미" from client;

-- 서점의 총 고객수는 몇명인가?
select count(*) as 총고객수 from client;

-------------------------------------------------------------------------------------------------

-- Group by <속성>
-- 그룹에 대한 질의를 기술 할 때 사용
-- 특정 열(속성)의 값을 기준으로 동일한 값의 데이터들끼리 그룹을 구성
-- 각 그룹에 대해 한행 씩 질의 결과를 생성

-- 각 도서번호별 판매수량 확인
-- group by 진행 한 경우 select 절에 집계함수를 통해 필요열의 집계 진행 가능, group by에 기준되는 열은 select에 포함시킬 수 있다.
select sum(bsqty), bookno
from booksale
group by bookno
order by bookno;

select sum(bsqty), bookno
from booksale
group by bookno
order by 2; -- select된 두번째 열을 기준으로 정렬

-- 각 지역별 고객의 수
select clientAddress as 지역, count(*) as 고객수
from client
group by clientAddress;

-- 성별에 따른 고객 수
select clientgender as 성별, count(*) as 고객수
from client
group by clientgender;

-- 성별에 따른 고객 수와 고객들의 지역명
-- groupby의 기준으로 사용하지 않는 필드는 select에 단독 사용 불가
/* select clientgender as 성별, count(*) as 고객수, clientAddress as 지역명
from client
group by clientgender; */

-- 성별에 따른 지역별 고객의 수
select clientgender as 성별, count(*) as 고객수, clientAddress as 지역명
from client
group by clientgender, clientAddress;

-- Having <검색 조건>
-- group by 절에 의해 구성된 그룹들에 대해 적용할 조건 기술
-- 집계함수와 함께 사용
-- 주의 !
-- 1. 반드시 group by 절과 함께 사용
-- 2. where절보다 뒤에 
-- 3. 검색조건에 집계함수가 와야 함

-- 각 출판사별 도서가격이 25000 이상인 도서가 2권 이상인 도서의 출판사번호와 도서권수
select pubNo, count(*) as 도서합계
from book
where bookPrice >= 25000
group by pubNo
having count(*) >= 2;
