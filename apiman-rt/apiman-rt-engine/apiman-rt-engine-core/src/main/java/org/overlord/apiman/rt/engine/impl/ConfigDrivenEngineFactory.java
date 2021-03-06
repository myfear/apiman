/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.apiman.rt.engine.impl;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.overlord.apiman.rt.engine.IComponentRegistry;
import org.overlord.apiman.rt.engine.IConnectorFactory;
import org.overlord.apiman.rt.engine.IEngineConfig;
import org.overlord.apiman.rt.engine.IRegistry;
import org.overlord.apiman.rt.engine.policy.IPolicyFactory;

/**
 * Factory for creating the engine, obviously.
 * 
 * @author eric.wittmann@redhat.com
 */
public class ConfigDrivenEngineFactory extends AbstractEngineFactory {

    private IEngineConfig engineConfig;

    /**
     * Constructor.
     * @param engineConfig
     */
    public ConfigDrivenEngineFactory(IEngineConfig engineConfig) {
        this.engineConfig = engineConfig;
    }

    /**
     * @see org.overlord.apiman.rt.engine.impl.AbstractEngineFactory#createRegistry()
     */
    @Override
    protected IRegistry createRegistry() {
        Class<? extends IRegistry> c = engineConfig.getRegistryClass();
        Map<String, String> config = engineConfig.getRegistryConfig();
        return create(c, config);
    }

    /**
     * @see org.overlord.apiman.rt.engine.impl.AbstractEngineFactory#createComponentRegistry()
     */
    @Override
    protected IComponentRegistry createComponentRegistry() {
        return new ConfigDrivenComponentRegistry(engineConfig);
    }

    /**
     * @see org.overlord.apiman.rt.engine.impl.AbstractEngineFactory#createConnectorFactory()
     */
    @Override
    protected IConnectorFactory createConnectorFactory() {
        Class<? extends IConnectorFactory> c = engineConfig.getConnectorFactoryClass();
        Map<String, String> config = engineConfig.getConnectorFactoryConfig();
        return create(c, config);
    }

    /**
     * @see org.overlord.apiman.rt.engine.impl.AbstractEngineFactory#createPolicyFactory()
     */
    @Override
    protected IPolicyFactory createPolicyFactory() {
        Class<? extends IPolicyFactory> c = engineConfig.getPolicyFactoryClass();
        Map<String, String> config = engineConfig.getPolicyFactoryConfig();
        return create(c, config);
    }
    
    /**
     * Creates a new instance of the given type, passing the given config
     * map if possible (if the class has a Map constructor).
     * @param type the type to create
     * @param config config to pass
     * @return a new instance of 'type'
     */
    protected static <T> T create(Class<T> type, Map<String, String> config) {
        try {
            Constructor<T> constructor = type.getConstructor(Map.class);
            return constructor.newInstance(config);
        } catch (Exception e) {
            // Probably doesn't have a map c'tor - so try a no-arg c'tor instead
        }
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
