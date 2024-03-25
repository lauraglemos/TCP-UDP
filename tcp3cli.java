
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class tcp3cli {

	public static void main(String[] args) throws IOException {
		
		boolean no_acabo=true;
		try {
		if(args.length == 3) {
			String direccionIP = args[0];
			int puertoServidor = Integer.parseInt(args[1]);
			int tiempoEspera = 10000;
			
			try {
			
				DatagramChannel DatagramaCliente = DatagramChannel.open();
				DatagramaCliente.bind(null);

				InetSocketAddress direccionServidor = new InetSocketAddress(direccionIP,puertoServidor);

				while(no_acabo ==true) {
				ByteBuffer bufferpararecibir = ByteBuffer.allocate(1024);
				ByteBuffer bufferparaenviar = ByteBuffer.allocate(1024);
				
				Scanner lectura = new Scanner (System.in);
				System.out.print("Escribe una cadena de numeros separados por espacios(el 0 indica el final de la cadena):\n");   
				
				String cadenaNumeros = lectura.nextLine();

				byte[] numero = new String(cadenaNumeros).getBytes();
				bufferparaenviar.clear();
				bufferparaenviar.put(numero);
				bufferparaenviar.flip();
				
				if (cadenaNumeros.startsWith("0")) {
					
					DatagramaCliente.close();
					System.exit(-1);
			
				 no_acabo = false;
				}
				else {

					DatagramaCliente.send(bufferparaenviar, direccionServidor);  
					
					bufferpararecibir.clear();
					
			
					DatagramaCliente.receive(bufferpararecibir);

					bufferpararecibir.flip();
					int acumulador= bufferpararecibir.getInt();

				    
				    System.out.println("Acumulador total: " + acumulador);
				    

				}
			}
				
				DatagramaCliente.close();
				}catch (SocketException m) {
					
				
				}
				

			catch (UnknownHostException e1) {
				
				e1.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			   
		}
			
			
		if(args.length== 2) {
			
			String direccionIP = args[0];
			int puertoServidor = Integer.parseInt(args[1]);
			
			try {
			
		try {
			try {
					
					SocketAddress socketAddress = new InetSocketAddress(direccionIP,puertoServidor);
					SocketChannel socketCliente = SocketChannel.open(socketAddress);

			while(no_acabo ==true) {
			ByteBuffer bufferpararecibir = ByteBuffer.allocate(1024);
			ByteBuffer bufferparaenviar = ByteBuffer.allocate(1024);
			
			Scanner lectura = new Scanner (System.in);
			System.out.print("Escribe una cadena de numeros separados por espacios (si la cadena empieza por un 0 el cliente se desconecta):\n"); 
			
			String cadenaNumeros = lectura.nextLine();
	
			byte[] numero = new String(cadenaNumeros).getBytes();
			bufferparaenviar.clear();
			bufferparaenviar.put(numero);
			bufferparaenviar.flip();
			
			if (cadenaNumeros.startsWith("0")) {
				
				socketCliente.close();
				System.exit(-1);

			no_acabo = false;	
		}
			

				
			else {

				socketCliente.write(bufferparaenviar);  
				
				bufferpararecibir.clear();
				
				socketCliente.read(bufferpararecibir);
				bufferpararecibir.flip();
				int acumulador= bufferpararecibir.getInt();

			    System.out.println("Acumulador total: " + acumulador);
	
				
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
			System.out.print("Sintaxis incorrecta \nSintaxis correcta udp: direccion_ip numero_puerto -u \nSintaxis correcta tcp: direccion_ip numero_puerto");
		}
	

		}catch(Exception e) {
			
		}
		
	}	
			
	}

