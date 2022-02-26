package sh.yannick.phoenix.components;

import sh.yannick.phoenix.layouts.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class SplashScreen extends JWindow implements SplashScreenHandle {
    private static final int MAX_VALUE = 100;

    private final JLabel message = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();

    private final SplashScreenModel model;

    public SplashScreen(SplashScreenModel model) {
        super();
        this.model = model;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(model.getBackgroundColor());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(getApplicationNamePanel(), BorderLayout.WEST);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new VerticalLayout(progressPanel));
        progressPanel.setForeground(model.getForegroundColor());
        progressPanel.setBackground(model.getBackgroundColor());

        message.setForeground(model.getForegroundColor());

        progressBar.setBorderPainted(false);
        progressBar.setForeground(model.getForegroundColor());
        progressBar.setBackground(model.getBackgroundColor());
        progressBar.setMaximum(MAX_VALUE);

        progressPanel.add(message);
        progressPanel.add(progressBar);

        panel.add(progressPanel, BorderLayout.SOUTH);

        add(panel);
//        pack();
        setSize(new Dimension(model.getWidth(), model.getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setText(String text) {
        message.setText(text);
    }

    @Override
    public void setValue(int value) {
        progressBar.setValue(value);
    }

    private JPanel getApplicationNamePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new VerticalLayout(panel));
        panel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JLabel applicationLabel = new JLabel(model.getApplicationName());
        applicationLabel.setFont(new Font("Sans", Font.BOLD, 30));
        applicationLabel.setForeground(model.getForegroundColor());
        applicationLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(applicationLabel);

        JLabel versionLabel = new JLabel("v%s".formatted(model.getApplicationVersion()));
        versionLabel.setFont(new Font("Sans", Font.BOLD, 15));
        versionLabel.setForeground(model.getForegroundColor());
        panel.add(versionLabel);

        JLabel copyrightLabel = new JLabel("\u00A9 %d - %d, %s".formatted(
            model.getApplicationCopyrightYear(),
            LocalDate.now().getYear(),
            model.getApplicationCopyright()));
        copyrightLabel.setFont(new Font("Sans", Font.BOLD, 15));
        copyrightLabel.setForeground(model.getForegroundColor());
        panel.add(copyrightLabel);

        return panel;
    }
}
