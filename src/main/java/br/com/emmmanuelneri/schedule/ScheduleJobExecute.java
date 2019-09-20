package br.com.emmmanuelneri.schedule;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

public class ScheduleJobExecute implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleJobExecute.class);

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        final JobDetail jobDetail = jobExecutionContext.getJobDetail();
        LOGGER.info("Job Key: {0} - Value: {1}", jobDetail.getKey(), jobDetail.getJobDataMap().get("value"));
    }
}
