-- 코드를 작성해주세요

# SELECT ((SELECT SCORE FROM HR_GRADE WHERE YEAR = 2022 AND HALF_YEAR = 1 AND EMP_NO = G.EMP_NO) + (SELECT SCORE FROM HR_GRADE WHERE YEAR = 2022 AND HALF_YEAR = 2 AND EMP_NO = G.EMP_NO)) AS SCORE, G.EMP_NO AS EMP_NO, E.EMP_NAME AS EMP_NAME, E.POSITION AS POSITION, E.EMAIL AS EMAIL
# FROM HR_GRADE G INNER JOIN HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO

# ORDER BY SCORE DESC LIMIT 1

WITH SUM_SCORE AS (
    SELECT SUM(SCORE) AS TOTAL, EMP_NO FROM HR_GRADE
    WHERE YEAR = 2022 GROUP BY EMP_NO 
)

SELECT S.TOTAL AS SCORE, E.EMP_NO AS EMP_NO, E.EMP_NAME AS EMP_NAME, E.POSITION AS POSITION, E.EMAIL AS EMAIL
FROM HR_EMPLOYEES E INNER JOIN SUM_SCORE S ON E.EMP_NO = S.EMP_NO
WHERE S.TOTAL = (SELECT MAX(TOTAL) FROM SUM_SCORE)