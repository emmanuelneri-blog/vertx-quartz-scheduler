package br.com.emmmanuelneri;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;

public class ScheduleJobVerticle extends AbstractVerticle {

    private final Scheduler scheduler;
    private final String jobKey;
    private final String cronExpression;

    public ScheduleJobVerticle(Scheduler scheduler, String jobKey, String cronExpression) {
        this.scheduler = scheduler;
        this.jobKey = jobKey;
        this.cronExpression = cronExpression;
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        final JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(JobExecute.class);
        jobDetail.setKey(new JobKey(jobKey));

        final CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName(jobKey + "Trigger");
        trigger.setCronExpression(cronExpression);

        scheduler.scheduleJob(jobDetail, trigger);
        startPromise.complete();
    }
}
