package com.ProjetoNarah.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ProjetoNarah.brewer.service.CadastroCervejaService;

@Configuration
@ComponentScan(basePackageClasses =  CadastroCervejaService.class)
public class ServiceConfig {

}