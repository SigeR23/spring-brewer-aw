$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney({ decimal: ',', thousands: '.'});
	
	var inteiro = $('.js-plain');
	inteiro.maskMoney({precision : 0, thousands: '.'});
	
	var telefone = $('.js-telefone');
	telefone.mask('(00) 0000-00000');
	
	var cep = $('.js-cep');
	cep.mask("00000-000");
	
	var dtNascimento = $('.js-dtnascimento');
	dtNascimento.mask("00/00/0000");

});
