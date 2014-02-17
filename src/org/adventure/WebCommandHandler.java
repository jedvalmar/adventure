package org.adventure;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.adventure.commands.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebCommandHandler extends TextWebSocketHandler {
	Logger log = LoggerFactory.getLogger(WebCommandHandler.class);
	@Autowired
	CommandHandler commandHandler;
	
	@Autowired
	CharacterHolder characterHolder;
	
    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		Object cId = session.getHandshakeAttributes().get("cId");
		log.debug("Establishing Connection.");
		characterHolder.setCharacterId(cId.toString());
		characterHolder.getCharacter().setSession(session);
		sendRoom(characterHolder.getCharacter().getCurrentRoom());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		characterHolder.getCharacter().setSession(null);
	}

	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		Object cId = session.getHandshakeAttributes().get("cId");
		characterHolder.setCharacterId(cId.toString());
    	System.out.println(message.getPayload());
    	System.out.println(session.getId() + " = " +Thread.currentThread().getId());
    	commandHandler.handle(message.getPayload());
    }
    
	  public void sendRoom(Room room) {
	    	try {
	    		JsonFactory jsonFactory = new JsonFactory();
	    		Writer writter = new StringWriter();
	    		jsonFactory.createGenerator(writter).setCodec(new ObjectMapper()).writeObject(room);
	    		StringBuilder sb = new StringBuilder();
	    		sb.append("{\"room\":");
	    		sb.append(writter.toString());
	    		sb.append("}");
	    		characterHolder.getCharacter().getSession().sendMessage(new TextMessage(sb.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
    public void sendMessage(String message) {
    	try {
    		StringBuilder sb = new StringBuilder();
    		sb.append("{\"messages\":[\"");
    		sb.append(message);
    		sb.append("\"]}");
    		characterHolder.getCharacter().getSession().sendMessage(new TextMessage(sb.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
