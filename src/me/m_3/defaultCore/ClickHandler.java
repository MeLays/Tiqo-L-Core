package me.m_3.defaultCore;

import me.m_3.tiqoL.htmlbuilder.exceptions.UnknownObjectIDException;
import me.m_3.tiqoL.htmlbuilder.handlers.HTMLClickHandler;
import me.m_3.tiqoL.user.User;

public class ClickHandler implements HTMLClickHandler{
	
	Main main;
	
	public ClickHandler (Main main) {
		this.main = main;
	}
	
	public void onClick(User user , String htmlObject , double x , double y , double pageX , double pageY) {
		if (htmlObject.equals("defaultCore.clickMe")) {
			main.clicks += 1;
			for (User u : main.getServer().getUserMap().values()) {
				if (u.getHtmlBox().getDirectAccess().containsKey("defaultCore.clickCounter")) {
					try {
						u.getHtmlBox().updateObject("defaultCore.clickCounter", u.getHtmlBox().getObject("defaultCore.clickCounter").setInnerText(main.clicks + " Clicks"), true);
					} catch (UnknownObjectIDException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
