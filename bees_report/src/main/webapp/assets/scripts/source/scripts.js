window.onload = function() {

	// Initiate scripts for content, e.g. pagination
	if (beesden.content) {
		beesden.content.init();
	}

	// Form plugins for the account / checkout
	if (beesden.forms) {
		beesden.forms.init();
	}

	// Allow JS-specific css styles
	document.body.className += ' js';	

};