import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class tcp1cli {

	public static void main(String[] args) {
		
		boolean no_acabo=true;
		
		if(args.length== 2) {
			String direccionIP = args[0];
			int puertoServidor = Integer.parseInt(args[1]);
			
			try {
			
		try {
			try {
				Socket socketCliente = new Socket(direccionIP,puertoServidor);
	
			
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
				socketCliente.close();
				in.close();
				out.close();
				no_acabo = false;	
		}
			

				
			
				out.writeUTF(cadenaNumeros);  
				
				
				String acumulador = in.readUTF();
				System.out.println("Acumulador total: " +acumulador);
				
				
				
			}}catch (SocketException m) {
				
			
			} }
			catch (EOFException n) {
               
			
			}} catch (IOException e) {
				
				e.printStackTrace();
			
             }	}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: direccion_ip numero_puerto");
		}
	

}}
