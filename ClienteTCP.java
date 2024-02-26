package Ej2;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClienteTCP {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		int puerto=3022;
		Socket cliente = new Socket("localhost", puerto);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("introduce el nºfactura");
		String numero = sc.next();
		System.out.println("introduce la fecha");
		String fecha = sc.next();
		System.out.println("introduce el importe");
		Double importe = sc.nextDouble();
		System.out.println("introduce el tipo");
		String tipo = sc.next();
		Factura factura= new Factura(numero, fecha, importe, tipo,null,null);

		ObjectOutputStream flujosalida = new ObjectOutputStream(cliente.getOutputStream());
		flujosalida.writeObject(factura);
		
		System.out.println("Enviado");
		
		ObjectInputStream entrada= new ObjectInputStream(cliente.getInputStream());
		
		Factura factura1=(Factura) entrada.readObject();
		
		System.out.println(factura1.getIva()+"  "+factura1.getImporteTotal());
}
}