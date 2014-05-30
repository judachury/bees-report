module.exports = function(grunt) {

	grunt.initConfig({
		// Configuration JSON
		pkg: grunt.file.readJSON('package.json'),

		// Init 'watch' - run commands on file change
		watch: {
			sass: {
				files: ['../styles/source/*.{scss,sass}'],
				tasks: ['compass']
			},
			js: {
				files: ['../scripts/*/*.js'],
				tasks: ['uglify']
			}
		},

		// Init 'compass' - css compiler
		compass: {
			all: {
		 		options: {			  
		 			sassDir: ['../styles/source'],
		 			cssDir: ['../styles'],
		 			outputStyle: 'compressed'
		 		}
			}
		},

		// Init 'uglify' - js compiler
		uglify: {
			all: {
				files: { '../scripts/scripts.js': '../scripts/source/*.js' }
			}
		}
	});

	// Load the plugins
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-compass');
	grunt.loadNpmTasks('grunt-contrib-uglify');

	// Default task(s).
	grunt.registerTask('default', ['watch']);
}