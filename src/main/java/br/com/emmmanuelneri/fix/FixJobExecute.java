package br.com.emmmanuelneri.fix;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

public class FixJobExecute implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixJobExecute.class);

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        final JobDetail jobDetail = jobExecutionContext.getJobDetail();
        LOGGER.info("Job {0}", jobDetail.getKey());
    }
}
