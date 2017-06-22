package com.object.diamond;

import com.object.utils.JSONUtil;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by syevia on 2015/1/4.
 */
public abstract class DynamicConfig {

    private volatile Map<String,String> configs;

    private  final Logger LOGGER = LoggerFactory.getLogger(DynamicConfig.class);

    public abstract String getUserGroupId();

    public abstract String getUserdynamicDataId();

    public DynamicConfig(){
        DefaultDiamondManager defaultDiamondManager = new DefaultDiamondManager(getUserGroupId(), getUserdynamicDataId(), ruleConfigListener);
        //第一次获取数据
        defaultDiamondManager.getManagerListeners().get(0).receiveConfigInfo(defaultDiamondManager.getAvailableConfigureInfomation(5000));
    }

    private ManagerListener ruleConfigListener = new ManagerListener() {
        public void receiveConfigInfo(String configInfo) {
            if (configInfo != null && configInfo.length() != 0) {
                LOGGER.info("[配置变更]CouponDynamicConfig的信息发生变更: " + configInfo);
                Properties prop = new Properties();
                try {
                    prop.load(new StringReader(configInfo));
                    refreshInfo(prop);
                } catch (IOException e) {
                    LOGGER.error(getUserdynamicDataId() + " parse failed, configInfo:" + configInfo, e);
                }
            } else {
                LOGGER.error("CouponDynamicConfig的配置信息为空");
                throw new RuntimeException("CouponDynamicConfig的配置信息为空");
            }
        }

        public Executor getExecutor() {
            return null;
        }
    };

    private void refreshInfo(Properties properties){
        if(null != configs){
            configs.clear();
        }
        configs = new HashMap<String, String>((Map)properties);
    }

    public Map<String, String> getMapValue(String key, Map<String, String> defaultValue) {
        if (configs != null && configs.get(key) != null) {
            try {
                return JSONUtil.parseJSONMessage(configs.get(key), HashMap.class);
            } catch (Exception e) {
                LOGGER.error("getMapValue Error:", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public String getStringValue(String key, String defaultValue) {
        if (configs != null && configs.get(key) != null) {
            try {
                return configs.get(key);
            } catch (Exception e) {
                LOGGER.error("getStringValue Error:", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public int getIntValue(String key, int defaultValue){
        if ( configs != null && configs.get(key) != null){
            try {
                return  Integer.parseInt(configs.get(key));
            }catch (Exception e){
                LOGGER.error("getIntValue Error:",e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public long getLongValue(String key, long defaultValue){
        if ( configs != null && configs.get(key) != null){
            try {
                return  Long.parseLong(configs.get(key));
            }catch (Exception e){
                LOGGER.error("getLongValue Error:",e);
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
