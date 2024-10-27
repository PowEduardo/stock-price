package br.com.powtec.finance.batch.stock_price.processor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.powtec.finance.batch.stock_price.model.AssetModel;

@Configuration
public class AssetItemProcessor {

  @Bean
  public ItemProcessor<AssetModel, AssetModel> processor() {
    return asset -> {
      try {
        // URL para a página da ação SANB4 no Google Finance
        String url = "https://www.google.com/finance/quote/{ticker}:BVMF".replace("{ticker}", asset.getTicker());

        // Conectar e obter o documento HTML
        Document doc = Jsoup.connect(url).get();

        // Extrair o valor da cotação
        Element priceElement = doc.selectFirst("div.YMlKec.fxKbKc");
        if (priceElement != null) {
          String stockPrice = priceElement.text();
          System.out.printf("O valor atual da ação %s é: %s\n", asset.getTicker(), stockPrice);
          asset.setValue(Double.parseDouble(stockPrice.replace("R$", "")));
        } else {
          System.out.printf("Não foi possível encontrar a cotação da ação: %s\n", asset.getTicker());
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
      return asset;
    };
  }
}
