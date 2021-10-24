package symulator;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Train extends Thread{
	int id;
	String stan;
	StacjaKolejowa current;
    int IleBenzyny;
    static int MaxIloscBenz = 1000;

	public Train(int id, StacjaKolejowa a){
		this.id = id;
        current = a;
		this.stan = "NaStacji";
        this.IleBenzyny = MaxIloscBenz;
	}
        
        public String getTrainstan() {
            return stan;
        }
	
        public int getTrainId() {
            return id;
        }
	
        public boolean wasCrashed() {
            if (this.IleBenzyny < 10) {
                return true;
            } else
                return false;
        }
        
        public void run(){
            Random ran = new Random();
            boolean keepRunning = true;
            
		while(keepRunning){
			switch (this.getTrainstan()) {
            	case "NaStacji" -> {
            		this.IleBenzyny = MaxIloscBenz;
                    System.out.println("Pociag nr " + this.getTrainId() + " startuje ");
                    current.Start(this.getTrainId());
                    stan = "Poczatek";
                    }
                case "Poczatek" -> {
                	System.out.println("Pociag nr " + this.getTrainId() + " zaczal podroz ");
                    this.IleBenzyny = ran.nextInt(IleBenzyny);
                    if (wasCrashed() == true) {
                    	stan = "Wypadek";
                    	} 
                    else 
                    	{
                    	stan = "Wtrakcie";
                    	}         
                    }
                 case "Wtrakcie" -> {
                	 System.out.println("Pociag nr " + this.getTrainId() + " jest blisko kolejnej stacji ");
                     this.IleBenzyny = ran.nextInt(IleBenzyny);
                     if (wasCrashed() == true) {
                     stan = "Wypadek";
                     } 
                     else {
                    	 stan = "Koniec";
                     	}
                     }
                case "Koniec" -> {
                	System.out.println("Pociag nr " + this.getTrainId() + " zakonczyl jazde ");
                        try 
                        {
                            stan = current.Koniec();
                        } 
                        catch (InterruptedException ex) 
                        {
                            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                case "Wypadek" -> {
                	System.out.println("Pociag nr " + +this.getTrainId() + " mial wypadek ");
                    if (current.Lp() == 1) {
                    	try {
                    		this.sleep(10000);
                            stan = "NaStacji";
                            } 
                    	catch (InterruptedException ex) 
                            {
                            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            } 
                            else 
                            {
                            System.out.println("nastapilo duzo wypadkow "); 
                            keepRunning = false;
                            }
                        }
                            
                    }
		}
	}
}
