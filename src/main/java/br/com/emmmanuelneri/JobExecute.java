package br.com.emmmanuelneri;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobExecute implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobExecute.class);

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final JobDetail jobDetail = jobExecutionContext.getJobDetail();
        LOGGER.info("Job {0}", jobDetail.getKey());
    }
}
