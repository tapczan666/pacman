package pacman;

import java.awt.Image;
/**
 * Klasa reprezentująca bonusy.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Bonus {
	/**
	 * Zmienna określająca położenie na osi X.
	 */
	private int x;
	/**
	 * Zmienna określająca położenie na osi Y.
	 */
	private int y;
	/**
	 * Zmienna przechowująca obrazek przedstawiający dany bonus.
	 */
	private Image bonusIcon;
	
	/**
	 * Konstruktor klasy Bonus.
	 * @param xx - położenie bonusa na mapie na osi X
	 * @param yy - położenie bonusa na mapie na osi Y
	 * @param icon - obrazek reprezentujący dany bonus
	 */
	public Bonus(int xx, int yy, Image icon){
		x = xx;
		y = yy;
		bonusIcon = icon;
	}
	
	/**
	 * Zwraca położenie bonusa na osi X.
	 * @return x - położenie bonusa na osi X
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Zwraca położenie bonusa na osi Y.
	 * @return - położenie bonusa na osi Y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Zwraca obrazek przedstawiający dany bonus.
	 * @return bonusIcon - obrazek przedstawiający bonus
	 */
	public Image getIcon(){
		return bonusIcon;
	}
}
