package br.com.emmmanuelneri;

import br.com.emmmanuelneri.fix.FixScheduleJobVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) throws SchedulerException {
        final Vertx vertx = Vertx.vertx();

        final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        vertx.deployVerticle(new FixScheduleJobVerticle(scheduler, "Fix Job Test", "*/2 * * * * ?"));

        scheduler.start();
    }

}
