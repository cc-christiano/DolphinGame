import java.awt.*;

public class Animal {

    public String name;
    public int xpos;
    public int ypos;
    public int dx = 2; //speed in x direction
    public int dy = -1; // speed in y direction
    public int height = 100;
    public int width = 100;
    public Rectangle rec;
    public boolean isBig;
    public boolean isAlive = true;


    public Animal(String paramName, int paramXpos, int paramYpos){
        name = paramName;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void makeBig(){
        isBig = false;
    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(ypos < 0){
            dy = -dy;
        }

        if(ypos > 700){
            dy = -dy;
        }

        if(xpos < 0){
            dx = -dx;
        }

        if(xpos > 1000){
            dx = -dx;
        }
        rec = new Rectangle(xpos, ypos, width, height); //so that the hit box moves with the astronaut
    }

    public void move2() {
        xpos = xpos - dx;
        ypos = ypos - dy;

        if(ypos < 0){
            dy = -dy;
        }

        if(ypos > 700){
            dy = -dy;
        }

        if(xpos < 0){
            dx = -dx;
        }

        if(xpos > 1000){
            dx = -dx;
        }
        rec = new Rectangle(xpos, ypos, width, height); //so that the hit box moves with the astronaut
    }

    public void wrap(){
        xpos = xpos + dx * 2;
        ypos = ypos + dy * 2;

        if (xpos > 1000 && dx > 0){
            xpos = -width;
        }

        if (ypos > 700){
            ypos = -height;
        }

        if(ypos < 0){
            ypos = 700;
        }

        if(xpos < 0 && dx < 0){
            xpos = 1000;
        }
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void upDown(){
        ypos = ypos + dy * 2;

        if (ypos > 700){
            dy = -dy;
        }

        if(ypos < 0){
            dy = -dy;
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void move3() {
        xpos = xpos - dx * 2;
        ypos = ypos - dy * 2;

        if(ypos < 0){
            dy = -dy;
        }

        if(ypos > 300){
            dy = -dy;
        }

        if(xpos < 0){
            dx = -dx;
        }

        if(xpos > 1000){
            dx = -dx;
        }
        rec = new Rectangle(xpos, ypos, width, height); //so that the hit box moves with the astronaut
    }

    public void upDown2(){
        ypos = ypos + dy;

        if (ypos > 200){
            dy = -dy;
        }

        if(ypos < 0){
            dy = -dy;
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }

    }




