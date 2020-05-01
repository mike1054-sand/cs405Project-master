	// Query should be give idnum, handle of at most 4 (Hint: LIMIT 4)
    // idnum and handles of people followed by people that are followed
	// by you BUT not you and not anyone you
	// already follow.
	// Input: curl -d '{"handle":"@cooldude42", "password":"mysecret!"}' -H "Content-Type: application/json" -X 
	//POST http://localhost:9990/api/suggestions (Links to an external site.)
	// Output, status > 0 is the number of suggested people returned
	// Output: {"status":"3", "idnums":"1,2,4", "handles":"@paul,@carlos","@fake"}
	// Output: {"status":"0", "error":"no suggestions"}
	@Get
	@Path("/suggestions")
	@Procedures(MediaType.APPLICATION_JSON)
	public Response suggestions(){
	String responseString = "{\"status_code\":0, "+"\"error\": \"no suggestions\"}";
	try{
	Map<String, String> teamMap = Launcher.dbEngine.sugUsers();
	responseString = "{\status_code\":3}" "+" Launcher.gson.toJson(teamMap);
	}
	catch (Exception ex){
	StringWriter sw = new StringWriter();
	ex.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    ex.printStackTrace();
    return Response.status(500).entity(exceptionAsString).build();
	}
	return Response.ok(responseString).header("Access-Control-Allow-Origin", "*").build();
	}//suggestions
