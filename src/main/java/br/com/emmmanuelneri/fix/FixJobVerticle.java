package br.com.emmmanuelneri.fix;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;

public class FixJobVerticle extends AbstractVerticle {

    private final Scheduler scheduler;
    private final JobKey jobKey;
    private final String cronExpression;

    public FixJobVerticle(Scheduler scheduler, String jobKey, String cronExpression) {
        this.scheduler = scheduler;
        this.jobKey = new JobKey(jobKey);
        this.cronExpression = cronExpression;
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        if (scheduler.checkExists(jobKey)) {
            startPromise.complete();
            return;
        }

        final JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(FixJobExecute.class);
        jobDetail.setKey(jobKey);

        final CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName(jobKey + "Trigger");
        trigger.setCronExpression(cronExpression);

        scheduler.scheduleJob(jobDetail, trigger);
        startPromise.complete();
    }
}
