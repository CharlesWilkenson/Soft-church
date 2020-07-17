
/****************************VALIDATION MAILSENDER*************************/
		$.validator.setDefaults( {
			submitHandler: function () {
				$(form).ajaxSubmit();
			}
		} );
		


		/****************************RECHERCHER MEMBRE*************************/

		$( document ).ready( function () {
			
			

			$( "#code_membre" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
							email: {
								required: true,
								email: true
							},
							code:{
								required:true,
								
				                 },
							
																	
						},
						messages: {
							email:{
								required:"Entrer votre email",
								email:"Email invalide",
								
							},
							
							
							code:{
								required:"Entrer le code du membre",
								
							}
						
							
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" );

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

						
						},
				
					
					} );
				} );

		
		
		
		
		
		
		
		
		

		/****************************MEMBRE*************************/

		$( document ).ready( function () {
			$.validator.addMethod("valid", function(value, element) {
				if (this.optional(element)) {
					return true;
				}
			    return /^[a-zA-Z \s]+$/.test(value);
			}, "Charactere invalide");
			
			

			
			$( "#membre" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						 
							nom:{
								required:true,
								valid:true,
								minlength: 3
						               },
							prenom: {
								required: true,
								valid:true,
								minlength: 3
							},
							identification: {
								required: true,
								minlength: 5
							},
							adresse: {
								required: true,
								minlength: 5,
								
							},
							email: {
								required: true,
								email: true
							},
							tel: {
								required: true,
								minlength:8
							},
									
						
							lieuNaissance: {
								required:true,
							
							},
							dateNaissance: {
								required:true,
							
							},
						
							nationalite: {
								required:true,
							
							},
						
							dateNaissance: {
								required:true,
							
							},
							
						},
						messages: {
						
							nom:{
								
								required:"Entrer le nom",
								minlength:"Minimun 3 Characteres",
								
							},
							prenom: {
								required:"Entrer le prenom",
								minlength:"Minimun 3 Characteres"
							},
							identification: {
								required:"Entrer l'identification",
								minlength:"Minimun 10 Characteres"
							},
							email: {
								required:"Entrer l'email",
								email:"Email invalide"
							},
							adresse: {
								required:"Entrer l'adresse",
								minlength:"Minimun 5 Characteres"
							},
							tel: {
								required:"Entrer le Telephone",
								minlength:"Minimun 8 Characteres"
							},
						
							
							lieuNaissance: {
								required:"Entrer le lieu de naissance",
							
							},
							dateNaissance: {
								required:"Entrer la date de naissance",
							
							},
							
							nationalite: {
								required:"Entrer la nationalite",
							
							},
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" );

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !element.next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element ).css("color", "#FF0000");
							}
						},
						success: function ( label, element ) {
							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !$( element ).next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) ).css("color", "green");
							}
						},
						highlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-error" ).removeClass( "has-success" );
							$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" ).css("color", "#FF0000");
						},
						unhighlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-success" ).removeClass( "has-error" );
							$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" ).css("color", "green");
						}
					} );
				} );
				
		
	


		/****************************Change Password*************************/

		$( document ).ready( function () {
		
			$( "#user" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						 
							password:{
								required:true,
						
								minlength: 5,
						               },
						               password_confirm : {
						                    minlength : 5,
						                    equalTo : "#password",
						                },
						                email:{
											required:true,
								            email:true,
									               },
									               
									               role:{
														required:true,
											
												               },
							
						},
						messages: {
							   role:{
									
									required:"Choisir le role",	
								},
	                        email:{
								email:"Email invalide",
								required:"Entrer l'email",	
							},
							password:{
								
								required:"Entrer votre nouveau mot de passe",
								minlength:"Minimun 5 Characteres",
								
							},
							password_confirm : {
								required:"Retappez votre nouveau mot de passe",
								minlength:"Minimun 3 Characteres",
								equalTo :"Les deux mots de passe ne correspondent pas",
							},
							
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" );

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !element.next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element ).css("color", "#FF0000");
							}
						},
						success: function ( label, element ) {
							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !$( element ).next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) ).css("color", "green");
							}
						},
						highlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-error" ).removeClass( "has-success" );
							$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" ).css("color", "#FF0000");
						},
						unhighlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-success" ).removeClass( "has-error" );
							$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" ).css("color", "green");
						}
					} );
				} );
		
		
		
		
		
		
		/****************************Don*************************/

		$( document ).ready( function () {
		
			$( "#formCheque" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						 
							donateur:{
								required:true,
						
						               },
						               montant : {
						            	   required:true,
						                },
						                devise:{
											required:true,
								           
									               },
									               
									               description:{
														required:true,
											
												               },
												               "#membre.code":{
																	required:true,
														
															               },
															               banque:{
																				required:true,
																	
																		               },
																		               numCheque:{
																							required:true,
																				
																					               },
						},
						messages: {
							donateur:{
									
									required:"Entrer le donateur",	
								},
								montant:{
						
								required:"Entrer le montant",	
							},
							devise:{
								
								required:"Entrer la devise",
							
								
							},
							description : {
								required:"Entrer la description",
						},
							
						"#membre.code" : {
							required:"Entrer le code du membre",
					},
					banque : {
						required:"Entrer la banque",
				},
				numCheque : {
					required:"Entrer le numero du cheque",
			},
				
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" );

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !element.next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element ).css("color", "#FF0000");
							}
						},
						success: function ( label, element ) {
							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !$( element ).next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) ).css("color", "green");
							}
						},
						highlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-error" ).removeClass( "has-success" );
							$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" ).css("color", "#FF0000");
						},
						unhighlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-success" ).removeClass( "has-error" );
							$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" ).css("color", "green");
						}
					} );
				} );
		
		$( document ).ready( function () {
			
			$( "#formCash" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						 
							donateur:{
								required:true,
						
						               },
						               montant : {
						            	   required:true,
						                },
						                devise:{
											required:true,
								           
									               },
									               
									               description:{
														required:true,
											
												               },
												               "#membre.code":{
																	required:true,
														
															               },
															               banque:{
																				required:true,
																	
																		               },
																		               numCheque:{
																							required:true,
																				
																					               },
						},
						messages: {
							donateur:{
									
									required:"Entrer le donateur",	
								},
								montant:{
						
								required:"Entrer le montant",	
							},
							devise:{
								
								required:"Entrer la devise",
							
								
							},
							description : {
								required:"Entrer la description",
						},
							
						"#membre.code" : {
							required:"Entrer le code du membre",
					},
					banque : {
						required:"Entrer la banque",
				},
				numCheque : {
					required:"Entrer le numero du cheque",
			},
				
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" );

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !element.next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element ).css("color", "#FF0000");
							}
						},
						success: function ( label, element ) {
							// Add the span element, if doesn't exists, and apply the icon classes to it.
							if ( !$( element ).next( "span" )[ 0 ] ) {
								$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) ).css("color", "green");
							}
						},
						highlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-error" ).removeClass( "has-success" );
							$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" ).css("color", "#FF0000");
						},
						unhighlight: function ( element, errorClass, validClass ) {
							$( element ).addClass( "has-success" ).removeClass( "has-error" );
							$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" ).css("color", "green");
						}
					} );
				} );