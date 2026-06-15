package dev.osvaldo.hilos;

/**
 * Demuestra el problema de race condition y su solucion con synchronized.
 *
 * PROBLEMA: Si varios hilos incrementan una variable compartida sin
 * sincronizacion, el resultado final es incorrecto porque las operaciones
 * se solapan (read-modify-write no es atomica).
 *
 * SOLUCION: El modificador synchronized garantiza que solo un hilo
 * a la vez ejecute el metodo incrementar(), eliminando la race condition.
 */
public class ConteoConcurrente {

    static final int NUM_HILOS   = 5;
    static final int INCREMENTOS = 100_000;

    static int contador = 0;

    /** Solo un hilo a la vez puede ejecutar este metodo */
    static synchronized void incrementar() {
        contador++;
    }

    static class Trabajador extends Thread {
        private final int id;

        Trabajador(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Hilo " + id + " iniciando " + INCREMENTOS + " incrementos");
            for (int i = 0; i < INCREMENTOS; i++) {
                incrementar();
            }
            System.out.println("Hilo " + id + " terminado");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        contador = 0;
        Trabajador[] hilos = new Trabajador[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new Trabajador(i);
            hilos[i].start();
        }

        for (Trabajador h : hilos) {
            h.join();   // espera a que cada hilo termine
        }

        int esperado = NUM_HILOS * INCREMENTOS;
        System.out.println("\nEsperado : " + esperado);
        System.out.println("Obtenido : " + contador);
        System.out.println(contador == esperado
            ? "CORRECTO: synchronized elimino la race condition."
            : "INCORRECTO: ocurrio race condition.");
    }
}
