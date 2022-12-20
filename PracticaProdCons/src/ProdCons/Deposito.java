package ProdCons;

public class Deposito {
private double litrosdeposito;
//método realiza llenado depósito del camión
synchronized public double llenarDepositoCamion(double numero)
{
	litrosdeposito=numero;
	return litrosdeposito;
}
//método productor llena el depósito
synchronized public double prodLlenaDep(double litros)
{
	litrosdeposito=litros;
	return litrosdeposito;
}
}
