package tests.models;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import app.models.WorldTime;
import app.models.WorldTime.TimeScale;

public class WorldTimeTest {
	
	private WorldTime worldTime;
	
	@Before
	public void setUp() throws Exception {
		worldTime = new WorldTime();
	}

	@After
	public void tearDown() throws Exception {
		worldTime = null;
	}
	
	@Test
	public void testWorldTime() {
		assertEquals("WorldTime should be initialised with the five second scale", WorldTime.TimeScale.FIVE_SECONDS, worldTime.scale);
		assertEquals("WorldTime should be initialised with the date as 2012/05/02 08:00:00", new DateTime(2012, 05, 02, 8, 0, 0), worldTime.getCurrentDateTime());
	}
	
	@Test
	public void testStep() {
		String format = "YYYY-MM-dd HH:mm:ss";
		DateTime newDate = worldTime.step(1);
		assertEquals(".step() with default scale returns the new date", "2012-05-02 08:00:05", newDate.toString(format));
		assertEquals(".step() updates the date on the instance", "2012-05-02 08:00:05", worldTime.getCurrentDateTime().toString(format));
		
		newDate = worldTime.step(2);
		assertEquals(".step() scales with the delta", "2012-05-02 08:00:15", newDate.toString(format));
		
		worldTime.setScale(TimeScale.THIRTY_MINUTES);
		newDate = worldTime.step(1);
		assertEquals(".step() scales with the selected time scale", "2012-05-02 08:30:15", newDate.toString(format));
	}
	
	@Test
	public void testSetScale() {
		worldTime.setScale(TimeScale.FIVE_HOURS);
		assertEquals(".setScale() sets the scale on the instance", TimeScale.FIVE_HOURS, worldTime.scale);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testScaleThrowsExceptionWhenNotTimeScale() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Null value not allowed");
		worldTime.setScale(null);
	}
}
