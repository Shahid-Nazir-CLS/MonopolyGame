import javax.swing.JPanel;

public class Token {

	private int direction = 1;
	private int locX = 0;
	private int locY = 0;
    JPanel square;
    private String tokenColor;

	public void Token(){
	}

    public String getTokenColor() {
        return this.tokenColor;
    }

    public void setTokenColor(String tokenColor) {
        this.tokenColor = tokenColor;
    }

	public int getlocX() {
        return this.locX;
    }

    public void setlocX(int locX) {
        this.locX = locX;
    }

    public int getlocY() {
        return this.locY;
    }

    public void setlocY(int locY) {
        this.locY = locY;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}