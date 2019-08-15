//session超时
$(function(){
	$.ajaxSetup({ 
	    statusCode: {
	        401: function() { 
	        	parent.location.href="page/user/login.html";
	        }, 
	        403: function() { 
	        	parent.location.href="page/user/login.html";
	        }
	    }
	})
});

/**
 * 获得url
 * @returns
 */
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = window.location.protocol + "//" + window.location.host
			+ pathName.substr(0, index + 1);
	return result;
}