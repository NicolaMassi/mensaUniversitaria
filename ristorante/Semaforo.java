package ristorante;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Semaforo {
    private int slotDisponibili = 0;
    
    public Semaforo(int slotDisponibili) {
        this.slotDisponibili = slotDisponibili;
    }

    public int getSlotDisponibili() {
        return slotDisponibili;
    }

    public void setSlotDisponibili(int slotDisponibili) {
        this.slotDisponibili = slotDisponibili;
    }
    
    public synchronized void V() {
        slotDisponibili++;
        
        notify();
    }
    
    public synchronized void P() {
        while (slotDisponibili <= 0) {
            try {
                wait(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        slotDisponibili--;
    }
}
