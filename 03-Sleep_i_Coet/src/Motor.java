import java.util.Random;

public class Motor extends Thread {

    private final int id;
    private final Random random = new Random();

    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean pendent = false;

    public Motor(int id) {
        super("Motor " + id);
        this.id = id;
    }

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
        this.pendent = true;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (potenciaActual == potenciaObjectiu) {
                        if (pendent) {
                            pendent = false;
                            System.out.println("Motor " + id + ": FerRes Objectiu: "
                                    + potenciaObjectiu + " Actual: " + potenciaActual);
                            if (potenciaObjectiu == 0) {
                                return;
                            }
                        }
                        wait();
                    }
                }

                Thread.sleep(1000 + random.nextInt(1001));

                synchronized (this) {
                    String accio;
                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        accio = "Incre.";
                    } else if (potenciaActual > potenciaObjectiu) {
                        potenciaActual--;
                        accio = "Decre.";
                    } else {
                        continue;
                    }
                    System.out.println("Motor " + id + ": " + accio + " Objectiu: "
                            + potenciaObjectiu + " Actual: " + potenciaActual);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Motor " + id + " interromput");
        }
    }
}
