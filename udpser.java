

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class udpser {

	public static void main(String[] args) {
		
		if(args.length== 1) {
		try {
			  int puertoCliente =  Integer.parseInt(args[0]);
			
			DatagramSocket socket= new DatagramSocket(puertoCliente); 
			byte[] buffer = new byte[1024];
			int acul =0;
			
			
			while(true) {
			
			DatagramPacket infoRecibida = new DatagramPacket(buffer, buffer.length);
			
			
			socket.receive(infoRecibida);
				
			String cadenaNumeros = new String(infoRecibida.getData());
			cadenaNumeros = cadenaNumeros.trim();
			String[] arrayNumeros = cadenaNumeros.split(" ");
			int i = arrayNumeros.length;
				
					
				for (int j=0;j<i;j++) {
					
					byte[] numero  =  ByteBuffer.allocate(4).putInt(acul).array();
					
				
					if (Integer.parseInt(arrayNumeros[j])==0 ) {
						
						
				DatagramPacket infoQueEnviamos = new DatagramPacket(numero,numero.length,infoRecibida.getAddress(),infoRecibida.getPort());
				socket.send(infoQueEnviamos);
				break;
				
					}
					
					int numeroNuevo=Integer.parseInt(arrayNumeros [j]);
					
					acul = acul + numeroNuevo;
					System.out.println(acul);
					
				}
				
				
				
			
		}} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: numero_puerto");
		}
		}

}
