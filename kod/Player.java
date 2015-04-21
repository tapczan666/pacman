package pacman;

import pacman.Map.Position;

/**
 * Klasa reprezentuj¹ca Pacmana.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Player {
	
	/**
	 * Zmienne u¿ywane do przechowywania po³o¿enia.
	 */
	private double tileX, tileY, oldDx, oldDy;
	/**
	 * Zmienna okreœlaj¹ca kierunek, w którym zwrócony jest Pacman.
	 */
	private String dir;
	
	/**
	 * Konstruktor klasy Player. Tworzy Pacmana w zadanym miejscu.
	 * @param pos - miejsce utworzenia Pacmana
	 */
	public Player(Position pos){
		tileX = pos.tileX;
		tileY = pos.tileY;
		dir = "right";
	}
	
	/**
	 * Zmienia po³o¿enie Pacmana z dok³adnoœci¹ do pola planszy.
	 * @param dx - zmiana po³o¿enia na osi X
	 * @param dy - zmiana po³o¿enia na osi Y
	 */
	public void move(double dx, double dy){
		if((tileY%1 != 0) && (dx != 0)){
			tileY += oldDy;		
		}else if((tileX%1 != 0) && (dy != 0)){
			tileX +=oldDx;			
		}
		else{
			tileX += dx;
			tileY += dy;
			oldDx = dx;
			oldDy = dy;
			if(dx > 0) dir = "right";
			if(dx < 0) dir = "left";
			if(dy < 0) dir = "up";
			if(dy > 0) dir = "down";
		}
	}
	
	/**
	 * Zwraca pozycjê Pacmana na osi X.
	 * @return tileX - pozycja Pacmana na osi X
	 */
	public double getTileX(){
		return tileX;
	}
	
	/**
	 * Zwraca pozycjê Pacmana na osi Y. 
	 * @return tileY - pozycja Pacmana na osi Y
	 */
	public double getTileY(){
		return tileY;
	}
	
	/**
	 * Ustawia now¹ pozycjê Pacmana.
	 * @param pos - nowa pozycja Pacmana
	 */
	public void setPosition(Position pos){
		tileX = pos.tileX;
		tileY = pos.tileY;
	}
	
	/**
	 * Zwraca kierunek Pacmana w formie zmiennej String.
	 * @return dir - kierunek Pacmana
	 */
	public String getDir(){
		return dir; 
	}
	
	/**
	 * Zmienia kierunek poruszania siê Pacmana.
	 * @param newdir - nowy kierunek poruszania
	 */
	public void changeDir(String newdir){
		dir = newdir;
	}
}
