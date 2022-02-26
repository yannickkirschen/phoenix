package sh.yannick.phoenix.components;

import lombok.Data;

import java.awt.*;

@Data
public class SplashScreenModel {
    private int width;
    private int height;

    private String applicationName;
    private String applicationVersion;
    private String applicationCopyright;
    private int applicationCopyrightYear;

    private Color foregroundColor;
    private Color backgroundColor;
}
