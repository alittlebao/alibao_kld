require.config({
	'baseUrl': 'requireJS',
	'paths': {
		"jquery": "../../base-js/jquery-3.3.1",
		"moduleA": "../moduleA",
		"moduleB": "../moduleB",
		"moduleC": "../moduleC",
		"moduleD": "../moduleD"
		
	},
	'shim': {
		'moduleD': {
			deps: ['jquery'],
			exports: 'moduleD'
		}
	}
});