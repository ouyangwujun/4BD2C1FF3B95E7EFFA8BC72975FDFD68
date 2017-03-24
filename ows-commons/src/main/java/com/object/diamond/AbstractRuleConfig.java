package com.object.diamond;

import com.object.diamond.impl.PropertiesDiamondConfig;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * Created by PUZE81 on 2017/3/21.
 */
public class AbstractRuleConfig<T>  {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRuleConfig.class);

    private volatile String dataId;
    private volatile String dataIdGroup;
    private volatile String subkeyPrefix;

    private volatile Map<String, T> defaultProperties = new HashMap();
    private volatile ConcurrentHashMap<String, PropertiesDiamondConfig<T>> appConfigs = new ConcurrentHashMap();
    private volatile PropertiesDiamondConfig.DynamicValueDecoder<T> dynamicValueDecoder;

    private ManagerListener ruleConfigListener = new ManagerListener() {
        public void receiveConfigInfo(String configInfo) {
            AbstractRuleConfig.logger.info(AbstractRuleConfig.this.dataId + " receiveConfigInfo:" + configInfo);
            if(configInfo != null) {
                Properties prop = new Properties();

                try {
                    HashMap e = new HashMap();
                    prop.load(new StringReader(configInfo));
                    Iterator iterator = prop.keySet().iterator();

                    while(iterator.hasNext()) {
                        Object key = iterator.next();
                        e.put((String)key, AbstractRuleConfig.this.dynamicValueDecoder.decode(prop.getProperty((String)key)));
                    }

                    AbstractRuleConfig.this.defaultProperties = e;
                } catch (IOException var6) {
                    AbstractRuleConfig.logger.error(AbstractRuleConfig.this.dataId + " parse failed, configInfo:" + configInfo, var6);
                }

            }
        }

        public Executor getExecutor() {
            return null;
        }
    };

    protected AbstractRuleConfig(String dataId, String dataIdGroup, String subkeyPrefix, PropertiesDiamondConfig.DynamicValueDecoder<T> dynamicValueDecoder) {
        this.dataId = dataId;
        this.dataIdGroup = dataIdGroup;
        this.subkeyPrefix = subkeyPrefix;
        this.dynamicValueDecoder = dynamicValueDecoder;
        DefaultDiamondManager defaultDiamondManager = new DefaultDiamondManager(dataIdGroup, dataId, this.ruleConfigListener);
        ((ManagerListener)defaultDiamondManager.getManagerListeners().get(0)).receiveConfigInfo(defaultDiamondManager.getAvailableConfigureInfomation(5000L));
    }

    public Object getRule(String key, String ruleName) {
        Object ruleValue = null;
        PropertiesDiamondConfig propertiesDiamondConfig = (PropertiesDiamondConfig)this.appConfigs.get(ruleName);
        if(null == propertiesDiamondConfig) {
            String dataId = this.dataId + "." + this.subkeyPrefix + "." + ruleName;
            PropertiesDiamondConfig tmpPropertiesDiamondConfig = new PropertiesDiamondConfig(dataId, this.dataIdGroup, this.dynamicValueDecoder);
            propertiesDiamondConfig = (PropertiesDiamondConfig)this.appConfigs.putIfAbsent(ruleName, tmpPropertiesDiamondConfig);
            if(null == propertiesDiamondConfig) {
                propertiesDiamondConfig = tmpPropertiesDiamondConfig;
            }
        }

        if(null != propertiesDiamondConfig) {
            ruleValue = propertiesDiamondConfig.getConfig(key);
        }

        if(null == ruleValue) {
            ruleValue = this.defaultProperties.get(ruleName);
        }
        return ruleValue;
    }
}
