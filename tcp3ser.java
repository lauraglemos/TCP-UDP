	
import java.awt.List;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

public class tcp3ser {

	
	public static void main(String[] args) throws IOException {
		SocketChannel sc=null;
		DatagramChannel dc = null;
		
		
		
		if(args.length == 1) {
			int puertoCliente =  Integer.parseInt(args[0]); 
			
			
			
			ServerSocketChannel SSC = ServerSocketChannel.open(); //canal que puede escuchar conexiones TCP entrantes
			
			SSC.socket().bind(new InetSocketAddress(puertoCliente)); 
			
			SSC.configureBlocking(false);
			
			DatagramChannel DC = DatagramChannel.open() ;// un canal que puede mandar y recibir paquetes UDP
			DC.bind(new InetSocketAddress(puertoCliente));
			DC.configureBlocking(false);

				
			Selector selector = Selector.open();
		
			int ops = SSC.validOps();
			SSC.register(selector, ops,null);
			
			
			
			HashMap<SocketChannel, Integer>MapaDatos = new HashMap<SocketChannel,Integer>();
			HashMap<DatagramChannel,Integer>MapaDatosUDP = new HashMap <DatagramChannel,Integer>();
			
			
			MapaDatosUDP.put(DC, 0);
			 System.out.println("Servidor conectado");
		      
			 while(true) {
				
					 
					 SSC.register(selector, SelectionKey.OP_ACCEPT);
					 
					 DC.register(selector, SelectionKey.OP_READ);
					 
					 
					
				 int readyChannels = selector.select();
				 
				 if(readyChannels == 0) continue;
				 
				 Set<SelectionKey> selectedKeys = selector.selectedKeys();

				  Iterator<SelectionKey> iterator = selectedKeys.iterator();

				  while(iterator.hasNext()) {

				    SelectionKey key = iterator.next();
				    
				    Channel c = (Channel) key.channel();

				  
				    
				    if(key.isAcceptable() && c == SSC) {
				    	try {
				    	ServerSocketChannel serverChannel= (ServerSocketChannel)key.channel();
				    	SocketChannel sc2= SSC.accept();
				    	
				    	sc2.configureBlocking(false);
				    	sc2.register(selector, SelectionKey.OP_READ);
				    	MapaDatos.put(sc2, 0);
				    		
				    } catch(Exception e) {
						MapaDatos.remove(sc);
						sc.close();
					  
					  }}
				    

				    else if (key.isReadable() && c!=DC) {
				    	
				    	try {
				    	sc = (SocketChannel)key.channel();
				    	   ByteBuffer buffer = ByteBuffer.allocate(1024);
				    	
				    	sc.read(buffer);

				    	String result = new String(buffer.array()).trim();
				    	int numeroNuevo = Integer.parseInt(result);
				    	
				    	int acumulador= MapaDatos.get(sc)+ numeroNuevo;
						MapaDatos.put(sc, acumulador);
							
							System.out.println("Acumulador: "+ acumulador);
							buffer.clear();
							buffer.putInt(acumulador);
							buffer.flip();
							
							sc.write(buffer);
				    }
				    	catch(Exception e) {
							MapaDatos.remove(sc);
							sc.close();
						  
						  }}
				    else if (key.isReadable() && c==DC) {
				    	
							
				    	try {
				    		
					    	 ByteBuffer bufferUDP = ByteBuffer.allocate(1024);
					    	 bufferUDP.clear();
					    	 
					    	 SocketAddress clientAddress = DC.receive(bufferUDP);
					    	    
					    	    String resultudp = new String(bufferUDP.array()).trim();
						    	int numeroNuevoudp = Integer.parseInt(resultudp);
						    	
						    	
						    	int acumuladorudp= MapaDatosUDP.get(DC)+ numeroNuevoudp;
						    	
								MapaDatosUDP.put(DC, acumuladorudp);
								
								System.out.println("Acumulador: "+ acumuladorudp);
								
								bufferUDP.clear();
								bufferUDP.putInt(acumuladorudp);
								bufferUDP.flip();
								
								DC.send(bufferUDP,clientAddress);
				    	}catch(Exception e) {
							MapaDatosUDP.remove(DC);
							DC.close();
						  
						  }
}
				   
				    iterator.remove();
				    }		
				  }
		      
				  }
			 
				
		
			 }
			
			
			

	}


