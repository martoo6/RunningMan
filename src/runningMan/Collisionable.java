package runningMan;

public interface Collisionable{
	public boolean collition(Collisionable colObj);
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
	public void render();
	public void update(int delta);
}
