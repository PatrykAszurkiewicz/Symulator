package symulator;

public class StacjaKolejowa {
	int LiczbaStacji;
	int LiczbaPociagow;
	int ZajeteStacje;
        
	StacjaKolejowa(int a,int b){
		this.LiczbaStacji=a;
		this.LiczbaPociagow=b;
		this.ZajeteStacje=0;
	}
        
	synchronized void Start(int a){
		ZajeteStacje--;
	}
        
	synchronized String Koniec() throws InterruptedException{
            Thread.currentThread().sleep(1000);
		if(ZajeteStacje < LiczbaStacji) 
		{
		ZajeteStacje++;
		return "Na stacji";
		} 
		else
        {
			return "Koniec";
        }

	}
	synchronized int Lp(){
		LiczbaPociagow--;
        	if (LiczbaPociagow == 0) 
        	{
        		return 0;
                } 
        	else {
        		return 1;
        	}     
        }
}
