package me.m_3.defaultCore;

import me.m_3.tiqoL.htmlbuilder.exceptions.UnknownObjectIDException;
import me.m_3.tiqoL.htmlbuilder.handlers.HTMLTextInputHandler;
import me.m_3.tiqoL.htmlbuilder.input.HTMLTextInput;
import me.m_3.tiqoL.user.User;

public class TextInputHandler implements HTMLTextInputHandler{

	Main main;
	
	public TextInputHandler (Main main) {
		this.main = main;
	}
	
	public void onInput(User user , String htmlObject , String text) {
		if (htmlObject.equals("defaultCore.textMe")) {
			main.text = text;
			for (User u : main.getServer().getUserMap().values()) {
				if (u.getHtmlBox().getDirectAccess().containsKey("defaultCore.textMe")) {
					try {
						HTMLTextInput textInput = (HTMLTextInput) u.getHtmlBox().getObject("defaultCore.textMe");
						textInput.setHtmlAttribute("value", text);
						u.getHtmlBox().updateObject("defaultCore.textMe", textInput , true);
					} catch (UnknownObjectIDException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
