# Cafetería – Gestión de descuentos (EA4 Proyecto integrador- Luissana Garcia) 

Aplicación Java (Swing + JDBC, sin frameworks externos) que calcula el descuento de un pedido
de cafetería y guarda el resultado en MariaDB.

## Estructura

```
src/cafeteria/
  modelo/        Producto (abstracta), Bebida, Comida, Pedido
  descuento/     ReglaDescuento (interfaz), DescuentoPorMonto, SinDescuento, GestorDescuentos
  funcional/     CalculadorDescuento (@FunctionalInterface)
  persistencia/  ConexionBD (JDBC, try-with-resources)
  vista/         VentanaPrincipal (JFrame), PanelDescuentos (JPanel)
  Main.java            punto de entrada (aquí vive la expresión lambda)
  PruebasDescuento.java pruebas por consola
scripts/cafeteria.sql   base de datos y tablas para MariaDB
docs/                   capturas de ejecución y salida de pruebas
```



## Regla de descuento

Si el total es mayor que $10 se aplica 15 %; en caso contrario, 0 %.
Para agregar otra regla: crear una clase que implemente `ReglaDescuento` y añadirla a la lista en `Main`.

este trabajo es hecho por Luissana Garcia
