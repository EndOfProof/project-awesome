package app.models;

import org.joda.time.DateTime;

public class WorldTime {
	public TimeScale scale;
	private DateTime currentWorldTime;
	
	public enum TimeScale {
		FIVE_SECONDS 	(5, 	"Five seconds"),
		THIRTY_SECONDS 	(30, 	"Thirty seconds"),
		FIVE_MINUTES 	(300, 	"Five minutess"),
		THIRTY_MINUTES 	(1800, 	"Thirty minutess"),
		FIVE_HOURS 		(18000, "Five hours");
		
		private final int multiplier;
		private final String label;
		TimeScale(int multiplier, String label) {
			this.multiplier = multiplier;
			this.label = label;
		}
	    public String label() { return label; }
	}
	
	public WorldTime() {
		this.scale = TimeScale.FIVE_SECONDS;
		this.currentWorldTime = new DateTime(2012, 5, 2, 8, 0);
	}
	
	public DateTime step(float delta) {
		this.currentWorldTime = this.currentWorldTime.plusSeconds(Math.round(delta * this.scale.multiplier));
		return this.currentWorldTime;
	}
	
	public DateTime getCurrentDateTime() {
		return this.currentWorldTime;
	}
	
	public void setScale(TimeScale scale) {
		if(scale == null) {
			throw new IllegalArgumentException("Null value not allowed");
		}
		this.scale = scale;
	}
}
