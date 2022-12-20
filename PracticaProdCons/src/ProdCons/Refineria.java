package ProdCons;

public class Refineria {
public static void main(String[] args) {
	
	
	Deposito dep = new Deposito();
	Productor productor= new Productor(dep);
	Camion camion1= new Camion(dep, 1);
	Camion camion2= new Camion(dep, 2);
	Camion camion3= new Camion(dep, 3);

	productor.start();
	camion1.start();
	camion2.start();
	camion3.start();
}
}
