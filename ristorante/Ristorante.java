package ristorante;

public class Ristorante {

  public static int vassoiTot = 0;
  public static int postiLib = 200;
  public static int[] contenitori = new int[10];
  public static Semaforo sStud = new Semaforo(postiLib);
  public static Semaforo sCam = new Semaforo(0);
  public static Semaforo cont = new Semaforo(1);

  public static void main(String args[]) {
    Thread tsS[] = new Thread[400];
    Thread tsC[] = new Thread[4];

    for(int i = 0; i < 10; i++){
      Ristorante.contenitori[i] = 20;
    }

    for (int i = 0; i < 400; i++) {
      Studente s = new Studente(i + 1);
      tsS[i] = new Thread(s);
      tsS[i].start();
    }

    for (int i = 0; i < 4; i++) {
      Cameriere c = new Cameriere(i + 1);
      tsC[i] = new Thread(c);
      tsC[i].start();
    }
    
    for (int i = 0; i < 400; i++) {
      try {
        tsS[i].join();
      } catch (InterruptedException e) {e.printStackTrace();}
    }

    for (int i = 0; i < 4; i++) {
      try {
        tsC[i].join();
      } catch (InterruptedException e) {e.printStackTrace();}
    }
  }
}
