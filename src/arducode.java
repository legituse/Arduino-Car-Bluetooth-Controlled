import arduino.Arduino;
import arduino.PortDropdownMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class arducode implements ActionListener, KeyListener {
    JFrame frame = new JFrame("Arduino Control");
    JButton up= new JButton("Up Button");
    JButton down= new JButton("Down Button");
    JButton right= new JButton("Right Button");
    JButton left= new JButton("Left Button");
    JButton stop= new JButton("Stop");
    JTextPane tf = new JTextPane();
    JPanel Panel = new JPanel(new GridLayout(3,3));
    PortDropdownMenu comPorts = new PortDropdownMenu();
    JButton connectButton = new JButton("Connect");
    Arduino ArduinoCon;
    //Setting up JButtons

    char Forward = '1';
    char Backward = '2';
    char Left = '3';
    char Right = '4';
    char Stop = '5';
    //Setting up Chars to send to the module


    arducode(){ // constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocation(400, 100);
        String text=("Try All Com Ports, When PC is connected to the Bluetooth Module on the Arduino \n\n\n"
                + "Use Arrrow Keys To Control up, down, left, right\n"
                + "And use Enter To Stop the Car");


        tf.setText(text);
        tf.setEditable(false);

        comPorts.refreshMenu();


        JButton btnRefresh = new JButton("Refresh");
        JPanel topPanel = new JPanel(new GridLayout(0,3));
        //Creating the Gui layout and adding buttons

        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                comPorts.refreshMenu();
            }
        });
        connectButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(connectButton.getText().equals("Connect")){
                    ArduinoCon = new Arduino(comPorts.getSelectedItem().toString(),9600);
                    if(ArduinoCon.openConnection()){
                        connectButton.setText("Disconnect");
                        comPorts.setEnabled(false);
                        btnRefresh.setEnabled(false);
                    }
                }
                else {
                    ArduinoCon.closeConnection();
                    connectButton.setText("Connect");;
                    comPorts.setEnabled(true);
                    btnRefresh.setEnabled(true);
                }
            }

        });



        topPanel.add(comPorts);
        topPanel.add(btnRefresh);
        topPanel.add(connectButton);
        topPanel.transferFocus();

        Panel.add(tf);
        Panel.add(up);
        Panel.add(Box.createHorizontalStrut(10));
        Panel.add(left);
        Panel.add(Box.createHorizontalStrut(10));
        Panel.add(right);
        Panel.add(Box.createHorizontalStrut(10));
        Panel.add(down);
        Panel.add(stop);

        comPorts.resetKeyboardActions();


        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.CENTER, Panel);



        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        stop.addActionListener(this);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    ArduinoCon.serialWrite(Forward);
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    ArduinoCon.serialWrite(Backward);
                }
                if (e.getKeyCode()==KeyEvent.VK_LEFT){
                    ArduinoCon.serialWrite(Left);
                }
                if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                    ArduinoCon.serialWrite(Right);
                }
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    ArduinoCon.serialWrite(Stop);
                }
            }
        });


        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setAutoRequestFocus(true);


    }






    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == up) {
            ArduinoCon.serialWrite(Forward);
        }
        else if (e.getSource() == down) {
            ArduinoCon.serialWrite(Backward);
        }
        else if (e.getSource() == left) {
            ArduinoCon.serialWrite(Left);
        }
        else if (e.getSource() == right) {
            ArduinoCon.serialWrite(Right);
        }
        else if (e.getSource() == stop) {
            ArduinoCon.serialWrite(Stop);
        }
    }



    public static void main(String[] args) throws InterruptedException {


        new arducode();
    }



    @Override
    public void keyPressed(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

}