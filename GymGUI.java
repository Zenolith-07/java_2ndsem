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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*; // For File I/O
import java.awt.Image; // For Image
import javax.imageio.ImageIO; // For ImageIO
import java.net.URL; // For URL to load image resource
public class GymGUI implements ActionListener
{
    // Inner class for drawing a background image
    class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String resourcePath) {
            try {
                URL imageUrl = getClass().getResource(resourcePath);
                if (imageUrl != null) {
                    backgroundImage = ImageIO.read(imageUrl);
                } else {
                    System.err.println("Background image not found at path: " + resourcePath);
                    backgroundImage = null; // Or set a default color/image
                }
            } catch (IOException e) {
                e.printStackTrace();
                backgroundImage = null; // Fallback
                System.err.println("Failed to load background image: " + resourcePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Scale the image to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Main Frame
    private JFrame mainFrame;
    private JPanel mainButtons; // This will become the container for buttons, placed on ImagePanel
    private JLabel welcomeText;
    private JButton addMemberButton, activateButton, deactivateButton, markAttendanceButton, viewAllMembersButton;

    // Add Member Dialog
    private JDialog addMemberDialog;
    private JPanel addMemberMainPanel, memberTypePanel, commonFieldsPanel, regularSpecificPanel, premiumSpecificPanel, addMemberButtonsPanel;

    // Member Type Selection
    private JRadioButton regularTypeRadioButton, premiumTypeRadioButton;
    private ButtonGroup memberTypeGroup;

    // Common Member Fields
    private JLabel idLabel, nameLabel, phoneLabel, locationLabel, emailLabel, genderLabel, dobLabel, msdLabel, amountLabelDialog; // Renamed amountLabel to avoid conflict
    private JTextField idField, nameField, phoneField, locationField, emailField, amountFieldDialog; // Renamed amountField
    private JRadioButton maleRadioButton, femaleRadioButton; // Unified gender radio buttons
    private ButtonGroup genderGroup; // Unified gender group
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox; // Unified DOB combo boxes
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox; // Unified MSD combo boxes

    // Regular Member Specific Fields
    private JLabel referralLabel, planLabel, regularPriceInfoLabel;
    private JTextField referralField;
    private JComboBox<String> planComboBox;
    // private JTextField regularPriceField; // Price is fixed, will use a JLabel

    // Premium Member Specific Fields
    private JLabel trainerLabel, premiumPriceInfoLabel;
    private JTextField trainerField;
    // private JTextField premiumPriceField; // Price is fixed, will use a JLabel

    // Add Member Dialog Buttons
    private JButton saveNewMemberButton, clearNewMemberFormButton, backFromAddMemberButton;

    // View Members Dialog
    private JDialog viewMembersDialog;
    private JTextArea membersTextArea;

    // Data
    private ArrayList<GymMember> list = new ArrayList<>();
    private ArrayList<Integer> idlist = new ArrayList<>();

    // Common data for JComboBoxes
    String[] years = {"2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private static final String DATA_FILE = "gym_data.dat"; // Define data file name

    public GymGUI(){
        loadData(); // Load data at startup

        // Main Frame Setup
        mainFrame = new JFrame("GYM Management System");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
                mainFrame.dispose();
                System.exit(0);
            }
        });
        mainFrame.setSize(800, 600); 
        mainFrame.setLocationRelativeTo(null); 

        // Set ImagePanel as the content pane
        ImagePanel contentPane = new ImagePanel("/gym_background.png");
        contentPane.setLayout(new BorderLayout(10, 10)); // Add some gaps between regions
        mainFrame.setContentPane(contentPane);

        // Welcome Text Setup (to be added to contentPane's NORTH)
        welcomeText = new JLabel("Gym Management System", SwingConstants.CENTER);
        welcomeText.setFont(new Font("Georgia", Font.BOLD, 40)); // Adjusted size
        welcomeText.setForeground(Color.WHITE); // Ensure contrast against dark image
        welcomeText.setOpaque(false); // Make label background transparent
        // Add some padding to the welcome text
        welcomeText.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        contentPane.add(welcomeText, BorderLayout.NORTH);

        // Button Container Panel Setup (to be added to contentPane's CENTER)
        // This panel will hold the buttons and use GridBagLayout
        JPanel buttonContainerPanel = new JPanel(new GridBagLayout());
        buttonContainerPanel.setOpaque(false); // Make this panel transparent
        contentPane.add(buttonContainerPanel, BorderLayout.CENTER);
        
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(15, 15, 15, 15); // Adjusted insets for better spacing
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.ipadx = 40; 
        gbcMain.ipady = 15; 

        addMemberButton = new JButton("Add New Member");
        addMemberButton.setFont(new Font("Georgia", Font.BOLD, 14));
        addMemberButton.addActionListener(this);
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        buttonContainerPanel.add(addMemberButton, gbcMain);

        activateButton = new JButton("Activate Membership");
        activateButton.setFont(new Font("Georgia", Font.BOLD, 14));
        activateButton.addActionListener(this);
        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        buttonContainerPanel.add(activateButton, gbcMain);

        deactivateButton = new JButton("Deactivate Membership");
        deactivateButton.setFont(new Font("Georgia", Font.BOLD, 14));
        deactivateButton.addActionListener(this);
        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        buttonContainerPanel.add(deactivateButton, gbcMain);

        markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.setFont(new Font("Georgia", Font.BOLD, 14));
        markAttendanceButton.addActionListener(this);
        gbcMain.gridx = 1;
        gbcMain.gridy = 1;
        buttonContainerPanel.add(markAttendanceButton, gbcMain);

        viewAllMembersButton = new JButton("View All Members");
        viewAllMembersButton.setFont(new Font("Georgia", Font.BOLD, 14));
        viewAllMembersButton.addActionListener(this);
        gbcMain.gridx = 0;
        gbcMain.gridy = 2; 
        gbcMain.gridwidth = 2; 
        buttonContainerPanel.add(viewAllMembersButton, gbcMain);
        gbcMain.gridwidth = 1; 

        // Initialize Add Member Dialog (but don't show it yet)
        createAddMemberDialog();

        // Initialize View Members Dialog
        createViewMembersDialog();

        mainFrame.setVisible(true);
    }

    private void createAddMemberDialog() {
        addMemberDialog = new JDialog(mainFrame, "Add New Gym Member", true); // Modal dialog
        addMemberDialog.setSize(700, 750); // Adjusted size
        addMemberDialog.setLocationRelativeTo(mainFrame);
        addMemberDialog.setLayout(new BorderLayout(10, 10)); // Add gaps

        // Member Type Selection Panel
        memberTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Add gaps
        memberTypePanel.setBorder(BorderFactory.createTitledBorder("Select Member Type"));
        regularTypeRadioButton = new JRadioButton("Regular Member", true); // Default selected
        premiumTypeRadioButton = new JRadioButton("Premium Member");
        memberTypeGroup = new ButtonGroup();
        memberTypeGroup.add(regularTypeRadioButton);
        memberTypeGroup.add(premiumTypeRadioButton);
        memberTypePanel.add(regularTypeRadioButton);
        memberTypePanel.add(premiumTypeRadioButton);
        addMemberDialog.add(memberTypePanel, BorderLayout.NORTH);

        // Main panel for fields (common and specific)
        addMemberMainPanel = new JPanel();
        addMemberMainPanel.setLayout(new BoxLayout(addMemberMainPanel, BoxLayout.Y_AXIS)); // Vertical layout
        addMemberMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Common Fields Panel
        commonFieldsPanel = new JPanel(new GridBagLayout());
        commonFieldsPanel.setBorder(BorderFactory.createTitledBorder("Common Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        idLabel = new JLabel("ID:");
        gbc.gridx = 0; gbc.gridy = 0; commonFieldsPanel.add(idLabel, gbc);
        idField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(idField, gbc);
        gbc.fill = GridBagConstraints.NONE; // Reset fill

        nameLabel = new JLabel("Name:");
        gbc.gridx = 0; gbc.gridy = 1; commonFieldsPanel.add(nameLabel, gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(nameField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        locationLabel = new JLabel("Location:");
        gbc.gridx = 0; gbc.gridy = 2; commonFieldsPanel.add(locationLabel, gbc);
        locationField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(locationField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        
        phoneLabel = new JLabel("Phone:");
        gbc.gridx = 0; gbc.gridy = 3; commonFieldsPanel.add(phoneLabel, gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(phoneField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        emailLabel = new JLabel("Email:");
        gbc.gridx = 0; gbc.gridy = 4; commonFieldsPanel.add(emailLabel, gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(emailField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        genderLabel = new JLabel("Gender:");
        gbc.gridx = 0; gbc.gridy = 5; commonFieldsPanel.add(genderLabel, gbc);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Compact panel
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        gbc.gridx = 1; gbc.gridy = 5; commonFieldsPanel.add(genderPanel, gbc);

        dobLabel = new JLabel("Date of Birth:");
        gbc.gridx = 0; gbc.gridy = 6; commonFieldsPanel.add(dobLabel, gbc);
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Compact panel with small gap
        dobPanel.add(dobYearComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobDayComboBox);
        gbc.gridx = 1; gbc.gridy = 6; commonFieldsPanel.add(dobPanel, gbc);

        msdLabel = new JLabel("Membership Start Date:");
        gbc.gridx = 0; gbc.gridy = 7; commonFieldsPanel.add(msdLabel, gbc);
        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
        JPanel msdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Compact panel
        msdPanel.add(msYearComboBox);
        msdPanel.add(msMonthComboBox);
        msdPanel.add(msDayComboBox);
        gbc.gridx = 1; gbc.gridy = 7; commonFieldsPanel.add(msdPanel, gbc);

        amountLabelDialog = new JLabel("Paid Amount:");
        gbc.gridx = 0; gbc.gridy = 8; commonFieldsPanel.add(amountLabelDialog, gbc);
        amountFieldDialog = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 8; gbc.fill = GridBagConstraints.HORIZONTAL; commonFieldsPanel.add(amountFieldDialog, gbc);
        gbc.fill = GridBagConstraints.NONE;

        addMemberMainPanel.add(commonFieldsPanel);

        // Regular Member Specific Panel
        regularSpecificPanel = new JPanel(new GridBagLayout());
        regularSpecificPanel.setBorder(BorderFactory.createTitledBorder("Regular Member Details"));
        // (gbc constraints are reused)
        referralLabel = new JLabel("Referral Source:");
        gbc.gridx = 0; gbc.gridy = 0; regularSpecificPanel.add(referralLabel, gbc);
        referralField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; regularSpecificPanel.add(referralField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        planLabel = new JLabel("Plan:");
        gbc.gridx = 0; gbc.gridy = 1; regularSpecificPanel.add(planLabel, gbc);
        planComboBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; regularSpecificPanel.add(planComboBox, gbc);
        gbc.fill = GridBagConstraints.NONE;
        
        regularPriceInfoLabel = new JLabel("<html><b>Plan Prices:</b><br>Basic: Rs. 6500<br>Standard: Rs. 12500<br>Deluxe: Rs. 18500</html>");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; regularSpecificPanel.add(regularPriceInfoLabel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth

        addMemberMainPanel.add(regularSpecificPanel);

        // Premium Member Specific Panel
        premiumSpecificPanel = new JPanel(new GridBagLayout());
        premiumSpecificPanel.setBorder(BorderFactory.createTitledBorder("Premium Member Details"));
        // (gbc constraints are reused)
        trainerLabel = new JLabel("Personal Trainer:");
        gbc.gridx = 0; gbc.gridy = 0; premiumSpecificPanel.add(trainerLabel, gbc);
        trainerField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; premiumSpecificPanel.add(trainerField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        premiumPriceInfoLabel = new JLabel("Price: Rs. 50000");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; premiumSpecificPanel.add(premiumPriceInfoLabel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth
        
        addMemberMainPanel.add(premiumSpecificPanel);
        premiumSpecificPanel.setVisible(false); // Initially hidden

        // Action listeners for member type radio buttons
        ActionListener memberTypeListener = e -> {
            if (regularTypeRadioButton.isSelected()) {
                regularSpecificPanel.setVisible(true);
                premiumSpecificPanel.setVisible(false);
            } else {
                regularSpecificPanel.setVisible(false);
                premiumSpecificPanel.setVisible(true);
            }
            addMemberDialog.pack(); // Adjust dialog size
            addMemberDialog.setSize(700, addMemberDialog.getHeight()); // Keep width, adjust height
            addMemberDialog.setLocationRelativeTo(mainFrame);
        };
        regularTypeRadioButton.addActionListener(memberTypeListener);
        premiumTypeRadioButton.addActionListener(memberTypeListener);


        // Add the main panel with all fields to a JScrollPane for better handling of varying content height
        JScrollPane scrollPane = new JScrollPane(addMemberMainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addMemberDialog.add(scrollPane, BorderLayout.CENTER);


        // Buttons Panel for Add Member Dialog
        addMemberButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10)); // Align buttons to the right
        saveNewMemberButton = new JButton("Save Member");
        clearNewMemberFormButton = new JButton("Clear Form");
        backFromAddMemberButton = new JButton("Back to Main");

        saveNewMemberButton.addActionListener(this);
        clearNewMemberFormButton.addActionListener(this);
        backFromAddMemberButton.addActionListener(this);

        addMemberButtonsPanel.add(saveNewMemberButton);
        addMemberButtonsPanel.add(clearNewMemberFormButton);
        addMemberButtonsPanel.add(backFromAddMemberButton);
        addMemberDialog.add(addMemberButtonsPanel, BorderLayout.SOUTH);

        // Initial state based on radio button selection
        if (regularTypeRadioButton.isSelected()) {
            regularSpecificPanel.setVisible(true);
            premiumSpecificPanel.setVisible(false);
        } else {
             regularSpecificPanel.setVisible(false);
            premiumSpecificPanel.setVisible(true);
        }
        // addMemberDialog.pack(); // Pack to fit content
        // addMemberDialog.setSize(700, addMemberDialog.getHeight()); 
    }

    private void createViewMembersDialog() {
        viewMembersDialog = new JDialog(mainFrame, "All Registered Members", false); // Non-modal
        viewMembersDialog.setSize(600, 500);
        viewMembersDialog.setLocationRelativeTo(mainFrame);
        viewMembersDialog.setLayout(new BorderLayout(10, 10));

        membersTextArea = new JTextArea();
        membersTextArea.setEditable(false);
        membersTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(membersTextArea);
        viewMembersDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeViewButton = new JButton("Close");
        closeViewButton.addActionListener(e -> viewMembersDialog.setVisible(false));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeViewButton);
        viewMembersDialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    private String getSelectedDate(JComboBox<String> year, JComboBox<String> month, JComboBox<String> day) {
        if (year.getSelectedItem() == null || month.getSelectedItem() == null || day.getSelectedItem() == null) {
            return "N/A-N/A-N/A"; // Handle null case, though ideally items should be pre-selected or validated
        }
        return year.getSelectedItem() + "-" + month.getSelectedItem() + "-" + day.getSelectedItem();
    }
   
    private String getSelectedGender() { // Now uses the unified radio buttons
        if (maleRadioButton.isSelected()) return "Male";
        if (femaleRadioButton.isSelected()) return "Female";
        return ""; // Or null, or throw exception if gender is mandatory
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMemberButton) {
            clearNewMemberForm(); // Clear form before showing
            // Ensure radio button state is correct for panel visibility before showing dialog
            if (regularTypeRadioButton.isSelected()) {
                regularSpecificPanel.setVisible(true);
                premiumSpecificPanel.setVisible(false);
            } else {
                premiumTypeRadioButton.setSelected(true); // Ensure one is selected if group was cleared
                regularSpecificPanel.setVisible(false);
                premiumSpecificPanel.setVisible(true);
            }
            addMemberDialog.pack();
            addMemberDialog.setSize(700, addMemberDialog.getHeight() + 20); // Add some padding
            addMemberDialog.setLocationRelativeTo(mainFrame);
            addMemberDialog.setVisible(true);
        } else if (e.getSource() == backFromAddMemberButton) {
            addMemberDialog.setVisible(false);
        } else if (e.getSource() == clearNewMemberFormButton) {
            clearNewMemberForm();
        } else if (e.getSource() == saveNewMemberButton) {
            saveNewMember();
        } else if (e.getSource() == activateButton) { // Renamed from 'activate'
            handleActivateDeactivate(true);
        } else if (e.getSource() == deactivateButton) { // Renamed from 'deactivate'
            handleActivateDeactivate(false);
        } else if (e.getSource() == markAttendanceButton) { // Renamed from 'attendance'
            handleMarkAttendance();
        } else if (e.getSource() == viewAllMembersButton) {
            // displayAllMembers(); // Old direct call
            Object[] options = {"View Regular Members", "View Premium Members", "View All Members", "Cancel"};
            int choice = JOptionPane.showOptionDialog(mainFrame,
                    "Which members would you like to view?",
                    "Select Member Type to View",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]); // Default to "View All Members"

            switch (choice) {
                case 0: // View Regular Members
                    displayAllMembers("Regular");
                    break;
                case 1: // View Premium Members
                    displayAllMembers("Premium");
                    break;
                case 2: // View All Members
                    displayAllMembers("All");
                    break;
                // case 3 or JOptionPane.CLOSED_OPTION: // Cancel or dialog closed
                default:
                    break;
            }
        }
    }

    private void clearNewMemberForm() {
            idField.setText("");
            nameField.setText("");
            locationField.setText("");
            phoneField.setText("");
            emailField.setText("");
        genderGroup.clearSelection();
        
        if (dobYearComboBox.getItemCount() > 0) dobYearComboBox.setSelectedIndex(0);
        if (dobMonthComboBox.getItemCount() > 0) dobMonthComboBox.setSelectedIndex(0);
        if (dobDayComboBox.getItemCount() > 0) dobDayComboBox.setSelectedIndex(0);
        if (msYearComboBox.getItemCount() > 0) msYearComboBox.setSelectedIndex(0);
        if (msMonthComboBox.getItemCount() > 0) msMonthComboBox.setSelectedIndex(0);
        if (msDayComboBox.getItemCount() > 0) msDayComboBox.setSelectedIndex(0);
        
        amountFieldDialog.setText("");

            referralField.setText("");
        if (planComboBox.getItemCount() > 0) planComboBox.setSelectedIndex(0);
        trainerField.setText("");
        
        regularTypeRadioButton.setSelected(true); 
        // Trigger the listener to update panel visibility and repack
        ActionListener[] listeners = regularTypeRadioButton.getActionListeners();
        if (listeners.length > 0) {
            listeners[0].actionPerformed(new ActionEvent(regularTypeRadioButton, ActionEvent.ACTION_PERFORMED, null));
        } else { // Fallback if listener not found or for direct setting
             regularSpecificPanel.setVisible(true);
             premiumSpecificPanel.setVisible(false);
             addMemberDialog.pack();
             addMemberDialog.setSize(700, addMemberDialog.getHeight() + 20);
        }
    }

    private void saveNewMember() {
        try {
            // Validate common fields
            if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || locationField.getText().trim().isEmpty() ||
                phoneField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || getSelectedGender().isEmpty() ||
                amountFieldDialog.getText().trim().isEmpty() ) { 
                JOptionPane.showMessageDialog(addMemberDialog, "Please fill all common fields, including gender and amount.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addMemberDialog, "Invalid ID. Please enter a numeric value.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (idlist.contains(id)) {
                JOptionPane.showMessageDialog(addMemberDialog, "Member ID already exists.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double paidAmount;
            try {
                paidAmount = Double.parseDouble(amountFieldDialog.getText().trim());
                if (paidAmount < 0) {
                     JOptionPane.showMessageDialog(addMemberDialog, "Paid amount cannot be negative.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addMemberDialog, "Invalid paid amount. Please enter a number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                 

            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String gender = getSelectedGender();
               String dob = getSelectedDate(dobYearComboBox, dobMonthComboBox, dobDayComboBox);
               String msDate = getSelectedDate(msYearComboBox, msMonthComboBox, msDayComboBox);

            if (regularTypeRadioButton.isSelected()) {
                if (referralField.getText().trim().isEmpty() || planComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(addMemberDialog, "Please fill all fields for Regular Member (Referral Source and Plan).", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String referralSource = referralField.getText().trim();
                String plan = (String) planComboBox.getSelectedItem();

                RegularMember rm = new RegularMember(id, name, location, phone, email, gender, dob, msDate, referralSource);
                String upgradeResult = rm.upgradePlan(plan);
                if (!"plan upgraded successfully".equalsIgnoreCase(upgradeResult) && !"same plan chosen".equalsIgnoreCase(upgradeResult)) {
                    // If upgradePlan indicated an issue (other than 'same plan chosen' which is acceptable for initial setup if default is selected)
                    // we might want to inform the user or log, but the member object is already created.
                    // For now, the console messages from RegularMember will suffice.
                    // If it was "invalid plan selected", the price in rm might be the old default.
                    System.out.println("GymGUI: Note - Plan upgrade message: " + upgradeResult);
                }
                
                this.list.add(rm);
               this.idlist.add(id);
                JOptionPane.showMessageDialog(addMemberDialog, "Regular Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else if (premiumTypeRadioButton.isSelected()) {
                if (trainerField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(addMemberDialog, "Please fill all fields for Premium Member (Trainer Name).", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String personalTrainer = trainerField.getText().trim();

                PremiumMember pm = new PremiumMember(id, name, location, phone, email, gender, dob, msDate, personalTrainer);
                if (paidAmount > 0) {
                    pm.payDueAmount(paidAmount);
                }
                
                this.list.add(pm);
                this.idlist.add(id);
                JOptionPane.showMessageDialog(addMemberDialog, "Premium Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            clearNewMemberForm(); 
            addMemberDialog.setVisible(false);

        } catch (NumberFormatException ex) { // This specific catch might be redundant due to earlier checks
            JOptionPane.showMessageDialog(addMemberDialog, "Invalid numeric input. Please check ID and Amount fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(addMemberDialog, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); 
        }
    }
    
    private void handleActivateDeactivate(boolean activate) {
        String idStr = JOptionPane.showInputDialog(mainFrame, "Enter Member ID to " + (activate ? "activate" : "deactivate") + ":");
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            GymMember member = findMember(id);
            if (member != null) {
                if (activate) {
                    member.activateMembership(); 
                    JOptionPane.showMessageDialog(mainFrame, "Membership for ID " + id + " activated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    member.deactivateMembership(); 
                    JOptionPane.showMessageDialog(mainFrame, "Membership for ID " + id + " deactivated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Member ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleMarkAttendance() {
        String idStr = JOptionPane.showInputDialog(mainFrame, "Enter Member ID to mark attendance:");
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            GymMember member = findMember(id);
            if (member != null) {
                if (member.isActiveStatus()) {
                    member.markAttendance();
                    JOptionPane.showMessageDialog(mainFrame, "Attendance marked for ID " + id + ".\nLoyalty Points: " + member.getLoyaltyPoints(), "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Cannot mark attendance for inactive member ID " + id + ".", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Member ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private GymMember findMember(int id) {
        for (GymMember member : list) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    private void displayAllMembers(String memberTypeToShow) {
        membersTextArea.setText(""); // Clear previous content
        StringBuilder sb = new StringBuilder();
        boolean membersFound = false;

        for (GymMember member : list) {
            boolean match = false;
            if ("Regular".equals(memberTypeToShow) && member instanceof RegularMember) {
                match = true;
            } else if ("Premium".equals(memberTypeToShow) && member instanceof PremiumMember) {
                match = true;
            } else if ("All".equals(memberTypeToShow)) {
                match = true;
            }

            if (match) {
                sb.append(member.getDisplayInfo());
                sb.append("\n-------------------------------------\n\n"); // Separator
                membersFound = true;
            }
        }

        if (!membersFound) {
            if ("All".equals(memberTypeToShow)) {
                 membersTextArea.setText("No members registered yet.");
            } else {
                 membersTextArea.setText("No " + memberTypeToShow.toLowerCase() + " members found.");
            }
        } else {
            membersTextArea.setText(sb.toString());
        }
        
        // Update dialog title
        if ("All".equals(memberTypeToShow)) {
            viewMembersDialog.setTitle("All Registered Members");
        } else {
            viewMembersDialog.setTitle("Registered " + memberTypeToShow + " Members");
        }

        membersTextArea.setCaretPosition(0); // Scroll to top
        viewMembersDialog.setVisible(true);
    }

    @SuppressWarnings("unchecked") // For casting generic ArrayLists
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            list = (ArrayList<GymMember>) ois.readObject();
            idlist = (ArrayList<Integer>) ois.readObject();
            if (list == null) list = new ArrayList<>(); // Ensure lists are not null if file was empty/corrupt
            if (idlist == null) idlist = new ArrayList<>();
            // JOptionPane.showMessageDialog(null, "Data loaded successfully!", "Load Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            // JOptionPane.showMessageDialog(null, "No existing data file found. Starting fresh.", "Load Status", JOptionPane.INFORMATION_MESSAGE);
            list = new ArrayList<>(); // Initialize if no file exists
            idlist = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage() + "\nStarting with a fresh dataset.", "Load Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            list = new ArrayList<>(); // Initialize on error
            idlist = new ArrayList<>();
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(list);
            oos.writeObject(idlist);
            // JOptionPane.showMessageDialog(null, "Data saved successfully!", "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GymGUI());
    }
}