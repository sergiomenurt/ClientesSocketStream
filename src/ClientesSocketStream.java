import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ClientesSocketStream extends JFrame implements ActionListener {

    private JLabel contador;
    private JButton bt1, bt2, bt3, bt4, bt5, bt6;
    private int cuenta = 0;
    private JPanel p1, p2;

    static String mensaje ="";

    public ClientesSocketStream() {
        // decoracion de Java
        JFrame.setDefaultLookAndFeelDecorated(true);

        /* la variable especial this se usa en metodos de instancia
         * para hacer referencia al objeto que contiene al metodo,
         * aqui equivale a JFrame */

        // accion por defecto al cerrar la ventana: salir del programa
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* paneles de disposicion: por defecto, los paneles se crean con FlowLayout
         * por ello es preferible definir el Layout en el constructor del panel para crearlo
         * desde el principio con ese aspecto y evitar la creacion de un FlowLayout temporal;
         * es preferible hacer:
         * p1 = new JPanel(new BorderLayout());
         * mejor que hacer esto otro:
         * p1 = new JPanel();
         * p1.setLayout(new BorderLayout()); */
        p1 = new JPanel(new BorderLayout());
        //  borde del panel: sin relleno, 10px por cada lado
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // panel para ocupar BorderLayout.SOUTH
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1,4,10,10));

        //  configurar fuente y convertir numero a String para JLabel
        contador = new JLabel(Integer.toString(cuenta), JLabel.CENTER);
        Font letra1 = new Font("Tahoma", Font.BOLD, 12);
        contador.setFont(letra1);
        //  configurar fuente para los botones
        Font letra2 = new Font("Tahoma", Font.PLAIN, 12);
        bt1 = new JButton("Boton CH");
        bt1.setFont(letra2);
        bt2 = new JButton("Boton X");
        bt2.setFont(letra2);
        bt3 = new JButton("Boton PR");
        bt3.setFont(letra2);
        bt4 = new JButton("Boton S");
        bt4.setFont(letra2);

        // tooltips para los botones
        bt1.setToolTipText("Boton CH");
        bt2.setToolTipText("Boton X");
        bt3.setToolTipText("Boton PR");
        bt4.setToolTipText("Boton S");

        /* accion por defecto ante los eventos de bt1 y bt3,
         * para poder diferenciar mas tarde cual de los 2 botones
         * ha generado el evento */
        bt1.setActionCommand ("Boton CH");
        bt2.setActionCommand ("Boton X");
        bt3.setActionCommand ("Boton PR");
        bt4.setActionCommand ("Boton S");

        /*colocar los componentes en los paneles;
         * para agregar un componente a un JFrame es preferible
         * frame.getContentPane().add(p1);
         * en lugar de
         * frame.add(p1);*/
        this.getContentPane().add(p1);
        p1.add(contador, BorderLayout.NORTH);
        p1.add(p2, BorderLayout.SOUTH);
        p2.add(bt1);
        p2.add(bt2);
        p2.add(bt3);
        p2.add(bt4);

        // crear y mostrar la ventana
        this.setTitle("ActionListener");
        this.setSize(500,100);
        this.setLocationRelativeTo(null); // centrar el formulario en la pantalla
        this.setResizable(false);
        this.setVisible(true);

        /*la clase principal implementa la interfaz ActionListener
         * por ello se pueden emplear sus metodos directamente.
         * aqui se detectan las acciones sobre los botones bt1 y bt3: */
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {
        // si se ha pulsado en el boton bt1 (si ActionCommand es Contar1)
        if ("Boton CH".equals(e.getActionCommand())) {
            mensaje = "Boton CH";
        }
        // si se ha pulsado en el boton bt3 (si ActionCommand es Contar3)
        else if ("Boton X".equals(e.getActionCommand())) {
            mensaje = "Boton X";

        } else if ("Boton PR".equals(e.getActionCommand())) {
            mensaje = "Boton PR";
        } else if ("Boton S".equals(e.getActionCommand())) {
            mensaje = "Boton S";
        }
    }

    public static void main(String[] args){

        // instancia de la clase principal
        new ClientesSocketStream();


        try{


            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr=new InetSocketAddress("localhost",5555);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os= clienteSocket.getOutputStream();

            os.write(mensaje.getBytes());

            System.out.println("Enviando mensaje");

            System.out.println("mensaje desde el cliente");


            System.out.println("Mensaje enviado");

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }




}