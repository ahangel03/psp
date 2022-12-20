package ProdCons;

public class Camion extends Thread {
	private Deposito d;
	private int n;
	//Constructor clase camión: n -> número de camión
	public Camion (Deposito dep, int n) {
		this.d=dep;
		this.n=n;
	}
	public void run()
	{
		for (int i = 0; i < 4; i++) {
			d.llenarDepositoCamion();
		}
	}
	}
