import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcp1ser {

	public static void main(String[] args) {
		
		boolean conexion_abierta = true;
		if(args.length == 1) {
			
			int puertoCliente =  Integer.parseInt(args[0]);
			  
			
			  try {
				  try {
			ServerSocket socketServidor= new ServerSocket(puertoCliente);   
			
			System.out.println("Servidor iniciado");  
		
			
			while(true) {
				
				Socket saux= socketServidor.accept(); 
				System.out.println("Cliente conectado"); 

				int acul =0;
			
				
				while(conexion_abierta) {
					DataInputStream in = new DataInputStream(saux.getInputStream());   
					DataOutputStream out = new DataOutputStream(saux.getOutputStream());
				
					
					
				String cadenaNumeros = in.readUTF();
					
				
				
				cadenaNumeros = cadenaNumeros.trim();
				String[] arrayNumeros = cadenaNumeros.split(" ");
				int i = arrayNumeros.length;
										
					for (int j=0;j<i;j++) {
						
						int numeroNuevo=Integer.parseInt(arrayNumeros [j]);
						
						acul = acul + numeroNuevo;
						
						System.out.println("Acumulador: "+ acul);
						
						if (cadenaNumeros.startsWith("0")) {
							conexion_abierta=false;
							break;
						}
					}
					
					//System.out.println("este es el acumulador" + acul);
					out.writeUTF(String.valueOf(acul));
					
//					System.out.println("este es el acumulador" + out.write(acul););
				}
				
				conexion_abierta=true;
		
				}}
                catch (EOFException ex1) {
                  
                }					
	} catch (IOException e) {
		
		e.printStackTrace();
	}}
		else {
			System.out.print("Sintaxis incorrecta \nSintaxis correcta: numero_puerto");
		}	

}}
