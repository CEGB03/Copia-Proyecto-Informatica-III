# Funcionamiento General
Se debe ejecutar el archivo principal Main.java, el cual instancia la clase Menu.java, en donde se le hará la siguiente pregunta:
>Do you want to enter the Stock Control System? Y/N

La cual es la forma de iniciar el Sistema de Control de Inventario, el cual al responder afirmativamente dara las siguientes opciones:
>1. Add Product
>2. Search Product
>3. Delete Product
>4. Print Inventary

Las cuales son Implementaciones que se desarrollan, detallan y explican mas adelante

Una vez realizada la operación seleccionada, aparecerá el siguiente mensaje de pregunta:
>Do you want to continue in the Stock Control System? Y/N

Que al responder afirmativamente se volvera a las opciones de operacion. Y de lo contrario de responder negativamente, finalizara el programa de Sistema de Control de Inventario
# Implementación de Funcionalidades
## Agregar Producto
Se solicita que se ingrese el nombre del producto y luego la cantidad numérica de stock, al querer ingresar un producto que ya se encuentra ingresado se muestra un mensaje de error notificando que se trata de un producto duplicado, pero igualmente se suma la cantidad de stock que se ingreso.
## Eliminar Producto
Se solicita que se ingrese el nombre del producto a eliminar. Si no se encuentra ningún producto con el nombre ingresado se muestra un mensaje de error notificando eso, de lo contrario si se encuentra, se elimina de las estructuras y se notifica mediante un mensaje en pantalla.
## Buscar Producto
Se solicita que se ingrese el nombre del producto a buscar, si es encontrado se muestra el nombre y su respectivo stock, y se pregunta si quiere modificar el stock, si es asi, se le pide la cantidad que desea modificar del stock. De lo contrario se mostrara un mensaje de que el producto que busca no se encuentra en el inventario.
## Mostrar Inventario
Se imprimirá todo el inventario primero utilizando el árbol de dos formas distintas, y luego se imprime usando la lista de la siguiente forma [Product, Stock]
### Impresiones de Ejemplo:
#### Impresión del Árbol 1:
[![Tree1.png](https://i.postimg.cc/vH1BkTxv/Tree1.png)](https://postimg.cc/0bsPJ8f6)

#### Impresión del Árbol 2:

[![Tree2.png](https://i.postimg.cc/h416n1TD/Tree2.png)](https://postimg.cc/QVV0k5fw)

#### Impresión de la Lista:

[![List.png](https://i.postimg.cc/Y0BHPr5d/List.png)](https://postimg.cc/N9DS9cfX)