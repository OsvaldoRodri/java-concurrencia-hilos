# Programación Concurrente en Java — Hilos y Sincronización 🧵

Dos ejercicios fundamentales de concurrencia en Java que demuestran race conditions y su solución.

## Ejercicios

### 1. `ConteoConcurrente.java` — Race condition y `synchronized`

**Problema:** 5 hilos incrementan un contador compartido 100,000 veces cada uno.
Sin sincronización, el resultado esperado (500,000) nunca se obtiene porque las operaciones de lectura-modificación-escritura se solapan entre hilos.

**Solución:** el modificador `synchronized` en el método `incrementar()` garantiza que solo un hilo a la vez modifica el contador.

```java
static synchronized void incrementar() {
    contador++;  // ahora es thread-safe
}
```

### 2. `SumaParalela.java` — División de trabajo con `join()`

**Problema:** sumar un arreglo de 20 elementos usando 4 hilos en paralelo.

**Estrategia:**
- Dividir el arreglo en 4 rangos iguales
- Cada hilo suma su rango de forma independiente (no hay escritura compartida)
- El hilo principal espera con `join()` y combina las sumas parciales

```
Arreglo [1..20] dividido entre 4 hilos:
Hilo 0: [1..5]   → suma = 15
Hilo 1: [6..10]  → suma = 40
Hilo 2: [11..15] → suma = 65
Hilo 3: [16..20] → suma = 90
Total = 210 ✓
```

## Conceptos cubiertos

| Concepto | Ejercicio |
|----------|-----------|
| `Thread` (extends) | Ambos |
| `.start()` / `.join()` | Ambos |
| Race condition | ConteoConcurrente |
| `synchronized` | ConteoConcurrente |
| División de trabajo | SumaParalela |
| Suma parcial sin sincronización | SumaParalela |

## Estructura

```
src/main/java/dev/osvaldo/hilos/
├── ConteoConcurrente.java
└── SumaParalela.java
```

## Cómo correrlo

```bash
git clone https://github.com/OsvaldoRodri/java-concurrencia-hilos.git
cd java-concurrencia-hilos

# Ejercicio 1
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.hilos.ConteoConcurrente"

# Ejercicio 2
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.hilos.SumaParalela"
```

## Tecnologías

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.x-red?logo=apache-maven)

---

*Parte del portafolio de [Osvaldo Rodríguez](https://github.com/OsvaldoRodri)*
