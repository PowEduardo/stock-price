package br.com.powtec.finance.batch.stock_price.writer;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.powtec.finance.batch.stock_price.model.AssetModel;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class AssetItemWriter {

  @Bean
  public JpaItemWriter<AssetModel> writer(EntityManagerFactory entityManagerFactory) {
    JpaItemWriter<AssetModel> writer = new JpaItemWriter<>();
    writer.setEntityManagerFactory(entityManagerFactory);
    return writer;
  }
}
