import java.awt.Font;
import java.awt.Image; //icono
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;  //icono
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; //ventan modal
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener { ///atributos de clase
	private JLabel etiqueta = new JLabel("Adivina el numero (entre 1 y 100)");
	private JLabel etiqueta2 = new JLabel("");
	private JTextField cajitaNumero = new JTextField();
	private JButton boton = new JButton("Comprobar");
	private int numeroSecreto;
	private int contador;
	private final int INTENTOS = 7;
	private JPanel panelIntentos = new JPanel();
	private JLabel etiquetaTextoIntentos = new JLabel("Intentos restantes");
	private JLabel etiquetaIntentos = new JLabel("0");

	public Ventana() { // PARA LA CLASE VENTANA:::
		this.setLayout(null); //coloca las cosas con valores absolutos o coordenadas X, Y
		this.setSize(800, 600); //tamaño ventana
		this.setLocationRelativeTo(null); //centra la ventana pos relative
		this.setResizable(false); // no permite cambiar tamaño
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //no se ejecuta por detras, cierra programa al cerrar ventana
		this.setTitle("Juego de adivinar");
		this.add(etiqueta); //añade etiqueta JLabel o ventanita a la ventana
		etiqueta.setLocation(250, 50); //aqui , tamaño y coloca y cambio de font, sin Location no se ve
		etiqueta.setSize(350, 50);
		etiqueta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(cajitaNumero);
		cajitaNumero.setLocation(340, 150);
		cajitaNumero.setSize(100, 25);
		cajitaNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		// cajitaNumero.addActionListener(this);
		this.add(boton);
		boton.setLocation(330, 200);
		boton.setSize(120, 30);
		boton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton.addActionListener(this); //añadimos ESCUCHADOR (de EventoS, mover raton, hacer click..)
		//implementa la ventana ActionListener: interfaz con metodos no implementados, te fuerza a ponerlos.
		//entonces al darle un evento sobre el boton ejecuta actionPerformed
		
		
		// boton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// JOptionPane.showMessageDialog(null, "botonPulsado");
		// }
		// });
		this.add(etiqueta2);
		etiqueta2.setLocation(250, 300);
		etiqueta2.setSize(450, 50);
		etiqueta2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        //añade panel JPanel				
		this.add(panelIntentos);
		//añade el layout en el panel con horientacion vertical
		panelIntentos.setLayout(new BoxLayout(panelIntentos, BoxLayout.Y_AXIS));
		panelIntentos.setLocation(460, 150);
		panelIntentos.setSize(250, 150);
		panelIntentos.add(etiquetaTextoIntentos);
		panelIntentos.add(etiquetaIntentos);
		etiquetaTextoIntentos.setAlignmentX(CENTER_ALIGNMENT); //alñade las etiquetas al panel
		etiquetaIntentos.setAlignmentX(CENTER_ALIGNMENT);
		etiquetaTextoIntentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		etiquetaIntentos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		ImageIcon ii = new ImageIcon("icon.png"); //añade icono
		Image image = ii.getImage();
		this.setIconImage(image);
		iniciar();
		

	}

	private void iniciar() { //obtiene el numero aleatorio en tre 1 y 100
		numeroSecreto = (int) (Math.random() * 100) + 1;
		cajitaNumero.setText("");
		cajitaNumero.requestFocus(); // el FOCO de espera en cada input
		contador = 0;
		etiqueta.setText("Adivina el numero (entre 1 y 100)"); //cambia contenido de las etiquetas
		etiqueta2.setText("");
		etiquetaIntentos.setText((INTENTOS - contador) + "");
		
	}
	
	@Override //este es el metodo que se ejecuta siempre que se hag auna accion sobre cualquier elemento
	//añadir al Action Listener
	public void actionPerformed(ActionEvent e) {
		int numero;
		//este if sirve para distinguir que componente ha lanzado el evento
		if (e.getSource() == boton) {
			try {
				numero = Integer.parseInt(cajitaNumero.getText());
			} catch (NumberFormatException nfe) {
				cajitaNumero.setText("");
				cajitaNumero.requestFocus();
				JOptionPane.showMessageDialog(null, "Debes introducir un numero.", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			contador++;
			if (numero < numeroSecreto) {
				etiqueta2.setText("El " + numero + " es menor que mi numero.");
				comprobarGameOver();
			} else {
				if (numero > numeroSecreto) {
					etiqueta2.setText("El " + numero + " es mayor que mi numero.");
					comprobarGameOver();
				} else {
					JOptionPane.showMessageDialog(null, "¡Enhorabuena! ¡Has ganado en el intento " + contador + "!",
							"Bien", JOptionPane.INFORMATION_MESSAGE);
					iniciar();
				}
			}

			cajitaNumero.setText("");
			cajitaNumero.requestFocus();

		}
		// if (e.getSource()==cajitaNumero){
		// JOptionPane.showMessageDialog(null, "cajita");
		// }

	}

	private void comprobarGameOver() {
		etiquetaIntentos.setText((INTENTOS - contador) + "");
		if (INTENTOS - contador <= 0) {
			JOptionPane.showMessageDialog(null, "¡Oooooh! ¡Has perdido!", "Looser", JOptionPane.ERROR_MESSAGE);
			iniciar();
		}

	}
}