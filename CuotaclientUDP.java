

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class CuotaclientUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket clienteSocket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre Cliente:");
        String nombre = sc.next();
        byte[] datosNombre = nombre.getBytes();
        DatagramPacket paqueteNombre = new DatagramPacket(datosNombre, datosNombre.length, InetAddress.getLocalHost(), 5000);
        clienteSocket.send(paqueteNombre);

        System.out.println("¿Cual es el importe del prestamo?:");
        double prestamo = sc.nextDouble();
        byte[] datosPrestamo = Double.toString(prestamo).getBytes();
        DatagramPacket paquetePrestamo = new DatagramPacket(datosPrestamo, datosPrestamo.length, InetAddress.getLocalHost(), 5000);
        clienteSocket.send(paquetePrestamo);

        System.out.println("¿Cual es el plazo de amortizacion(en años)?:");
        double amortizacion = sc.nextDouble();
        byte[] datosAmortizacion = Double.toString(amortizacion).getBytes();
        DatagramPacket paqueteAmortizacion = new DatagramPacket(datosAmortizacion, datosAmortizacion.length, InetAddress.getLocalHost(), 5000);
        clienteSocket.send(paqueteAmortizacion);

        System.out.println("¿Cual es el tipo de interes anual?:");
        double interes = sc.nextDouble();
        byte[] datosInteres = Double.toString(interes).getBytes();
        DatagramPacket paqueteInteres = new DatagramPacket(datosInteres, datosInteres.length, InetAddress.getLocalHost(), 5000);
        clienteSocket.send(paqueteInteres);

        System.out.println("***********RESULTADOS DEL SIMULADOR DE CUOTA****************");
        byte[] datosRecibidos = new byte[1024];

        DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
        clienteSocket.receive(paqueteRecibido);

        String resultado = new String(paqueteRecibido.getData());
        System.out.println(resultado.trim());
        clienteSocket.close();
    }
}
