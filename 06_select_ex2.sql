/*
1.도서 테이블에서 가격 순으로 내림차순 정렬하여,  도서명, 저자, 가격 출력 (가격이 같으면 저자 순으로 오름차순 정렬)
2.도서 테이블에서 저자에 '길동'이 들어가는 도서의 총 재고 수량 계산하여 출력
3.도서 테이블에서 ‘서울 출판사' 도서 중 최고가와 최저가 출력
4.도서 테이블에서 출판사별로 총 재고수량과 평균 재고 수량 계산하여 출력 (‘총 재고 수량’으로 내림차순 정렬)
5.도서판매 테이블에서 고객별로 ‘총 주문 수량’과 ‘총 주문 건수’ 출력. 단 주문 건수가 2이상인 고객만 해당
*/

-- 도서 테이블에서 가격 순으로 내림차순 정렬하여,  도서명, 저자, 가격 출력 (가격이 같으면 저자 순으로 오름차순 정렬)
select bookname, bookauthor, bookprice
from book
order by bookprice desc, bookauthor asc;

-- 도서 테이블에서 저자에 '길동'이 들어가는 도서의 총 재고 수량 계산하여 출력
select sum(bookstock) as "총 재고 수량"
from book
WHERE bookauthor like '%길동%';

-- 도서 테이블에서 ‘서울 출판사' 도서 중 최고가와 최저가 출력
select max(bookPrice) as 최고가, min(bookPrice) as 최저가
from book
where pubno = 1;

-- 도서 테이블에서 출판사별로 총 재고수량과 평균 재고 수량 계산하여 출력 (‘총 재고 수량’으로 내림차순 정렬)
select pubno as "출판사", sum(bookstock) as "총 재고 수량", avg(bookstock) as "평균 재고 수량"
from book
group by pubno
order by sum(bookstock) desc;

-- 정수표현(반올림) - Round(대상, 소수점이하 자리수)
select pubno as "출판사", sum(bookstock) as "총 재고 수량", round(avg(bookstock),2) as "평균 재고 수량"
from book
group by pubno
order by "총 재고 수량" desc;

-- 도서판매 테이블에서 고객별로 ‘총 주문 수량’과 ‘총 주문 건수’ 출력. 단 주문 건수가 2이상인 고객만 해당
select CLIENTNo, sum(bsqty) as "총 주문 수량", count(*) as "총 주문 건수"
from booksale
group by clientNo
having count(*) >= 2;

