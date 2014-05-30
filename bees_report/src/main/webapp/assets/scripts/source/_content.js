beesden.content = function(d) {

	/*
	 * @name	pagination
	 * @type	Function
	 * @desc	Paginate through results using AJAX instead of full page refreshes
	*/	
	pagination = function () {
		var container = d.querySelectorAll('.paginationContainer'),
			popped = ('state' in window.history && window.history.state !== null),
			initialUrl = location.href,
			linkList,
			overlay = d.getElementById("contentOverlay"),
			sortOrders,
			link;

		if (!container.length) {
			return
		}

		// Set up the overlay if it does not exist
		if (!overlay) {
			overlay = overlay || d.createElement("div");
			overlay.id = 'contentOverlay';
			container[0].parentNode.appendChild(overlay);
		}

		// Insert new content and reinitialise scripts
		 insertContent

		 // Iterate over the parents to calculate total distance from top
		 function findPos(obj) {
			var curtop = 0;
			if (obj.offsetParent) {
				do {
					curtop += obj.offsetTop;
				} while (obj = obj.offsetParent);
				return curtop;
			}
		}

    	// Scroll to the top whilst the ajax is loading
	    function scrollTo(element, to, duration) {
	        if (d.body.scrollTop <= to) return;
	        var perTick = to / duration * 50;
	       	d.body.scrollTop = d.body.scrollTop - perTick;	        
	        setTimeout(function() {
	            scrollTo(element, to, duration);
	        }, 5);
	    }

		// Run ajax request for updated content
		function updateContent(link) {
			popped = true;
			overlay.className += (' reveal');
			scrollTo(overlay, findPos(contentOverlay) - 50, 700);
			beesden.ajax(location.href, function(data) {
				d.getElementById('info').innerHTML = data.responseText;
				pagination();			
			}, function() {
				overlay.className = overlay.className.replace(' reveal', '');
			});		
		}

		for (i = 0; i < container.length; i++) {
			// Make link elements ajaxable			
			linkList = container[i].getElementsByTagName('a');
			for (var j = 0; j < linkList.length; j++) {
				linkList[j].onclick = function() {
					window.history.pushState(null, null, this.href);
					updateContent(link);
					return false;
				}
			}
			// Make sort order select ajaxable			
			sortOrders = container[i].getElementsByTagName('select');
			for (var j = 0; j < sortOrders.length; j++) {
				sortOrders[j].onchange = function() {
					link = this.form.action + beesden.serialize(this.form);
					window.history.pushState(null, document.title, link);	
					updateContent(link);		
				}
			}
		}
		// Implements history for browser back / forward buttons
		window.onpopstate = function(event){
			// Prevent reload on initial page load
			if (location.href == initialUrl && !popped) {
				return;
			}
			updateContent(link);			
		}
	}

	return {
		init: function() {
			pagination();
		}
	};
}(document);