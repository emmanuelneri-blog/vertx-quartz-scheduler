package br.com.emmmanuelneri;

import br.com.emmmanuelneri.fix.FixJobVerticle;
import br.com.emmmanuelneri.schedule.queue.ScheduleQueueJobVerticle;
import br.com.emmmanuelneri.schedule.time.ScheduleTimeJobVerticle;
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
        scheduler.start();

        vertx.deployVerticle(new FixJobVerticle(scheduler, "Fix Job Test", "*/2 * * * * ?"));
        vertx.deployVerticle(new ScheduleTimeJobVerticle(scheduler, "Schedule Job to 1 minutes", 1, "Job 2 - 1 minute"));

        final String eventBusAddress = "EVENT_BUS_QUEUE";
        vertx.deployVerticle(new ScheduleQueueJobVerticle(eventBusAddress, scheduler), asyncResult -> {
            final int totalMessage = 20;
            for (int i = 1; i <= totalMessage; i++) {
                vertx.eventBus().send(eventBusAddress, String.format("Message %d", i));
            }
        });
    }

}
