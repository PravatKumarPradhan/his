/**
 * PANEL SIDE BAR JS
 */

$(document).on('click', '#menu-toggle', function(e){

//$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").removeClass("toggled-2");
    $("#wrapper").toggleClass("toggled");
    $('#menu ul').hide();
});
$(document).on('click', '#menu-toggle-2', function(e){
	//salert("jk");
    e.preventDefault();
    $("#wrapper").removeClass("toggled");
    $("#wrapper").toggleClass("toggled-2");
    $('#menu ul').hide();
});

$(document).ready(function(){
	initMenu();
	// $("#wrapper").toggleClass("toggled-2");
	// $('#menu ul').hide();
	
});


 function initMenu() {
	
	 $('#menu ul').hide();
  $('#menu ul').children('.current').parent().show();
  //$('#menu ul:first').show();
  $(document).on('click', '#menu li a', function(){
      var checkElement = $(this).next();
      if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
    	  checkElement.slideUp('normal');
    	  return false;
        }
      if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {

    	  $(".sidebar-nav li").each(function() {
    	      var navItem = $(this);
    	      if (navItem.find("a").attr("href") == location.pathname.substr(14)) {
    	      	
    	      	$(this).find("a").parents("li").addClass("active");
    	      	//$(this).find("a").parents("li").parents(".nav-pills ").addClass("open-ul");
    	      }
    	      else
    	      	{
    	      	$(this).find("a").parents("li").removeClass("active"); 
    	      	}
    	  });
        //$('#menu ul:visible').slideUp('normal');
       // $('#menu ul:visible').slideUp('normal');
    	  $(this).parent().parent().find('ul:visible').slideUp('normal');
	        checkElement.slideDown('normal');
	        return false;
        }
      }
    );
  }
