-- SELECT 연습문제_4

/*
1.고객 테이블에서 고객명, 생년월일, 성별 출력
2.고객 테이블에서 주소만 검색하여 출력 (중복되는 튜플은 한번만 출력)
3.고객 테이블에서 취미가 '축구'이거나 '등산'인 고객의 고객명, 취미 출력
4.도서 테이블에서 저자의 두 번째 위치에 '길'이 들어 있는 저자명 출력 (중복되는 튜플은 한번만 출력)
5.도서 테이블에서 발행일이 2018년인 도서의 도서명, 저자, 발행일 출력
6.도서판매 테이블에서 고객번호1, 2를 제외한 모든 튜플 출력
7.고객 테이블에서 취미가 NULL이 아니면서 주소가 '서울'인 고객의 고객명, 주소, 취미 출력
8.도서 테이블에서 가격이 25000 이상이면서 저자 이름에 '길동'이 들어가는 도서의 도서명, 저자, 가격, 재고 출력
9.도서 테이블에서 가격이 20,000 ~25,000원인 모든 튜플 출력
10.도서 테이블에서 저자명에 '길동'이 들어 있지 않는 도서의 도서명, 저자 출력
*/

-- select 속성명1, 속성명2,... from 테이블 명
-- select * from 테이블명 [where 조건절]

-- 고객 테이블(client)에서 고객명, 생년월일, 성별 출력
SELECT clientName, clientBirth, clientGender  FROM CLIENT;

-- 고객 테이블에서 주소만 검색하여 출력 (중복되는 튜플은 한번만 출력)
select distinct clientAddress from client;

-- 고객 테이블에서 취미가 '축구'이거나 '등산'인 고객의 고객명, 취미 출력
select clientName, clientHobby from client
where clientHobby='축구' or clientHobby = '등산';

-- 도서 테이블에서 저자의 두 번째 위치에 '길'이 들어 있는 저자명 출력 (중복되는 튜플은 한번만 출력)
SELECT DISTINCT BOOKAUTHOR FROM BOOK
WHERE BOOKAUTHOR LIKE '_길%';

-- 도서 테이블에서 발행일이 2018년인 도서의 도서명, 저자, 발행일 출력
select bookName, bookAuthor, bookDate from book
where bookDate like '2018%'

-- 도서판매 테이블에서 고객번호1, 2를 제외한 모든 튜플 출력
select * from booksale
where clientNo not in('1','2');

-- 고객 테이블에서 취미가 NULL이 아니면서 주소가 '서울'인 고객의 고객명, 주소, 취미 출력
select clientNo, clientAddress, clientHobby from client
where clientHobby is not null and clientAddress like '%서울%';

-- 도서 테이블에서 가격이 25000 이상이면서 저자 이름에 '길동'이 들어가는 도서의 도서명, 저자, 가격, 재고 출력
select bookName, bookAuthor, bookPrice, bookDate, bookStock from book
where bookPrice >= 25000 and bookAuthor like '%길동%';

-- 도서 테이블에서 가격이 20,000 ~ 25,000원인 모든 튜플 출력
select * from book
where bookprice between 20000 and 25000; --between은 이상과 이하 표현

-- 도서 테이블에서 저자명에 '길동'이 들어 있지 않는 도서의 도서명, 저자 출력
select bookName, bookAuthor from book
where bookAuthor not like '%길동%'
order by bookPrice;

-- order by 절은 정렬시 기준필드의 값이 동일한 경우 2번째 기준을 추가할 수 있음
-- 도서 테이블에서 저자명에 '길동'이 들어 있지 않는 도서의 도서명, 저자 출력
-- 도서가격 기준으로 오름차순 정렬, 가격이 동일하면 저장 기준 내림차순 정렬 결과 추출
select bookName, bookAuthor from book
where bookAuthor not like '%길동%'
order by bookPrice asc, bookAuthor desc;