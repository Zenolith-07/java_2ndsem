
/**
 * Write a description of class GymGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//Importing all necessary package
//Importing all necessary package
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GymGUI implements ActionListener
{
    private JFrame mainFrame,regularFrame,premiumFrame;
    private JPanel mainHeader,mainLeft,mainRight,mainButtons, mainCenter,regularLabels,regularButtons,regularHeader,regularLeft,regularRight,dobPanel,msdPanel,genderPanel,preiumLabels,premiumButtons,premiumHeader,premiumLeft,premiumRight,pDobpanel,pMsdpanel,pgenderpanel;
    private JLabel welcomeText,headerLabel,idLabel, nameLabel, phoneLabel, locationLabel, emailLabel,genderLabel,DOBLabel,MSBLabel,referralLabel,amountLabel,planLabel,priceLabel,pheaderLabel,pIdlabel,pNameLabel, pPhoneLabel, pLocationLabel, pEmailLabel,pGenderLabel,pDOBLabel,pMSBLabel,pTrainerLabel,pPriceLabel,pAmountLabel;
    private JTextField idField, nameField, phoneField, locationField, emailField,DOBField,MSBField,referralField,amountField,priceField,pIdField, pNameField, pPhoneField, pLocationField, pEmailField,pDOBField,pMSBField,pPriceField,pAmountField,pTrainerField;
    private JTextArea info;
    private JRadioButton maleRadioButton, femaleRadioButton,pMaleRadioButton,pFemaleRadioButton;
    private JComboBox planComboBox,dobYearComboBox, dobMonthComboBox, dobDayComboBox,msYearComboBox, msMonthComboBox, msDayComboBox,pdobYearComboBox, pdobMonthComboBox, pdobDayComboBox,pmsYearComboBox, pmsMonthComboBox, pmsDayComboBox;
    private JButton regular,premium,activate,deactivate,revertRegular,revertPremium,attendance,backButton, saveButton, displayButton, clearButton,pbackButton, psaveButton, pdisplayButton, pclearButton;
    private ButtonGroup group, pgroup;
    private ArrayList <GymMember> list=new ArrayList<GymMember>();
    private ArrayList<Integer> idlist = new ArrayList<>();
    public GymGUI(){
        mainFrame=new JFrame ("GYM Management System");
        mainFrame.setVisible(true);
        mainFrame.setSize(1500,1500);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        mainHeader =new JPanel();
        mainHeader.setPreferredSize(new Dimension(600,100));
        mainHeader.setBackground(Color.cyan);
        mainFrame.add(mainHeader,BorderLayout.NORTH);
       
        mainLeft=new JPanel();
        mainLeft.setPreferredSize(new Dimension(150,250));
        mainLeft.setBackground(Color.cyan);
        mainFrame.add(mainLeft,BorderLayout.WEST);
       
        //Right panel
        mainRight=new JPanel();
        mainRight.setPreferredSize(new Dimension(150,150));
        mainRight.setBackground(Color.cyan);
        mainFrame.add(mainRight,BorderLayout.EAST);
       
        //center panel
       
        mainButtons=new JPanel();
        mainButtons.setPreferredSize(new Dimension(600,400));
        mainButtons.setBackground(Color.cyan);
        mainFrame.add(mainButtons,BorderLayout.CENTER);
       
        welcomeText=new JLabel("Gym Management System");
        welcomeText.setFont(new Font("Georgia",Font.BOLD,52));
        welcomeText.setForeground(Color.WHITE);
        mainHeader.add(welcomeText);
       
        
       
        regular=new JButton("Add Regular Member");
        regular.setFont(new Font("Georgia", Font.BOLD, 14));
        regular.setBackground(Color.WHITE);
        regular.addActionListener(this);
       
        premium=new JButton("Add Premium Member");
        premium.setFont(new Font("Georgia", Font.BOLD, 14));
        premium.setBackground(Color.WHITE);
        premium.addActionListener(this);
       
        activate=new JButton("Activate Membership");
        activate.setFont(new Font("Georgia", Font.BOLD, 14));
        activate.setBackground(Color.WHITE);
        activate.addActionListener(this);
       
        deactivate=new JButton("Deactivate Membership");
        deactivate.setFont(new Font("Georgia", Font.BOLD, 14));
        deactivate.setBackground(Color.WHITE);
        deactivate.addActionListener(this);
       
        revertPremium=new JButton("Revert Regular Member");
        revertPremium.setFont(new Font("Georgia", Font.BOLD, 14));
        revertPremium.setBackground(Color.WHITE);
        revertPremium.addActionListener(this);
       
        revertRegular=new JButton("Revert Premium Member");
        revertRegular.setFont(new Font("Georgia", Font.BOLD, 14));
        revertRegular.setBackground(Color.WHITE);
        revertRegular.addActionListener(this);
       
        attendance=new JButton("Mark Attendance");
        attendance.setFont(new Font("Georgia", Font.BOLD, 14));
        attendance.setBackground(Color.WHITE);
        attendance.addActionListener(this);
   
        mainButtons.setLayout(new GridBagLayout());
        GridBagConstraints buttons=new GridBagConstraints();
        buttons.insets=new Insets(30,30,30,30);
       
        buttons.gridx=0;
        buttons.gridy=0;
        mainButtons.add(regular,buttons);
       
        buttons.gridx=1;
        buttons.gridy=0;
        mainButtons.add(premium,buttons);
       
        buttons.gridx=2;
        buttons.gridy=0;
        mainButtons.add(activate,buttons);
       
        buttons.gridx=3;
        buttons.gridy=0;
        mainButtons.add(deactivate,buttons);
       
        buttons.gridx=0;
        buttons.gridy=1;
        mainButtons.add(revertRegular,buttons);
       
        buttons.gridx=1;
        buttons.gridy=1;
        mainButtons.add(revertPremium,buttons);
       
        buttons.gridx=2;
        buttons.gridy=1;
        mainButtons.add(attendance,buttons);
       
        //Regular Member Frame
        regularFrame= new JFrame("Gym");
        //header panel
        regularHeader =new JPanel();
        regularHeader.setPreferredSize(new Dimension(600,100));
        regularHeader.setBackground(Color.CYAN);
        regularFrame.add(regularHeader,BorderLayout.NORTH);
       
        //left panel
        regularLeft=new JPanel();
        regularLeft.setPreferredSize(new Dimension(400,400));
        regularLeft.setBackground(Color.cyan);
        regularFrame.add(regularLeft,BorderLayout.WEST);
       
        //Right panel
        regularRight=new JPanel();
        regularRight.setPreferredSize(new Dimension(400,400));
        regularRight.setBackground(Color.cyan);
        regularFrame.add(regularRight,BorderLayout.EAST);
       
        //center panel
        regularLabels=new JPanel();
        regularLabels.setBackground(Color.WHITE);
        regularLabels.setPreferredSize(new Dimension(500,500));
        regularFrame.add(regularLabels,BorderLayout.CENTER);
       
        //text for header
        headerLabel=new JLabel("Regular Member");
        headerLabel.setFont(new Font("Georgia",Font.BOLD,45));
        headerLabel.setForeground(Color.WHITE);
        regularHeader.add(headerLabel);
       
        //labels and text field
        idLabel=new JLabel("ID:");
        idLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        idField=new JTextField();
        idField.setPreferredSize(new Dimension(250,23));
       
        nameLabel=new JLabel("Name:");
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        nameField=new JTextField();
        nameField.setPreferredSize(new Dimension(250,23));
       
        locationLabel=new JLabel("Location:");
        locationLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        locationField=new JTextField();
        locationField.setPreferredSize(new Dimension(250,23));
       
        phoneLabel=new JLabel("Phone:");
        phoneLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        phoneField=new JTextField();
        phoneField.setPreferredSize(new Dimension(250,20));
       
        emailLabel=new JLabel("Email:");
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        emailField=new JTextField();
        emailField.setPreferredSize(new Dimension(250,23));
       
        genderLabel=new JLabel("Gender:");
        genderLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        maleRadioButton=new JRadioButton("Male");
        maleRadioButton.setBackground(Color.WHITE);
        maleRadioButton.setPreferredSize(new Dimension(70,23));
       
        femaleRadioButton=new JRadioButton("Female");
        femaleRadioButton.setBackground(Color.WHITE);
        femaleRadioButton.setPreferredSize(new Dimension(70,23));
       
        group=new ButtonGroup();
        group.add(maleRadioButton);
        group.add(femaleRadioButton);
       
        DOBLabel=new JLabel("Date of Birth:");
        DOBLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        String[] years={"2025","2024","2023","2022","2021","2020","2019","2014","2017","2014","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998"};
        dobYearComboBox=new JComboBox<>(years);
        dobYearComboBox.setPreferredSize(new Dimension(100,23));
        dobYearComboBox.setBackground(Color.WHITE);
       
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        dobMonthComboBox = new JComboBox<>(months);
        dobMonthComboBox.setPreferredSize(new Dimension(100,23));
        dobMonthComboBox.setBackground(Color.WHITE);
       
       
        String[] days= {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10","11", "12", "13", "14", "15", "16", "17", "14", "19", "20","21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31"};
        dobDayComboBox=new JComboBox<>(days);
        dobDayComboBox.setPreferredSize(new Dimension(50,23));
        dobDayComboBox.setBackground(Color.WHITE);
       
       
        MSBLabel=new JLabel("Membership Start Date:");
        MSBLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        msYearComboBox=new JComboBox<>(years);
        msYearComboBox.setBackground(Color.WHITE);
        msYearComboBox.setPreferredSize(new Dimension(100,23));
       
        msMonthComboBox=new JComboBox<>(months);
        msMonthComboBox.setPreferredSize(new Dimension(100,23));
        msMonthComboBox.setBackground(Color.WHITE);
       
        msDayComboBox=new JComboBox<>(days);
        msDayComboBox.setPreferredSize(new Dimension(50,23));
        msDayComboBox.setBackground(Color.WHITE);
       
       
        referralLabel=new JLabel("Referral Source:");
        referralLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        referralField=new JTextField();
        referralField.setPreferredSize(new Dimension(250,20));
       
        amountLabel=new JLabel("Paid Amount:");
        amountLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        amountField=new JTextField();
        amountField.setPreferredSize(new Dimension(250,23));
       
        planLabel=new JLabel("Plans:");
        planLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        planComboBox=new JComboBox();
        planComboBox.addItem("Basic");
        planComboBox.addItem("Standard");
        planComboBox.addItem("Deluxe");
        planComboBox.setPreferredSize(new Dimension(250,23));
        planComboBox.setBackground(Color.white);
       
        priceLabel=new JLabel("Regular Plan Price:");
        priceLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        priceField=new JTextField("6500");
        priceField.setEditable(false);
        priceField.setPreferredSize(new Dimension(250,23));
        priceField.setBackground(Color.WHITE);
       
        regularLabels.setLayout(new GridBagLayout());
        GridBagConstraints gbs=new GridBagConstraints();
        gbs.insets=new Insets(5,5,5,5);
        gbs.fill = GridBagConstraints.HORIZONTAL;
       
        gbs.gridx=0;
        gbs.gridy=0;
        regularLabels.add(idLabel,gbs);
        gbs.gridx=1;
        gbs.gridwidth=1;
        regularLabels.add(idField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=1;
        regularLabels.add(nameLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(nameField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=2;
        regularLabels.add(locationLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(locationField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=3;
        regularLabels.add(phoneLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(phoneField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=4;
        regularLabels.add(emailLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(emailField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=5;
        regularLabels.add(genderLabel,gbs);
        gbs.gridx=1;
        genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        regularLabels.add(genderPanel, gbs);

       
        gbs.gridx=0;
        gbs.gridy=6;
        regularLabels.add(DOBLabel,gbs);
        gbs.gridx=1;
        dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.setBackground(Color.WHITE);
        dobPanel.add(dobYearComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobDayComboBox);
        regularLabels.add(dobPanel, gbs);
       
       
        gbs.gridx=0;
        gbs.gridy=7;
        regularLabels.add(MSBLabel,gbs);
        gbs.gridx=1;
        msdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        msdPanel.setBackground(Color.WHITE);
        msdPanel.add(msYearComboBox);
        msdPanel.add(msMonthComboBox);
        msdPanel.add(msDayComboBox);
        regularLabels.add(msdPanel, gbs);

       
        gbs.gridx=0;
        gbs.gridy=8;
        regularLabels.add(referralLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(referralField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=9;
        regularLabels.add(amountLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(amountField,gbs);
       
        gbs.gridx=0;
        gbs.gridy=10;
        regularLabels.add(planLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(planComboBox,gbs);
       
        gbs.gridx=0;
        gbs.gridy=11;
        regularLabels.add(priceLabel,gbs);
        gbs.gridx=1;
        regularLabels.add(priceField,gbs);
       
        regularButtons=new JPanel();
        regularButtons.setPreferredSize(new Dimension(600,100));
        regularButtons.setBackground(Color.cyan);
        regularFrame.add(regularButtons,BorderLayout.SOUTH);
       
        backButton=new JButton("Back");
        backButton.setFont(new Font("Georgia", Font.BOLD, 14));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(this);
       
        saveButton=new JButton("Add");
        saveButton.setFont(new Font("Georgia", Font.BOLD, 14));
        saveButton.setBackground(Color.WHITE);
        saveButton.addActionListener(this);
       
        displayButton=new JButton("Display");
        displayButton.setFont(new Font("Georgia", Font.BOLD, 14));
        displayButton.setBackground(Color.WHITE);
        displayButton.addActionListener(this);
       
       
        clearButton=new JButton("Clear");
        clearButton.setFont(new Font("Georgia", Font.BOLD, 14));
        clearButton.setBackground(Color.WHITE);
        clearButton.addActionListener(this);
       
        regularButtons.setLayout(new GridBagLayout());
        GridBagConstraints buttonGBC=new GridBagConstraints();
        buttonGBC.insets=new Insets(30,30,30,30);
       
        buttonGBC.gridx=0;
        buttonGBC.gridy=0;
        regularButtons.add(backButton,buttonGBC);
       
        buttonGBC.gridx=1;
        buttonGBC.gridy=0;
        regularButtons.add(saveButton,buttonGBC);
       
        buttonGBC.gridx=2;
        buttonGBC.gridy=0;
        regularButtons.add(displayButton,buttonGBC);
       
        buttonGBC.gridx=3;
        buttonGBC.gridy=0;
        regularButtons.add(clearButton,buttonGBC);
        regularFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
   
       
        premiumFrame=new JFrame("Premium Member");
        premiumFrame.setSize(1500,1500);
        premiumFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        premiumHeader =new JPanel();
        premiumHeader.setPreferredSize(new Dimension(600,100));
        premiumHeader.setBackground(Color.cyan);
        premiumFrame.add(premiumHeader,BorderLayout.NORTH);
       
        //left panel
        premiumLeft=new JPanel();
        premiumLeft.setPreferredSize(new Dimension(400,400));
        premiumLeft.setBackground(Color.cyan);
        premiumFrame.add(premiumLeft,BorderLayout.WEST);
       
        //Right panel
        premiumRight=new JPanel();
        premiumRight.setPreferredSize(new Dimension(400,400));
        premiumRight.setBackground(Color.cyan);
        premiumFrame.add(premiumRight,BorderLayout.EAST);
       
        //center panel
        preiumLabels=new JPanel();
        preiumLabels.setBackground(Color.WHITE);
        preiumLabels.setPreferredSize(new Dimension(500,500));
        premiumFrame.add(preiumLabels,BorderLayout.CENTER);
       
        //text for header
        pheaderLabel=new JLabel("Premium Member");
        pheaderLabel.setFont(new Font("Georgia",Font.BOLD,45));
        pheaderLabel.setForeground(Color.WHITE);
        premiumHeader.add(pheaderLabel);
       
        //labels and text field
        pIdlabel=new JLabel("ID:");
        pIdlabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pIdField=new JTextField();
        pIdField.setPreferredSize(new Dimension(250,23));
       
        pNameLabel=new JLabel("Name:");
        pNameLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pNameField=new JTextField();
        pNameField.setPreferredSize(new Dimension(250,23));
       
        pLocationLabel=new JLabel("Location:");
        pLocationLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pLocationField=new JTextField();
        pLocationField.setPreferredSize(new Dimension(250,23));
       
        pPhoneLabel=new JLabel("Phone:");
        pPhoneLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pPhoneField=new JTextField();
        pPhoneField.setPreferredSize(new Dimension(250,20));
       
        pEmailLabel=new JLabel("Email:");
        pEmailLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pEmailField=new JTextField();
        pEmailField.setPreferredSize(new Dimension(250,23));
       
        pGenderLabel=new JLabel("Gender:");
        pGenderLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        pMaleRadioButton=new JRadioButton("Male");
        pMaleRadioButton.setBackground(Color.WHITE);
        pMaleRadioButton.setPreferredSize(new Dimension(70,23));
       
        pFemaleRadioButton=new JRadioButton("Female");
        pFemaleRadioButton.setBackground(Color.WHITE);
        pFemaleRadioButton.setPreferredSize(new Dimension(70,23));
       
        pgroup=new ButtonGroup();
        pgroup.add(pMaleRadioButton);
        pgroup.add(pFemaleRadioButton);
       
        pDOBLabel=new JLabel("Date of Birth:");
        pDOBLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        pdobYearComboBox=new JComboBox<>(years);
        pdobYearComboBox.setPreferredSize(new Dimension(100,23));
        pdobYearComboBox.setBackground(Color.WHITE);
       
        pdobMonthComboBox = new JComboBox<>(months);
        pdobMonthComboBox.setPreferredSize(new Dimension(100,23));
        pdobMonthComboBox.setBackground(Color.WHITE);
       
       
        pdobDayComboBox=new JComboBox<>(days);
        pdobDayComboBox.setPreferredSize(new Dimension(50,23));
        pdobDayComboBox.setBackground(Color.WHITE);
       
       
        pMSBLabel=new JLabel("Membership Start Date:");
        pMSBLabel.setFont(new Font("Georgia", Font.BOLD, 14));
       
        pmsYearComboBox=new JComboBox<>(years);
        pmsYearComboBox.setBackground(Color.WHITE);
        pmsYearComboBox.setPreferredSize(new Dimension(100,23));
       
        pmsMonthComboBox=new JComboBox<>(months);
        pmsMonthComboBox.setPreferredSize(new Dimension(100,23));
        pmsMonthComboBox.setBackground(Color.WHITE);
       
        pmsDayComboBox=new JComboBox<>(days);
        pmsDayComboBox.setPreferredSize(new Dimension(50,23));
        pmsDayComboBox.setBackground(Color.WHITE);
       
       
        pAmountLabel=new JLabel("Paid Amount:");
        pAmountLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pAmountField=new JTextField();
        pAmountField.setPreferredSize(new Dimension(250,23));
       
       
        pPriceLabel=new JLabel("Premium Price:");
        pPriceLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pPriceField=new JTextField("Rs.50000");
        pPriceField.setEditable(false);
        pPriceField.setPreferredSize(new Dimension(250,23));
        pPriceField.setBackground(Color.WHITE);
       
        pTrainerLabel=new JLabel("Trainers Name:");
        pTrainerLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        pTrainerField=new JTextField();
        pTrainerField.setPreferredSize(new Dimension(250,23));
        pTrainerField.setBackground(Color.WHITE);
       
        preiumLabels.setLayout(new GridBagLayout());
        GridBagConstraints pgbc=new GridBagConstraints();
        pgbc.insets=new Insets(5,5,5,5);
        pgbc.fill= GridBagConstraints.HORIZONTAL;
       
        pgbc.gridx=0;
        pgbc.gridy=0;
        preiumLabels.add(pIdlabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pIdField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=1;
        preiumLabels.add(pNameLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pNameField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=2;
        preiumLabels.add(pLocationLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pLocationField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=3;
        preiumLabels.add(pPhoneLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pPhoneField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=4;
        preiumLabels.add(pEmailLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pEmailField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=5;
        preiumLabels.add(pGenderLabel,pgbc);
        pgbc.gridx=1;
        pgenderpanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pgenderpanel.setBackground(Color.WHITE);
        pgenderpanel.add(pMaleRadioButton);
        pgenderpanel.add(pFemaleRadioButton);
        preiumLabels.add(pgenderpanel,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=6;
        preiumLabels.add(pDOBLabel,pgbc);
        pgbc.gridx=1;
        pDobpanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pDobpanel.setBackground(Color.WHITE);
        pDobpanel.add(pdobYearComboBox);
        pDobpanel.add(pdobMonthComboBox);
        pDobpanel.add(pdobDayComboBox);
        preiumLabels.add(pDobpanel,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=7;
        preiumLabels.add(pMSBLabel,pgbc);
        pgbc.gridx=1;
        pMsdpanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pMsdpanel.setBackground(Color.WHITE);
        pMsdpanel.add(pmsYearComboBox);
        pMsdpanel.add(pmsMonthComboBox);
        pMsdpanel.add(pmsDayComboBox);
        preiumLabels.add(pMsdpanel,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=8;
        preiumLabels.add(pAmountLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pAmountField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=9;
        preiumLabels.add(pPriceLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pPriceField,pgbc);
       
        pgbc.gridx=0;
        pgbc.gridy=10;
        preiumLabels.add(pTrainerLabel,pgbc);
        pgbc.gridx=1;
        preiumLabels.add(pTrainerField,pgbc);
       
       
        premiumButtons=new JPanel();
        premiumButtons.setPreferredSize(new Dimension(600,100));
        premiumButtons.setBackground(Color.cyan);
        premiumFrame.add(premiumButtons,BorderLayout.SOUTH);
       
        pbackButton=new JButton("Back");
        pbackButton.setFont(new Font("Georgia", Font.BOLD, 14));
        pbackButton.setBackground(Color.WHITE);
        pbackButton.addActionListener(this);
       
        psaveButton=new JButton("Add");
        psaveButton.setFont(new Font("Georgia", Font.BOLD, 14));
        psaveButton.setBackground(Color.WHITE);
        psaveButton.addActionListener(this);
       
        pdisplayButton=new JButton("Display");
        pdisplayButton.setFont(new Font("Georgia", Font.BOLD, 14));
        pdisplayButton.setBackground(Color.WHITE);
        pdisplayButton.addActionListener(this);
       
       
        pclearButton=new JButton("Clear");
        pclearButton.setFont(new Font("Georgia", Font.BOLD, 14));
        pclearButton.setBackground(Color.WHITE);
        pclearButton.addActionListener(this);
       
        premiumButtons.setLayout(new GridBagLayout());
        GridBagConstraints pbuttonGBC=new GridBagConstraints();
        pbuttonGBC.insets=new Insets(30,30,30,30);
       
        pbuttonGBC.gridx=0;
        pbuttonGBC.gridy=0;
        premiumButtons.add(pbackButton,pbuttonGBC);
       
        pbuttonGBC.gridx=1;
        pbuttonGBC.gridy=0;
        premiumButtons.add(psaveButton,pbuttonGBC);
       
        pbuttonGBC.gridx=2;
        pbuttonGBC.gridy=0;
        premiumButtons.add(pdisplayButton,pbuttonGBC);
       
        pbuttonGBC.gridx=3;
        pbuttonGBC.gridy=0;
        premiumButtons.add(pclearButton,pbuttonGBC);
       
    }
      private String getSelectedDate(JComboBox<String> year, JComboBox<String> month, JComboBox<String> day) {
        return year.getSelectedItem() + "-" + month.getSelectedItem() + "-" + day.getSelectedItem();
    }
   
      private String getSelectedGender() {
        if (maleRadioButton.isSelected()) return "Male";
        if (femaleRadioButton.isSelected()) return "Female";
        return "";
    }
        //adding action listenener to addpremiummember button
       
      public void actionPerformed(ActionEvent one){
           if(one.getSource()==regular){
            mainFrame.setVisible(false);
           regularFrame.setVisible(true);
        }
       
        else if(one.getSource()==premium){
            mainFrame.setVisible(false);
            premiumFrame.setVisible(true);
        }
       
        else if(one.getSource()==backButton){
            mainFrame.setVisible(true);
            regularFrame.setVisible(false);
        }
       
        else if(one.getSource()==clearButton){
            idField.setText("");
            nameField.setText("");
            locationField.setText("");
            phoneField.setText("");
            emailField.setText("");
            referralField.setText("");
            amountField.setText("");
            group.clearSelection();
            dobYearComboBox.setSelectedIndex(0);
            dobMonthComboBox.setSelectedIndex(0);
            dobDayComboBox.setSelectedIndex(0);
            msYearComboBox.setSelectedIndex(0);
            msMonthComboBox.setSelectedIndex(0);
            msDayComboBox.setSelectedIndex(0);
            planComboBox.setSelectedIndex(0);
        }
       
        else if(one.getSource()==pbackButton){
            mainFrame.setVisible(true);
            premiumFrame.setVisible(false);
        }
       
        else if(one.getSource()==saveButton){
            try{
                if (nameField.getText().isEmpty()||idField.getText().isEmpty()||locationField.getText().isEmpty()||phoneField.getText().isEmpty()||emailField.getText().isEmpty()||referralField.getText().isEmpty()||amountField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(regularFrame, "Regular member not added.Please fill out all the fields","failed", JOptionPane.WARNING_MESSAGE);
                }
                else{
               int id=Integer.parseInt(idField.getText());
               if(idlist.contains(id)){
                   JOptionPane.showMessageDialog(regularFrame, "Member ID already exists","failed", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                 
               //get texts from textfields
               String name=idField.getText();
               String location=locationField.getText();
               String phone=phoneField.getText();
               String email=emailField.getText();
               String referralSource=referralField.getText();
               String dob = getSelectedDate(dobYearComboBox, dobMonthComboBox, dobDayComboBox);
               String msDate = getSelectedDate(msYearComboBox, msMonthComboBox, msDayComboBox);
               String gender=getSelectedGender();
               String amount=amountField.getText();
               RegularMember r1=new RegularMember(id,name,location,phone,email,gender,dob,msDate,referralSource);
               this.list.add(r1);
               this.idlist.add(id);
               JOptionPane.showMessageDialog(regularFrame, "Regular member added successfully", "Added", JOptionPane.INFORMATION_MESSAGE);
            }
            }catch(NumberFormatException Error){
                    JOptionPane.showMessageDialog(regularFrame, "Member not added. Please input valid data", "Failed", JOptionPane.WARNING_MESSAGE);
               
            }
        }
       
        else if(one.getSource()==attendance){
            try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID to mark attendance:"));
            GymMember r1 = findMember(id);
            if (r1 != null) {
                if (r1.isActiveStatus()) {
                    r1.markAttendance();
                    JOptionPane.showMessageDialog(regularFrame,"Attendance marked successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(regularFrame,"Cannot mark attendance for inactive member","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(regularFrame,"Member ID not found","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(regularFrame,"Invalid ID format. Please enter a number","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        }
       
        else if(one.getSource()==activate){
            try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID to mark attendance:"));
            GymMember r1 = findMember(id);
            if (r1 != null) {
               
                    r1.activateMembership();
                    JOptionPane.showMessageDialog(regularFrame,"Membership activated successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
                }else {
                JOptionPane.showMessageDialog(regularFrame,"Member ID not found","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(regularFrame,"Invalid ID format. Please enter a number","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        }
       
        else if(one.getSource()==deactivate){
           try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID to mark attendance:"));
            GymMember r1 = findMember(id);
            if (r1 != null) {
               
                    r1.deactivateMembership();
                    JOptionPane.showMessageDialog(regularFrame,"Membership deactivated successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
                }else {
                JOptionPane.showMessageDialog(regularFrame,"Member ID not found","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(regularFrame,"Invalid ID format. Please enter a number","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        }
       
        else if(one.getSource()==psaveButton){
            try{
                if (pNameField.getText().isEmpty()||pIdField.getText().isEmpty()||pLocationField.getText().isEmpty()||pPhoneField.getText().isEmpty()||pEmailField.getText().isEmpty()||pTrainerField.getText().isEmpty()||pAmountField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(premiumFrame, "Premium member not added.Please fill out all the fields","failed", JOptionPane.WARNING_MESSAGE);
                }
                else{
               int id=Integer.parseInt(pIdField.getText());
               if(idlist.contains(id)){
                   JOptionPane.showMessageDialog(premiumFrame, "Member ID already exists","failed", JOptionPane.WARNING_MESSAGE);
                   return;
                }
               //get texts from textfields
               String name=pIdField.getText();
               String location=pLocationField.getText();
               String phone=pPhoneField.getText();
               String email=pEmailField.getText();
               String personalTrainer=pTrainerField.getText();
               String dob = getSelectedDate(pdobYearComboBox, pdobMonthComboBox, pdobDayComboBox);
               String msDate = getSelectedDate(pmsYearComboBox, pmsMonthComboBox, pmsDayComboBox);
               String gender=getSelectedGender();
               String amount=amountField.getText();
               PremiumMember p1=new PremiumMember(id,name,location,phone,email,gender,dob,msDate,personalTrainer);
               this.list.add(p1);
               this.idlist.add(id);
               JOptionPane.showMessageDialog(regularFrame, "Premium member added successfully", "Added", JOptionPane.INFORMATION_MESSAGE);
            }
            }catch(NumberFormatException Error){
                    JOptionPane.showMessageDialog(regularFrame, "Member not added. Please input valid data", "Failed", JOptionPane.WARNING_MESSAGE);
               
            }
        }
       
         else if(one.getSource()==pclearButton){
            pIdField.setText("");
            pNameField.setText("");
            pLocationField.setText("");
            pPhoneField.setText("");
            pEmailField.setText("");
            referralField.setText("");
            pAmountField.setText("");
            pgroup.clearSelection();
            pdobYearComboBox.setSelectedIndex(0);
            pdobMonthComboBox.setSelectedIndex(0);
            pdobDayComboBox.setSelectedIndex(0);
            pmsYearComboBox.setSelectedIndex(0);
            pmsMonthComboBox.setSelectedIndex(0);
            pmsDayComboBox.setSelectedIndex(0);
            pTrainerField.setText("");
        }
       
         
    }
    private GymMember findMember(int id) {
        for (GymMember  member: list) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> new GymGUI());
    }

}