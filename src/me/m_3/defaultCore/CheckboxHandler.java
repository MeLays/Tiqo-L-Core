package me.m_3.defaultCore;

import me.m_3.tiqoL.htmlbuilder.exceptions.UnknownObjectIDException;
import me.m_3.tiqoL.htmlbuilder.handlers.HTMLCheckboxHandler;
import me.m_3.tiqoL.htmlbuilder.input.HTMLCheckbox;
import me.m_3.tiqoL.user.User;

public class CheckboxHandler implements HTMLCheckboxHandler{
	
	Main main;
	
	public CheckboxHandler(Main main) {
		this.main = main;
	}

	public void onToggle(User user , String htmlObject , boolean checked) {
		if (htmlObject.equals("defaultCore.checkMe")) {
			main.checked = checked;
			for (User u : main.getServer().getUserMap().values()) {
				if (u.getHtmlBox().getDirectAccess().containsKey("defaultCore.clickCounter")) {
					try {
						u.getHtmlBox().updateObject("defaultCore.checkMe", ((HTMLCheckbox)u.getHtmlBox().getObject("defaultCore.checkMe")).setChecked(main.checked), true);
					} catch (UnknownObjectIDException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
