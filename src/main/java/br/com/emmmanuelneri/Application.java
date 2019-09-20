package br.com.emmmanuelneri;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) throws SchedulerException, ParseException {
        final Vertx vertx = Vertx.vertx();

        final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        vertx.deployVerticle(new ScheduleJobVerticle(scheduler, "Test", "*/2 * * * * ?"));
        vertx.deployVerticle(new ScheduleJobVerticle(scheduler, "Test2", "*/2 * * * * ?"));

        scheduler.start();
    }

}
