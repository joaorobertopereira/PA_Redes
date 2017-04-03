package br.edu.ifrn.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author joao
 * Classe que implementa a parte do servidor
 * socket na aplicação
 */
public class SocketServidor {

    private ServerSocket servidor;
    private Socket cliente;
    private List<DataInputStream> clientes;

    /**Construtor da classe*/
    public SocketServidor( int porta ) throws IOException{
         this.servidor = new ServerSocket(porta);
         this.clientes = new LinkedList<DataInputStream>();
   }

    /**Fica ouvindo para receber novas conexões*/
    public void recebeCliente() throws Exception {
         try{
            //Abre conexão
            this.cliente = this.servidor.accept();
            InputStream input = this.cliente.getInputStream();
            DataInputStream leitor = new DataInputStream( input );
            this.clientes.add( leitor );
         }catch(Exception ex){
            System.err.println("Cliente não recebido "+ex);
         }
    }

    /**Envia mensagem com codificação ISO8859-1*/
    public void enviarMsg(String mensagem) throws IOException{
       //Envia mensagem de confirmação de recebimento do Cliente
       OutputStream output = this.cliente.getOutputStream();
       DataOutputStream escritor = new DataOutputStream(output);
       escritor.write(mensagem.getBytes("ISO8859-1"));
   }

    /**Recebe mensagem*/
    public String receberMsg() throws IOException, ClassNotFoundException {
        //recebe mensagem do ultimo cliente adicionado na lista
        byte[] saida = new byte[1000];
        clientes.get(clientes.size()-1).read(saida);
        String msg = new String(saida);        
        return msg;
    }

    /**Seta timeout*/
    public void setTimeout(int time) throws SocketException{
        servidor.setSoTimeout(time);
    }

    public void closeServer(){
        try {
            servidor.close();
        } catch (IOException ex) {
            System.err.println("Não foi possível fechar o servidor: "+ex);
        }
    }

    /**Ao receber conexão exibe o ip de quem requisitou a conexão*/
    public String getIpCliente(){
       return String.valueOf(this.cliente.getInetAddress());
    }

    /**Fecha conexão do socket*/
    public void fecharServidor() throws IOException {
        for( DataInputStream cliente : this.clientes ) {
                cliente.close();
        }
    }

    /**Testa se a conexao do servidor esta aberta*/
    public boolean testeServidor(){
        boolean status = false;
        if(this.servidor.isClosed()){
            status = true;
        }
        return status;
   }

}
