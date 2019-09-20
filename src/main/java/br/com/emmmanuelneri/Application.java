package br.com.emmmanuelneri;

import br.com.emmmanuelneri.fix.FixJobVerticle;
import br.com.emmmanuelneri.schedule.ScheduleJobVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) throws SchedulerException {
        final Vertx vertx = Vertx.vertx();

        final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        vertx.deployVerticle(new FixJobVerticle(scheduler, "Fix Job Test", "*/2 * * * * ?"));
        vertx.deployVerticle(new ScheduleJobVerticle(scheduler, "Schedule Job to 1 minutes", 1, "Job 2 - 1 minute"));

        scheduler.start();
    }

}
