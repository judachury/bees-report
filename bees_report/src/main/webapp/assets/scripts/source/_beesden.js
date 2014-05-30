var beesden = (function(d) {

	return {

		/*
		 * @name	ajax
		 * @type	Function
		 * @desc	Provides AJAX post / get requests for various form / page submissions
		 * @param	{String} link - Target for ajax request
		 * @param	{Function} callback - Callback to be fired on successful ajax request
		 * @param	{String} data - Serialized data to be sent as part of the ajax call
		 * @param	{String} method - Ajax method type: ['POST', 'GET', 'PUT', 'DELETE']
		*/
		ajax: function(link, callback, error, data, method, responseType) {
			var xmlhttp = new XMLHttpRequest();	
			method = method ? method : 'GET';
			// Set ajax parameter to 'true' for all ajax submissions
			link = link.replace(/[?&]ajax=([^&]*)/, '');
			link = link + (link.indexOf('?') > -1 ? '&' : '?') + 'ajax=true';
			// submit ajax request function
			xmlhttp.onreadystatechange = function () {
				if (xmlhttp.readyState === 4) {
					if (xmlhttp.status === 200) {
						if (callback && typeof(callback) === "function") {
						 callback(xmlhttp);
					  }
					} else {
						if (error && typeof(error) === "function") {
						 error(xmlhttp);
					  }
					}
				}
			};
			// ajax request settings
			xmlhttp.open(method, link, true);
			xmlhttp.setRequestHeader("Cache-Control","no-cache");
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.send(data);
		},

		async: function(url, id) {	
			if (d.getElementById(id)) {	
				return false;
			}
			var script = d.createElement("script");
			script.type = "text/javascript";
			script.async = true;
			script.id = id;
			script.src = url;
			d.body.appendChild(script);
		},

		/*
		 * @name	serialize
		 * @type	String
		 * @desc	Converts a form into serialized json
		 * @param	{Object} form - Target form for conversion
		*/
		serialize: function(form) {
		  var query = [];
		  for (i = 0; i < form.elements.length; i++) {
			 j = form.elements[i];
			 if (j.nodeName == 'INPUT' || j.nodeName == 'BUTTON') {
				 if (j.type == 'radio' || j.type == 'checkbox') {
					 if (form.elements[i].checked) query.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				 } else if (j.type != 'submit') {
					 query.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				 }
			 } else if (j.nodeName == 'SELECT') {
				 query.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
			 } else if (j.nodeName == 'TEXTAREA') {
				 query.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
			 }
		  }
		  return query.join("&");
		}

	}

}(document));