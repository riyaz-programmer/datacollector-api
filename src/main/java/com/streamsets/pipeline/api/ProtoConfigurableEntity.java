/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.api;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Comparator;
import java.util.Map;

/**
 * Shared interface for all active configurable objects - such as Stage or Service.
 *
 * This interface is used to share methods and contexts between applicable stages. It should not be used otherwise.
 */
public interface ProtoConfigurableEntity {

  /**
   * Context that provides runtime information and services to the stage.
   */
  public interface Context extends ConfigIssueContext {
    /**
     * Returns the absolute path to the SDC resources directory.
     *
     * @return  the absolute path to the SDC resources directory.
     */
    public String getResourcesDirectory();

    /**
     * Creates an empty record.
     *
     * @param recordSourceId the ID to identify the record. It should include enough information to track down
     * the record source.
     * @return an empty record with the specified ID.
     */
    Record createRecord(String recordSourceId);

    /**
     * Creates an empty record including the original raw data of the record.
     *
     * @param recordSourceId the ID to identify the record. It should include enough information to track down
     * the record source.
     * @param raw the record raw data.
     * @param rawMime the MIME type of the raw data.
     * @return an empty record with the specified ID and raw data.
     */
    Record createRecord(String recordSourceId, byte[] raw, String rawMime);
    /**
     * Returns the {@link MetricRegistry} used by the pipeline.
     *
     * @return the {@link MetricRegistry} used by the pipeline.
     */
    public MetricRegistry getMetrics();

    /**
     * Creates a {@link Timer} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Timer</code> name.
     * @return A <code>Timer</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Timer createTimer(String name);

    /**
     * Gets the already created {@link Timer} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Timer</code> name.
     * @return the already created <code>Timer</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Timer getTimer(String name);

    /**
     * Creates a {@link Meter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Meter</code> name.
     * @return a <code>Meter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Meter createMeter(String name);

    /**
     * Gets the already created {@link Meter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Meter</code> name.
     * @return the already created <code>Meter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Meter getMeter(String name);

    /**
     * Creates a {@link Counter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Counter</code> name.
     * @return a <code>Counter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Counter createCounter(String name);

    /**
     * Gets the already created {@link Counter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Counter</code> name.
     * @return the already created <code>Counter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Counter getCounter(String name);

    /**
     * Creates a {@link Histogram} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * Creates exponentially decaying histogram that biases the reservoir to the past 5 minutes of measurements.
     *
     * @param name the <code>Histogram</code> name.
     * @return a <code>Histogram</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Histogram createHistogram(String name);

    /**
     * Gets the already created {@link Histogram} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Histogram</code> name.
     * @return the already created <code>Histogram</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Histogram getHistogram(String name);

    /**
     * Creates a {@link Gauge} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Gauge</code> name.
     */
   public Gauge<Map<String, Object>> createGauge(String name);

    /**
     * Creates a {@link Gauge} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * This Gauge's underlying map will be sorted based on the comparator.
     *
     * @param name the <code>Gauge</code> name.
     */
   public Gauge<Map<String, Object>> createGauge(String name, Comparator<String> comparator);

    /**
     * Gets the already created {@link Gauge} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Gauge</code> name.
     * @return the already created <code>Gauge</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Gauge<Map<String, Object>> getGauge(String name);
  }
}
