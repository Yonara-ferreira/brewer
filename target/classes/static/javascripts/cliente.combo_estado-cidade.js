var Brewer = Brewer || {};

Brewer.ComboEstado = (function() {

	function ComboEstado() {
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	ComboEstado.prototype.iniciar = function() {
		this.combo.on('change', onEstadoAlterado.bind(this));
	}
	function onEstadoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}

	return ComboEstado;

}());

Brewer.ComboCidade = (function() {

	function ComboCidade(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.imgLoading = $('.js-img-loading');
	}

	ComboCidade.prototype.iniciar = function() {
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
	}

	function onEstadoAlterado(_evento, codigoEstado) {
		if (codigoEstado) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'estado': codigoEstado },
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			
			resposta.done(onBuscarCidadesFinalizado.bind(this));
		}
	}
	
	function onBuscarCidadesFinalizado(cidades){
		var options = [];
		cidades.forEach(function(cidade) {
			options.push('<option value"' + cidade.codigo + '">' + cidade.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
	}

	function iniciarRequisicao() {
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return ComboCidade;


}());

$(function() {

	var comboEstado = new Brewer.ComboEstado();
	comboEstado.iniciar();

	var comboCidade = new Brewer.ComboCidade(comboEstado);
	comboCidade.iniciar();


});