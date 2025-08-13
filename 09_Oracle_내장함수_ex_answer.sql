-- 연습문제_2

-- 저자 중 성이 '손'인 모든 저자 조회
select bookauthor
from book
where substr(bookauthor,1,1) = '손';

-- 저자 중에서 같은 성을 가진 사람이 몇 명이나 되는지 조회
select substr(bookauthor, 1, 1) as "성", count(*) as "인원수"
from book
group by substr(bookauthor, 1, 1);


-------------------------------------------------------------------------------------------------------------------

-- 연습문제_3

-- 주문일에 7일을 더한 날을 배송일로 계산하여 조회
select bsdate 주문일, bsdate+7 배송일
from booksale;

-- 도서 테이블에서 도서명과 출판연도 조회
select bookname 도서명, extract(year from bookdate) 출판연도
from book;

