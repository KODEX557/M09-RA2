public class Fil extends Thread {
 
    private final long feina;
    private final long delay;
    private final String verbFinal;
    private long acumulat;
 
    public Fil(String nom, int prioritat, long feina, long delay, String verbFinal) {
        super(nom);
        setPriority(prioritat);
        this.feina = feina;
        this.delay = delay;
        this.verbFinal = verbFinal;
    }
 
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++) {
                System.out.println(getName() + " " + i);
                if (delay > 0) {
                    Thread.sleep(delay);
                } else {
                    simulaFeina();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println(verbFinal + " el fil " + getName());
    }
 
    private void simulaFeina() {
        for (long c = 0; c < feina; c++) {
            acumulat += c;
        }
    }
}