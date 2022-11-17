import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class 실습13장6번 extends JFrame{
    private ImageIcon icon = new ImageIcon("images/Lenna.png");  //아이콘(이미지) = 버블
    private JLabel label;
    private Container c;
    private MyRunnable runnable;
    private Thread thread;
    
    public 실습13장6번(){
        super("버블 게임");    //프레임 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(null);
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //마우스 클릭 이벤트 설정
                super.mousePressed(e);
                label = new JLabel(icon);
                label.setLocation(e.getX(), e.getY()); //아이콘 위치(마우스를 클릭한 위치) 설정
                label.setSize(icon.getIconWidth(), icon.getIconHeight());
                c.add(label); //아이콘 생성
 
                runnable = new MyRunnable(label);
                thread = new Thread(runnable);
                thread.start();
                repaint();
            }
        });
        setSize(2000, 1000);
        setVisible(true);
    }
 
    public void deleteLabel(JLabel label){
        c.remove(label);
    }
 
    class MyRunnable implements Runnable{
        private JLabel label;
        private boolean flag = false;
        public MyRunnable(JLabel label){
            this.label = label;
        }
        @Override
        public void run() {
            while(true){
                if(flag == true){ //flag는 아이콘의 상태(출력화면 밖에 벗어났을 경우) 
                   label.setVisible(false); //아이콘을 보이지않게함
                    deleteLabel(this.label); //아이콘을 지운다
                }
                else{
                    try{
                        Thread.sleep(20); //이동속도
                        label.setLocation(label.getX(), label.getY()-5); // 아이콘의 Y좌표 5픽셀씩 위치가바뀐
                        if(label.getY()+label.getHeight() < 0){ //아이콘이 출력화면 벗어났을 경우 (아이콘의 Y위치+아이콘의높이 가 0[Y좌표 0 = 출력화면의 가장 위쪽]보다 작을시)
                            this.finish(); //flage=true-> 아이콘 지움.
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        public void finish(){
            flag = true;
        }
    }
    public static void main(String[] args){
        new 실습13장6번();
    }
}
