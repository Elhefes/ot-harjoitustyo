package kurssikuulustelija.ui;

/**
 *
 * @author henripal
 */
import javafx.application.Application;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class JavaFxSpringService extends Application {
    
    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        super.init();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.close();
    }

    public static void launchApp(Class<? extends JavaFxSpringService> appClass, String[] args, ConfigurableApplicationContext appContext) {
        applicationContext = appContext;
        Application.launch(appClass, args);
    }

}


