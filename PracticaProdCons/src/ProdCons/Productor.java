package ProdCons;

public class Productor extends Thread {
	Deposito d = new Deposito();
	//Constructor clase productor
	public Productor(Deposito dep)
	{
		dep=d;
	}
	public void run()
	{
		
		for (int i = 0; i < 14; i++) {
			double numero = (double)(Math.random()*1000+1);
			d.prodLlenaDep(numero);
		}
		System.out.println("Productor llena el deposito con " );
	}  
	}
