package org.aooshi.j.numberserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class SnowFlakeConfigurationE15 implements ISnowFlakeConfiguration {


	@Value("${SnowFlake15.MachineId}")
	private long machineId;
	@Value("${SnowFlake15.StartStamp}")
	private long startStamp;


	@Override
	public long getMachineId() {
		return machineId;
	}

	@Override
	public long getStartStamp() {
		return startStamp;
	}

	@Override
	public long getDataCenterId() {
		return -1;
	}

	public SnowFlakeConfigurationE15()
	{	
		 Properties props = null;
		 
		 try {

			props = PropertiesLoaderUtils
						.loadAllProperties("application.properties");

		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 if (props != null)
		 {
			 this.machineId  =  Long.parseLong(props.getProperty("SnowFlake15.MachineId"));
			 this.startStamp  =  Long.parseLong(props.getProperty("SnowFlake15.StartStamp"));
		 }
	}
}
