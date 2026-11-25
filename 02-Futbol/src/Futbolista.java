import java.util.Random;

public class Futbolista extends Thread {

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    private int ngols;
    private int ntirades;
    private final Random random = new Random();

    public Futbolista(String nom) {
        super(nom);
        this.ngols = 0;
        this.ntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (random.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public static void main(String[] args) throws InterruptedException {
        String[] noms = {"Kane", "Vinicius", "Haaland", "Nico Paz", "Jackson",
                "Lewandoski", "Bellingham", "Lamine", "Raphinha", "Messi", "Mbappé"};

        System.out.println("Inici dels xuts --------------------");

        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
        }

        for (Futbolista f : jugadors) {
            f.start();
        }

        for (Futbolista f : jugadors) {
            f.join();
        }

        System.out.println("Fi dels xuts -----------------------");

        System.out.println("--- Estadistiques ------");
        for (Futbolista f : jugadors) {
            System.out.printf("%-10s -> %2d gols%n", f.getName(), f.getNgols());
        }
    }
}