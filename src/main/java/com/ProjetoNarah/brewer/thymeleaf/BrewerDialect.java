package com.ProjetoNarah.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.ProjetoNarah.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.ProjetoNarah.brewer.thymeleaf.processor.MessageElementtTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect{
	
	 public BrewerDialect() {
		super("ProjetoNarah brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementtTagProcessor(dialectPrefix));
		return processadores;
	}
	
	

}
