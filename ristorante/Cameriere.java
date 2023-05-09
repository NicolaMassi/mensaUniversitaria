package ristorante;

public class Cameriere implements Runnable{
  private int id;
  private Semaforo sCam;
  private Semaforo sStud;

  public Cameriere(int id, Semaforo sStud, Semaforo sCam ){
    this.id = id;
    this.sStud = sStud;
    this.sCam = sCam;
  }

    public void run() {
    try {

      while(Ristorante.vassoiTot < 400){
        System.out.println(id + ": Cameriere sto aspettando...");
        sCam.P();
      
        for(int i = 0; i < 10; i++){
          if(Ristorante.contenitori[i] == 0){
            System.out.println(id + ": Cameriere sto svuotando il " + (i+1) + " contenitore.");
      
            try {
              Thread.sleep(200);
            } catch (Exception ex) {ex.printStackTrace();}
      
            Ristorante.contenitori[i] = 20;
            System.out.println(id + ": Cameriere ha svuotato il contenitore.");
            
            for(int j = 0; j < 20; j++){
              sStud.V();
            }
            System.out.println(id + ":Posti ripristinati");
            break;
          }
        }
      }
    } catch (Exception ex) {ex.printStackTrace();}
  }
}