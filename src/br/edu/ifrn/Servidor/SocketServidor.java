package br.edu.ifrn.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidor {

    private int porta;
    //Declaro o ServerSocket
    private ServerSocket servidor = null;
    //Declaro o Socket de comunicação  
    private Socket comunicacao = null;
    //Declaro o leitor para a entrada de dados  
    private BufferedReader entrada = null;

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(Socket comunicacao) {
        this.comunicacao = comunicacao;
    }

    public BufferedReader getEntrada() {
        return entrada;
    }

    public void setEntrada(BufferedReader entrada) {
        this.entrada = entrada;
    }

    public SocketServidor(int porta) {
        this.porta = porta;
        try {
            //Cria o ServerSocket na porta 7000 se estiver disponível  
            servidor = new ServerSocket(porta);
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FecharConexao() {
        try {

            //Encerro o socket de comunicação  
            this.comunicacao.close();

            //Encerro o ServerSocket  
            this.servidor.close();

        } catch (IOException e) {
        }
    }

    public boolean IniciaServico() throws IOException {
        boolean retorno = false;
        //Aguarda uma conexão na porta especificada e cria retorna o socket que irá comunicar com o cliente  
        setComunicacao(this.servidor.accept());
        try {

            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s  
            entrada = new BufferedReader(new InputStreamReader(this.comunicacao.getInputStream()));

            //Aguarda por algum dado e imprime a linha recebida quando recebe  
            System.out.println(entrada.readLine());
            retorno = true;
            //trata possíveis excessões de input/output. Note que as excessões são as mesmas utilizadas para as classes de java.io      
        } catch (IOException e) {
            System.out.println("Algum problema ocorreu para criar ou receber o socket.");
            
        } 
        return retorno;
    }
}
