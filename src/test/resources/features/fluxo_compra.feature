# language: pt
Funcionalidade: Fluxo completo de compra no SauceDemo

  Esquema do Cenario: Compra de produtos
    Dado que acesso o site "https://www.saucedemo.com"
    Quando seleciono <produto>
    E clico no botao Add to cart
    Entao visualiza a lista de compras
    Quando preencho "Teste" , "Usuario" e "12345"
    E aperto botao Continue
    Entao olho se os dados estao certos
    E aperto o botao Finish

    Exemplos:
      | produto                    |
      | Sauce Labs Backpack        |
      | Sauce Labs Bolt T-Shirt    |
      | Sauce Labs Bike Light      |
      | Sauce Labs Fleece Jacket   |

