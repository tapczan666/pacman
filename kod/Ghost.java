package pacman;

import pacman.Map.Position;

/**
 * Klasa reprezentuj¹ca duszka.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Ghost {
	/**
	 * Zmienne u¿ywane do przechowywania po³o¿enia.
	 */
	private double tileX, tileY, dx, dy;
	/**
	 * Zmienna okreœlaj¹ca kierunek, w którym zwrócony jest duszek.
	 */
	private String dir;
	
	/**
	 * Konstruktor klasy Ghost. Tworzy duszka w zadanym miejscu.
	 * @param pos - miejsce utworzenia duszka
	 */
	public Ghost(Position pos){
		tileX = pos.tileX;
		tileY = pos.tileY;	
		dir = "right";
		dx = 0;
		dy = 0;
	}
	
	/**
	 * Zmienia po³o¿enie duszka o krok w kierunku ka¿dej osi.
	 */
	public void move(){
		tileX +=dx;
		tileY +=dy;
		
	}
	
	/**
	 * Zwraca pozycjê duszka na osi X.
	 * @return tileX - pozycja duszka na osi X
	 */
	public double getTileX(){
		return tileX;
	}
	
	/**
	 * Zwraca pozycjê duszka na osi Y.
	 * @return tileY - pozycja duszka na osi Y
	 */
	public double getTileY(){
		return tileY;
	}
	
	/**
	 * Zwraca wartoœæ kroku duszka wzd³u¿ osi X.
	 * @return dx - wartoœæ kroku duszka wzd³u¿ osi X
	 */
	public double getDx(){
		return dx;
	}
	
	/**
	 * Zwraca wartoœæ kroku duszka wzd³u¿ osi Y.
	 * @return dy - wartoœæ kroku duszka wzd³u¿ osi Y
	 */
	public double getDy(){
		return dy;
	}
	
	/**
	 * Ustawia ¿¹dan¹ wartoœæ kroku duszka wzd³u¿ osi X.
	 * @param dgx - ¿¹dana wartoœæ kroku duszka wzd³u¿ osi X
	 */
	public void setDx(double dgx){
		dx = dgx;
	}
	
	/**
	 * Ustawia ¿¹dan¹ wartoœæ kroku duszka wzd³u¿ osi Y.
	 * @param dgy - ¿¹dana wartoœæ kroku duszka wzd³u¿ osi Y
	 */
	public void setDy(double dgy){
		dy = dgy;
	}
	
	/**
	 * Zwraca kierunek poruszania siê duszka.
	 * @return - kierunek poruszania siê duszka
	 */
	public String getDir(){
		return dir; 
	}
	
	/**
	 * Ustawia ¿¹dany kierunek (left lub right).
	 * @param newdir - ¿¹dany kierunek
	 */
	public void setDir(String newdir){
		dir = newdir;
	}
}
