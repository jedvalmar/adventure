$(document).ready(function(){

    	  $("input").keypress(function( event ) {
    		  if ( event.which == 13 ) {
    			  getConnection().send($("input").val());
    			  $("input").val("");
    		  }
    		});

    	  
    	  var handleJsonMessage = function(jsonMsg) {
//    		  {  "messages": ["text Message"],
//    			 "room": { "name":"Town Center",
//    				     "description":"",
//    				     "items":[],
//    				     "characters":[]     
//    			 		 }
//    		  }
    		  if (jsonMsg.messages) {
    			  for (var i = 0; i < jsonMsg.messages.length; i++) {
    				    $("div#messages").append(jsonMsg.messages[i] + "<br/>");
    			  }
    		  }
    		  if (jsonMsg.room) {
    			  if (jsonMsg.room.name) {
    				  $("div#room_name").text(jsonMsg.room.name);
    			  }
    			  if (jsonMsg.room.description) {
    				  $("div#description").empty();
    				  for (var i = 0; i < jsonMsg.room.description.length; i++) {
    					  $("div#description").append(jsonMsg.room.description[i] + "<br/>");
    				  }
    			  }
    			  if (jsonMsg.room.items) {
    				  $("div#items").empty();
    				  for (var i = 0; i < jsonMsg.room.items.length; i++) {
      				    $("div#items").append(jsonMsg.room.items[i].name + ", ");
    				  }
    			  }
    			  if (jsonMsg.room.characters) {
    				  $("div#characters").empty();
    				  for (var i = 0; i < jsonMsg.room.characters.length; i++) {
        				    $("div#characters").append(jsonMsg.room.characters[i].name + ", ");
      				  }
    			  }
    		  }
    	  };
    	  var connection;
    	  var getConnection = function() {
    		  if (typeof(connection) == "undefined" || connection.readyState != 1) {
    			  if ("WebSocket" in window){
    				  connection = new WebSocket("ws://localhost:8080/Adventure/adventure/myHandler?cId=" + queryString["cId"]);
    				  connection.onopen = function(){
    					  /*Send a small message to the console once the connection is established */
    					  console.log('Connection open!');
    				  };
    				  connection.onmessage = function(e){
    					  var server_message = e.data;
    					  console.log(server_message);
    					  document.getElementsByName('text').value = server_message ;
    					  var jsonMsg = JSON.parse(server_message);
    					  handleJsonMessage(jsonMsg);
    				  };
    			  } else {
    				  /*WebSockets are not supported. Try a fallback method like long-polling etc*/
    			  };
    		  }
    		  return connection;
    	  };
    	  var queryString = function() {
    		  // This function is anonymous, is executed immediately and 
    		  // the return value is assigned to QueryString!
    		  var query_string = {};
    		  var query = window.location.search.substring(1);
    		  var vars = query.split("&");
    		  for (var i=0;i<vars.length;i++) {
    		    var pair = vars[i].split("=");
    		    	// If first entry with this name
    		    if (typeof query_string[pair[0]] === "undefined") {
    		      query_string[pair[0]] = pair[1];
    		    	// If second entry with this name
    		    } else if (typeof query_string[pair[0]] === "string") {
    		      var arr = [ query_string[pair[0]], pair[1] ];
    		      query_string[pair[0]] = arr;
    		    	// If third or later entry with this name
    		    } else {
    		      query_string[pair[0]].push(pair[1]);
    		    }
    		  } 
    		    return query_string;
    		} ();
    	  
  });