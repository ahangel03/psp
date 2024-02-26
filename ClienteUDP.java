package Ej2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
public static void main(String[] args) throws IOException, ClassNotFoundException {
	  String SERVIDOR = "localhost";
      int PUERTO = 12345;
	
	
	  DatagramSocket socket = new DatagramSocket();
      InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);

      // Pedir datos de la factura al usuario
      Scanner scanner = new Scanner(System.in);
      System.out.println("Introduce los datos de la factura:");
      System.out.print("Número de factura: ");
      String numeroFactura = scanner.nextLine();
      System.out.print("Fecha de factura: ");
      String fechaFactura = scanner.nextLine();
      System.out.print("Importe en €: ");
      double importe = Double.parseDouble(scanner.nextLine());
      System.out.print("Tipo de IVA (IGC, ESP, EUR): ");
      String tipoIVA = scanner.nextLine();

      // Crear objeto Factura
      Factura factura = new Factura(numeroFactura, fechaFactura, importe, tipoIVA,null,null);

      // Convertir objeto a array de bytes
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      ObjectOutputStream objetoSalida = new ObjectOutputStream(byteStream);
      objetoSalida.writeObject(factura);

      byte[] datos = byteStream.toByteArray();
      DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionServidor, PUERTO);
      socket.send(paquete);

      // Recibir respuesta del servidor
      byte[] buffer = new byte[1024];
      DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
      socket.receive(paqueteRecibido);

      // Convertir array de bytes a objeto Factura
      ByteArrayInputStream byteStreamRecibido = new ByteArrayInputStream(paqueteRecibido.getData());
      ObjectInputStream objetoEntrada = new ObjectInputStream(byteStreamRecibido);
      Factura facturaCalculada = (Factura) objetoEntrada.readObject();

      // Mostrar factura calculada
      System.out.println("\nFactura calculada:");
      System.out.println(facturaCalculada);

      socket.close();
      scanner.close();
}
}
