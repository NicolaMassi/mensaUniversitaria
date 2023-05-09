package ristorante;

public class Counter implements Runnable {
    private Semaforo semaforo;
    
    public Counter(Semaforo semaforo) {
        this.semaforo = semaforo;
    }
    
    @Override
    public void run() {
        semaforo.P();
        Ristorante.vassoiTot += 1;
        
        System.out.println("Vassoi totali inseriti: " + Ristorante.vassoiTot);
        semaforo.V();
    }
}