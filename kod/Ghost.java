package pacman;

import pacman.Map.Position;

/**
 * Klasa reprezentuj�ca duszka.
 * @author Maciek W�jcik & Kuba Wilkowski
 *
 */
public class Ghost {
	/**
	 * Zmienne u�ywane do przechowywania po�o�enia.
	 */
	private double tileX, tileY, dx, dy;
	/**
	 * Zmienna okre�laj�ca kierunek, w kt�rym zwr�cony jest duszek.
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
	 * Zmienia po�o�enie duszka o krok w kierunku ka�dej osi.
	 */
	public void move(){
		tileX +=dx;
		tileY +=dy;
		
	}
	
	/**
	 * Zwraca pozycj� duszka na osi X.
	 * @return tileX - pozycja duszka na osi X
	 */
	public double getTileX(){
		return tileX;
	}
	
	/**
	 * Zwraca pozycj� duszka na osi Y.
	 * @return tileY - pozycja duszka na osi Y
	 */
	public double getTileY(){
		return tileY;
	}
	
	/**
	 * Zwraca warto�� kroku duszka wzd�u� osi X.
	 * @return dx - warto�� kroku duszka wzd�u� osi X
	 */
	public double getDx(){
		return dx;
	}
	
	/**
	 * Zwraca warto�� kroku duszka wzd�u� osi Y.
	 * @return dy - warto�� kroku duszka wzd�u� osi Y
	 */
	public double getDy(){
		return dy;
	}
	
	/**
	 * Ustawia ��dan� warto�� kroku duszka wzd�u� osi X.
	 * @param dgx - ��dana warto�� kroku duszka wzd�u� osi X
	 */
	public void setDx(double dgx){
		dx = dgx;
	}
	
	/**
	 * Ustawia ��dan� warto�� kroku duszka wzd�u� osi Y.
	 * @param dgy - ��dana warto�� kroku duszka wzd�u� osi Y
	 */
	public void setDy(double dgy){
		dy = dgy;
	}
	
	/**
	 * Zwraca kierunek poruszania si� duszka.
	 * @return - kierunek poruszania si� duszka
	 */
	public String getDir(){
		return dir; 
	}
	
	/**
	 * Ustawia ��dany kierunek (left lub right).
	 * @param newdir - ��dany kierunek
	 */
	public void setDir(String newdir){
		dir = newdir;
	}
}
