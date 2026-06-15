package dev.osvaldo.hilos;

/**
 * Suma paralela de un arreglo usando division de trabajo entre hilos.
 *
 * El arreglo se divide en rangos iguales. Cada hilo suma su rango
 * de forma independiente (sin sincronizacion porque cada hilo
 * accede a indices distintos — no hay datos compartidos en escritura).
 * Al final el hilo principal combina las sumas parciales con join().
 */
public class SumaParalela {

    static final int NUM_HILOS = 4;
    static final int TAM       = 20;
    static int[]     arreglo   = new int[TAM];

    static class SumadorRango extends Thread {
        private final int id;
        private final int inicio;
        private final int fin;
        private long sumaParcial = 0;

        SumadorRango(int id, int inicio, int fin) {
            this.id     = id;
            this.inicio = inicio;
            this.fin    = fin;
        }

        @Override
        public void run() {
            System.out.printf("Hilo %d procesando indices [%d..%d]%n", id, inicio, fin - 1);
            for (int i = inicio; i < fin; i++) {
                sumaParcial += arreglo[i];
            }
            System.out.printf("Hilo %d suma parcial = %d%n", id, sumaParcial);
        }

        long getSumaParcial() {
            return sumaParcial;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Llenar arreglo con valores 1..20
        for (int i = 0; i < TAM; i++) {
            arreglo[i] = i + 1;
        }

        int trozo = TAM / NUM_HILOS;
        SumadorRango[] hilos = new SumadorRango[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            int inicio = i * trozo;
            int fin    = (i == NUM_HILOS - 1) ? TAM : (i + 1) * trozo;
            hilos[i]   = new SumadorRango(i, inicio, fin);
            hilos[i].start();
        }

        long total = 0;
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i].join();
            total += hilos[i].getSumaParcial();
        }

        System.out.println("\nSuma total (1.." + TAM + ") = " + total);
        System.out.println("Esperado: " + (TAM * (TAM + 1) / 2));
    }
}
