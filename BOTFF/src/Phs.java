import Entitys.EntityA;
import Entitys.EntityB;
import Entitys.EntityC;
import Entitys.EntityD;
import Entitys.EntityE;
public class Phs {
	public static boolean Collision(EntityA enta, EntityE ente) 
	{
		// player to drop
		if (enta.getBounds().intersects(ente.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityA enta, EntityB entb) 
	{
		// player/bullet to enemy
		if (enta.getBounds().intersects(entb.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityB entb, EntityA enta) 
	{
		// enemy to player
		if (entb.getBounds().intersects(enta.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityB entb, EntityC entc) 
	{
		// enemy to wall
		if (entb.getBounds().intersects(entc.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityA enta, EntityC entc) 
	{
		// player to wall
		if (enta.getBounds().intersects(entc.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityA enta, EntityD entd) 
	{
		// player to enemy bullet
		if (enta.getBounds().intersects(entd.getBounds()))
			return true;
		return false;
	}
	public static boolean Collision(EntityE ente, EntityC entc) {// player to enemy bullet
		if (ente.getBounds().intersects(entc.getBounds()))
			return true;
		return false;
	}
}