package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Snake {
   JFrame frame;
   final int WIDTH = 1600;
   final int HEIGHT = 800;
  
   int x = 10;
   int y = 10;
   int size = 20;
   int score = 0;
   final int UP = 0;
   final int RIGHT = 1;
   final int DOWN = 2;
   final int LEFT = 3;
   int direction = RIGHT;
   
   int speed = 100;
   
   int x1 = 50;
   int y1 = 10;
   int size1 = 20;
   int score1 = 0;
   final int UP1 = 0;
   final int RIGHT1 = 1;
   final int DOWN1 = 2;
   final int LEFT1 = 3;
   int direction1 = LEFT1;
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////////////
   int jewelX;
   int jewelY;
   int bombX;
   int bombY;
   int fenceX = (WIDTH / size) + 3;
   int fenceY = (HEIGHT / size) + 3;
   int outX = (WIDTH / size) + 2;
   int outY = (HEIGHT / size) + 2;
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ArrayList<Madi> snake = new ArrayList<Madi>();
   ArrayList<Madi> snake1 = new ArrayList<Madi>();
   ArrayList<Jewel> jewels = new ArrayList<Jewel>();
   ArrayList<Bomb> bombs = new ArrayList<Bomb>();

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public Snake() {

      frame = new JFrame("The Game Of Snake");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      MyPanel panel = new MyPanel();
      frame.getContentPane().add(panel);
      
      panel.setBackground(Color.white);
        
      frame.setSize(WIDTH, HEIGHT);
      frame.setVisible(true);
      frame.addKeyListener(new MyKey());
      frame.addKeyListener(new MyKey1());

      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      for (int i = 0; i < 250; i++) {
         jewels.add(new Jewel());
      }
      
      for (int n = 0; n < 25; n++) {
         bombs.add(new Bomb());
      } 
 
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      snake.add(new Madi(x, y));
      /*snake.add(new Madi(x-1, y));
      snake.add(new Madi(x-2, y));
      snake.add(new Madi(x-3, y));*/
      
      snake1.add(new Madi(x1, y1));
      /*snake1.add(new Madi(x1-1, y1));
      snake1.add(new Madi(x1-2, y1));
      snake1.add(new Madi(x1-3, y1));*/
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public void start() {
     
      try { 
         Thread.sleep(5000);    // 조작안내 시간을 5초로 설정
      } catch (InterruptedException e) { 
         e.printStackTrace(); 
      }  
      
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      while (true) {
       
         if (direction == UP) {
            y--;
            if(y == 0) {
               break;
            }
         }
         if (direction == RIGHT) {
            x++;
            if(x == 80) {
               break;
            }
         }
         if (direction == DOWN) {
            y++;
            if(y == 40) {
               break;
            }
         }
         if (direction == LEFT) {
            x--;
            if(x == 0) {
               break;
            }
         }
         
         //////////////////////////////////////////////////////////////////////////////////////
         
         if (direction1 == UP1) {
             y1--;
             if(y1 == 0) {
                break;
             }
         }
         if (direction1 == RIGHT1) {
             x1++;
             if(x1 == 80) {
                break;
             }
         }
         if (direction1 == DOWN1) {
            y1++;
            if(y1 == 40) {
               break;
            }
         }
         if (direction1 == LEFT1) {
            x1--;
            if(x1 == 0) {
               break;
            }
         }         
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         
         Madi lastMadi = snake.get(snake.size()-1);

         for (int i = snake.size()-1; i > 0; i--) {
            snake.set(i, snake.get(i-1));
         }
         snake.set(0, new Madi(x, y));
         
         Madi lastMadi1 = snake1.get(snake1.size()-1);
         
         for (int i = snake1.size()-1; i > 0; i--) {
             snake1.set(i, snake1.get(i-1));
          }
          snake1.set(0, new Madi(x1, y1));
          
         
  
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         for (int i = 0; i < 250; i++) {
            Jewel j = jewels.get(i);
            jewelX = j.getX();
            jewelY = j.getY();

            if ((x == jewelX) && (y == jewelY)) {
               snake.add(snake.size(), lastMadi);
               score++;
               jewels.remove(j);
               jewels.add(new Jewel());
            }
            
            
            else if ((x1 == jewelX) && (y1 == jewelY)) {
                snake1.add(snake1.size(), lastMadi1);
                score1++;
                jewels.remove(j);
                jewels.add(new Jewel());
            }
         
         }
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         for (int n = 0; n < 25; n++) {
            Bomb b = bombs.get(n);
            bombX = b.getX();
            bombY = b.getY();

            if ((x == bombX) && (y == bombY) || (x1 == bombX) && (y1 == bombY))
               break;
            
            
          }
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         if ((x == bombX) && (y == bombY) || (x1 == bombX) && (y1 == bombY)) 
            break;
     
         
         if(score >= 20){
            break;
         }
         
         if(score1 >= 20 ){
            break;
         }
         
         if((x == x1) && (y == y1)) {
            if(score > score1) 
               break;
            
            else if(score1 > score) 
               break;
            
            else
               break;
         }
         
         frame.repaint();
         
         try {
            Thread.sleep(speed);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
 }
      
   
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
        public class Madi {
      private int x;
      private int y;
      
      public Madi(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      public int getX() {
         return x;
      }
      
      public int getY() {
         return y;
      }
   }  
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public class Jewel {
      int x;
      int y;
    
      public Jewel() {
         x = (int) ((Math.random() * WIDTH/size) + 2);
         y = (int) ((Math.random() * HEIGHT/size) + 2);
      }
      
      public int getX() {
         return x;
      }
      
      public int getY() {
         return y;
      }
   }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public class Bomb {
      int x;
      int y;
      
      public Bomb() {
         x = (int) ((Math.random() * WIDTH/size) + 2);
          y = (int) ((Math.random() * HEIGHT/size) + 2);
   
      }
      
      public int getX() {
         return x;
      }
      
      public int getY() {
         return y;
      }
   }
   
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public class MyKey implements KeyListener {

      @Override
      public void keyPressed(KeyEvent key) {
         if (key.getKeyCode() == KeyEvent.VK_W) {
            direction = UP;
         }   
         if (key.getKeyCode() == KeyEvent.VK_D) {
            direction = RIGHT;
         }
         if (key.getKeyCode() == KeyEvent.VK_S) {
            direction = DOWN;
         }
         if (key.getKeyCode() == KeyEvent.VK_A) {
            direction = LEFT;
         }
         if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
         }
      }

      @Override
      public void keyReleased(KeyEvent arg0) {
         
      }

      @Override
      public void keyTyped(KeyEvent arg0) {
         
      }
   }
   
   public class MyKey1 implements KeyListener {

       @Override
       public void keyPressed(KeyEvent key1) {
          if (key1.getKeyCode() == KeyEvent.VK_UP) {
             direction1 = UP1;
          }   
          if (key1.getKeyCode() == KeyEvent.VK_RIGHT) {
             direction1 = RIGHT1;
          }
          if (key1.getKeyCode() == KeyEvent.VK_DOWN) {
             direction1 = DOWN1;
          }
          if (key1.getKeyCode() == KeyEvent.VK_LEFT) {
             direction1 = LEFT1;
          }
          if(key1.getKeyCode() == KeyEvent.VK_ESCAPE) {
             System.exit(0);
          }
        
       }

       @Override
       public void keyReleased(KeyEvent arg01) {
          
       }

       @Override
       public void keyTyped(KeyEvent arg01) {
          
       }
    }
   
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   public class MyPanel extends JPanel {
    
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
        
        g.setColor(Color.RED); 
        g.setFont(new Font("궁서", Font.BOLD, 20));
        g.drawString("조작법: 1P : 방향키          2P : W,A,S,D \n", 100, 920); 
        g.setColor(Color.BLUE); 
        g.drawString("Made by 안현찬, 양기석", 1500, 930); 
        g.setColor(Color.black); 
        g.drawString("폭탄을 먹거나 벽에 부딪히거나 점수 20점 달성시 게임 종료", 100, 950);
        
        g.setColor(Color.red);
        g.drawString("1P 점수 " + score, 1700, 400);
        g.drawString("2P 점수 " + score1, 1700, 600);  
         
         Image png = new ImageIcon("C:\\Users\\Yang\\Desktop\\asdfsa\\bomb.png").getImage();
         Image png1 = new ImageIcon("C:\\Users\\Yang\\Desktop\\asdfsa\\jewel.png").getImage();
         Image png2 = new ImageIcon("C:\\Users\\Yang\\Desktop\\asdfsa\\snakehead.png").getImage();
         Image png3 = new ImageIcon("C:\\Users\\Yang\\Desktop\\asdfsa\\snakehead2.png").getImage();
         Image png4 = new ImageIcon("C:\\Users\\Yang\\Desktop\\asdfsa\\tree.png").getImage();
         
         g.setFont(new Font("궁서", Font.BOLD, 23));
         for (Madi madi : snake) {
           if(madi.x == 1) {
              g.drawString("2P Win", 500, 350);
              break;
           }
           if(madi.x == 79) {
              g.drawString("2P Win", 500, 350);
              break;
           }
           if(madi.y == 1) {
              g.drawString("2P Win", 500, 350);
              break;
           }
           if(madi.y == 39) {
              g.drawString("2P Win", 500, 350);
              break;
           }
       
           if(snake.indexOf(madi) == 0) {
              g.drawImage(png3 ,madi.getX()*size, madi.getY()*size, size, size, null);
           }
           else {
              g.setColor(Color.RED);
              g.fillRect(madi.getX()*size, madi.getY()*size, size, size);
           }
         }
        
         for (Madi madi2 : snake1) {
            if(madi2.x == 1) {
               g.drawString("1P Win", 500, 350);
               break;
            }
            if(madi2.x == 79) {
               g.drawString("1P Win", 500, 350);
               break;
            }
            if(madi2.y == 1) {
               g.drawString("1P Win", 500, 350);
               break;
            }
            if(madi2.y == 39) {
               g.drawString("1P Win", 500, 350);
               break;
            }
        
            if(snake1.indexOf(madi2) == 0) {
               g.drawImage(png2 ,madi2.getX()*size1, madi2.getY()*size1, size1, size1, null);
            }
            else {
               g.setColor(Color.GREEN);
               g.fillRect(madi2.getX()*size1, madi2.getY()*size1, size1, size1);         
            }
         }
         
         g.setColor(Color.white);         
         for (Madi madi : snake) {
            g.drawRect(madi.getX()*size, madi.getY()*size, size, size);
         }
         for (Madi madi2 : snake1) {
             g.drawRect(madi2.getX()*size1, madi2.getY()*size1, size1, size1);  
          }
         
         for (Jewel j : jewels) {
            g.drawImage(png1, j.getX()*size, j.getY()*size, size, size, null);
          }
     
         for (Bomb b : bombs) {
            g.drawImage(png, b.getX()*size, b.getY()*size, size, size, null);        
         }
        

         for(int k = 0; k <= fenceX; k++) {
            g.drawImage(png4, k*size, 0, size, size, null);
            g.drawImage(png4, k*size, size, size, size, null);
            g.drawImage(png4, k*size, (fenceY - 1)*size, size, size, null);
            g.drawImage(png4, k*size, fenceY*size, size, size, null);
         }
         for(int l = 0; l <= fenceY; l++) {
            g.drawImage(png4, 0, l*size, size, size, null);
            g.drawImage(png4, size, l*size, size, size, null);
            g.drawImage(png4, (fenceX - 1)*size, l*size, size, size, null);
            g.drawImage(png4, fenceX*size, l*size, size, size, null);
         }

         
         
         g.setColor(Color.RED);
         
         if ((x == bombX) && (y == bombY)){
             g.drawString("2P Win", 1000, 500);
          }
         if ((x1 == bombX) && (y1 == bombY)){
             g.drawString("1P Win", 200, 500);
          }
         
         
         if((x == x1) && (y == y1)) {
             if(score > score1) {
                g.drawString("1P Win", 200, 500);
             }
             else if(score < score1)	{ 
                g.drawString("2P Win", 1000, 500);
             }
             else	{ 
                g.drawString("DRAW", 600, 500);
             }
          }
       
         if(score >= 20) {
            g.drawString("1P Win", 200, 500);
         }
         if(score1 >= 20) {
            g.drawString("2P Win", 1000, 500);
         }
         
                           
    }  
   } 
}