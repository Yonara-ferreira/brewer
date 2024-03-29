 package com.ProjetoNarah.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ProjetoNarah.brewer.config.JPAConfig;
import com.ProjetoNarah.brewer.config.SecurityConfig;
import com.ProjetoNarah.brewer.config.ServiceConfig;
import com.ProjetoNarah.brewer.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		///		NÃO FUNCIONA NO SPRING SECURITY ///
//		CharacterEncodingFilter characterEncondingFilter = new CharacterEncodingFilter();
//		characterEncondingFilter.setEncoding("UTF-8");
//		characterEncondingFilter.setForceEncoding(true);
		
		return new Filter[] {};
	}
	
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}

