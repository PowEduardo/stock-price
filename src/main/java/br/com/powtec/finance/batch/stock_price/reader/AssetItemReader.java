package br.com.powtec.finance.batch.stock_price.reader;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.powtec.finance.database.library.model.AssetModel;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class AssetItemReader {

  @Bean
  public JpaPagingItemReader<AssetModel> reader(EntityManagerFactory entityManagerFactory) {
    JpaPagingItemReader<AssetModel> reader = new JpaPagingItemReader<>();
    reader.setEntityManagerFactory(entityManagerFactory);
    reader.setQueryString("SELECT asset FROM AssetModel AS asset");
    reader.setPageSize(10); // Define o tamanho da página, leia 10 registros por vez
    reader.setName("AssetReader");
    return reader;
  }
}
