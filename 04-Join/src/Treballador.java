import java.util.Random;

public class Treballador extends Thread {

    private static final int MESOS_ANY = 12;
    private static final float PERCENTATGE_IMPOSTOS = 0.24f;
    private static final int MIN_MS_MES = 7;
    private static final int MAX_MS_MES = 10;

    private final float sou_anual_brut;
    private final int edat_inici_treball;
    private final int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private final Random rnd = new Random();

    public Treballador(String nom, float sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(nom);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
    }

    private void cobra() {
        cobrat += sou_anual_brut / MESOS_ANY;
    }

    private void pagaImpostos() {
        cobrat -= (sou_anual_brut / MESOS_ANY) * PERCENTATGE_IMPOSTOS;
    }

    @Override
    public void run() {
        edat_actual = edat_inici_treball;
        try {
            while (edat_actual < edat_fi_treball) {
                for (int mes = 0; mes < MESOS_ANY; mes++) {
                    cobra();
                    pagaImpostos();
                    Thread.sleep(MIN_MS_MES + rnd.nextInt(MAX_MS_MES - MIN_MS_MES + 1));
                }
                edat_actual++;
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interromput");
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}