define([],function () {

	console.log("define moduleC");
	
	(function b(){
		console.log("zzzbbbb");
		return "fuction b"
	})();

    return {
		name: "moduleC",
		size: "unisize",
		getName: function(key) {
			return b() + "==>" + key
		}
	}
})