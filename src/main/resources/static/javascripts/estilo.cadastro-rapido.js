var Brewer = Brewer || {}; 

Brewer.EstiloCadastroRapido = (function(){
	
	function EstiloCadastroRapido(){
		this.modal = $('#modalCadastroRapidoEstilo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeEstilo = $('#nomeEstilo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	}
	
	EstiloCadastroRapido.prototype.iniciar= function () {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
//		evento focus dentro do input
		function onModalShow() {
			this.inputNomeEstilo.focus();
		}
		
		/* Limpar input */
		function onModalClose(){
			this.inputNomeEstilo.val('');
			this.containerMensagemErro.addClass('hidden');
			this.form.find('.form-group').removeClass('has-error');
		}
		
		function onBotaoSalvarClick(){
			var nomeEstilo = this.inputNomeEstilo.val().trim();
			
			$.ajax({
				url: this.url,
				method: 'POST', 
				contentType: 'application/json',
				data: JSON.stringify({ nome: nomeEstilo }),
				error: onErroSalvandoEstilo.bind(this),
				success: onEstiloSalvo.bind(this)
			});
		}
		
		function onErroSalvandoEstilo(obj){
			var mensagemErro = obj.responseText;
			this.containerMensagemErro.removeClass('hidden'); // Removendo classe para mostrar mensagem
			this.containerMensagemErro.html('<span>' + mensagemErro + '</span>'); // Add mensagem
			this.form.find('.form-group').addClass('has-error'); // Campos vermelhos
			
		}
		
		function onEstiloSalvo(estilo){
			var comboEstilo = $('#estilo');
			comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>') // Preenchendo com valor vindo do controller
			comboEstilo.val(estilo.codigo); // Já ficar selecionado
			this.modal.modal('hide');  // Fechar modal
		}

		
	return EstiloCadastroRapido;
	
}());


$(function () {

	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
		estiloCadastroRapido.iniciar();
		
});