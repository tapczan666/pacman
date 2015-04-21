package pacman;

import java.awt.Dimension;
import java.awt.Point;	
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Klasa Pacman, ustalanie okna, w¹tek gry.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Pacman extends JFrame implements Runnable {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 *Zmienne na, kórych operuj¹ w¹tki 
	 */
	public Board board;
	public TopTen topTen;
	/**
	 *Nazwa gracza
	 */
	String name="";
	/**
	 *Numer mapy 
	 */
	int mapNumber = 1;
	/**
	 *
	 *zmienna odpowiedzialna za koñczenie gry 
	 */
	boolean gameOver = false;
	
	/**
	 *Konstruktor
	 *@param point - wspó³rzêdna lewego górnego roku okna
	 *@param dim - Wymiary okna
	 *@param mapNumber - numer mapy do wczytania 
	 */
	public Pacman(Point point, Dimension dim, int mapNumber){
		
		topTen = new TopTen();
		this.setTitle("GIT Pacman");
		setMinimumSize(new Dimension(150, 200));	
		Dimension size = this.getSize();
		this.setSize(dim);
		this.setResizable(true);
		this.setVisible(true);
		Insets insets = this.getInsets();
		int insetwidth = insets.left + insets.right;
		int insetheight = insets.top + insets.bottom;
		this.setSize((int)size.getWidth() + insetwidth,(int)size.getHeight() + insetheight);
		this.setSize(dim);
		board = new Board(mapNumber);
		this.add(board);
		board.requestFocusInWindow();
		this.setLocation(point);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Thread game = new Thread(this);
		game.start();
	}

	/**
	 *Ustawia imiê z poziomu Menu
	 *@param name - string z imieniem 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Ustawia numer wczytywanej mapy
	 * @param number - numer mapy
	 */
	public void setMapNumber(int number) {
		board.setMap(number);
	}

	/**
	 *Metoda run, nadzoruje przebieg gry i koñczy j¹. 
	 */
	public void run(){
		
			while(true){
				if(!board.gameOver){
					if(board.getPellets() == 0){
						board.nextLevel();
					}else if(board.getLives() == 0){
						board.gameOver();
						try {
							System.out.println(name+"\n");
							System.out.println(board.getScore()+ "\n");
							topTen.send(name, board.getScore());
						}catch (IOException e) {
							e.printStackTrace();
							System.exit(0);
						}	
					}
					validate();
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
						
					}
				}
				else{

					try {
						Thread.sleep(5000);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
	}
}
