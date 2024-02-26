

import java.io.*;
import java.net.*;

public class CuotaserverUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5000);

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
            String nombre = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Importe del préstamo
            socket.receive(receivePacket);
            double prestamo = Double.parseDouble(new String(receivePacket.getData(), 0, receivePacket.getLength()));

            // Plazo de amortización
            socket.receive(receivePacket);
            double amortizacion = Double.parseDouble(new String(receivePacket.getData(), 0, receivePacket.getLength()));

            // Tipo de interés anual
            socket.receive(receivePacket);
            double interes = Double.parseDouble(new String(receivePacket.getData(), 0, receivePacket.getLength()));

            // Calcular cuota mensual
            double importePrestamo = prestamo;
            double periodoAmortizacion = amortizacion * 12;
            double tipoInteresAnual = interes / (12 * 100);
            double resultado1 = (tipoInteresAnual * Math.pow(tipoInteresAnual + 1, periodoAmortizacion));
            double resultado2 = (Math.pow(1 + tipoInteresAnual, periodoAmortizacion) - 1);
            double cuotaMensual = importePrestamo * (resultado1 / resultado2);
            System.out.println(cuotaMensual);
            // Enviar resultado al cliente
            String resultado = String.format("Cuota mensual para %s: %.2f", nombre, cuotaMensual);
            sendData = resultado.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }
}
