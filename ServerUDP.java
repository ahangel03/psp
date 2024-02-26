package Ej2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

class Kactura implements Serializable{
	String nfactura;
	String fecha;
	Double importe;
	String tipo;
	Double Iva;
	Double ImporteTotal;
	public Kactura(String nfactura, String fecha, Double importe, String tipo,Double Iva,Double ImporteTotal) {
		super();
		this.nfactura = nfactura;
		this.fecha = fecha;
		this.importe = importe;
		this.tipo = tipo;
		this.Iva = Iva;
		this.ImporteTotal = ImporteTotal;
	}
	public String getNfactura() {
		return nfactura;
	}
	public void setNfactura(String nfactura) {
		this.nfactura = nfactura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getIva() {
		return Iva;
	}
	public void setIva(Double iva) {
		Iva = iva;
	}
	public Double getImporteTotal() {
		return ImporteTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		ImporteTotal = importeTotal;
	}

	public String toString() {
		return "Factura [nfactura=" + nfactura + ", fecha=" + fecha + ", importe=" + importe + ", tipo=" + tipo
				+ ", Iva=" + Iva + ", ImporteTotal=" + ImporteTotal + "]";
	}
	
	
	
}

public class ServerUDP {
public static void main(String[] args) throws IOException, ClassNotFoundException {
	int puerto=12345;
	DatagramSocket datagramSocket= new DatagramSocket(puerto);
	System.out.println("Servidor iniciando");
	while(true) {
		 byte[] bufferEntrada = new byte[1024];
         DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
         datagramSocket.receive(paqueteEntrada);

         ByteArrayInputStream byteStream = new ByteArrayInputStream(paqueteEntrada.getData());
         ObjectInputStream objetoEntrada = new ObjectInputStream(byteStream);
         Factura factura = (Factura) objetoEntrada.readObject();
		 
         double importe =factura.getImporte();
         String tipo=factura.getTipo();
         if(tipo.equals("IGC")) {
        	 double iva = 0.07 * importe;
        	 double total=iva+importe;
        	 System.out.println(iva);
 			System.out.println(total);
 			factura.setIva(iva);
 			factura.setImporteTotal(total);
         }
         else if(tipo.equals("ESP")) {
        	 double iva = 0.21 * importe;
        	 double total=iva+importe;
        	 System.out.println(iva);
 			System.out.println(total);
 			factura.setIva(iva);
 			factura.setImporteTotal(total);
         }
         else if(tipo.equals("EUR")) {
        	 double iva = 0.15 * importe;
        	 double total=iva+importe;
        	 System.out.println(iva);
 			System.out.println(total);
 			factura.setIva(iva);
 			factura.setImporteTotal(total);
         }
         ByteArrayOutputStream byteStreamSalida = new ByteArrayOutputStream();
         ObjectOutputStream objetoSalida = new ObjectOutputStream(byteStreamSalida);
         objetoSalida.writeObject(factura);

         byte[] bufferSalida = byteStreamSalida.toByteArray();
         DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, paqueteEntrada.getAddress(), paqueteEntrada.getPort());
         datagramSocket.send(paqueteSalida);
        
	}
}
}
