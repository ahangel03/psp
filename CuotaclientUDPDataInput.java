package Ej2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CuotaclientUDPDataInput {
    public static void main(String[] args) throws IOException {
        DatagramSocket clienteSocket = new DatagramSocket();
        byte[] sendData;

        // Nombre del cliente
        System.out.println("Nombre Cliente:");
        String nombre = new Scanner(System.in).next();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(nombre);
        sendData = baos.toByteArray();
        DatagramPacket paqueteNombre = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 5001);
        clienteSocket.send(paqueteNombre);

        // Importe del préstamo
        System.out.println("¿Cuál es el importe del préstamo?:");
        double prestamo = new Scanner(System.in).nextDouble();
        baos.reset();
        dos.writeDouble(prestamo);
        sendData = baos.toByteArray();
        DatagramPacket paquetePrestamo = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 5001);
        clienteSocket.send(paquetePrestamo);

        // Plazo de amortización
        System.out.println("¿Cuál es el plazo de amortización (en años)?:");
        double amortizacion = new Scanner(System.in).nextDouble();
        baos.reset();
        dos.writeDouble(amortizacion);
        sendData = baos.toByteArray();
        DatagramPacket paqueteAmortizacion = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 5001);
        clienteSocket.send(paqueteAmortizacion);

        // Tipo de interés anual
        System.out.println("¿Cuál es el tipo de interés anual?:");
        double interes = new Scanner(System.in).nextDouble();
        baos.reset();
        dos.writeDouble(interes);
        sendData = baos.toByteArray();
        DatagramPacket paqueteInteres = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 5001);
        clienteSocket.send(paqueteInteres);

        // Recibir resultado
        byte[] receiveData = new byte[1024];
        DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
        clienteSocket.receive(paqueteRecibido);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(paqueteRecibido.getData()));
        String resultado = dis.readUTF();
        System.out.println("*********** RESULTADOS DEL SIMULADOR DE CUOTA ***********");
        System.out.println(resultado.trim());

        clienteSocket.close();
    }
}
