import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.border.LineBorder;

public class Ui extends JFrame {

    int boardWidth = 360;
    int boardHeight =540; 

    String[] buttonsvalues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    Color custumlightfray = new Color(212,212,210);
    Color custdarkgtar = new Color(80,80,80);
    Color customBlack = new Color(28,28,28);
    Color customOranf = new Color(255,149,0);

    JFrame frame = new JFrame("Calculator");
    JLabel dislabel = new JLabel();
    JPanel dispanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;



   

    Ui() {

       //frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        dislabel.setBackground(customBlack);
        dislabel.setForeground(Color.white);
        dislabel.setFont(new Font("Arial",Font.PLAIN,80));
        dislabel.setHorizontalAlignment(JLabel.RIGHT);
        dislabel.setText("0");
        dislabel.setOpaque(true);

        dispanel.setLayout(new BorderLayout());
        dispanel.add(dislabel);
        frame.add(dispanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for(int i=0; i<buttonsvalues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonsvalues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(custumlightfray);
                button.setForeground(customBlack);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOranf);
                button.setForeground(Color.white);
            }
            else{
                button.setBackground(custdarkgtar);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);

              button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonvalue = button.getText();

                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue == "="){
                            if(A != null){
                                B = dislabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(operator == "+"){
                                    dislabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if(operator == "-"){
                                    dislabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if(operator == "×"){
                                    dislabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if(operator == "÷"){
                                    dislabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }
                        } 
                        else if("+-×÷".contains(buttonValue)){
                            if(operator == null){
                                A = dislabel.getText();
                                dislabel.setText("0");
                                B= "0";
                            }
                            operator = buttonValue;
                        }
                    }

                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue == "AC"){
                            clearAll();
                            dislabel.setText("0");
                        }
                        else if(buttonValue=="+/-"){
                            double numDisplay = Double.parseDouble(dislabel.getText());
                            numDisplay *= -1;
                            dislabel.setText(removeZeroDecimal(numDisplay));
                        }
                        else if(buttonValue == "%"){
                            double numDisplay = Double.parseDouble(dislabel.getText());
                            numDisplay /= 100;
                            dislabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }

                    else {
                        if(buttonValue == "."){
                           if(!dislabel.getText().contains(buttonValue)){
                            dislabel.setText(dislabel.getText() + buttonValue);
                           }
                        }

                        else if("0123456789".contains(buttonValue)){
                            if(dislabel.getText()=="0"){
                                dislabel.setText(buttonValue);
                            }
                            else{
                                dislabel.setText(dislabel.getText() + buttonvalue);
                            }
                        }
                    }
                }
              });
              frame.setVisible(true);
        }

      
     
    }

    void clearAll(){
        A= "0";
        operator = null;
        B = null;
    }

    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int)numDisplay);
        }
        return Double.toString(numDisplay);
    }
}

    




  