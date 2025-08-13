-- subQuery 연습문제 / join으로도 가능하면 진행.

-- 1.호날두(고객명)가 주문한 도서의 총 구매량 출력
select sum(bsqty) "총구매량"
from booksale
where clientno = (select clientno
                    from client
                    where clientname='호날두');

-- in 단일행 다중행 모두 사용 가능                    
select sum(bsqty) "총구매량"
from booksale
where clientno in (select clientno
                    from client
                    where clientname='호날두');                    

-- 2.‘정보출판사’에서 출간한 도서를 구매한 적이 있는 고객명 출력
select clientname
from client
where clientno in (select clientno
                    from booksale
                    where bookno in (select bookno
                                    from book
                                    where pubno in (select pubno
                                                    from publisher
                                                    where pubname = '정보출판사')));

-- 3.베컴이 주문한 도서의 최고 주문수량 보다 더 많은 도서를 구매한 고객명 출력
select clientname
from client
where clientno in (select clientno
                   from booksale
                   where bsqty > ALL(select bsqty
                                     from booksale
                                     where clientno in (select clientno
                                                        from client
                                                        where clientname = '배컴')));
-- max 사용                                                       
select clientname
from client
where clientno in (select clientno
                   from booksale
                   where bsqty > (select max(bsqty) -- 단일행 서브쿼리
                                  from booksale
                                  where clientno in (select clientno
                                                     from client
                                                     where clientname = '배컴')));                                            


-- 4.천안에 거주하는 고객에게 판매한 도서의 총 판매량 출력
select sum(bsqty) as "천안 고객의 총판매량"
from booksale
where clientno in (select clientno
                   from client
                   where clientaddress like '%천안%');
                   
-- exists
-- 연산자 앞에 어떤 속성도 오면 안됨
-- 서브쿼리에서 반환되는 행이 존재하는지의 여부만 확인
select sum(bsqty) as "천안 고객의 총판매량"
from booksale
where exists (select clientno
              from client
              where clientaddress like '%천안%'
                    and client.clientno = booksale.clientno);

