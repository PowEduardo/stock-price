package br.com.powtec.finance.batch.stock_price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.powtec.finance.batch.stock_price.model.AssetModel;

@Repository
public interface AssetRepository extends JpaRepository<AssetModel, Long>, JpaSpecificationExecutor<AssetModel> {
}
