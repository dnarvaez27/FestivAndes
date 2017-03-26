SELECT
  F.FECHA,
  L.NOMBRE                 AS LUGAR,
  ROL                      AS TIPO_USUARIO,
  NVL(BOLETAS_VENDIDAS, 0) AS BOLETAS_VENDIDAS,
  NVL(TOTAL, 0)            AS TOTAL
FROM (SELECT
        FECHA,
        ID_LUGAR,
        ROL,
        SUM(BOLETAS_VENDIDAS)         AS BOLETAS_VENDIDAS,
        SUM(BOLETAS_VENDIDAS * COSTO) AS TOTAL
      FROM (SELECT
              FECHA,
              ID_LUGAR,
              ID_LOCALIDAD,
              COSTO,
              ROL,
              COUNT(*) AS BOLETAS_VENDIDAS
            FROM (SELECT
                    F.FECHA,
                    F.ID_LUGAR,
                    F.ID_LOCALIDAD,
                    F.COSTO,
                    B.ID_USUARIO
                  FROM COSTO_LOCALIDAD F
                    INNER JOIN BOLETAS B
                      ON F.ID_LUGAR = B.ID_LUGAR
                         AND F.FECHA = B.FECHA
                         AND F.ID_LOCALIDAD = B.ID_LOCALIDAD) B INNER JOIN USUARIOS U
                ON B.ID_USUARIO = U.IDENTIFICACION
            GROUP BY FECHA, ID_LUGAR, COSTO, ID_LOCALIDAD, ROL)
      GROUP BY FECHA, ID_LUGAR, ROL)
     A RIGHT JOIN FUNCIONES F
    ON A.FECHA = F.FECHA
       AND A.ID_LUGAR = F.ID_LUGAR
  LEFT JOIN LUGARES L ON F.ID_LUGAR = L.ID
WHERE f.ID_ESPECTACULO = 2
ORDER BY FECHA, L.NOMBRE, ROL, BOLETAS_VENDIDAS;

--P2
SELECT
  l.NOMBRE                             AS lugar,
  NVL( ( OCUPADOS * 100 / TOTAL ), 0 ) AS PORCENTAJE_DE_OCUPACION
FROM ( SELECT
         id_lugar,
         SUM( CAPACIDAD ) AS TOTAL
       FROM LUGAR_LOCALIDAD
       GROUP BY id_lugar ) C
  INNER JOIN ( SELECT
                 F.id_lugar,
                 COUNT( B.id_lugar ) OCUPADOS
               FROM BOLETAS B RIGHT JOIN FUNCIONES F
                   ON B.ID_LUGAR = F.ID_LUGAR
                      AND B.FECHA = F.FECHA
               WHERE F.ID_ESPECTACULO = &P
               GROUP BY F.id_lugar ) O
    ON C.id_lugar = O.id_lugar
  INNER JOIN LUGARES L
    ON O.ID_LUGAR = L.ID
ORDER BY PORCENTAJE_DE_OCUPACION DESC;