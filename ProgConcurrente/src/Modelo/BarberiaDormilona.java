package Modelo;

import java.util.concurrent.Semaphore;

public class BarberiaDormilona {
    private Semaphore sillaBarbero;
    private Semaphore sillasEspera;
    private Semaphore barberoDespierto;

    public BarberiaDormilona(int nSillas) {
        sillaBarbero = new Semaphore(1);
        sillasEspera = new Semaphore(nSillas);
        barberoDespierto = new Semaphore(0);
    }

    public void cliente(int id) throws InterruptedException {
        if (sillaBarbero.tryAcquire()) {
            System.out.println("Cliente " + id + " despierta al barbero y se sienta en la silla del barbero.");
            int tiempoCorte = (int) (Math.random() * 5) + 1;
            Thread.sleep(tiempoCorte * 1000);
            System.out.println("Cliente " + id + " ha terminado su corte de pelo y se va.");
            sillaBarbero.release();
        } else {
            if (sillasEspera.tryAcquire()) {
                System.out.println("Cliente " + id + " se sienta en una silla de espera.");
                sillasEspera.release(); // Deja la silla de espera para el pr�ximo cliente
                sillaBarbero.acquire(); // Espera que la silla del barbero est� disponible
                System.out.println("Cliente " + id + " se sienta en la silla del barbero.");
                int tiempoCorte = (int) (Math.random() * 5) + 1;
                Thread.sleep(tiempoCorte * 1000);
                System.out.println("Cliente " + id + " ha terminado su corte de pelo y se va.");
                sillaBarbero.release();
            } else {
                System.out.println("Cliente " + id + " se va porque no hay sillas disponibles en la sala de espera.");
            }
        }
    }

    public void barbero() throws InterruptedException {
        while (true) {
            System.out.println("El barbero se duerme.");
            barberoDespierto.acquire();
            sillaBarbero.acquire();
            System.out.println("El barbero se despierta y est� listo para cortar el pelo.");

            if (sillasEspera.availablePermits() < sillasEspera.availablePermits() + 1) {
                System.out.println("El barbero est� cortando el pelo de un cliente.");
                int tiempoCorte = (int) (Math.random() * 5) + 1;
                Thread.sleep(tiempoCorte * 1000);
                System.out.println("El barbero ha terminado de cortar el pelo.");
                sillaBarbero.release();
                Thread.sleep(500); // Espera un momento para que los clientes se puedan sentar
            } else {
                System.out.println("El barbero no tiene clientes y se duerme nuevamente.");
                sillaBarbero.release();
            }
        }
    }

    public static void main(String[] args) {
        int nSillas = 5;
        BarberiaDormilona barberia = new BarberiaDormilona(nSillas);

        Thread barberoThread = new Thread(() -> {
            try {
                barberia.barbero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        barberoThread.start();

        for (int i = 0; i < 10; i++) {
            int tiempoLlegada = (int) (Math.random() * 3) + 1;
            try {
                Thread.sleep(tiempoLlegada * 1000);
                final int clienteId = i;
                Thread clienteThread = new Thread(() -> {
                    try {
                        barberia.cliente(clienteId);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                clienteThread.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

