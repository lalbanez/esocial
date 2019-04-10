/**
 * 
 */

    	toastr.options = {
    			  "closeButton": true,
    			  "debug": false,
    			  "progressBar": true,
    			  "preventDuplicates": false,
    			  "positionClass": "toast-top-right",
    			  "onclick": null,
    			  "showDuration": "400",
    			  "hideDuration": "1000",
    			  "timeOut": "10000",
    			  "extendedTimeOut": "1000",
    			  "showEasing": "swing",
    			  "hideEasing": "linear",
    			  "showMethod": "fadeIn",
    			  "hideMethod": "fadeOut"
    			};
    	

	     //<![CDATA[
	     function apenasNumeros(evento){
	    	 var keyCode = evento.keyCode;
	    	 if(keyCode > 48 && keyCode < 57){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     }
	   //]]>
	     
	     
	     function adicionarMascaraData(id){
	    	    $('#'+ id).datepicker({
	    	        todayBtn: "linked",
	    	        format: 'dd/mm/yyyy',
	    	        keyboardNavigation: false,
	    	        forceParse: false,
	    	        calendarWeeks: false,
	    	        autoclose: true
	    	    });
	    	 
	     }