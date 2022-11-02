
public class Weapon {
	public String type;
	public int damage;
	public Weapon(String weaponType) {
		type = weaponType;
		if (type.equals("fists")) {
			damage = 10;
		}
		else if (type.equals("pick")) {
			damage = 15;
		}
		else if (type.equals("sword")) {
			damage = 30;
		}
		else if (type.equals("gun")) {
			damage = 20;
		}
	}
}
