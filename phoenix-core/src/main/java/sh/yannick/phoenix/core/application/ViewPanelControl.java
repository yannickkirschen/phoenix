package sh.yannick.phoenix.core.application;

import lombok.*;

import javax.swing.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewPanelControl {
    private String text;
    private Class<? extends JPanel> dialogClass;
}
