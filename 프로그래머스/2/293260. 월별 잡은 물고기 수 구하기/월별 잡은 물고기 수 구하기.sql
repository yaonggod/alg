-- 코드를 작성해주세요

SELECT COUNT(*) AS FISH_COUNT, MONTH(TIME) AS MONTH 
FROM FISH_INFO GROUP BY MONTH ORDER BY MONTH