package pacman;

import java.awt.Image;
/**
 * Klasa reprezentuj�ca bonusy.
 * @author Maciek W�jcik & Kuba Wilkowski
 *
 */
public class Bonus {
	/**
	 * Zmienna okre�laj�ca po�o�enie na osi X.
	 */
	private int x;
	/**
	 * Zmienna okre�laj�ca po�o�enie na osi Y.
	 */
	private int y;
	/**
	 * Zmienna przechowuj�ca obrazek przedstawiaj�cy dany bonus.
	 */
	private Image bonusIcon;
	
	/**
	 * Konstruktor klasy Bonus.
	 * @param xx - po�o�enie bonusa na mapie na osi X
	 * @param yy - po�o�enie bonusa na mapie na osi Y
	 * @param icon - obrazek reprezentuj�cy dany bonus
	 */
	public Bonus(int xx, int yy, Image icon){
		x = xx;
		y = yy;
		bonusIcon = icon;
	}
	
	/**
	 * Zwraca po�o�enie bonusa na osi X.
	 * @return x - po�o�enie bonusa na osi X
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Zwraca po�o�enie bonusa na osi Y.
	 * @return - po�o�enie bonusa na osi Y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Zwraca obrazek przedstawiaj�cy dany bonus.
	 * @return bonusIcon - obrazek przedstawiaj�cy bonus
	 */
	public Image getIcon(){
		return bonusIcon;
	}
}
