-- subQuery 연습문제 / join으로도 가능하면 진행.

-- 1.호날두(고객명)가 주문한 도서의 총 구매량 출력

-- 1.1.서브쿼리
SELECT SUM(BSQTY) AS "총 구매량"
FROM BOOKSALE
WHERE CLIENTNO = (SELECT CLIENTNO
                  FROM CLIENT
                  WHERE CLIENTNAME = '호날두');
                  
-- 1.2.조인
SELECT SUM(BS.BSQTY) AS "총 구매량"
FROM BOOKSALE BS
    INNER JOIN CLIENT C ON BS.CLIENTNO = C.CLIENTNO
WHERE C.CLIENTNAME = '호날두';

-- 2.‘정보출판사’에서 출간한 도서를 구매한 적이 있는 고객명 출력
-- 2.1.서브쿼리
SELECT CLIENTNAME
FROM CLIENT
WHERE CLIENTNO IN (SELECT CLIENTNO
                       FROM BOOKSALE
                       WHERE BOOKNO IN (SELECT BOOKNO
                                        FROM BOOK
                                        WHERE PUBNO = (SELECT PUBNO
                                                       FROM PUBLISHER
                                                       WHERE PUBNAME = '정보출판사')));
                  
-- 2.2.조인
SELECT C.CLIENTNAME
FROM CLIENT C
    JOIN BOOKSALE BS ON C.CLIENTNO = BS.CLIENTNO
    JOIN BOOK B ON BS.BOOKNO = B.BOOKNO
    JOIN PUBLISHER P ON B.PUBNO = P.PUBNO
WHERE P.PUBNAME = '정보출판사';


-- 3.베컴이 주문한 도서의 최고 주문수량 보다 더 많은 도서를 구매한 고객명 출력
-- 3.1.서브쿼리
SELECT CLIENTNAME
FROM CLIENT
WHERE CLIENTNO IN (SELECT CLIENTNO
                   FROM BOOKSALE
                   WHERE BSQTY > (SELECT MAX(BSQTY)
                                  FROM BOOKSALE
                                  WHERE CLIENTNO = (SELECT CLIENTNO 
                                                    FROM CLIENT 
                                                    WHERE CLIENTNAME = '베컴')));
                                                    
-- 3.2.조인
SELECT CLIENTNAME
FROM CLIENT C
    JOIN BOOKSALE BS ON C.CLIENTNO = BS.CLIENTNO
    WHERE BS.BSQTY > (SELECT MAX(BSQTY) 
                      FROM BOOKSALE
                      WHERE CLIENTNO = (SELECT CLIENTNO 
                                        FROM CLIENT 
                                        WHERE CLIENTNAME = '베컴'));

-- 4.천안에 거주하는 고객에게 판매한 도서의 총 판매량 출력

-- 4.1.서브쿼리
SELECT SUM(BSQTY) AS "총 판매량"
FROM BOOKSALE
WHERE CLIENTNO IN (SELECT CLIENTNO
                   FROM CLIENT
                   WHERE CLIENTADDRESS LIKE '%천안%'
                   );
                   
-- 4.2.조인 
SELECT SUM(BS.BSQTY) AS "총 판매량"
FROM CLIENT C
JOIN BOOKSALE BS ON C.CLIENTNO = BS.CLIENTNO
WHERE C.CLIENTADDRESS LIKE '%천안%';

