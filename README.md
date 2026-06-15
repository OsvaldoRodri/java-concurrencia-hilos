# java-concurrencia-hilos

Dos ejercicios de programación concurrente en Java: race conditions con `synchronized` y suma paralela con división de trabajo.

## ConteoConcurrente

Cinco hilos incrementan un contador compartido 100,000 veces cada uno. Sin sincronización el resultado es incorrecto porque `contador++` no es atómica: es una lectura, una suma y una escritura que se pueden intercalar entre hilos. El modificador `synchronized` en el método garantiza que solo un hilo lo ejecute a la vez.

## SumaParalela

Suma un arreglo de 20 elementos con 4 hilos. El arreglo se divide en rangos iguales y cada hilo suma su rango de forma independiente. Como no hay escritura compartida no se necesita sincronización. El hilo principal espera con `join()` y combina las sumas parciales.

```
Arreglo [1..20]:
Hilo 0: [1..5]   suma = 15
Hilo 1: [6..10]  suma = 40
Hilo 2: [11..15] suma = 65
Hilo 3: [16..20] suma = 90
Total = 210
```

## Cómo correr

```bash
git clone https://github.com/OsvaldoRodri/java-concurrencia-hilos.git
cd java-concurrencia-hilos
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.hilos.ConteoConcurrente"
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.hilos.SumaParalela"
```
