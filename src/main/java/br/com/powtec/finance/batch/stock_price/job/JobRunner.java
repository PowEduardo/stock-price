package br.com.powtec.finance.batch.stock_price.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Override
  public void run(String... args) throws Exception {
    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("time", System.currentTimeMillis()) // Cria parâmetros únicos
        .toJobParameters();

    jobLauncher.run(job, jobParameters);
  }
}
