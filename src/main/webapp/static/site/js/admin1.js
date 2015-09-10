$(function(){
	var $menu = $("#menu");
	$menu.on("click", "a", function(e){
		$menu.find(".active").removeClass("active");
		$(this).parent().addClass("active");
		$("#mainFrame").attr("src", $(this).attr("href"));
		return false;
	});
	
	resizeFrame();
	$(window).on("resize", function(e){
		resizeFrame();
	});
	
	function resizeFrame(){
		$(".frame").css("height", $(window).height() - 51);
	}
});