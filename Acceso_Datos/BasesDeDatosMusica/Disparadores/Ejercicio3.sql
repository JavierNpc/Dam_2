SELECT g1.cod, count(g1.cod) FROM grupo g1, club c1 
WHERE c1.cod_gru = g1.cod GROUP BY g1.cod ORDER BY g1.cod

