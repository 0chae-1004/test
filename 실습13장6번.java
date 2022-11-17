import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class �ǽ�13��6�� extends JFrame{
    private ImageIcon icon = new ImageIcon("images/Lenna.png");  //������(�̹���) = ����
    private JLabel label;
    private Container c;
    private MyRunnable runnable;
    private Thread thread;
    
    public �ǽ�13��6��(){
        super("���� ����");    //������ ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(null);
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //���콺 Ŭ�� �̺�Ʈ ����
                super.mousePressed(e);
                label = new JLabel(icon);
                label.setLocation(e.getX(), e.getY()); //������ ��ġ(���콺�� Ŭ���� ��ġ) ����
                label.setSize(icon.getIconWidth(), icon.getIconHeight());
                c.add(label); //������ ����
 
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
                if(flag == true){ //flag�� �������� ����(���ȭ�� �ۿ� ����� ���) 
                   label.setVisible(false); //�������� �������ʰ���
                    deleteLabel(this.label); //�������� �����
                }
                else{
                    try{
                        Thread.sleep(20); //�̵��ӵ�
                        label.setLocation(label.getX(), label.getY()-5); // �������� Y��ǥ 5�ȼ��� ��ġ���ٲ�
                        if(label.getY()+label.getHeight() < 0){ //�������� ���ȭ�� ����� ��� (�������� Y��ġ+�������ǳ��� �� 0[Y��ǥ 0 = ���ȭ���� ���� ����]���� ������)
                            this.finish(); //flage=true-> ������ ����.
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
        new �ǽ�13��6��();
    }
}
