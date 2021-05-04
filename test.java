import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class of test, extends jframe and impliment actionListener
public class test extends JFrame implements ActionListener {

    // define GUI items and some helper items which learn price of choicen item
    private JComboBox cBox;
    private JButton btnAdd, btnBuy;
    private JLabel lblAmount, lblGenre;
    private JTextField txtAmount;
    private JRadioButton rdGold, rdSilver, rdNon;

    // helper items which learn price of choicen item
    private String[][] genre1 = { {"science [10p]","10"}, {"history [15p]","15"}, {"drama [5p]","5"}, {"romance [7p]","7"}, {"action [13p]","13"} };//define double array with name and price to find price of choicen item
    private String[] genre2 = { "science [10p]", "history [15p]","drama [5p]","romance [7p]","action [13p]" };// will add JCombobox item names

    //helper items
    private Double price,amount,totalPrice=0.0;
    private String selectedBook="";
    private String text="";

    public test() {//constructor of test
        setLayout(null);

        //initialize label of genre, set location and size of label and add panel
        lblGenre = new JLabel("Genre");
        lblGenre.setLocation(30, 20);
        lblGenre.setSize(100, 20);
        add(lblGenre);

        //initialize combobox of genre, set location and size of combobox and add panel
        cBox = new JComboBox(genre2);
        cBox.setLocation(30, 55);
        cBox.setSize(150, 25);
        add(cBox);

        //initialize label of amount, set location and size of label and add panel
        lblAmount = new JLabel("Amount");
        lblAmount.setLocation(280, 20);
        lblAmount.setSize(100, 20);
        add(lblAmount);

        //initialize text field of amount, set location and size of text field and add panel
        txtAmount = new JTextField();
        txtAmount.setLocation(280, 55);
        txtAmount.setSize(130, 25);
        add(txtAmount);

        //initialize button of add, set location and size of button and add panel
        btnAdd = new JButton("Add");
        btnAdd.setLocation(500, 55);
        btnAdd.setSize(100, 25);
        add(btnAdd);

        //initialize button of buy, set location and size of button and add panel
        btnBuy = new JButton("Buy");
        btnBuy.setLocation(500, 130);
        btnBuy.setSize(100, 25);
        add(btnBuy);

        ButtonGroup radioGroup=new ButtonGroup();// to select only one of the radio buttons

        //initialize radio button of golden member, set location and size of radio button and add panel and button group
        rdGold = new JRadioButton("Golden Member");
        rdGold.setLocation(30, 200);
        rdGold.setSize(150, 25);
        add(rdGold);
        radioGroup.add(rdGold);

        //initialize radio button of silver member, set location and size of radio button and add panel and button group
        rdSilver = new JRadioButton("Silver Member");
        rdSilver.setLocation(200, 200);
        rdSilver.setSize(150, 25);
        add(rdSilver);
        radioGroup.add(rdSilver);

        //initialize radio button of non-member, set location and size of radio button and add panel and button group
        rdNon = new JRadioButton("Non-member");
        rdNon.setLocation(370, 200);
        rdNon.setSize(150, 25);
        add(rdNon);
        radioGroup.add(rdNon);

        //design panel in terms of size, title, default close oparetion, visible, resizable
        setSize(700, 300);
        setTitle("Book Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        //action listener added to buttons
        btnAdd.addActionListener(this);
        btnBuy.addActionListener(this);

        rdGold.setSelected(true); //default selection of member option
    }//end of the constructor

    public static void main(String[] args) {
        new test();//instance of test class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //to find which button click
        if(e.getSource()==btnAdd){//if click button add, these work
            if(txtAmount.getText().equals("")){//to find text of amount which is null or not and if it is null, this warning message is showed. if not, work else
                JOptionPane.showMessageDialog(this, "Please enter an amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else{
                try {                
                    amount=Double.parseDouble(txtAmount.getText());// to get text of amount and convert to double
                    for (int i = 0; i < genre1.length; i++) {// to find price of selected item
                        if(cBox.getSelectedItem().toString()==genre1[i][0]){
                            selectedBook=genre1[i][0];//find which book selected
                            price=Double.parseDouble(genre1[i][1]);//find price of selected book
                        }
                    }
                totalPrice=totalPrice+price*amount;//add price of book as amount to totalPrice
                text=text+"Type of Book: "+selectedBook+" Amount: "+amount+" Price of a Book: "+price+"\n"; //add text about type of book, how many of them, price of an item to text and prepare to inform about text after clik button buy                 
                }catch (NumberFormatException ex) {//if text of amount is not number,this works 
                    //TODO: handle exception
                    JOptionPane.showMessageDialog(this, "Input Integer Numbers", "Number Format Problem", JOptionPane.ERROR_MESSAGE);
                }                 
            }  
            txtAmount.setText("");                      
        }
        if(e.getSource()==btnBuy){//if click button buy, these work
            Double basketPrice=totalPrice;
            if(rdGold.isSelected()){//these else if blocks is to do sale to basket as selected radio button
                basketPrice=basketPrice*85/100;// if selected gold radio button, give 15% sale of basket
            }else if(rdSilver.isSelected()){
                basketPrice=basketPrice*90/100;// if selected silver radio button, give 10% sale of basket
            }else if(rdNon.isSelected()){
                // if selected non-member radio button, give no sale of basket
            }
            JOptionPane.showMessageDialog(this, text+"Total Price: "+basketPrice ,"Basket", JOptionPane.INFORMATION_MESSAGE);//to show each type of book, how many of them, each price of an item and total price they need to pay
        }
    }//end of the method
}//end of the class