import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ClientesSocketStream {

    public static void main(String[] args){
        try{
            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr=new InetSocketAddress("localhost",5555);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os= clienteSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            String mensaje="mensaje desde el cliente";
            os.write(mensaje.getBytes());

            JFrame ventana = new JFrame("Interfaz gráfica con 4 botones");
            JPanel panel = new JPanel();
            JButton boton1 = new JButton("Botón 1");
            JButton boton2 = new JButton("Botón 2");
            JButton boton3 = new JButton("Botón 3");
            JButton boton4 = new JButton("Botón 4");

            boton1.setSize(150,100);
            boton2.setSize(150,100);
            boton3.setSize(150,100);
            boton4.setSize(150,100);

            panel.add(boton1);
            panel.add(boton2);
            panel.add(boton3);
            panel.add(boton4);

            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(300, 200);
            ventana.setContentPane(panel);
            ventana.setVisible(true);

            System.out.println("Mensaje enviado");

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}	