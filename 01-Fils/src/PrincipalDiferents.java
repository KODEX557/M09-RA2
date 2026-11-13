public class PrincipalDiferents {
    public static void main(String[] args) {
        Fil pepe = new Fil("Pepe", Thread.MIN_PRIORITY, 1000, 0, "Acaba");
        Fil juan = new Fil("Juan", Thread.MAX_PRIORITY, 1000, 0, "Acaba");
 
        pepe.start();
        juan.start();
 
        System.out.println("Acaba thread main");
    }
}