package org.aooshi.j.numberserver.service;

import org.aooshi.j.numberserver.util.*;

public class SnowFlakeEService {

    private SnowFlakeE snowFlakeE = null;
    private SnowFlakeE snowFlakeE16 = null;
    private SnowFlakeE snowFlakeE15 = null;

    public SnowFlakeE getSnowFlakeE() {
        if (this.snowFlakeE == null) {
            ISnowFlakeConfiguration snowFlakeConfigurationE = SpringContextUtils.getBean(SnowFlakeConfigurationE.class);
            this.snowFlakeE = new SnowFlakeE(snowFlakeConfigurationE);
        }
        return snowFlakeE;
    }

    public SnowFlakeE getSnowFlakeE16() {
        if (this.snowFlakeE16 == null) {
            ISnowFlakeConfiguration snowFlakeConfigurationE16 = SpringContextUtils.getBean(SnowFlakeConfigurationE16.class);
            this.snowFlakeE16 = new SnowFlakeE(snowFlakeConfigurationE16);
        }
        return snowFlakeE16;
    }

    public SnowFlakeE getSnowFlakeE15() {
        if (this.snowFlakeE15 == null) {
            ISnowFlakeConfiguration snowFlakeConfigurationE15 = SpringContextUtils.getBean(SnowFlakeConfigurationE15.class);
            this.snowFlakeE15 = new SnowFlakeE(snowFlakeConfigurationE15);
        }
        return snowFlakeE15;
    }
}
