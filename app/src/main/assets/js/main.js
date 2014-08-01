//Robert Smith 
//MDF3 Week 4 Term 1407
//This is the main.js file for MDF3 Week 4 Project

$(document).ready(function(){

	$(form).submit(function(){
		var formFields = $("form").serializeArray();

		console.log(formFields[0]);
		console.log(formFields[1]);
		console.log(formFields[2]);

		Android.saveFormFields(formFields[0].value, formFields[1].value, formFields[2]);

	});


});