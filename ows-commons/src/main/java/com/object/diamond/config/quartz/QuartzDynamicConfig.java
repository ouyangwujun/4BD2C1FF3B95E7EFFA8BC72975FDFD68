package com.object.diamond.config.quartz;

import com.object.diamond.AbstractRuleConfig;
import com.object.diamond.impl.PropertiesDiamondConfig;

/**
 * Created by PUZE81 on 2017/3/24.
 */
public class QuartzDynamicConfig extends AbstractRuleConfig<String> {

    private final static String LOGISTICS_DYNAMIC_CONFIG_GROUPID = "common";
    private final static String LOGISTICS_DYNAMIC_CONFIG_DATAID = "com.object.common.quartz";

    protected QuartzDynamicConfig(String subkeyPrefix) {
         super(LOGISTICS_DYNAMIC_CONFIG_DATAID,
                LOGISTICS_DYNAMIC_CONFIG_GROUPID,
                subkeyPrefix,
                new PropertiesDiamondConfig.DynamicValueDecoder<String>() {
            public String decode(String value) {
                return value;
            }
         });
    }

}
