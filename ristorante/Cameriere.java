package ristorante;

public class Cameriere implements Runnable{
  private int id;
  private Semaforo sCount = Ristorante.cont;
  private Semaforo sCam = Ristorante.sCam;
  private Semaforo sStud = Ristorante.sStud;

  public Cameriere(int id){
    this.id = id;
  }

    public void run() {
    try {

      while(Ristorante.vassoiTot < 400){
        System.out.println(id + "Cameriere: sto aspettando...");
        sCam.P();
      
        for(int i = 0; i < 10; i++){
          if(Ristorante.contenitori[i] == 0){
            Counter counter = new Counter(sCount);
            Thread t = new Thread(counter);
            System.out.println(id + ": sto svuotando");
      
            try {
              Thread.sleep(200);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
      
            Ristorante.contenitori[i] = 20;
            sStud.V();
            t.start();
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}