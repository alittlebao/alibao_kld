require.config({
	'paths': {
		"commonJS": "require.config",
		
	}
});

require(["commonJS","moduleA"], function(commonJS,moduleA) {
	
	console.log(moduleA.name);
})
/*
require(["moduleB"], function(moduleB) {
	
	console.log(moduleB.getName("this is moduleB"));
})*/

require(["commonJS","moduleC"], function(moduleC) {
	
	//console.log(moduleC.getName("this is moduleC"));
})

/*require([["commonJS","moduleD"]], function(moduleD) {
	
	var aa = showCommon();
	
	console.log(aa);
})*/




