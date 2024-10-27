package br.com.powtec.finance.batch.stock_price.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.powtec.finance.database.library.model.AssetModel;

@Configuration
@EnableBatchProcessing
public class AssetJob {

  @Bean
  public Step step1(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<AssetModel> reader,
      ItemProcessor<AssetModel, AssetModel> processor,
      ItemWriter<AssetModel> writer) {
    return new StepBuilder("AssetStepPrice", jobRepository)
        .<AssetModel, AssetModel>chunk(10, transactionManager) // Processa 10 registros por vez
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }

  @Bean
  public Job assetPrice(JobRepository jobRepository, Step step1) {
    return new JobBuilder("AssetJobPrice", jobRepository)
        .start(step1)
        .build();
  }
}
