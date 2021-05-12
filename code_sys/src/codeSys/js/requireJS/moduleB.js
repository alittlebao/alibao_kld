define(function() {

	console.log("define moduleB");

	return {
		name: "moduleB",
		size: "unisize",
		getName: function(key) {
			return "==>" + key
		}
	}
})