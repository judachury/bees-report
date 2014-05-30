beesden.forms = function (d) {


	var rangeBubble = function() {

		var inputs = d.querySelectorAll('input[type="range"]'),
			input,
			output,
			i;

		function setValue(input) {			
			output = d.querySelector('output[for=' + input.id + ']');
			output.innerHTML = input.value + '%';
		}

		for (i = 0; i < inputs.length; i++) {
			input = inputs[i];

			setValue(input);

			input.onchange = function() {
				setValue(this);
			}
		}

	},

	fieldAddition = function () {
		var fieldLink = d.querySelector('.addField'),
			fieldInputs = d.querySelector('.items > dl'),
			itemName = 'itemName-',
			itemValue = 'itemValue-',
			types = ['input', 'output', 'label'],
			number,
			item,
			range,
			value;

		if (!fieldLink || !fieldInputs) {
			return
		}

		fieldLink.onclick = function(e) {
			e.preventDefault();

			value = fieldInputs.lastChild.cloneNode(true);
			range = fieldInputs.lastChild.previousSibling.cloneNode(true);
			// Update attributes depending on type			
			for (var i = 0; i < types.length; i++) {
				// Update input field
				item = value.querySelector(types[i]);
				if (item) {			
					number = 0;	
					while (d.getElementById('itemName + number')) {
						number++;
					}
					number = fieldInputs.querySelectorAll('dt').length;	
					if (types[i] == 'output' || types[i] == 'label') {
						item.setAttribute('for', itemName + number);
					}		
					if (types[i] == 'input') {
						item.value = '';
						item.id = itemName + number;
						item.name = itemName + number;
					}
				}
				// Update range field
				item = range.querySelector(types[i]);
				if (item) {				
					number = fieldInputs.querySelectorAll('dt').length;		
					if (types[i] == 'output' || types[i] == 'label') {
						item.setAttribute('for', itemValue + number);
					}		
					if (types[i] == 'input') {
						item.value = 0;
						item.id = itemValue + number;
						item.name = itemValue + number;
					}
				}
			}
			// Insert at end of list and add range bubble output
			fieldInputs.insertBefore(range, fieldInputs.lastChild.nextSibling);
			fieldInputs.insertBefore(value, fieldInputs.lastChild.nextSibling);
			rangeBubble();
			return false;
		}
	},

	inputTidy = function () {

		Array.prototype.forEach.call(d.getElementsByTagName('input'), function(el, i) {

			if (el.type == 'hidden' || el.type == 'checkbox' || el.type == 'radio') {
				return;
			}

			el.addEventListener('blur', function() {
				this.value = this.value.trim();
			});
		});
		
	}

	return {
		init: function() {
			fieldAddition();
			inputTidy();
			rangeBubble();
		}
	};
	
}(document);