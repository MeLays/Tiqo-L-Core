package me.m_3.defaultCore;

import org.json.JSONException;
import org.json.JSONObject;

import me.m_3.tiqoL.WSServer;
import me.m_3.tiqoL.event.EventHandler;
import me.m_3.tiqoL.htmlbuilder.HTMLBody;
import me.m_3.tiqoL.htmlbuilder.HTMLObject;
import me.m_3.tiqoL.htmlbuilder.HTMLSpan;
import me.m_3.tiqoL.htmlbuilder.box.HTMLBox;
import me.m_3.tiqoL.htmlbuilder.input.HTMLCheckbox;
import me.m_3.tiqoL.htmlbuilder.input.HTMLTextInput;
import me.m_3.tiqoL.user.User;

public class Events implements EventHandler {
	
	Main main;
	WSServer server;
	
	public Events(Main main) {
		this.main = main;
		this.server = main.getServer();
	}
	
	public void onHandshakeComplete(User user , String secret) {
		
		try {
			HTMLBox defaultBox = new HTMLBox(server , user);
			defaultBox.fromJSON(new JSONObject(main.defaultSite));
			if (defaultBox.getDirectAccess().containsKey("defaultCore.clickCounter")) defaultBox.updateObject("defaultCore.clickCounter", defaultBox.getObject("defaultCore.clickCounter").setInnerText(main.clicks + " Clicks"), true);
			if (defaultBox.getDirectAccess().containsKey("defaultCore.clickMe")) defaultBox.updateObject("defaultCore.clickMe", defaultBox.getObject("defaultCore.clickMe").setClickHandler(server.getEventManager(), main.clickHandler), true);
			if (defaultBox.getDirectAccess().containsKey("defaultCore.checkMe")) defaultBox.updateObject("defaultCore.checkMe", ((HTMLCheckbox)defaultBox.getObject("defaultCore.checkMe")).setChecked(main.checked).setCheckboxHandler(server.getEventManager(), main.checkboxHandler), true);
			if (defaultBox.getDirectAccess().containsKey("defaultCore.textMe")) defaultBox.updateObject("defaultCore.textMe", ((HTMLTextInput)defaultBox.getObject("defaultCore.textMe")).setTextInputHandler(server.getEventManager(), main.textInputHandler).setHtmlAttribute("value", main.text), true);
			
			if (defaultBox.getDirectAccess().containsKey("defaultCore.Welcome")) {
				HTMLObject obj = defaultBox.getObject("defaultCore.Welcome");
				
				String id = server.getContentServer().serveFile(main.logo);
				main.logo.addUser(user);
				
				HTMLObject img = new HTMLObject("img");
				img.setHtmlAttribute("src", server.getContentServer().getPrivateURL(id));
				
				obj.addChild(img);
				
				defaultBox.updateObject("defaultCore.Welcome", obj , true);
			}

			
			user.addHeaderTag("<link href=\"https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c\" rel=\"stylesheet\">");
			user.addHeaderTag("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css\">");
			defaultBox.setHTMLBody((HTMLBody) defaultBox.body.setJavaScriptCSS("font-family", "'M PLUS Rounded 1c', sans-serif"));			
			user.setHTMLBox(defaultBox);
			user.setTitle("Tiqo-L Welcome");
		} catch (JSONException e) {
			user.getHtmlBox().setHTMLBody((HTMLBody) new HTMLBody().addChild(new HTMLSpan("JSON Error in the default_htmlbox.json-file, look in the server-console for the complete error.")));
			e.printStackTrace();
		} catch (Exception e) {
			user.getHtmlBox().setHTMLBody((HTMLBody) new HTMLBody().addChild(new HTMLSpan("Could not read the default_htmlbox.json-file, look in the server-console for the complete error.")));
			e.printStackTrace();
		}
		
	}
	
	public void onConnectionEnd(User user , int code , String reason , boolean remote) {
		
	}
	
}
