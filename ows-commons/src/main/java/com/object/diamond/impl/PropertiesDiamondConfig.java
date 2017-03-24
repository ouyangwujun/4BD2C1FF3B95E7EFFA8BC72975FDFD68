package com.object.diamond.impl;

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
public class PropertiesDiamondConfig<T> implements ManagerListener {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesDiamondConfig.class);

    private String dataId;
    private String dataIdGroup;
    private PropertiesDiamondConfig.DynamicValueDecoder<T> dynamicValueDecoder;
    private volatile Map<String, T> properties = new HashMap();


    public Map<String, T> getProperties() {
        return this.properties;
    }

    public PropertiesDiamondConfig(String dataId, String dataIdGroup, PropertiesDiamondConfig.DynamicValueDecoder<T> dynamicValueDecoder) {
        this.dataId = dataId;
        this.dataIdGroup = dataIdGroup;
        this.dynamicValueDecoder = dynamicValueDecoder;
        DefaultDiamondManager defaultDiamondManager = new DefaultDiamondManager(dataIdGroup, dataId, this);
        ((ManagerListener)defaultDiamondManager.getManagerListeners().get(0)).receiveConfigInfo(defaultDiamondManager.getAvailableConfigureInfomation(5000L));
    }

    public Executor getExecutor() {
        return null;
    }

    public void receiveConfigInfo(String configInfo) {
        logger.info(this.dataId + " receiveConfigInfo:" + configInfo);
        if(configInfo != null) {
            Properties prop = new Properties();
            try {
                HashMap e = new HashMap();
                prop.load(new StringReader(configInfo));
                Iterator iterator = prop.keySet().iterator();
                while(iterator.hasNext()) {
                    Object key = iterator.next();
                    e.put((String)key, this.dynamicValueDecoder.decode(prop.getProperty((String)key)));
                }
                this.properties = e;
            } catch (IOException var6) {
                logger.error(this.dataId + " parse failed, configInfo:" + configInfo, var6);
            }

        }
    }

    public T getConfig(String configItemName) {
        return this.properties.get(configItemName);
    }

    public interface DynamicValueDecoder<T> {
        T decode(String var1);
    }
}
