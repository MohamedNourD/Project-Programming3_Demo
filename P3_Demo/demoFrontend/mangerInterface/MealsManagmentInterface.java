package mangerInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Meals.Meal;

public class MealsManagmentInterface extends JFrame {

    private JPanel mainPanel;
    private JPanel mealsPanel;
    private JScrollPane scrollPane;
    private JButton addMealButton;
    private JButton backButton;
    private int panelWidth = 300;
    private int panelHeight = 300;
    private final Color fontColor = new Color(102, 102, 102); // Font color for labels and text fields

    public MealsManagmentInterface() {
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mealsPanel = new JPanel();
        addMealButton = new JButton("Add New Meal");
        backButton = new JButton("Back");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1010, 640));

        // Use BorderLayout for the main panel
        mainPanel.setBackground(new Color(251, 133, 0));
        mainPanel.setLayout(new BorderLayout());

        // Use a custom layout for the meals panel
        mealsPanel.setBackground(new Color(251, 133, 8));
        mealsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10)); // Custom layout for meal items

        scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of the main panel

        // Create a bottom panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(251, 133, 0));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center-align buttons with 20px horizontal
                                                                          // gap

        addMealButton.setBackground(new Color(251, 133, 0));
        addMealButton.setForeground(Color.WHITE);
        addMealButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        addMealButton.addActionListener(evt -> onAddMealButtonClicked());
        buttonPanel.add(addMealButton);

        backButton.setBackground(new Color(251, 133, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        backButton.addActionListener(evt -> onBackButtonClicked());
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the main panel

        add(mainPanel);
        pack();
    }

    private void onBackButtonClicked() {
        new WelcomeManger().setVisible(true);
        this.dispose();
    }

    private JPanel createMealItemPanel() {
        JPanel mealItemPanel = new JPanel();
        mealItemPanel.setBackground(Color.WHITE);
        mealItemPanel.setPreferredSize(new Dimension(panelWidth, panelHeight)); // Fixed size for meal item panel
        mealItemPanel.setLayout(null); // Use null layout for precise positioning within the meal item panel

        JLabel mealIconLabel = new JLabel("Icon Meal", SwingConstants.CENTER);
        mealIconLabel.setBounds(10, 10, 280, 100);
        mealIconLabel.setForeground(fontColor);
        mealItemPanel.add(mealIconLabel);

        JLabel mealNameLabel = new JLabel("Meal Name:");
        mealNameLabel.setBounds(10, 120, 80, 20);
        mealNameLabel.setForeground(fontColor);
        mealItemPanel.add(mealNameLabel);

        JTextField mealNameField = new JTextField();
        mealNameField.setBounds(100, 120, 180, 20);
        mealNameField.setForeground(fontColor);
        mealNameField.setEnabled(false);
        mealItemPanel.add(mealNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 150, 80, 20);
        priceLabel.setForeground(fontColor);
        mealItemPanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(100, 150, 180, 20);
        priceField.setForeground(fontColor);
        priceField.setEnabled(false);
        mealItemPanel.add(priceField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(10, 180, 80, 20);
        ingredientsLabel.setForeground(fontColor);
        mealItemPanel.add(ingredientsLabel);

        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setBounds(100, 180, 180, 60);
        ingredientsArea.setForeground(fontColor);
        ingredientsArea.setEnabled(false);
        mealItemPanel.add(ingredientsArea);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(100, 250, 80, 30);
        editButton.setBackground(new Color(251, 133, 0));
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(evt -> onEditButtonClicked(mealNameField, priceField, ingredientsArea));
        mealItemPanel.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(190, 250, 80, 30);
        removeButton.setBackground(new Color(251, 133, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(evt -> onRemoveMealButtonClicked(mealItemPanel));
        mealItemPanel.add(removeButton);

        mealIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMealIconClicked(mealIconLabel);
            }
        });

        return mealItemPanel;
    }

    private void onEditButtonClicked(JTextField mealNameField, JTextField priceField, JTextArea ingredientsArea) {
        mealNameField.setEnabled(true);
        priceField.setEnabled(true);
        ingredientsArea.setEnabled(true);
    }

    private void onAddMealButtonClicked() {
        JPanel newMealPanel = createMealItemPanel();
        mealsPanel.add(newMealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    private void onRemoveMealButtonClicked(JPanel mealPanel) {
        mealsPanel.remove(mealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    private void onMealIconClicked(JLabel mealIconLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Meal Icon");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            ImageIcon icon = new ImageIcon(selectedFilePath);
            mealIconLabel.setIcon(icon);
            mealIconLabel.setText("");
        }
    }

    public void addMealsToPanel(List<Meal> meals) {
        for (Meal meal : meals) {
            JPanel mealPanel = createMealItemPanel();

            for (Component component : mealPanel.getComponents()) {
                if (component instanceof JTextField textField) {
                    if (textField.getBounds().y == 120) {
                        textField.setText(meal.getName());
                    } else if (textField.getBounds().y == 150) {
                        textField.setText(String.valueOf(meal.getPrice()));
                    }
                } else if (component instanceof JTextArea textArea) {
                    textArea.setText(meal.getIngredients());
                }
            }

            mealsPanel.add(mealPanel);
        }

        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MealsManagmentInterface().setVisible(true));
    }
}