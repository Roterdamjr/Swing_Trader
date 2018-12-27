select data_pregao,h.preabe,h.premax,premin,h.preult,h.quatot
 from tb_hist_cotacoes_final h where codneg=trim(?)
 order by premin