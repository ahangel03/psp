package Ej2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

class Factura implements Serializable{
	String nfactura;
	String fecha;
	Double importe;
	String tipo;
	Double Iva;
	Double ImporteTotal;
	public Factura(String nfactura, String fecha, Double importe, String tipo,Double Iva,Double ImporteTotal) {
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

public class ServidorTCP {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int numeroPuerto=3022;
		ServerSocket servidor= new ServerSocket(numeroPuerto);
		Socket cliente;
		
		while(true){
			cliente=servidor.accept();
			ObjectInputStream entrada= new ObjectInputStream(cliente.getInputStream());
			
			Factura factura1=(Factura) entrada.readObject();
		
			String tipo= factura1.getTipo();
			Double importe=factura1.getImporte();
			System.out.println(tipo);
			System.out.println(factura1.toString());
			if(tipo.toString()=="IGC"){
				double iva=importe*0.07;
				double total=iva+importe;
				
				System.out.println(iva);
				System.out.println(total);
				factura1.setIva(iva);
				factura1.setImporteTotal(total);
				
				ObjectOutputStream flujosalida = new ObjectOutputStream(cliente.getOutputStream());
				flujosalida.writeObject(factura1);
			}
		}
	}
	
}
