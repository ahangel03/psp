import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class cuotaserver {
public static void main(String[] args) throws IOException {
	int numeroPuerto=5000;
	ServerSocket servidor= new ServerSocket(numeroPuerto);
	Socket cliente;
	System.out.println("Simulador de prestamos preparado....");
	while(true) {
		cliente=servidor.accept();
		InputStream entrada=null;
		entrada= cliente.getInputStream();
		DataInputStream flujoentrada= new DataInputStream(entrada);
		String nombre=flujoentrada.readUTF();
		double prestamo=flujoentrada.readDouble();
		double amortizacion=flujoentrada.readDouble();
		double interes=flujoentrada.readDouble();
		
		System.out.println(nombre+prestamo+amortizacion+interes);
		double importeprestamo= prestamo;
		double periodoamortizacion=amortizacion*12;
		double tipointeresanual=interes/(12*100);
		double cuotamensual;
		double resultado1= (tipointeresanual*Math.pow(tipointeresanual+1, periodoamortizacion));;
		double resultado2=(Math.pow(1+tipointeresanual, periodoamortizacion)-1);;
		cuotamensual=importeprestamo*(resultado1/resultado2);
		System.out.println(cuotamensual);
		DataOutputStream outObjeto= new DataOutputStream(cliente.getOutputStream());
		outObjeto.writeUTF(nombre);
		outObjeto= new DataOutputStream(cliente.getOutputStream());
		outObjeto.writeDouble(cuotamensual);
	}
	
}
}
