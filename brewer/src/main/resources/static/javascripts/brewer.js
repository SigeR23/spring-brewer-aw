$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney();
	$('.js-plain').maskMoney({precision : 0})
});
