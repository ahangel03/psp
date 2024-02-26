import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cuotaclient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket("localhost", 5000);
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre Cliente:");
		String nombre = sc.next();
		DataOutputStream flujosalida = new DataOutputStream(cliente.getOutputStream());
		flujosalida.writeUTF(nombre);
		System.out.println("¿Cual es el importe del prestamo?:");
		double prestamo = sc.nextDouble();
		flujosalida = new DataOutputStream(cliente.getOutputStream());
		flujosalida.writeDouble(prestamo);
		System.out.println("¿Cual es el plazo de amortizacion(en años)?:");
		double amortizacion = sc.nextDouble();
		flujosalida = new DataOutputStream(cliente.getOutputStream());
		flujosalida.writeDouble(amortizacion);
		System.out.println("¿Cual es el tipo de interes anual?:");
		double interes = sc.nextDouble();
		flujosalida = new DataOutputStream(cliente.getOutputStream());
		flujosalida.writeDouble(interes);
		
		System.out.println("***********RESULTADOS DEL SIMULADOR DE CUOTA****************");
		DataInputStream inObjeto= new DataInputStream(cliente.getInputStream());
		System.out.println("Nombre CLiente: "+inObjeto.readUTF());
		inObjeto= new DataInputStream(cliente.getInputStream());
		System.out.println("Cuota mensual: "+inObjeto.readDouble());
		System.out.println("************************************************************");
	}
}
