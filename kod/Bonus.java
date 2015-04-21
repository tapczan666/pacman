package pacman;

import java.awt.Image;
/**
 * Klasa reprezentuj¹ca bonusy.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Bonus {
	/**
	 * Zmienna okreœlaj¹ca po³o¿enie na osi X.
	 */
	private int x;
	/**
	 * Zmienna okreœlaj¹ca po³o¿enie na osi Y.
	 */
	private int y;
	/**
	 * Zmienna przechowuj¹ca obrazek przedstawiaj¹cy dany bonus.
	 */
	private Image bonusIcon;
	
	/**
	 * Konstruktor klasy Bonus.
	 * @param xx - po³o¿enie bonusa na mapie na osi X
	 * @param yy - po³o¿enie bonusa na mapie na osi Y
	 * @param icon - obrazek reprezentuj¹cy dany bonus
	 */
	public Bonus(int xx, int yy, Image icon){
		x = xx;
		y = yy;
		bonusIcon = icon;
	}
	
	/**
	 * Zwraca po³o¿enie bonusa na osi X.
	 * @return x - po³o¿enie bonusa na osi X
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Zwraca po³o¿enie bonusa na osi Y.
	 * @return - po³o¿enie bonusa na osi Y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Zwraca obrazek przedstawiaj¹cy dany bonus.
	 * @return bonusIcon - obrazek przedstawiaj¹cy bonus
	 */
	public Image getIcon(){
		return bonusIcon;
	}
}
