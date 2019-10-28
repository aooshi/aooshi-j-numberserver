package org.aooshi.j.numberserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class SnowFlakeConfiguration {


	@Value("${SnowFlake.DataCenterId}")
	private long dataCenterId;
	@Value("${SnowFlake.MachineId}")
	private long machineId;
	@Value("${SnowFlake.StartStamp}")
	private long startStamp;


	public long getDataCenterId() {
		return dataCenterId;
	}

	public long getMachineId() {
		return machineId;
	}

	public long getStartStamp() {
		return startStamp;
	}

	private SnowFlakeConfiguration()
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
			 this.dataCenterId  = Long.parseLong(props.getProperty("SnowFlake.DataCenterId"));
			 this.machineId  =  Long.parseLong(props.getProperty("SnowFlake.MachineId"));
			 this.startStamp  =  Long.parseLong(props.getProperty("SnowFlake.StartStamp"));
		 }
	}
}
