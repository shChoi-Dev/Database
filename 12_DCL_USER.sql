-- 계정에 할당된 QUOTA 확인
SELECT * FROM USER_TS_QUOTAS; -- 할당량 MAX_BYTES 값이 -1이면 무제한의미

-- 계정 조회
-- SELECT * FROM DBA_USERS; -- DBA권한으로만 조회 가능
SELECT * FROM ALL_USERS; -- DBA권한보다 낮은 모든 사용자 권한으로 조회가 가능
SELECT * FROM USER_USERS; -- 현재 계정에 대한 정보 조회

