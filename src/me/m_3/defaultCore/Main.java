package me.m_3.defaultCore;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.dizitart.no2.Nitrite;
import org.slf4j.LoggerFactory;

import me.m_3.tiqoL.WSServer;
import me.m_3.tiqoL.contentserver.ServableFile;
import me.m_3.tiqoL.coreloader.Core;

public class Main extends Core{
	
	ClickHandler clickHandler;
	CheckboxHandler checkboxHandler;
	TextInputHandler textInputHandler;

	
	static org.slf4j.Logger Logger = LoggerFactory.getLogger(Main.class);
	
	String defaultSite;
	ServableFile logo;
	
	int clicks = 0;
	boolean checked = false;
	String text = "Start editing this ...";
	
	Nitrite db;

	public Main(WSServer server , String name) {
		super(server , name);
		
		clickHandler = new ClickHandler(this);
		checkboxHandler = new CheckboxHandler(this);
		textInputHandler = new TextInputHandler(this);
		
		this.registerEventHandler(new Events(this));
		
		Logger.info("Updating the 'default_htmlbox.json' file ...");
    	
    	InputStream defaultSite = this.getClass().getResourceAsStream("default_htmlbox.json");
    	File file = new File(System.getProperty("user.dir") + File.separator + "core" + File.separator + "favicon.ico");
		logo = new ServableFile(file);
		logo.setPrivate(true);
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(defaultSite));
        StringBuilder out = new StringBuilder();
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    out.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.defaultSite = out.toString();
		
	}

}
