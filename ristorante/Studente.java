package ristorante;

public class Studente implements Runnable {

  private int id;
  private Semaforo sStud = Ristorante.sStud;
  private Semaforo sCam = Ristorante.sCam;

  public Studente(int id) {
    this.id = id;
  }

  @Override
  public void run() {
    try {
      System.out.println(id + ": sto aspettando...");
      sStud.P(); //aspettando il semaforo

      for(int i = 0; i < 10; i++){
        if(Ristorante.contenitori[i] > 0){
          System.out.println(id + ": sto mettendo il vassoio...");
          Ristorante.contenitori[i]--; //diminuisco il numero di slot liberi nei contenitori
          if(Ristorante.contenitori[i] == 0){
            System.out.println("Contenitore" + i + "pieno!!!");
            sCam.V(); //attivo il cameriere
          }
          break;
        }
      }
    } catch (Exception ex) {ex.printStackTrace();}
  }
}