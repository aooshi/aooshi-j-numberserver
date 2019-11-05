package org.aooshi.j.numberserver.util;

import static java.lang.Thread.sleep;

/**
 * Twitter的分布式自增ID雪花算法snowflake简化版本，位数长度52位（JS兼容），输出15-16字符长度
 * 18 年内， 15超过则16
 **/
public class SnowFlakeE {

    private ISnowFlakeConfiguration snowFlakeConfiguration;
    public ISnowFlakeConfiguration getConfiguration()
    {
        return this.snowFlakeConfiguration;
    }

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 7; //序列号占用的位数 / 128
    private final static long MACHINE_BIT = 4;   //机器标识占用的位数 / 16

    /**
     * 每一部分的最大值
     */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_STMP = -1L ^ (-1L << 41);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIMESTMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳
    private long START_STMP = 0;

     public SnowFlakeE(ISnowFlakeConfiguration snowFlakeConfiguration) {

        this.snowFlakeConfiguration = snowFlakeConfiguration;

        long machineId = this.snowFlakeConfiguration.getMachineId();
        long startStmp = this.snowFlakeConfiguration.getStartStamp();

        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
        throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
        this.START_STMP = startStmp;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if ((currStmp - START_STMP) > MAX_STMP) {
            throw new RuntimeException("Time limit exceeded, 69 year");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        //SnowFlake snowFlake = new SnowFlake(1, 1);

        ISnowFlakeConfiguration snowFlakeConfigurationE = new SnowFlakeConfigurationE15();

        SnowFlakeE snowFlake = new SnowFlakeE(snowFlakeConfigurationE);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(snowFlake.nextId());
            sleep(1000);
        }

        System.out.println(System.currentTimeMillis() - start);


        //99 year - 19
        //2647246329121738752

        //< 1 year -13
        //2968271327232

        //1 year - 18
        //132274979596275712

        //10 year- 19
        //1323444201031077888
    }
}

