package com.siger.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.siger.brewer.service.CadastroCervejaService;
import com.siger.brewer.storage.FotoStorage;
import com.siger.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorageLocal() {
		return new FotoStorageLocal();
	}
}
