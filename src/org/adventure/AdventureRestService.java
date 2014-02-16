package org.adventure;

import org.adventure.commands.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Scope("session")
@RequestMapping("/rest")
public class AdventureRestService {

	@Autowired
	CommandHandler commandHanlder;
	
	
	@RequestMapping(value="/cmd", method = RequestMethod.GET, produces="application/json", consumes="application/json")
	@ResponseBody
	public String processSubmit(@RequestParam String cmd) {
		
	    commandHanlder.handle(cmd);
	    return "done.";
	}
}
