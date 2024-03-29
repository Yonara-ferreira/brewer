package com.ProjetoNarah.brewer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.ProjetoNarah.brewer.validation.AtributoConfirmacao;

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object> {
	
	private String atributo; 
	private String atributoConfirmacao;

	@Override
	public void initialize(AtributoConfirmacao constraintAnnotation) {
		this.atributo = constraintAnnotation.atributo();
		this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valido = false;
		
		try{
			Object valorAtributo = BeanUtils.getProperty(object, this.atributo);
			Object valorAtributoConfirmacao = BeanUtils.getProperty(object, this.atributoConfirmacao);
			
			valido = ambosSaoNull(valorAtributo, valorAtributoConfirmacao) || ambosSaoIguais(valorAtributo, valorAtributoConfirmacao);
			
		} catch (Exception e){
			throw new RuntimeException("Erro ao recuperar os valores dos atributos", e);
		}
		
		//código é usado para adicionar manualmente uma mensagem personalizada de 
		//violação de restrição em um objeto em caso de não validação automática do Bean Validation.
		
		if(!valido) {
			context.disableDefaultConstraintViolation();
			
			String message = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message); 
			
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}
		
		return valido;
	}
	
		private boolean ambosSaoNull(Object valorAtributo, Object valorAtributoConfirmacao) {
			return valorAtributo == null && valorAtributoConfirmacao == null;
	}

		private boolean ambosSaoIguais(Object valorAtributo, Object valorAtributoConfirmacao) {
			return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}	


}
