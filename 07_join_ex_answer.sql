-- 연습문제2 | Join

/*
1.모든 도서에 대하여 도서의 도서번호, 도서명, 출판사명 출력
2.'서울 출판사'에서 출간한 도서의 도서명, 저자명, 출판사명 출력 (출판사명 사용)
3.＇정보출판사'에서 출간한 도서 중 판매된 도서의 도서명 출력 (중복된 경우 한 번만 출력) (출판사명 사용)
4.도서가격이 30,000원 이상인 도서를 주문한 고객의 고객명, 도서명, 도서가격, 주문수량 출력
5.'안드로이드 프로그래밍' 도서를 구매한 고객에 대하여 도서명, 고객명, 성별, 주소 출력 (고객명으로 오름차순 정렬)
6.‘도서출판 강남'에서 출간된 도서 중 판매된 도서에 대하여 ‘총 매출액’ 출력
7.‘서울 출판사'에서 출간된 도서에 대하여 판매일, 출판사명, 도서명, 도서가격, 주문수량, 주문액 출력
8.판매된 도서에 대하여 도서별로 도서번호, 도서명, 총 주문 수량 출력
9.판매된 도서에 대하여 고객별로 고객명, 총구매액 출력 ( 총구매액이 100,000원 이상인 경우만 해당)
10.판매된 도서 중 ＇도서출판 강남'에서 출간한 도서에 대하여 고객명, 주문일, 도서명, 주문수량, 출판사명 출력 (고객명으로 오름차순 정렬)
*/

-- 1. 모든 도서에 대하여 도서의 도서번호, 도서명, 출판사명 출력
select *
from book, publisher
where book.pubno = publisher.pubno;

select b.bookno, b.bookname, b.bookauthor, p.pubname
from book b
    inner join publisher p on b.pubno = p.pubno;


-- 2. '서울 출판사'에서 출간한 도서의 도서명, 저자명, 출판사명 출력 (출판사명 사용)
select b.bookname, b.bookauthor, p.pubname
from book b
    inner join publisher p on b.pubno = p.pubno
where p.pubname = '서울 출판사';
    
select b.bookname, b.bookauthor, p.pubname
from book b, publisher p
where b.pubno = p.pubno and p.pubname = '서울 출판사';

-- 3. '정보출판사'에서 출간한 도서 중 판매된 도서의 도서명 출력 (중복된 경우 한 번만 출력) (출판사명 사용)
select distinct bookname
from book B
    inner join publisher P on B.pubno = P.pubno
    inner join booksale BS on B.bookno = BS.bookno
where P.pubname = '정보출판사';

-- 4.도서가격이 30,000원 이상인 도서를 주문한 고객의 고객명, 도서명, 도서가격, 주문수량 출력
select C.clientname, B.bookname, B.bookprice, BS.bsqty
from booksale BS
    inner join book B on BS.bookno = B.bookno
    inner join client C on BS.clientno = C.clientno
where B.bookprice >= 30000;

-- 5. '안드로이드 프로그래밍' 도서를 구매한 고객에 대하여 도서명, 고객명, 성별, 주소 출력 (고객명으로 오름차순 정렬)
select B.bookname, C.clientname, C.clientgender, C.clientaddress
from book B
    inner join booksale BS on BS.bookno = B.bookno
    inner join client C on BS.clientno = C.clientno
where B.bookname = '안드로이드 프로그래밍'
order by clientname;

-- 6. '도서출판 강남'(publisher)에서 출간된 도서 중 판매된 도서(booksale)에 대하여 ‘총 매출액’(단가(book) * 주문수량) 출력
select P.pubName, sum(B.bookPrice * BS.bsQty) as "총 매출액"
from book B
    inner join publisher P on B.pubNo = P.pubNo
    inner join bookSale BS on B.bookNo = BS.bookNo
where P.pubName = '도서출판 강남'
group by P.pubName;

-- 7. '서울 출판사'에서 출간된 도서에 대하여 판매일, 출판사명, 도서명, 도서가격, 주문수량, 주문액 출력
select BS.bsdate, P.pubname, B.bookname, B.bookprice, BS.bsqty, B.bookprice * BS.bsqty as "주문금액"
from book B
    inner join booksale BS on B.bookNo = BS.bookNo
    inner join publisher P on P.pubno = B.pubno
where P.pubname = '서울 출판사';


-- 8.판매된 도서에 대하여 도서별로 도서번호, 도서명, 총 주문 수량 출력
select B.bookno, B.bookname, sum(BS.bsqty) as "총 주문 수량"
from book B
    inner join booksale BS on B.bookNo = BS.bookNo
group by B.bookno, B.bookname;


-- 9.판매된 도서에 대하여 고객별로 고객명, 총구매액 출력 ( 총구매액이 100,000원 이상인 경우만 해당)
select C.clientname, sum(B.bookprice * BS.bsqty) as "총구매액"
from booksale bs
    inner join client C on BS.clientno = C.clientno
    inner join book B on BS.bookno = B.bookno
group by C.clientno, C.clientname
having sum(B.bookprice * BS.bsqty) >= 100000;

-- 10.판매된 도서 중 ＇도서출판 강남'에서 출간한 도서에 대하여 고객명, 주문일, 도서명, 주문수량, 출판사명 출력 (고객명으로 오름차순 정렬)
select C.clientname, BS.bsdate, B.bookname, BS.bsqty, P.pubname
from booksale bs
    inner join client C on BS.clientno = C.clientno
    inner join book B on BS.bookno = B.bookno
    inner join publisher P  on B.pubno = P.pubno
where P.pubname = '도서출판 강남'
order by C.clientname;

