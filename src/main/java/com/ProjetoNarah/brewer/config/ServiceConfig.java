package com.ProjetoNarah.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ProjetoNarah.brewer.service.CadastroCervejaService;
import com.ProjetoNarah.brewer.storage.FotoStorage;
import com.ProjetoNarah.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses =  CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
