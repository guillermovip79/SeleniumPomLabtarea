Feature: Agregar articulo al carrito
  Como cliente de Sauce Demo
  Quiero agregar un articulo al carrito
  Para poder comprarlo

  Background:
    Given que estoy en la pagina del producto "Grey jacket"

  Scenario: Verificar titulo y precio del producto
    Then el titulo del producto debe ser "Grey jacket"
    And el precio del producto debe ser "£55.00"

  Scenario: Agregar un articulo al carrito y verificarlo
    When agrego el producto al carrito
    Then el producto "Grey jacket" debe aparecer en el carrito
