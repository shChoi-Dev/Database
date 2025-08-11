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
select bookNo, bookName, pubName
from book B
    inner join publisher P
    on B.pubNo = P.pubNo;

-- 2. '서울 출판사'에서 출간한 도서의 도서명, 저자명, 출판사명 출력 (출판사명 사용)
select B.bookName, B.bookAuthor, P.pubName
from book B
    inner join publisher P
    on B.pubNo = P.pubNo
where P.pubName = '서울 출판사';
    

-- 3. '정보출판사'에서 출간한 도서 중 판매된 도서의 도서명 출력 (중복된 경우 한 번만 출력) (출판사명 사용)
select UNIQUE B.bookName
from book B
    inner join publisher P on B.pubNo = P.pubNo
    inner join bookSale BS on B.bookNo = BS.bookNo
where P.pubName = '정보출판사';

-- 4. 도서가격이 30,000원 이상인 도서를 주문한 고객의 고객명, 도서명, 도서가격, 주문수량 출력
select C.clientname, B.bookname, B.bookprice, BS.bsQty
from bookSale BS
    inner join client C on BS.clientNo = C.clientNo
    inner join Book B on BS.bookNo = B.bookNo
where B.bookprice >= 30000;

-- 5. '안드로이드 프로그래밍' 도서를 구매한 고객에 대하여 도서명, 고객명, 성별, 주소 출력 (고객명으로 오름차순 정렬)
select B.bookname, C.clientname, C.clientGender, C.clientAddress
from bookSale BS
    inner join client C on BS.clientNo = C.clientNo
    inner join Book B on BS.bookNo = B.bookNo
where B.bookname = '안드로이드 프로그래밍'
order by C.clientname;

-- 6. '도서출판 강남'(publisher)에서 출간된 도서 중 판매된 도서(booksale)에 대하여 ‘총 매출액’(단가(book) * 주문수량) 출력
select P.pubName, sum(B.bookPrice * BS.bsQty) as "총 매출액"
from book B
    inner join publisher P on B.pubNo = P.pubNo
    inner join bookSale BS on B.bookNo = BS.bookNo
where P.pubName = '도서출판 강남'
group by P.pubName;

-- 7. '서울 출판사'에서 출간된 도서에 대하여 판매일, 출판사명, 도서명, 도서가격, 주문수량, 주문액 출력
select B.bookDate, P.pubName, B.bookName, B.bookPrice, BS.bsQty, 
       BS.bsqty * b.BookPrice as "주문액"
from book B
    inner join publisher P on B.pubNo = P.pubNo
    inner join bookSale BS on B.bookNo = BS.bookNo
where P.pubName = '서울 출판사';

-- 8.판매된 도서에 대하여 도서별로 도서번호, 도서명, 총 주문 수량 출력
select B.bookNo, B.bookName, sum(BS.bsqty) as "총 주문 수량"
from book B
    inner join bookSale BS on B.bookNo = BS.bookNo
group by B.bookNo, B.bookName;

-- 9.판매된 도서에 대하여 고객별로 고객명, 총구매액 출력 ( 총구매액이 100,000원 이상인 경우만 해당)
select C.clientName, sum(B.bookPrice * BS.bsQty) as "총구매액"
from client C
    inner join bookSale BS on C.clientNo = BS.clientNo
    inner join book B on BS.bookNo = BS.bookNo
group by C.clientName
having sum(B.bookPrice * BS.bsQty) >= 100000;

-- 10.판매된 도서 중 ＇도서출판 강남'에서 출간한 도서에 대하여 고객명, 주문일, 도서명, 주문수량, 출판사명 출력 (고객명으로 오름차순 정렬)
select C.clientName, BS.bsDate, B.bookName, BS.bsQty, P.pubName
from client C
    inner join bookSale BS on C.clientNo = BS.clientNo
    inner join book B on B.bookNo = BS.bookNo
    inner join publisher P on B.pubNo = P.pubNo
where P.pubName = '도서출판 강남'
order by C.clientName;

