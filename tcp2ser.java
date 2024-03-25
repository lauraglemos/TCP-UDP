import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class tcp2ser {

	public static void main(String[] args) {
		if(args.length == 1) {
			int puertoCliente =  Integer.parseInt(args[0]);
			
			try {
				try {
				ServerSocket socketServidor= new ServerSocket(puertoCliente);
				 
				System.out.println("Servidor iniciado");
				while(true) {
				
				Socket saux= socketServidor.accept();

				 
				ClientService cli= new ClientService(saux);
				cli.start();
				System.out.println("Cliente conectado");
			
			
		}
			

				
	
			}catch (EOFException e) {
			}
			} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: numero_puerto");
		}
	
	

}}
	class ClientService extends Thread{
	
		Socket miClienteSocket;
		ClientService(Socket s){
			miClienteSocket = s;
		}
		
		public void run() {
			try {
				try {
				DataInputStream in = new DataInputStream(miClienteSocket.getInputStream());
			
			DataOutputStream out = new DataOutputStream(miClienteSocket.getOutputStream());
			int acul=0;
			while(true) {
				
				String cadenaNumeros = in.readUTF();
				cadenaNumeros = cadenaNumeros.trim();
				String[] arrayNumeros = cadenaNumeros.split(" ");
				int i = arrayNumeros.length;
										
					for (int j=0;j<i;j++) {
						
						int numeroNuevo=Integer.parseInt(arrayNumeros [j]);
						
						acul = acul + numeroNuevo;
						
						System.out.println("Acumulador: "+ acul);
						
			
					}
					out.writeUTF(String.valueOf(acul));
				
			}
				}catch (SocketException e) {
					
				}catch (EOFException e) {
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
