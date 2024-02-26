package Ej2;

import java.io.*;
import java.net.*;

public class CuotaserverUDPDatainput {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5001);

        byte[] receiveData;
        byte[] sendData;

        DatagramPacket receivePacket;
        DatagramPacket sendPacket;

        System.out.println("Simulador de préstamos preparado....");

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            // Leer datos del cliente
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength()));
            String nombre = dis.readUTF();
            double prestamo = dis.readDouble();
            double amortizacion = dis.readDouble();
            double interes = dis.readDouble();

            // Calcular cuota mensual
            double importePrestamo = prestamo;
            double periodoAmortizacion = amortizacion * 12;
            double tipoInteresAnual = interes / (12 * 100);
            double resultado1 = (tipoInteresAnual * Math.pow(tipoInteresAnual + 1, periodoAmortizacion));
            double resultado2 = (Math.pow(1 + tipoInteresAnual, periodoAmortizacion) - 1);
            double cuotaMensual = importePrestamo * (resultado1 / resultado2);

            // Enviar resultado al cliente
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeUTF(String.format("Cuota mensual para %s: %.2f", nombre, cuotaMensual));
            sendData = baos.toByteArray();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }
}