# language: es

Característica: Validar funcionalidad del carrito de compras en SauceLabs

  @Test
  Esquema del escenario: Agregar productos al carrito de compras

    Dado estoy en la aplicación de SauceLabs
    Y valido que carguen correctamente los productos en la galería
    Cuando agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Entonces valido que el carrito de compra actualice correctamente

    Ejemplos:
      | PRODUCTO                | UNIDADES |
      | Sauce Labs Backpack     | 1        |
      | Sauce Labs Bolt T-Shirt | 1        |
      | Sauce Labs Bike Light   | 2        |


