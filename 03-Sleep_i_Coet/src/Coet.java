import java.util.Scanner;

public class Coet {

    private static final int NUM_MOTORS = 4;
    private final Motor[] motors = new Motor[NUM_MOTORS];

    public Coet() {
        for (int i = 0; i < NUM_MOTORS; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            throw new IllegalArgumentException("Potència incorrecta: " + p + " (ha d'estar entre 0 i 10)");
        }
        System.out.println("Passant a potència " + p);
        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }

    public void arranca() {
        for (Motor m : motors) {
            m.start();
        }
    }

    public void esperaAturada() throws InterruptedException {
        for (Motor m : motors) {
            m.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Coet coet = new Coet();
        coet.arranca();

        Scanner sc = new Scanner(System.in);
        int p;
        do {
            if (!sc.hasNextInt()) {
                if (!sc.hasNext()) break;
                sc.next();
                System.out.println("Introdueix un número entre 0 i 10");
                p = -1;
                continue;
            }
            p = sc.nextInt();
            try {
                coet.passaAPotencia(p);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                p = -1;
            }
        } while (p != 0);

        sc.close();
        coet.esperaAturada();
    }
}