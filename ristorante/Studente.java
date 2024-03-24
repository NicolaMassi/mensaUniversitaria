package ristorante;

public class Studente implements Runnable {

  private int id;
  private Semaforo sStud;
  private Semaforo sCam;
  private Semaforo sCont;

  public Studente(int id, Semaforo sStud, Semaforo sCam, Semaforo sCont) {
    this.id = id;
    this.sStud = sStud;
    this.sCam = sCam;
    this.sCont = sCont;
  }

  @Override
  public void run() {
    try {
      System.out.println(id + ": sto aspettando...");
      sStud.P(); // aspettando il semaforo
        
      for (int i = 0; i < 10; i++) {
        if (Ristorante.contenitori[i] > 0) {
          System.out.println(id + ": sto mettendo il vassoio...(contenutore n. "+  (i+1) +")");
          Ristorante.contenitori[i]--; // diminuisco il numero di slot liberi nei contenitori
          Counter counter = new Counter(sCont);
          Thread t = new Thread(counter);
          t.start();
          

          if (Ristorante.contenitori[i] == 0) {
            System.out.println("Contenitore " + i + " pieno!!!");
            sCam.V(); // attivo il cameriere
          }
          break;
        }
        
      }
    } catch (Exception ex) {ex.printStackTrace();}
  }
}