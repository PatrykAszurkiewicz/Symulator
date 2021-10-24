package symulator;

public class Main {

	public static void main(String[] args) {
		int LiczbaStacji = 16;
        int LiczbaPociagow = 14;
        
        StacjaKolejowa stacja = new StacjaKolejowa(2, LiczbaStacji);
        for(int i = 1; i <= LiczbaPociagow; i++) 
        {
        	new Train(i, stacja).start();
        }
	}

}
