$(function() {
	var $menu = $("#side-menu");
	$menu.metisMenu();
    $menu.on("click", 'a', function(){
		var url = $(this).attr("href");
		if(url!='#') {
			$.get(url, function(data) {
				$('#page-wrapper').html(data);
			});
		}
		$menu.find(".active").removeClass("active");
		$menu.find(".in").removeClass("in");
		var element = $(this).addClass("active").parent().parent().addClass('in').parent();
	    if (element.is('li')) {
	    	element.addClass('active');
	    }
		return false;
    });
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("height", (height) + "px");
        }
    });

});