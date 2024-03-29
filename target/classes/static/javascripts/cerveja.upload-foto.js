var Brewer = Brewer || {};

Brewer.UploadFoto = (function() {

	function UploadFoto() {

		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');

		this.hmtlfotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.hmtlfotoCervejaTemplate);

		this.ContainerFotoCerveja = $('.js-container-foto-cerveja');

		this.uploadDrop = $('#upload-drop');

	}

	UploadFoto.prototype.iniciar = function() {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.ContainerFotoCerveja.data('url-fotos'),
			complete: onUploadCompleto.bind(this),
			beforeSend: adicionarCsrfToken
		}

		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop($('#upload-drop'), settings);
		
		if(this.inputNomeFoto.val()){
			onUploadCompleto.call(this, { nome: this.inputNomeFoto.val(), contentType: this.inputContentType.val() });
		}
		
		
	}

	function onUploadCompleto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);

		this.uploadDrop.addClass('hidden');

		var htmlfotoCerveja = this.template({ nomeFoto: resposta.nome });
		this.ContainerFotoCerveja.append(htmlfotoCerveja);

		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}

	function onRemoverFoto() {
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType('');
	}
	
	function adicionarCsrfToken(xhr){
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}

	return UploadFoto;

})();

$(function() {

	var uploadFoto = new Brewer.UploadFoto();
	uploadFoto.iniciar();

});