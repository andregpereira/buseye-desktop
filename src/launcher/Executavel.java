package launcher;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.InterfaceGrafica;

public class Executavel {

	public static final Image icone = Toolkit.getDefaultToolkit()
			.getImage(Executavel.class.getResource("/view/icon/BusEyeLogo.png"));

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				new InterfaceGrafica();
			}
		});
	}
}
