// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

    public class BasicGame implements Runnable {

        //Variable Definition Section
        //Declare the variables used in the program
        //You can set their initial values too

        //Sets the width and height of the program window
        final int WIDTH = 1000;
        final int HEIGHT = 700;

        //Declare the variables needed for the graphics
        public JFrame frame;
        public Canvas canvas;
        public JPanel panel;

        public BufferStrategy bufferStrategy;

        public Animal denny;
        public Image dennyPic;
        public Image ocean;
        public Animal shark;
        public Image sharkPic;
        public Animal pufferfish;
        public Image pufferfishBigPic;
        public Image pufferfishSmallPic;
        public Animal mermaid;
        public Image mermaidPic;
        public Animal salmon;
        public Image salmonPic;
        public Animal net;
        public Image netPic;




        // Main method definition
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            BasicGame ex = new BasicGame();   //creates a new instance of the game
            new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
        }


        // This section is the setup portion of the program
        // Initialize your variables and construct your program objects here.
        public BasicGame() { // BasicGameApp constructor

            setUpGraphics();
            denny = new Animal("denny", 200,300);
            dennyPic = Toolkit.getDefaultToolkit().getImage("denny.png");
            ocean = Toolkit.getDefaultToolkit().getImage("ocean.jpeg");
            shark = new Animal("shark", 400,300);
            sharkPic = Toolkit.getDefaultToolkit().getImage("shark.png");
            pufferfish = new Animal ("pufferfish", 400, 600);
            pufferfishBigPic = Toolkit.getDefaultToolkit().getImage("pufferfishBig.png");
            pufferfishSmallPic = Toolkit.getDefaultToolkit().getImage("pufferfishSmall.png");
            mermaid = new Animal ("mermaid", 800, 200);
            mermaidPic = Toolkit.getDefaultToolkit().getImage("mermaid.png");
            salmon = new Animal ("salmon", 200, 200);
            salmonPic = Toolkit.getDefaultToolkit().getImage("salmon.png");
            net = new Animal ("net", 100, 0);
            netPic = Toolkit.getDefaultToolkit().getImage("net2.png");
            //variable and objects
            //create (construct) the objects needed for the game

        } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

        // main thread
        // this is the code that plays the game after you set things up
        public void run() {
            //for the moment we will loop things forever.
            while (true) {
                moveThings();  //move all the game objects
                render();  // paint the graphics
                pause(10); // sleep for 10 ms
                collisions();
            }
        }

        public void moveThings() {
            denny.move();
            shark.move2();
            pufferfish.wrap();
            mermaid.upDown();
            salmon.move3();
            net.upDown2();

            //call the move() code for each object
        }

        public void collisions(){
            //check whether oliver and hales are colliding
            //if they collide reverse their directions
            if(denny.rec.intersects(shark.rec)){
                denny.dx = -denny.dx;
                denny.dy = -denny.dy;
                shark.dx = -shark.dx;
                shark.dy = -shark.dy;
            }

            if(pufferfish.rec.intersects(shark.rec)){
                pufferfish.isBig = true;

            }

            if(denny.rec.intersects(mermaid.rec)){
                denny.dx = -denny.dx;
                denny.dy = -denny.dy;
            }

            if(salmon.rec.intersects(net.rec)){
                salmon.isAlive = false;
                salmon = new Animal ("salmon", (int)(Math.random()*1000), (int)(Math.random()*300));

            }

        }

        //Paints things on the screen using bufferStrategy
        private void render() {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(ocean, 0,0,1000, 700, null);
            g.drawImage(dennyPic, denny.xpos, denny.ypos, denny.width, denny.height, null);
            g.drawImage(sharkPic, shark.xpos, shark.ypos, shark.width, shark.height, null);

            if(pufferfish.isBig == true) {
                g.drawImage(pufferfishBigPic, pufferfish.xpos, pufferfish.ypos, pufferfish.width, pufferfish.height, null);
            }
            else {
                g.drawImage(pufferfishSmallPic, pufferfish.xpos, pufferfish.ypos, pufferfish.width, pufferfish.height, null);
            }
            g.drawImage(mermaidPic, mermaid.xpos, mermaid.ypos, mermaid.width, mermaid.height, null);

            if(shark.rec.intersects(mermaid.rec)){
                shark.dx = -shark.dx;
                shark.dy = -shark.dy;
            }
            if(pufferfish.rec.intersects(mermaid.rec) && pufferfish.isBig == true){
                pufferfish.isBig = false;
                g.drawImage(pufferfishSmallPic, pufferfish.xpos, pufferfish.ypos, pufferfish.width, pufferfish.height, null);
            }
            if( salmon.isAlive == true){
                g.drawImage(salmonPic, salmon.xpos, salmon.ypos, salmon.width, salmon.height, null);
            }

            g.drawImage(netPic, net.xpos, net.ypos, net.width, net.height, null);

            //draw the images

            g.dispose();
            bufferStrategy.show();
        }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time ) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
        }

        //Graphics setup method
        private void setUpGraphics() {
            frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");
        }

    }


