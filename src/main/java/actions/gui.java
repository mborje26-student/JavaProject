package main.java.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame jframe = new JFrame("GUI Screen");

        // Set the default close operation
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        jframe.setSize(400, 400);

        // Create a custom panel with an image background
        BackgroundPanel backgroundPanel = new BackgroundPanel();

        // Set a layout that allows components to be centered
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 150)); // Center the buttons with padding

        // Create first and second button objects with the specified color
        JButton firstButton = createButton("Login", new Color(255, 255, 255)); // RGB color for button background
        JButton secondButton = createButton("Register", new Color(255, 255, 255)); // RGB color for button background

        // Add the buttons to the background panel
        backgroundPanel.add(firstButton);
        backgroundPanel.add(secondButton);

        // Set the background panel as the content pane
        jframe.setContentPane(backgroundPanel);

        // Make the frame visible
        jframe.setVisible(true);
    }

    // Method to create a button with rounded corners and specific color
    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(color);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
                super.paintComponent(g);
            }
        };

        button.setFocusPainted(false); // Remove the focus border
        button.setBorderPainted(false); // Remove the button border
        button.setContentAreaFilled(false); // Make the button transparent

        // Set button properties
        button.setPreferredSize(new Dimension(120, 50)); // Set button size
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        button.setForeground(Color.BLACK); // Change text color to black

        // Add action listener for button click (optional)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can add your login or registration logic here
                System.out.println(text + " button clicked!");
            }
        });

        return button;
    }

    // Custom JPanel to paint an image as the background
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            backgroundImage = new ImageIcon("C:\\Users\\SZ16M7\\Downloads\\house-7513001_1280.png").getImage(); // Update with your image path
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
