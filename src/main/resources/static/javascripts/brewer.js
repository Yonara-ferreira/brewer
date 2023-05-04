var Brewer = Brewer || {}; 


Brewer.MaskMoney = (function() {
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');		
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.'});
		this.plain.maskMoney({ precision: 0, thousands: '.'});
	}
	
	return MaskMoney;
	
}());

Brewer.MaskPhoneNumber = (function() {
	
    class MaskPhoneNumber {
        constructor() {
            this.inputPhoneNumber = $('.js-phone-number');
        }
        enable() {
            var maskBehavior = function(val) {
                return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
            };
            var options = {
                onKeyPress: function(_val, _e, field, options) {
                    field.mask(maskBehavior.apply({}, arguments), options);
                }
            };
            this.inputPhoneNumber.mask(maskBehavior, options);
        }
    }
	
	
	return MaskPhoneNumber;
	
}());

Brewer.MaskCep = (function() {
	
    class MaskCep {
        constructor() {
            this.inputCep = $('.js-cep');
        }
        enable() {
            this.inputCep.mask('00.000-000');
        }
    }
	
	
	return MaskCep;
	
}());

Brewer.MaskDate = (function(){ 
	
	function MaskDate(){
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function(){
		this.inputDate.mask('00/00/0000');
	}
	
	return MaskDate;
	
}());

Brewer.Security = (function() {

	function Security() {
		this.header = $('input[name=_csrf_header]').val();
		this.token = $('input[name=_csrf]').val();
	}

	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}

	return Security;
}());

/* Função após a página ser carregada */

$(function(){
	var maskNumber = new Brewer.MaskMoney();
		maskNumber.enable();
	
	var maskPhoneNumber = new Brewer.MaskPhoneNumber();
		maskPhoneNumber.enable();
		
	var maskCep = new Brewer.MaskCep();
		maskCep.enable();
		
	var maskDate = new Brewer.MaskDate();
		maskDate.enable();
		
	var security = new Brewer.Security();
		security.enable();
});

