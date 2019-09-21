package br.com.emmmanuelneri.schedule.queue;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.util.UUID;

import static org.quartz.JobBuilder.newJob;

public class ScheduleQueueJobVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleQueueJobVerticle.class);
    private static final String SCHEDULE_QUEUE_GROUP = "SCHEDULE_QUEUE";

    private final String eventBusAddress;
    private final Scheduler scheduler;

    public ScheduleQueueJobVerticle(String eventBusAddress, Scheduler scheduler) {
        this.eventBusAddress = eventBusAddress;
        this.scheduler = scheduler;
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        vertx.eventBus().localConsumer(eventBusAddress, message -> {
            final JobDetail jobDetail = newJob(ScheduleQueueJobExecute.class)
                    .withIdentity(UUID.randomUUID().toString(), SCHEDULE_QUEUE_GROUP)
                    .withDescription(String.format("Queue Schedule - %s", message.body().toString()))
                    .usingJobData("value", message.body().toString())
                    .storeDurably()
                    .build();

            try {
                scheduler.addJob(jobDetail, true);
                scheduler.triggerJob(jobDetail.getKey());
                LOGGER.info("scheduled job. key:{0}", jobDetail.getKey());
            } catch (SchedulerException ex) {
                LOGGER.error("schedule error. Message:{0}", message.body(), ex);
            }
        });

        startPromise.complete();
    }
}
