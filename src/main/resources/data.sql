REPLACE INTO usuario VALUES 
(1, 'Admin', '02006', 'C/ Nicaragua 16', '74419571N', 'admin@hotmail.com', '1994-11-01', 'Admin', '$2a$04$DxO1pQRJ2YAf.YCbaZWvhO6YFge7bEc/PHYUp7z24pN487.umJ4Si', 'Albacete', '664524979', 1, 2);

INSERT IGNORE INTO usuario VALUES 
(2, 'Frita', '02006', 'C/ Patata 1', '65488628S', 'usuario@hotmail.com', '1970-01-01', 'Patata', '$2a$04$RzNGCjZ0TbNOES18un3vo.H6JQ1AkcmhV7J/Gcu9y/DuXcxGRWOo.', 'Albacete', '664524971', 1, 1);
	
REPLACE INTO provincia VALUES
	(1, 2, 'Albacete'),
	(2, 3, 'Alicante'),
	(3, 4, 'Almería'),
	(4, 1, 'Álava'),
	(5, 33, 'Asturias'),
	(6, 5, 'Ávila'),
	(7, 6, 'Badajoz'),
	(8, 7, 'Islas Baleares'),
	(9, 8, 'Barcelona'),
	(10, 48, 'Bizkaia'),
	(11, 9, 'Burgos'),
	(12, 10, 'Cáceres'),
	(13, 11, 'Cádiz'),
	(14, 39, 'Cantabria'),
	(15, 12, 'Castellón'),
	(16, 51, 'Ceuta'),
	(17, 13, 'Ciudad Real'),
	(18, 14, 'Córdoba'),
	(19, 15, 'A Coruña'),
	(20, 16, 'Cuenca'),
	(21, 20, 'Gipuzkoa'),
	(22, 17, 'Girona'),
	(23, 18, 'Granada'),
	(24, 19, 'Guadalajara'),
	(25, 21, 'Huelva'),
	(26, 22, 'Huesca'),
	(27, 23, 'Jaén'),
	(28, 24, 'León'),
	(29, 27, 'Lugo'),
	(30, 25, 'Lleida'),
	(31, 28, 'Madrid'),
	(32, 29, 'Málaga'),
	(33, 52, 'Melilla'),
	(34, 30, 'Murcia'),
	(35, 31, 'Navarra'),
	(36, 32, 'Ourense'),
	(37, 34, 'Palencia'),
	(38, 35, 'Las Palmas'),
	(39, 36, 'Pontevedra'),
	(40, 26, 'La Rioja'),
	(41, 37, 'Salamanca'),
	(42, 38, 'Santa Cruz de Tenerife'),
	(43, 40, 'Segovia'),
	(44, 41, 'Sevilla'),
	(45, 42, 'Soria'),
	(46, 43, 'Tarragona'),
	(47, 44, 'Teruel'),
	(48, 45, 'Toledo'),
	(49, 46, 'Valencia'),
	(50, 47, 'Valladolid'),
	(51, 49, 'Zamora'),
	(52, 50, 'Zaragoza');

REPLACE INTO rol VALUES
	(1, 'USER'),
	(2, 'ADMIN');
	
INSERT IGNORE INTO animal (`ID`, `NOMBRE`, `TIPO`, `SEXO`, `RAZA`, `FNAC`, `PROVINCIA_ID`, `POBLACION`, `ESTERILIZADO`, `FOTO`, `DESCRIPCION`, `ESTADO`, `USUARIO_ID`, `FECHA_ALTA`) VALUES
	(1, 'Pipo', 'PERRO', 'MACHO', 'Beagle', '2018-12-04', 1, 'Albacete', 'SI', '2019-12-08__18.53.01.592.png', 'Pipo es un buen perro, aunque un poco travieso. Su antigua dueña lo trataba mal y pudo ser requisado. Necesita una familia que lo quiera como se merece. ¡Dale una oportunidad!', 'EN_ADOPCION', NULL, '2019-11-11 18:53:01'),
	(2, 'Michito', 'PERRO', 'MACHO', 'Chihuahua', '2019-11-10', 1, 'Hellín', 'PENDIENTE', '2019-12-08__18.54.56.705.jpg', 'Michito es un cachorrito que apareció en una caja de cartón en la calle, junto a sus hermanos, que ya han sido adoptados. Sólo falta él por adoptar. Necesita una familia que le de mucho calor y cariño. ', 'EN_ADOPCION', NULL, '2019-11-28 18:54:56'),
	(3, 'Roxy', 'PERRO', 'HEMBRA', 'Pastor alemán', '2019-10-20', 1, 'Albacete', 'PENDIENTE', '2019-12-08__18.57.02.702.jpg', 'Roxy es una cachorrita que no pudieron vender en una tienda que cerró, y ahora está en el albergue. Es muy lista y necesita una familia que la quiera toda la vida. ', 'EN_ADOPCION', NULL, '2019-11-23 18:57:02'),
	(4, 'Scottex', 'PERRO', 'MACHO', 'Labrador Retriever', '2019-08-05', 2, 'Alicante', 'PENDIENTE', '2019-12-08__18.58.35.136.jpg', 'Scotexx es un cachorro que fue encontrado vagando por la calle. Es bastante travieso, hay que estar alerta o la puede liar parda, pero es muy cariñoso. ¡Se merece una oportunidad!', 'EN_ADOPCION', NULL, '2019-11-23 18:57:02'),
	(5, 'Yuki', 'GATO', 'HEMBRA', 'Angora turco', '2013-04-25', 48, 'Toledo', 'SI', '2019-12-08__19.03.12.325.jpg', 'Yuki es una gatita que desde que murió su dueña, nadie de su "familia" fue capaz de quedársela. Tiene unos bonitos y curiosos ojos con heterocromía. Necesita una familia que nunca mas la vuelva a abandonar. ', 'EN_ADOPCION', NULL, '2019-10-03 18:57:02'),
	(6, 'Garfield', 'GATO', 'MACHO', 'Persa', '2012-06-12', 31, 'Madrid', 'SI', '2019-12-08__19.04.26.794.jpg', 'Garfield es un gato al que echaron de casa porque no dejaba de comer todos los restos que encontraba, aún así es un amor. Solo hay que controlarlo un poco, necesita una familia que lo eduque bien. ', 'EN_ADOPCION', NULL, '2019-05-12 19:04:26'),
	(7, 'Neeko', 'GATO', 'HEMBRA', 'Bosque de noruega', '2010-01-28', 36, 'Verín', 'SI', '2019-12-08__19.06.35.818.jpg', 'Neeko es una gatita que apareció vagando por la calle, y se dejó acariciar y tocar. No tenía chip. No hemos podido localizar a su dueño, o no sabemos si fue abandonada. Necesita un hogar donde se sienta como en casa. ', 'EN_ADOPCION', NULL, '2019-06-12 19:06:35'),
	(8, 'Asriel', 'GATO', 'MACHO', 'Europeo común', '2015-07-15', 32, 'Marbella', 'SI', '2019-12-08__19.13.30.784.jpg', 'Asriel es un gato que tuvo que ser rescatado de un pozo a varios metros de profundidad, en un monte. Está muy asustado y necesita una familia comprensiva que le haga recuperar la confianza. ', 'EN_ADOPCION', NULL, '2019-12-02 19:13:30'),
	(9, 'Gnar', 'GATO', 'MACHO', 'Común europeo', '2019-10-05', 1, 'Albacete', 'PENDIENTE', '2019-12-08__19.16.34.374.jpg', 'Gnar es un gatito que fue rescatado en medio de una tormenta. Llegó con hipotermia pero ya ha sido estabilizado y ahora necesita un hogar donde nunca más tenga que estar en la interperie. ', 'EN_ADOPCION', NULL, '2019-11-20 19:16:34'),
	(10, 'Yamper', 'PERRO', 'MACHO', 'Corgi', '2016-03-05', 9, 'Barcelona', 'SI', '2019-12-08__19.19.49.453.png', 'Yamper es un perro que fue encontrado en el campo, correteando en la hierba alta. Le encanta jugar y necesita un hogar donde le quieran mucho. ', 'EN_ADOPCION', NULL, '2019-12-08 19:19:49'),
	(11, 'Toriel', 'GATO', 'HEMBRA', 'Labrador retriever', '2015-09-15', 32, 'Marbella', 'SI', '2019-12-08__19.24.21.784.jpg', 'A Toriel la encontraron dentro de un hoyo y estaba allí atrapada, sin comer ni beber durante varios días. Necesita una familia que la quiera y que le de la confianza de saber que nunca más volverá a estar en peligro. Se lleva bien con perros y gatos. ', 'EN_ADOPCION', NULL, '2019-12-06 19:24:21'),
	(12, 'Meowstic', 'GATO', 'HEMBRA', 'Siamés', '2019-11-01', 1, 'Albacete', 'PENDIENTE', '2019-12-08__19.26.41.286.jpg', 'Meowstic es una cachorrita que estaba abandonada en una finca. La encontraron en mal estado pero está saliendo adelante. Necesita el calor de un hogar. Es muy cariñosa. ', 'EN_ADOPCION', NULL, '2019-12-08 19:26:41'),
	(13, 'Manchas', 'PERRO', 'MACHO', 'Dálmata', '2014-05-20', 1, 'Albacete', 'SI', '2019-12-09__05.12.28.300.jpg', 'Manchas es un dálmata que se ha quedado sin dueños, porque murieron en un accidente, y no tiene con quien quedarse. Es muy cariñoso, aunque un poco nervioso. ¡Dale una oportunidad!', 'EN_ADOPCION', NULL, '2019-12-09 05:12:28');

