package sh.yannick.phoenix.core;

import sh.yannick.phoenix.common.*;
import sh.yannick.phoenix.common.laf.LookAndFeelWrapper;
import sh.yannick.phoenix.components.Icon;
import sh.yannick.phoenix.components.SplashScreen;
import sh.yannick.phoenix.components.*;
import sh.yannick.phoenix.core.application.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;

public final class PhoenixApplication {
    private PhoenixApplication() {
    }

    public static PhoenixContext run() {
        return run(List.of());
    }

    public static PhoenixContext run(Collection<Consumer<SplashScreenHandle>> consumers) {
        if ("Mac OS X".equals(System.getProperty("os.name"))) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        PhoenixContext context = PhoenixContext.getInstance();
        PhoenixConfiguration configuration = context.getConfiguration();
        setLookAndFeel(configuration);

        String iconSetClass = configuration.getString("phoenix.laf.iconSet");
        Icon.setIconSet(Reflection.newInstance(iconSetClass));

        List<String> packages = context.getConfiguration().getList(String.class, "phoenix.view.locator.packages");
        Collection<Class<? extends ViewHandle>> views;
        boolean splashEnabled = configuration.getBoolean("phoenix.splash.enabled");
        if (splashEnabled) {
            SplashScreen splashScreen = new SplashScreen(getSplashScreenModel(configuration));
            views = locateViews(packages);
            consumers.forEach(c -> c.accept(splashScreen));
            splashScreen.dispose();
        } else {
            views = locateViews(packages);
        }

        EventQueue.invokeLater(() -> new UILoader(views, configuration).load().setVisible(true));
        return context;
    }

    private static void setLookAndFeel(PhoenixConfiguration configuration) {
        try {
            UIManager.setLookAndFeel(configuration.getString("phoenix.laf"));

            // We use this as a trick to load the current wrapper as it might perform some initialization work.
            LookAndFeelWrapper.defaultWrapper();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new PhoenixException(e);
        }
    }

    private static SplashScreenModel getSplashScreenModel(PhoenixConfiguration configuration) {
        int width = configuration.getInt("phoenix.splash.width");
        int height = configuration.getInt("phoenix.splash.height");

        String applicationName = configuration.getString("phoenix.splash.application.name");
        String applicationVersion = configuration.getString("phoenix.splash.application.version");
        String copyright = configuration.getString("phoenix.splash.application.copyright");
        int copyrightYear = configuration.getInt("phoenix.splash.application.copyright.year");

        Color foreground = configuration.getColor("phoenix.splash.foreground");
        Color background = configuration.getColor("phoenix.splash.background");

        SplashScreenModel model = new SplashScreenModel();
        model.setWidth(width);
        model.setHeight(height);
        model.setApplicationName(applicationName);
        model.setApplicationVersion(applicationVersion);
        model.setApplicationCopyright(copyright);
        model.setApplicationCopyrightYear(copyrightYear);
        model.setForegroundColor(foreground);
        model.setBackgroundColor(background);

        return model;
    }

    private static Collection<Class<? extends ViewHandle>> locateViews(Collection<String> packages) {
        return new ViewLocator().pkg(packages).locate();
    }
}
