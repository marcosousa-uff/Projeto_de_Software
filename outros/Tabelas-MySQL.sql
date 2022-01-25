https://dev.mysql.com/downloads/

Download MySQL Community Server

Últimas versões:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)


DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `login` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `senha` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `versao` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `login`, `senha`, `versao`) VALUES
(2, 'pedro', 'senha', '123', 10),
(3, 'marco', 'marco', '123', 0),
(4, 'maria', 'maria', '123', 0);
COMMIT;