/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.configuration.kafka.metrics;

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.TypeHint;

import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A {@link org.apache.kafka.common.metrics.MetricsReporter} class for consumer metrics.
 */
@Internal
@TypeHint(ConsumerKafkaMetricsReporter.class)
public class ConsumerKafkaMetricsReporter extends AbstractKafkaMetricsReporter {

    private static final String CONSUMER_PREFIX = AbstractKafkaConfiguration.PREFIX + ".consumer";
    private static final Set<String> INCLUDED_TAGS;

    static {
        final Set<String> includedTags = new HashSet<>();
        includedTags.add(TAG_CLIENT_ID);
        includedTags.add(TAG_TOPIC);
        includedTags.add("group-id");
        INCLUDED_TAGS = Collections.unmodifiableSet(includedTags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getMetricPrefix() {
        return CONSUMER_PREFIX;
    }

    /**
     * The tags to include in the gauge. Defaults to just the client-id.
     *
     * @return The tags to include
     */
    @Override
    protected Set<String> getIncludedTags() {
        return INCLUDED_TAGS;
    }

    /**
     * Method to close bean.  This must remain here for metrics to continue to function correctly for some reason?!.
     */
    @PreDestroy
    @Override
    public void close() {
        super.close();
    }
}
