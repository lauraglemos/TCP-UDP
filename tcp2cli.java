import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class tcp2cli {

	public static void main(String[] args) {
		
		boolean no_acabo=true;
		
		if(args.length== 2) {
			String direccionIP = args[0];
			int puertoServidor = Integer.parseInt(args[1]);
			
			try {
			
		try {
			try {
				
				Socket socketCliente = new Socket();
				try {
					
					SocketAddress socketAddress = new InetSocketAddress(direccionIP,puertoServidor);
					socketCliente.connect(socketAddress, 15000);

					}catch(SocketTimeoutException e) {
						System.out.println("Tiempo de espera finalizado.");
						System.exit(-1);
					}

			DataInputStream in= new DataInputStream(socketCliente.getInputStream());
			DataOutputStream out= new DataOutputStream(socketCliente.getOutputStream());
			
			while(no_acabo ==true) {
			Scanner lectura = new Scanner (System.in);
			System.out.print("Escribe una cadena de numeros separados por espacios (si la cadena empieza por un 0 el cliente se desconecta):\n"); 
			
			String cadenaNumeros = lectura.nextLine();
			
			if (cadenaNumeros.startsWith("0")) {
				

				out.writeUTF(cadenaNumeros);  
				
				 String acumulador = in.readUTF();
				 
				System.out.println("Acumulador total: " + acumulador);

			no_acabo = false;	
		}
			

				
			else {
				out.writeUTF(cadenaNumeros);  
				
				
				String acumulador = in.readUTF();
				System.out.println("Acumulador total: " +acumulador);
				
				
			}
			}
			socketCliente.close();
			}catch (SocketException m) {
				
			
			} }
			catch (EOFException n) {
               
			
			}} catch (IOException e) {
				
				e.printStackTrace();
			
             }
			catch(Exception e2) {}}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: direccion_ip numero_puerto");
		}
	

}}
