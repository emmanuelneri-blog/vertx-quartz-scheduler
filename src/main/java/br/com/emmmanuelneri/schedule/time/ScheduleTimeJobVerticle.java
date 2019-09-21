package br.com.emmmanuelneri.schedule.time;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ScheduleTimeJobVerticle extends AbstractVerticle {

    private final Scheduler scheduler;
    private final JobKey jobKey;
    private final int minutesToFire;
    private final String value;

    public ScheduleTimeJobVerticle(Scheduler scheduler, String jobKey, int minutesToFire, String value) {
        this.scheduler = scheduler;
        this.jobKey = new JobKey(jobKey);
        this.minutesToFire = minutesToFire;
        this.value = value;
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        if (scheduler.checkExists(jobKey)) {
            startPromise.complete();
            return;
        }

        final JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(ScheduleTimeJobExecute.class);
        jobDetail.setKey(jobKey);

        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("value", value);
        jobDetail.setJobDataMap(jobDataMap);

        final ZonedDateTime fireTime = ZonedDateTime.now((ZoneId.of("America/Sao_Paulo"))).minusMinutes(minutesToFire);
        final SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(jobKey + " - Trigger")
                .startAt(Date.from(fireTime.toInstant()))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        startPromise.complete();
    }
}
