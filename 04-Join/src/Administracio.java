public class Administracio {

    private final int num_poblacio_activa = 50;
    private final Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

    public Administracio() {
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-" + i, 25000f, 20, 65);
        }
    }

    public void inicia() {
        for (Treballador t : poblacio_activa) {
            t.start();
        }
    }

    public void esperaFinal() throws InterruptedException {
        for (Treballador t : poblacio_activa) {
            t.join();
        }
    }

    public void mostraEstadistiques() {
        for (Treballador t : poblacio_activa) {
            System.out.printf("%-10s -> edat: %d / total: %.2f%n",
                    t.getName(), t.getEdat(), t.getCobrat());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Administracio administracio = new Administracio();
        administracio.inicia();
        administracio.esperaFinal();
        administracio.mostraEstadistiques();
    }
}