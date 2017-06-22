package com.object.diamond.impl;

import com.object.diamond.DynamicConfig;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by PUZE81 on 2017/3/21.
 */
public class DemoDiamondConfig extends DynamicConfig {

    private static final String                USER_GROUP_ID          = "COUPON";
    private static final String                USERDYNAMIC_DATA_ID    = "com.sfebiz.coupon.dynamic.config";

    private DemoDiamondConfig(){
        super();
    }

    private static final DemoDiamondConfig instance = new DemoDiamondConfig();

    public static DemoDiamondConfig getInstance() {
        return instance;
    }

    @Override
    public String getUserGroupId() {
        return USER_GROUP_ID;
    }

    @Override
    public String getUserdynamicDataId() {
        return USERDYNAMIC_DATA_ID;
    }
}
