package pacman;

import javax.swing.*;

import java.awt.*;

/**
 *Klasa, w której rozpoczyna siê ca³y program. Tutaj jest inicjowane te¿ Menu 
 *@author Maciek Wójcik & Kuba Wilkowski
 */
public class Game  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * inicjacja menu
	 */
	private Menu myMenu;
	/**
	 *  Funkcja main
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Game();
			}
		});
	}
	/**
	 * konstruktor klasy Game, ustawienia okna, tytu³u
	 */	
	
	public Game() {
		this.setTitle("GIT Pacman");
		this.setSize(450,550);
		this.setResizable(true);
		setMinimumSize(new Dimension(150, 200));
		this.setVisible(true);
		Dimension size = this.getSize();
		Insets insets = this.getInsets();
		int insetwidth = insets.left + insets.right;
		int insetheight = insets.top + insets.bottom;
		this.setSize((int)size.getWidth() + insetwidth,(int)size.getHeight() + insetheight);
		myMenu = new Menu(this);
		this.add(myMenu);
		myMenu.requestFocusInWindow();
		getContentPane().add(this.myMenu);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}