package io.study.batch.hellobatch;

import io.study.batch.hellobatch.config.SampleJob1Config;
import io.study.batch.hellobatch.config.TestJobConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestJobConfig.class, SampleJob1Config.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SampleJob1Test {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("배치_단순동작_테스트")
    @Test
    public void 배치_단순동작_테스트(){
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob();
            Assertions.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JobParameters defaultJobParameters(){
        JobParametersBuilder paramBuilder = new JobParametersBuilder();
        paramBuilder.addString("jobID", String.valueOf(System.currentTimeMillis()));
        return paramBuilder.toJobParameters();
    }
}
