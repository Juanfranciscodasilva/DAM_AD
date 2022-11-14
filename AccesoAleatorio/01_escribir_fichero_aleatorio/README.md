# Escribir fichero aleatorio

Crea un programa que inserta datos de empleados en un fichero aleatorio (empleados.dat).

Datos a insertar: apellido, departamento y salario. Se obtienen de varios arrays que se llenan en el código directamente. Los datos se van introduciendo de forma secuencial (no es necesario utilizar seek()). El archivo se abre en modo lectura y escritura (rw).

Por cada empleado se insertará un identificador (mayor que 0) que coincidirá con el índice + 1 con el que se recorren los arrays. Por ejemplo, id=4, índice=3+1

El registro de cada empleado es de 36 bytes:
- Identificador, entero de 4 bytes
- Apellido, cadena de 10 caracteres, 20 bytes (2 por cada, al ser caracteres UNICODE)
- Departamento, entero de 4 bytes
- Salario, doble de 8 bytes

