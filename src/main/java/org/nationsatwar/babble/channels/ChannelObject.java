package org.nationsatwar.babble.channels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "channels")
public class ChannelObject {
	
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String channelName;
	
	public ChannelObject(String channelName) {
		
		this.channelName = channelName;
	}
	
	public String getChannelName() {
		return channelName;
	}
	
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}