import java.awt.Color;
import java.awt.Graphics;

public interface ITransport {
	void draw(Graphics g);

	void move(Graphics g);

	void setPosition(int x, int y);

	void setMainColor(Color color);
}