package de.stphngrtz.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @see <a href="https://spring.io/guides/gs/scheduling-tasks/">Scheduling Tasks</a>
 */
@Component
public class GreetingsTask {

    private static final Logger log = LoggerFactory.getLogger(GreetingsTask.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    private void greet() {
        log.info("Hello World! It is {}", sdf.format(new Date()));
    }
}
