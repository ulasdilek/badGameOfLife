import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Frame extends JFrame {

    private JPanel imagePanel, controlPanel, gridSizePanel, buttonPanel, speedPanel, playPausePanel;
    private JLabel gridSizeLabel;
    private Grid gameGrid;
    private JButton generateButton, randomiseButton, proceedButton, playPauseButton, resetButton;
    private JTextField widthField, heightField;
    private JSlider speedSlider;
    private ImageIcon playIcon, pauseIcon, resetIcon;
    private boolean paused = true;

    public Frame ()
    {
        playIcon = new ImageIcon(getClass().getResource("/img/playButton.png"));
        pauseIcon = new ImageIcon(getClass().getResource("/img/pauseButton.png"));
        resetIcon = new ImageIcon(getClass().getResource("/img/resetButton.png"));

        imagePanel = new JPanel();
        gameGrid = new Grid(9, 9);
        imagePanel.add(gameGrid);
        add(imagePanel, BorderLayout.LINE_START);

        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        gridSizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridSizeLabel = new JLabel("Grid Size: ");
        widthField = new JTextField("9", 9);
        widthField.addActionListener(new WidthListener());
        heightField = new JTextField("9", 9);
        heightField.addActionListener(new HeightListener());
        gridSizePanel.add(gridSizeLabel);
        gridSizePanel.add(widthField);
        gridSizePanel.add(heightField);
        controlPanel.add(gridSizePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        generateButton = new JButton("Generate");
        generateButton.setHorizontalAlignment(SwingConstants.CENTER);
        generateButton.addActionListener(new GenerateListener());
        randomiseButton = new JButton("Randomise");
        randomiseButton.setHorizontalAlignment(SwingConstants.CENTER);
        randomiseButton.addActionListener(new RandomiseListener());
        proceedButton = new JButton("Proceed");
        proceedButton.setHorizontalAlignment(SwingConstants.CENTER);
        proceedButton.addActionListener(new ProceedListener());
        buttonPanel.add(generateButton);
        buttonPanel.add(randomiseButton);
        buttonPanel.add(proceedButton);
        controlPanel.add(buttonPanel);

        speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
        playPausePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playPauseButton = new JButton();
        playPauseButton.addActionListener(new PlayPauseListener());
        playPauseButton.setHorizontalAlignment(SwingConstants.CENTER);
        playPauseButton.setVerticalAlignment(SwingConstants.CENTER);
        playPauseButton.setIcon(playIcon);
        playPauseButton.setPreferredSize(new Dimension(100, 80));
        playPauseButton.setMaximumSize(new Dimension(150, 130));
        playPauseButton.setMinimumSize(new Dimension(30, 32));
        resetButton = new JButton(resetIcon);
        resetButton.addActionListener(new ResetListener());
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setVerticalAlignment(SwingConstants.CENTER);
        resetButton.setPreferredSize(new Dimension(100, 80));
        resetButton.setMaximumSize(new Dimension(150, 130));
        resetButton.setMinimumSize(new Dimension(30, 32));
        speedSlider = new JSlider(1, 10, 2);
        playPausePanel.add(playPauseButton);
        playPausePanel.add(resetButton);
        playPausePanel.setPreferredSize(new Dimension(200, 80));
        playPausePanel.setMaximumSize(new Dimension(300, 130));
        playPausePanel.setMinimumSize(new Dimension(75, 32));
        speedPanel.add(playPausePanel);
        speedPanel.add(speedSlider);
        speedPanel.setPreferredSize(new Dimension(200, 100));
        speedPanel.setMaximumSize(new Dimension(300, 150));
        speedPanel.setMinimumSize(new Dimension(75, 52));
        controlPanel.add(speedPanel);

        add(controlPanel, BorderLayout.LINE_END);

        setSize(700, 300);
        setTitle("John Conway's Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void test ()
    {
        gameGrid.test();
    }

    class WidthListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            JTextField textField = (JTextField) e.getSource();
            String input = textField.getText();
            input = input.replaceAll("[^0-9]", "");
            try {
                int width = Integer.parseInt(input);
                gameGrid.setGridWidth(width);
            } catch (NumberFormatException exception) {
                textField.setText(Integer.toString(gameGrid.getGridWidth()));
            }
        }
    }

    class HeightListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            JTextField textField = (JTextField) e.getSource();
            String input = textField.getText();
            input = input.replaceAll("[^0-9]", "");
            try {
                int height = Integer.parseInt(input);
                gameGrid.setGridHeight(height);
            } catch (NumberFormatException exception) {
                textField.setText(Integer.toString(gameGrid.getGridHeight()));
            }
        }
    }

    class GenerateListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            gameGrid.generate();
        }
    }

    class RandomiseListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            gameGrid.randomise();
        }
    }

    class ProceedListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            gameGrid.proceed();
        }
    }

    class PlayPauseListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            if (paused) {
                gameGrid.play();
                playPauseButton.setIcon(pauseIcon);
                paused = false;
            } else {
                gameGrid.pause();
                playPauseButton.setIcon(playIcon);
                paused = true;
            }
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            gameGrid.reset();
        }
    }

}
