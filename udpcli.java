
import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class udpcli{

	public static void main(String[] args) { 
		if(args.length== 2) {
		String direccionIP = args[0];
		int puertoServidor = Integer.parseInt(args[1]);
		int tiempoEspera = 10000;

		try {
		
			InetAddress direccionServidor =  InetAddress.getByName(direccionIP);

			DatagramSocket socket= new DatagramSocket();
			
			
			socket.setSoTimeout(tiempoEspera);  
			Scanner lectura = new Scanner (System.in);
			System.out.print("Escribe una cadena de numeros separados por espacios(el 0 indica el final de la cadena):\n");   
			
			String cadenaNumeros = lectura.nextLine();   
			
			byte[] listaDatos = cadenaNumeros.getBytes();

			DatagramPacket infoParaServidor= new DatagramPacket(listaDatos,listaDatos.length,direccionServidor,puertoServidor);  
			
			
			socket.send(infoParaServidor);
					
			
			
		    byte[] buffer = new byte[1000];

			DatagramPacket infoVieneServidor = new DatagramPacket(buffer, buffer.length);
			     
			  try { 
				                 socket.receive(infoVieneServidor);
				                 }   catch(SocketTimeoutException e) { 
				                	 System.out.println("Tiempo de espera finalizado");
				                 }
			byte[] nuevoNumero = infoVieneServidor.getData();
	
			socket.close(); 
		
				      
		 } catch (SocketException e) {
			
			e.printStackTrace();
		}	
		catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		   
	}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: direccion_ip numero_puerto");
		}
}}
