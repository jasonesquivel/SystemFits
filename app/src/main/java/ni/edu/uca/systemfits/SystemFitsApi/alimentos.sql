-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2023 at 08:29 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `alimentos`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_recetas`
--

CREATE TABLE `tbl_recetas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(1500) NOT NULL,
  `calorias` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_recetas`
--

INSERT INTO `tbl_recetas` (`id`, `nombre`, `descripcion`, `calorias`, `estado`) VALUES
(1, 'Manzana', 'Fruta refrescante y crujiente', 52, 0),
(2, 'Pollo a la parrilla', 'Proteína baja en grasa', 165, 0),
(3, 'Brócoli', 'Verdura rica en nutrientes y fibra', 55, 0),
(4, 'Salmón', 'Pescado rico en ácidos grasos omega-3 y proteínas', 206, 0),
(5, 'Quinoa', 'Pseudocereal nutritivo y sin gluten', 120, 0),
(6, 'Yogur griego', 'Lácteo rico en proteínas y calcio', 133, 0),
(7, 'Espinacas', 'Hojas verdes cargadas de vitaminas y minerales', 23, 0),
(8, 'Plátano', 'Fruta energética y rica en potasio', 96, 0),
(9, 'Almendras', 'Frutos secos saludables y llenos de nutrientes', 579, 0),
(10, 'Pavo', 'Carne magra y alta en proteínas', 165, 0),
(11, 'Zanahoria', 'Vegetal naranja y rico en vitamina A', 41, 0),
(12, 'Arroz integral', 'Grano entero rico en fibra y nutrientes', 111, 0),
(13, 'Huevo', 'Fuente de proteínas y nutrientes', 155, 0),
(14, 'Fresas', 'Frutas dulces y bajas en calorías', 32, 0),
(15, 'Atún', 'Pescado rico en proteínas y ácidos grasos omega-3', 116, 0),
(16, 'Queso cottage', 'Lácteo bajo en grasa y alto en proteínas', 98, 0),
(17, 'Avena', 'Cereal integral rico en fibra', 389, 0),
(18, 'Tomate', 'Fruta roja y jugosa', 18, 0),
(19, 'Pechuga de pavo', 'Carne blanca baja en grasa y alta en proteínas', 125, 0),
(20, 'Espinacas', 'Hojas verdes ricas en hierro y antioxidantes', 23, 0),
(21, 'Garbanzos', 'Legumbre rica en proteínas y fibra', 364, 0),
(22, 'Kiwi', 'Fruta exótica y rica en vitamina C', 61, 0),
(23, 'Yogur de vainilla', 'Lácteo suave y aromático', 118, 0),
(24, 'Calabaza', 'Vegetal anaranjado y nutritivo', 26, 0),
(25, 'Garbanzos', 'Legumbre versátil y rica en proteínas', 164, 0),
(26, 'Pera', 'Fruta jugosa y ligeramente dulce', 57, 0),
(27, 'Tofu', 'Proteína vegetal rica en nutrientes', 144, 0),
(28, 'Papas', 'Tubérculo versátil y rico en carbohidratos', 77, 0),
(29, 'Yogur natural', 'Lácteo probiótico y saludable', 61, 0),
(30, 'Cerezas', 'Frutas pequeñas y dulces', 50, 0),
(31, 'Ternera magra', 'Carne roja baja en grasa y alta en proteínas', 250, 0),
(32, 'Espinacas', 'Hojas verdes ricas en vitaminas y minerales', 23, 0),
(33, 'Lentejas', 'Legumbre nutritiva y fuente de proteínas', 353, 0),
(34, 'Mango', 'Fruta tropical dulce y jugosa', 60, 0),
(35, 'Hummus', 'Pasta de garbanzos rica en proteínas y fibra', 166, 0),
(36, 'Sandía', 'Fruta refrescante y baja en calorías', 30, 0),
(37, 'Pechuga de pollo', 'Carne magra y fuente de proteínas', 110, 0),
(38, 'Pasta integral', 'Harina de trigo integral y rica en fibra', 131, 0),
(39, 'Naranja', 'Fruta cítrica y alta en vitamina C', 43, 0),
(40, 'Albahaca', 'Hierba aromática y llena de sabor', 23, 0),
(41, 'Pargo rojo', 'Pescado blanco y sabroso', 116, 0),
(42, 'Garbanzos', 'Legumbre versátil y rica en proteínas', 269, 0),
(43, 'Papaya', 'Fruta tropical dulce y jugosa', 43, 0),
(44, 'Huevo', 'Fuente de proteínas y nutrientes', 155, 0),
(45, 'Pasta de trigo', 'Harina de trigo y fuente de carbohidratos', 131, 0),
(46, 'Pimiento rojo', 'Vegetal crujiente y lleno de vitamina C', 31, 0),
(47, 'Tilapia', 'Pescado blanco y bajo en calorías', 96, 0),
(48, 'Guisantes', 'Legumbre verde y fuente de proteínas', 81, 0),
(49, 'Coco rallado', 'Fruto seco y sabroso', 354, 0),
(50, 'Pargo rojo', 'Pescado blanco y sabroso', 116, 0),
(76, 'Tortilla de patatas', 'Clásico plato español con huevos, patatas y cebolla', 350, 0),
(77, 'Ensalada César', 'Ensalada con lechuga, pollo, croutones, queso parmesano y aderezo César', 250, 0),
(78, 'Sopa de tomate', 'Sopa caliente hecha a base de tomates frescos', 150, 0),
(79, 'Spaghetti Bolognese', 'Pasta con salsa de carne y tomate', 400, 0),
(80, 'Pizza Margherita', 'Pizza con salsa de tomate, mozzarella y albahaca', 300, 0),
(81, 'Hamburguesa con queso', 'Hamburguesa de carne con queso, lechuga, tomate y salsa', 500, 0),
(82, 'Sushi de salmón', 'Rollitos de sushi rellenos de salmón y arroz', 250, 0),
(83, 'Pollo al horno', 'Pollo asado al horno con hierbas y especias', 300, 0),
(84, 'Lasagna', 'Plato de pasta con capas de carne, salsa y queso', 450, 0),
(85, 'Enchiladas', 'Tortillas rellenas de pollo o carne con salsa de tomate y queso', 350, 0),
(86, 'Ceviche', 'Plato de pescado marinado en jugo de limón con cebolla y especias', 200, 0),
(87, 'Risotto de champiñones', 'Arroz cremoso con champiñones y queso', 400, 0),
(88, 'Tacos de carne asada', 'Tortillas de maíz rellenas de carne asada, cebolla y cilantro', 350, 0),
(89, 'Pasta Alfredo', 'Pasta con salsa cremosa de queso y ajo', 380, 0),
(90, 'Gazpacho', 'Sopa fría de tomate, pepino, pimiento y ajo', 120, 0),
(91, 'Cordero al horno', 'Pierna de cordero asada al horno con hierbas', 400, 0),
(92, 'Pancakes', 'Tortitas esponjosas para el desayuno', 250, 0),
(93, 'Sopa de lentejas', 'Sopa caliente de lentejas con verduras', 200, 0),
(94, 'Burritos', 'Tortillas rellenas de carne, arroz, frijoles y salsa', 450, 0),
(95, 'Mousse de chocolate', 'Postre cremoso de chocolate', 300, 0),
(96, 'Guiso de pollo', 'Estofado de pollo con verduras', 280, 0),
(97, 'Pastel de carne', 'Pastel horneado con carne picada, verduras y puré de papas', 350, 0),
(98, 'Arroz con pollo', 'Arroz cocido con pollo, verduras y especias', 380, 0),
(99, 'Galletas de avena', 'Galletas horneadas con copos de avena y pasas', 150, 0),
(100, 'Tiramisú', 'Postre italiano de capas de bizcocho, café y crema', 400, 0),
(101, 'Pollo al curry', 'Pollo cocinado en salsa de curry y especias', 320, 0),
(102, 'Muffins de arándanos', 'Muffins esponjosos con arándanos frescos', 200, 0),
(103, 'Sopa de calabaza', 'Sopa cremosa de calabaza y especias', 150, 0),
(104, 'Empanadas', 'Empanadas horneadas o fritas rellenas de carne, pollo o verduras', 300, 0),
(105, 'Cheesecake', 'Tarta de queso cremosa con base de galleta', 450, 0),
(106, 'Lasaña de verduras', 'Plato de pasta con capas de verduras, salsa y queso', 320, 0),
(107, 'Smoothie de frutas', 'Bebida cremosa a base de frutas y yogur', 180, 0),
(108, 'Costillas a la barbacoa', 'Costillas de cerdo marinadas en salsa barbacoa y asadas', 450, 0),
(109, 'Flan', 'Postre cremoso y suave con caramelo', 250, 0),
(110, 'Fajitas de pollo', 'Tortillas rellenas de pollo, pimientos y cebolla', 380, 0),
(111, 'Galletas de chocolate', 'Galletas crujientes con trozos de chocolate', 180, 0),
(112, 'Salmón a la parrilla', 'Filete de salmón asado a la parrilla con limón y especias', 320, 0),
(113, 'Mousse de fresa', 'Postre ligero y espumoso de fresas', 220, 0),
(114, 'Pollo a la plancha', 'Pechuga de pollo cocinada a la plancha sin grasa', 200, 0),
(115, 'Quiche de espinacas', 'Tarta salada con base de masa y relleno de espinacas y queso', 300, 0),
(116, 'Hummus', 'Pasta de garbanzos con tahini, ajo y limón', 150, 0),
(117, 'Sopa de pollo y verduras', 'Sopa reconfortante con pollo, zanahorias, apio y otras verduras', 180, 0),
(118, 'Tarta de manzana', 'Tarta dulce con relleno de manzana y canela', 280, 0),
(119, 'Panqueques de banana', 'Panqueques esponjosos con trozos de banana', 220, 0),
(120, 'Papas fritas', 'Papas cortadas en rodajas y fritas hasta dorar', 350, 0),
(121, 'Tarta de limón', 'Tarta con relleno de crema de limón y merengue', 320, 0),
(122, 'Pollo agridulce', 'Trozos de pollo salteados con salsa agridulce y verduras', 400, 0),
(123, 'Pescado a la plancha', 'Filete de pescado cocinado a la plancha con limón y hierbas', 180, 0),
(124, 'Ratatouille', 'Guiso de verduras asadas como berenjena, calabacín y pimiento', 220, 0),
(125, 'Fideos chinos salteados', 'Fideos de arroz salteados con verduras y salsa de soja', 320, 0),
(126, 'Gallo pinto', 'Plato tradicional de arroz y frijoles', 300, 0),
(127, 'Nacatamal', 'Tamal de masa relleno de carne de cerdo y verduras', 400, 0),
(128, 'Vigorón', 'Plato de yuca hervida, chicharrón y ensalada de repollo', 350, 0),
(129, 'Indio viejo', 'Estofado de carne de res o cerdo con maíz molido', 380, 0),
(130, 'Baho', 'Plato de carne de res o cerdo, yuca y plátano cocidos al vapor', 450, 0),
(131, 'Vaho', 'Variante del Baho con carne de res ahumada', 480, 0),
(132, 'Sopa de mondongo', 'Sopa hecha a base de callos de res con verduras', 320, 0),
(133, 'Sopa de queso', 'Sopa cremosa de queso con tortilla de maíz', 280, 0),
(134, 'Guiso de güirila', 'Guiso con masa de maíz, cerdo y especias', 350, 0),
(135, 'Rondón', 'Sopa de mariscos y pescado con leche de coco', 400, 0),
(136, 'Quesillo', 'Queso enrollado en tortilla de maíz con cebolla y crema', 250, 0),
(137, 'Atol de elote', 'Bebida caliente de maíz dulce', 200, 0),
(138, 'Tiste', 'Bebida a base de maíz fermentado', 180, 0),
(139, 'Sopa de albóndigas', 'Sopa con albóndigas de carne y verduras', 320, 0),
(140, 'Cajetas', 'Dulce de leche espeso y suave', 300, 0),
(141, 'Roscas', 'Panecillos dulces en forma de rosquilla', 250, 0),
(142, 'Caballo bayo', 'Plato de carne de res en salsa con plátano frito', 380, 0),
(143, 'Chicha de maíz', 'Bebida fermentada de maíz', 150, 0),
(144, 'Sopa de res', 'Sopa de carne de res con vegetales y especias', 280, 0),
(145, 'Chancho con yuca', 'Cerdo frito con yuca hervida', 400, 0),
(146, 'Güirila', 'Tortilla gruesa de masa de maíz tierno', 180, 0),
(147, 'Guiso de garrobo', 'Estofado de carne de iguana con vegetales y especias', 320, 0),
(148, 'Vigorón', 'Plato de yuca hervida, chicharrón y ensalada de repollo', 350, 0),
(149, 'Raspado de cacao', 'Bebida refrescante a base de cacao rallado y hielo', 200, 0),
(150, 'Tajadas de plátano', 'Plátano verde frito en rodajas', 280, 0),
(151, 'Sopa de queso con chorizo', 'Sopa cremosa de queso con chorizo', 320, 0),
(152, 'Chicharrón de pescado', 'Filete de pescado frito hasta quedar crujiente', 300, 0),
(153, 'Buñuelos de yuca', 'Bolitas fritas hechas de masa de yuca', 250, 0),
(154, 'Tostones', 'Plátano verde aplastado y frito', 220, 0),
(155, 'Sopa de albóndigas de pescado', 'Sopa con albóndigas de pescado y vegetales', 350, 0),
(156, 'Arroz aguado', 'Arroz cocinado en caldo con pollo y verduras', 400, 0),
(157, 'Sopa de frijoles', 'Sopa espesa hecha de frijoles con especias y cerdo', 320, 0),
(158, 'Sopa de pescado', 'Sopa de pescado con vegetales y especias', 280, 0),
(159, 'Guiso de chiltoma', 'Guiso con chiltoma (pimiento) relleno de carne molida', 380, 0),
(160, 'Guiso de pipián', 'Guiso espeso de semillas de calabaza con carne', 350, 0),
(161, 'Pinolillo', 'Bebida caliente a base de maíz y cacao', 200, 0),
(162, 'Tamales nicas', 'Tamales de masa de maíz rellenos de carne y envueltos en hojas de plátano', 400, 0),
(163, 'Bistec encebollado', 'Bistec de res salteado con cebolla', 320, 0),
(164, 'Camarones a la diabla', 'Camarones picantes en salsa de tomate', 280, 0),
(165, 'Nica punch', 'Bebida refrescante con frutas tropicales y jugos', 180, 0),
(166, 'Batido de proteínas', 'Batido hecho con proteína en polvo, leche y frutas', 300, 0),
(167, 'Pechuga de pollo a la parrilla', 'Pechuga de pollo a la parrilla con especias y ensalada', 350, 0),
(168, 'Salmón al horno', 'Filete de salmón horneado con limón y hierbas', 400, 0),
(169, 'Huevos revueltos con vegetales', 'Huevos revueltos con espinacas, pimientos y champiñones', 320, 0),
(170, 'Ensalada de atún', 'Ensalada de atún con aguacate, tomate y espinacas', 280, 0),
(171, 'Tacos de carne asada', 'Tacos con carne asada, guacamole y salsa', 380, 0),
(172, 'Batido de plátano y avena', 'Batido de plátano, avena, leche y mantequilla de maní', 320, 0),
(173, 'Quinoa con pollo y verduras', 'Quinoa con trozos de pollo a la plancha y vegetales', 400, 0),
(174, 'Carne magra a la plancha', 'Filete de carne magra a la plancha con vegetales salteados', 350, 0),
(175, 'Ensalada de quinoa y lentejas', 'Ensalada de quinoa, lentejas, vegetales y aderezo ligero', 320, 0),
(176, 'Omelette de claras de huevo', 'Omelette hecho solo con claras de huevo y vegetales', 250, 0),
(177, 'Wrap de pollo y aguacate', 'Wrap con pollo a la plancha, aguacate y verduras', 380, 0),
(178, 'Ternera al horno con patatas', 'Ternera al horno con patatas asadas y vegetales', 400, 0),
(179, 'Batido de proteínas con avena', 'Batido de proteínas, avena, leche y plátano', 350, 0),
(180, 'Pavo a la plancha con arroz integral', 'Pechuga de pavo a la plancha con arroz integral y vegetales', 320, 0),
(181, 'Ensalada de pollo a la parrilla', 'Ensalada con pollo a la parrilla, queso feta y nueces', 280, 0),
(182, 'Bowl de salmón y quinoa', 'Bowl con salmón al horno, quinoa y vegetales', 380, 0),
(183, 'Smoothie de proteínas y frutas', 'Smoothie hecho con proteína en polvo, frutas y leche', 300, 0),
(184, 'Pollo a la parrilla con batata', 'Muslos de pollo a la parrilla con batata asada y ensalada', 350, 0),
(185, 'Ensalada de garbanzos', 'Ensalada con garbanzos, tomate, pepino y vinagreta', 280, 0),
(186, 'Ensalada César', 'Lechuga romana, pollo a la parrilla, croutones y aderezo César', 250, 0),
(187, 'Ensalada Caprese', 'Tomates, mozzarella fresca, hojas de albahaca y aceite de oliva', 180, 0),
(188, 'Ensalada de quinoa', 'Quinoa, espinacas, aguacate, tomates cherry y vinagreta de limón', 320, 0),
(189, 'Ensalada de pollo y mango', 'Pollo a la parrilla, mango, rúcula, nueces y aderezo de miel y mostaza', 280, 0),
(190, 'Ensalada de atún', 'Atún enlatado, lechuga, tomates, pepinos y aderezo ligero', 220, 0),
(191, 'Ensalada griega', 'Pepinos, tomates, aceitunas, queso feta y aderezo de yogur', 300, 0),
(192, 'Ensalada de espinacas y fresas', 'Espinacas, fresas, queso de cabra, nueces y vinagreta balsámica', 240, 0),
(193, 'Ensalada de garbanzos', 'Garbanzos, pimientos, cebolla roja, perejil y aderezo de limón', 280, 0),
(194, 'Ensalada de col rizada', 'Col rizada, manzana, zanahorias, pasas y vinagreta de sidra de manzana', 260, 0),
(195, 'Ensalada de aguacate y tomate', 'Aguacate, tomates, cebolla roja, cilantro y aderezo de limón', 230, 0),
(196, 'Palitos de zanahoria con hummus', 'Zanahorias crujientes acompañadas de hummus casero', 120, 0),
(197, 'Yogur griego con frutas y nueces', 'Yogur griego mezclado con frutas frescas y nueces', 150, 0),
(198, 'Rodajas de manzana con mantequilla de maní', 'Manzanas cortadas en rodajas con mantequilla de maní', 180, 0),
(199, 'Hummus con palitos de apio', 'Hummus casero servido con palitos de apio', 140, 0),
(200, 'Tostadas de aguacate', 'Tostadas de pan integral con aguacate, tomate y sal marina', 160, 0),
(201, 'Barritas de granola caseras', 'Barritas de granola hechas con avena, miel y frutos secos', 200, 0),
(202, 'Rollitos de jamón y queso', 'Rollitos de jamón y queso envueltos en hojas de lechuga', 180, 0),
(203, 'Galletas de avena y plátano', 'Galletas saludables hechas con avena, plátano y canela', 150, 0),
(204, 'Rodajas de pepino con yogur griego', 'Rodajas de pepino acompañadas de yogur griego y especias', 120, 0),
(205, 'Batido verde de espinacas', 'Espinacas, plátano, piña, leche de almendras y semillas de chía', 150, 0),
(206, 'Batido verde de kale', 'Kale, mango, aguacate, jugo de limón y agua de coco', 180, 0),
(207, 'Batido verde de espinacas y manzana', 'Espinacas, manzana verde, pepino, jugo de limón y agua de coco', 140, 0),
(208, 'Batido verde de espinacas y kiwi', 'Espinacas, kiwi, plátano, jugo de lima y leche de almendras', 160, 0),
(209, 'Batido verde de pepino y menta', 'Pepino, espinacas, menta, piña, jugo de limón y agua de coco', 130, 0),
(210, 'Batido verde de espinacas y fresas', 'Espinacas, fresas, plátano, leche de almendras y semillas de chía', 170, 0),
(211, 'Batido verde de espinacas y aguacate', 'Espinacas, aguacate, manzana verde, jugo de limón y agua de coco', 190, 0),
(212, 'Batido verde de espinacas y piña', 'Espinacas, piña, plátano, jugo de limón y leche de almendras', 160, 0),
(213, 'Batido verde de kale y mango', 'Kale, mango, plátano, leche de coco y semillas de chía', 200, 0),
(214, 'Batido verde de espinacas y pera', 'Espinacas, pera, piña, jugo de limón y agua de coco', 150, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_recetas`
--
ALTER TABLE `tbl_recetas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_recetas`
--
ALTER TABLE `tbl_recetas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=215;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
