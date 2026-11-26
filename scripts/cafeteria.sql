-- Script para MariaDB (ejecutar en DBeaver sobre la conexión local de MariaDB).
-- Crea la base de datos "cafeteria", sus tablas y algunos productos de ejemplo.

CREATE DATABASE IF NOT EXISTS cafeteria
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE cafeteria;

CREATE TABLE IF NOT EXISTS productos (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(60)    NOT NULL UNIQUE,
    categoria ENUM('BEBIDA','COMIDA') NOT NULL,
    precio    DECIMAL(10,2)  NOT NULL
);

CREATE TABLE IF NOT EXISTS pedidos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    fecha       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total       DECIMAL(10,2)  NOT NULL,
    descuento   DECIMAL(10,2)  NOT NULL,
    total_final DECIMAL(10,2)  NOT NULL
);

INSERT IGNORE INTO productos (nombre, categoria, precio) VALUES
    ('Café americano',    'BEBIDA', 3.50),
    ('Capuchino',         'BEBIDA', 4.80),
    ('Jugo de naranja',   'BEBIDA', 3.20),
    ('Sándwich de pollo', 'COMIDA', 6.20),
    ('Croissant',         'COMIDA', 2.90);

-- Consultas de verificación
SELECT * FROM productos;
SELECT * FROM pedidos ORDER BY fecha DESC;
