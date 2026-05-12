DELETE FROM producto_categoria;
DELETE FROM productos;
DELETE FROM categorias;
DELETE FROM marcas;
DELETE FROM categorias;

INSERT INTO categorias (nombre, descripcion, imagen) 
VALUES
    (  
        'Portátiles',
        'Consolas portátiles de todo tipo',
        '/imagenes/categorias/consolas-portatiles.jpg'
    ),
    (
        'Realidad Virtual',
        'Gafas de realidad virtual para disfrutar de tus juegos',
        '/imagenes/categorias/consolas-realidadvirtual.jpg'
    ),

    (
        'Retro',
        'Consolas retro para los amantes de lo clásico',
        '/imagenes/categorias/consolas-retro.jpg'
    ),

    (
        'Sobre Mesa',
        'Las consolas más potentes para jugar en casa',
        '/imagenes/categorias/consolas-sobremesa.jpg'
    ),

    (
        'Híbridas',
        'Consolas que combinan portátil y sobremesa',
        NULL
    ),

    (
        'Arcade',
        'Máquinas y sistemas para amantes de los recreativos',
        '/imagenes/categorias/consolas-arcade.jpg'
    ),

    (
        'Cloud',
        'Juega en streaming sin necesidad de hardware potente',
        '/imagenes/categorias/consolas-cloud.jpg'
    ),

    (
        'Experimentales',
        'Tecnologías innovadoras y conceptos fuera de lo común',
        '/imagenes/categorias/consolas-experimentales.jpg'
    ),

    (
        'Canceladas',
        'Aquellos proyectos de consola que no llegaron a ver la luz',
        '/imagenes/categorias/consolas-canceladas.jpg'
    );

INSERT INTO marcas (id, nombre) VALUES
(1, 'Nintendo'),
(2, 'Valve'),
(3, 'Sony'),
(4, 'Asus'),
(5, 'Microsoft'),
(6, 'Google'),
(7, 'Logitech'),
(8, 'Panic Inc.'),
(9, 'Analogue'),
(10, 'Samchung');

INSERT INTO productos (
    codigo, 
    nombre, 
    marca_id, 
    descripcion,
    imagen, 
    precio, 
    descuento,
    stock
)
VALUES
    (
        '8400000000017',
        'Nintendo Switch OLED',
        1,
        'Consola híbrida de última generación con pantalla OLED de gran calidad. Permite jugar tanto en modo portátil como en televisor. Su catálogo incluye juegos exclusivos, experiencias familiares y títulos competitivos. Es una de las consolas más versátiles del mercado actual.',
        'https://thumb.pccomponentes.com/w-530-530/articles/43/432881/1680-nintendo-switch-oled-blanca.jpg',
        349.99,
        10,
        50
    ),

    (
        '8400000000024',
        'Steam Deck',
        2,
        'Dispositivo portátil para ejecutar juegos de PC en cualquier lugar. Compatible con Steam, mods y una enorme biblioteca de juegos. Su potencia permite experiencias AAA en formato portátil con controles integrados y sistema operativo optimizado para gaming.',
        'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D640/https://d2e6ccujb3mkqf.cloudfront.net/f6b19d2e-b062-4836-acb5-ddaf7e420f1a-1_a376146e-f9da-4a2c-9119-029361d224d7.jpg',
        499.99,
        5,
        50
    ),

    (
        '8400000000031',
        'PlayStation VR2',
        3,
        'Sistema de realidad virtual de nueva generación con seguimiento ocular, mandos hápticos y alta resolución. Ofrece experiencias inmersivas únicas en juegos compatibles de PlayStation 5. Además, incorpora materiales resistentes, una interfaz intuitiva y opciones de conectividad actuales para facilitar partidas rápidas o sesiones largas, tanto en solitario como en compañía.',
        'https://thumb.pccomponentes.com/w-530-530/articles/1100/11009771/1766-gafas-de-realidad-virtual-sony-playstation-vr2-oled-2000-x-2040-pixeles-110-120-hz-552b617b-4eb7-4785-a6cf-38c281a0bd0d.jpg',
        599.99,
        15,
        50
    ),

    (
        '8400000000048',
        'Oculus Quest 2',
        3,
        'Gafas de realidad virtual autónomas sin necesidad de PC o consola. Permite jugar, ver contenido multimedia y experiencias sociales VR con gran libertad de movimiento. Además, su configuración es sencilla, ofrece controles precisos y mantiene un rendimiento estable durante sesiones prolongadas, por lo que resulta una alternativa muy recomendable para usuarios nuevos y entusiastas de la realidad virtual.',
        'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D640/https://d2e6ccujb3mkqf.cloudfront.net/3aa9e3d2-c468-4462-9e33-3b56ae1a04b2-2_f91d1bd0-1c2a-4d69-ba7f-a6df89a4674e.jpg',
        399.99,
        0,
        50
    ),

    (
        '8400000000055',
        'Game Boy Color',
        1,
        'Consola portátil clásica de finales de los 90 que marcó una generación. Su catálogo incluye juegos icónicos y es muy valorada por coleccionistas. Además de su importancia histórica, sigue siendo una pieza ideal para disfrutar de títulos retro en formato original, con una construcción compacta y una experiencia nostálgica que atrae tanto a jugadores veteranos como a nuevos aficionados.',
        'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D640/https://d2e6ccujb3mkqf.cloudfront.net/a7a753c7-98d6-403b-9465-b33d76d074ed-1_57fb2113-14e6-4f78-85ff-ebad0bd9bfeb.jpg',
        119.90,
        0,
        50
    ),

    (
        '8400000000062',
        'Super Nintendo Classic',
        1,
        'Edición mini de la clásica consola SNES con juegos precargados. Ideal para nostálgicos y coleccionistas que quieren revivir títulos legendarios. Incluye una selección cuidada de obras históricas, un diseño fiel al modelo original y un uso muy sencillo para conectar y jugar en minutos, por lo que se convierte en una opción excelente para compartir partidas clásicas en casa con familiares y amigos.',
        'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D640/https://d2e6ccujb3mkqf.cloudfront.net/7199890c-b66c-49b7-b5f0-45ad0f1726b3-1_eed97fe9-2807-421a-8a02-4a31f889b4fc.jpg',
        89.95,
        10,
        50
    ),

    (
        '8400000000079',
        'PlayStation 5',
        2,
        'Consola de nueva generación con SSD ultrarrápido, gráficos 4K, trazado de rayos y una experiencia de juego inmersiva. Incluye exclusivos de alto nivel. También destaca por su interfaz moderna, tiempos de carga muy reducidos y compatibilidad con servicios online que amplían el catálogo disponible, ofreciendo una plataforma sólida para quienes buscan rendimiento, calidad visual y entretenimiento continuo.',
        'https://thumb.pccomponentes.com/w-530-530/articles/1099/10990567/1987-sony-playstation-5-digital-edition-sobremesa-amd-ryzen-ssd-825gb-2-mandos-dualsense-blanca.jpg',
        549.99,
        12,
        50
    ),

    (
        '8400000000086',
        'Xbox Series X',
        5,
        'Consola potente enfocada en rendimiento 4K y servicio Game Pass. Ofrece retrocompatibilidad y tiempos de carga mínimos. Gracias a su hardware equilibrado y a su ecosistema digital, permite jugar títulos actuales y clásicos con gran fluidez, además de facilitar el acceso a una biblioteca amplia mediante suscripción, lo que la convierte en una opción muy completa para todo tipo de jugadores.',
        Null,
        529.99,
        8,
        50
    ),

    (
        '8400000000093',
        'Nintendo Switch Lite',
        1,
        'Versión portátil de Nintendo Switch diseñada exclusivamente para juego handheld. Compacta, ligera y con gran catálogo. Su formato ergonómico la hace ideal para jugar fuera de casa, y su autonomía junto con la variedad de títulos disponibles permite sesiones cómodas y entretenidas durante viajes, descansos o uso diario, siendo una alternativa práctica para quienes priorizan movilidad y sencillez.',
        'https://thumb.pccomponentes.com/w-530-530/articles/39/392021/1171-nintendo-switch-lite-azul.jpg',
        229.99,
        5,
        50
    ),

    (
        '8400000000109',
        'ROG Ally',
        4,
        'Consola portátil tipo PC gaming con Windows, ideal para juegos de Steam, Game Pass y más plataformas. Combina potencia y versatilidad para ejecutar títulos exigentes en movilidad, con controles integrados y opciones de personalización que mejoran la experiencia según el estilo de cada usuario, por lo que es una propuesta muy atractiva para quienes quieren rendimiento de PC en formato compacto.',
        'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D640/https://d2e6ccujb3mkqf.cloudfront.net/070ad022-7f1a-4470-83c8-7e547179a448-1_c4e60c3b-5aef-45b1-b257-607220056f21.jpg',
        699.99,
        7   ,
        50
    ),

    (
        '8400000000116',
        'Neo Geo Mini',
        2,
        'Consola retro con juegos arcade clásicos incluidos. Perfecta para coleccionistas y amantes de los recreativos. Su diseño inspirado en máquinas tradicionales y su selección de títulos emblemáticos permiten revivir la esencia de los salones arcade en casa, con una experiencia directa y entretenida que también funciona como pieza decorativa para espacios gaming y colecciones temáticas.',
        Null,
        149.99,
        0,
        50
    ),

    (
        '8400000000123',
        'Arcade Fight Stick',
        1,
        'Mando arcade profesional para juegos de lucha. Diseñado para máxima precisión en juegos competitivos. Sus componentes están orientados a respuestas rápidas y movimientos consistentes, lo que facilita combos complejos y control detallado en partidas exigentes. Es una opción excelente tanto para entrenar de forma seria como para disfrutar torneos locales con una sensación cercana a la de recreativa.',
        'https://thumb.pccomponentes.com/w-530-530/articles/1081/10810963/182-hori-fighting-stick-mini-blanco-ps5-ps4-pc.jpg',
        79.99,
        0,
        50
    ),

    (
        '8400000000130',
        'Google Stadia Controller',
        6,
        'Controlador diseñado para juegos en la nube. Permite conexión directa a servidores sin consola física. Su propuesta se centra en reducir latencia y simplificar el acceso a plataformas de streaming, ofreciendo una experiencia cómoda para sesiones casuales o continuas. Gracias a su ergonomía y conectividad, resulta útil para jugadores que priorizan practicidad y acceso inmediato al catálogo online.',
        'https://thumb.pccomponentes.com/w-530-530/articles/1101/11015918/1910-mando-inalambrico-8bitdo-ultimate-2c-azul-bluetooth-pc-android-bateria-32-h.jpg',
        79.99,
        0,
        50
    ),

    (
        '8400000000147',
        'Logitech G Cloud',
        7,
        'Dispositivo portátil enfocado en cloud gaming con gran autonomía y pantalla optimizada para streaming de juegos. Está pensado para ofrecer comodidad durante largas sesiones gracias a su formato ligero y controles integrados, permitiendo disfrutar de servicios en la nube con estabilidad y buena calidad visual. Es ideal para quienes desean jugar en movilidad sin depender de un hardware local potente.',
        'https://img.pccomponentes.com/articles/1086/10868145/1820-logitech-g-cloud-consola-portatil-para-juegos-en-la-nube-foto.jpg',
        349.99,
        5,
        50
    ),

    (
        '8400000000154',
        'Playdate',
        8,
        'Consola experimental con diseño único y manivela lateral. Ofrece juegos indie creativos y diferentes. Su enfoque apuesta por mecánicas originales y propuestas artísticas poco convencionales, convirtiéndola en una plataforma muy atractiva para descubrir experiencias nuevas. Es perfecta para jugadores curiosos que valoran la innovación, el diseño distintivo y un catálogo alternativo frente a opciones tradicionales.',
        'https://assetsio.gnwcdn.com/playdate_consola.png?width=690&height=431&fit=crop&quality=85&format=jpg&auto=webp',
        219.99,
        0,
        50
    ),

    (
        '8400000000161',
        'Analogue Pocket',
        9,
        'Consola premium para cartuchos retro con pantalla de alta calidad. Compatible con múltiples sistemas clásicos. Destaca por su acabado cuidado, fidelidad de imagen y soporte para colecciones físicas, permitiendo disfrutar juegos históricos con una presentación moderna y cómoda. Es una opción excelente para aficionados al retro que buscan preservar su biblioteca y jugar con gran nitidez y precisión.',
        'https://tokyogamestory.com/183089-large_default/analogue-pocket-console-black-brand-new-sealedneuve-scellee-free-region-.jpg',
        299.99,
        10,
        50
    ),

    (
        '8400000000178',
        'PS Portal',
        2,
        'Dispositivo de juego remoto para PlayStation 5. Permite jugar en streaming desde cualquier habitación. Su pantalla integrada y controles inspirados en el mando oficial facilitan continuar partidas sin ocupar el televisor principal, mejorando la flexibilidad en casa. Es especialmente útil para usuarios que quieren mantener su progreso diario con comodidad, buena respuesta y una configuración rápida.',
        'https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_149107710?x=536&y=402&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=536&ey=402&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=536&cdy=402',
        219.99,
        5,
        50
    ),

    (
        '8400000000185',
        'Virtual Boy',
        1,
        'Consola experimental histórica con visión estereoscópica en rojo. Producto de colección. Representa una etapa singular de la evolución del videojuego por su propuesta visual diferente y su carácter pionero en tecnología tridimensional doméstica. Hoy es una pieza muy apreciada por coleccionistas y entusiastas que buscan conservar hardware emblemático con valor histórico y cultural dentro del sector gaming.',
        Null,
        79.99,
        0,
        50
    ),

    (
        '8400000000192',
        'MINILOONG Pocket',
        3,
        'Consola portátil enfocada en emulación de sistemas retro con gran comunidad de desarrollo. Gracias a su ecosistema activo, dispone de mejoras constantes, configuraciones personalizables y compatibilidad con múltiples plataformas clásicas. Su formato compacto la hace ideal para jugar en cualquier lugar, siendo una alternativa interesante para quienes disfrutan explorando catálogos antiguos con comodidad y flexibilidad.',
        'https://gogamegeek.com/cdn/shop/files/d97f18c1-e652-46b5-920c-04b8ac642717.jpg?v=1776219474&width=1220',
        99.99,
        0,
        50
    ),

    (
        '8400000000208',
        'Cloud Box One',
        4,
        'Sistema experimental de cloud gaming que permite jugar sin hardware potente mediante streaming. Su planteamiento prioriza el acceso inmediato a juegos a través de internet, reduciendo requisitos locales y simplificando la experiencia para usuarios de distintos perfiles. Es una opción llamativa para probar nuevas formas de consumo digital, con un enfoque práctico en conectividad y disponibilidad de contenidos.',
        Null,
        199.99,
        0,
        50
    );

INSERT INTO producto_categoria (producto_id, categoria_id) VALUES

-- Producto con una categoria
(2, 1), -- Steam Deck → Portátiles

-- Producto con varias categorias
(1, 5), -- Switch → Híbridas
(1, 1), --  → Portátiles
(7, 4), -- PS5 → Sobremesa
(7, 7), --  → Cloud

-- Categoria con un producto
(11, 6), -- Neo Geo Mini → Arcade

-- Categoria con varios productos
(5, 3), -- Game Boy → Retro
(6, 3), -- SNES → Retro
(3, 2), -- VR2 → Realidad Virtual
(4, 2); -- Oculus → Realidad Virtual

ALTER TABLE marcas ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM marcas);

-- Creación de roles
INSERT INTO roles (id, descripcion) VALUES ('USER', 'Usuario normal');
INSERT INTO roles (id, descripcion) VALUES ('ADMIN', 'Administrador');

-- Borramos por si acaso para evitar duplicados en pruebas
DELETE FROM usuarios_roles;
DELETE FROM usuarios;

-- Admin: admin@tienda.com / Password
INSERT INTO usuarios (id, nombre, apellidos, email, password, fecha_registro)
VALUES (1, 'Admin', 'Sistemas', 'admin@tienda.com', '$2a$12$U8UYcznv9gB3E.6Yj8Bvhulwmpd3A.P4tqBpGyQzmkgFhUDuhFp7C', CURRENT_TIMESTAMP);

-- User: user@tienda.com / Password
INSERT INTO usuarios (id, nombre, apellidos, email, password, fecha_registro)
VALUES (2, 'User', 'Normal', 'user@tienda.com', '$2a$12$U8UYcznv9gB3E.6Yj8Bvhulwmpd3A.P4tqBpGyQzmkgFhUDuhFp7C', CURRENT_TIMESTAMP);

-- Asignación de roles
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 'USER');
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 'ADMIN');
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 'USER');

-- Reiniciar secuencias
ALTER TABLE usuarios ALTER COLUMN id RESTART WITH 3;