package br.edu.ifrn.Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author joao
 * classe que implemnta o cliente socket
 * na aplicação
 */
public class SocketCliente {
    private Socket cliente;

   public SocketCliente (String ip, int porta) throws UnknownHostException, IOException{
        this.cliente = new Socket(ip,porta);
   }

   public SocketCliente (){
       this.cliente = new Socket();
   }

   public void enviarMsg(String mensagem) throws IOException{
        OutputStream output = this.cliente.getOutputStream();
        DataOutputStream escritor = new DataOutputStream(output);
        escritor.write(mensagem.getBytes("ISO8859-1"));
   }

   public String receberMsg() throws IOException, ClassNotFoundException{
        InputStream input = this.cliente.getInputStream();
        DataInputStream leitor = new DataInputStream( input );
        byte[] saida = new byte[1000];
        leitor.read(saida);
        String mensagem = new String(saida);
        return mensagem;        
   }

   public void setTimeout(int time) throws SocketException{
       cliente.setSoTimeout(time);
   }

   public void fechaConxao() throws IOException{
             this.cliente.close();
   }

   public String getIpCliente(){
       return String.valueOf(this.cliente.getInetAddress());
    }

   public void connect(SocketAddress s, int t) throws IOException{
       this.cliente.connect(s, t);
   }
   
}
