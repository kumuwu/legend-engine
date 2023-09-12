// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.persistence.components.util;

import org.finos.legend.engine.persistence.components.logicalplan.LogicalPlan;
import org.finos.legend.engine.persistence.components.logicalplan.operations.Insert;
import org.finos.legend.engine.persistence.components.logicalplan.values.*;
import org.finos.legend.engine.persistence.components.relational.RelationalSink;
import org.finos.legend.engine.persistence.components.relational.SqlPlan;
import org.finos.legend.engine.persistence.components.relational.ansi.optimizer.UpperCaseOptimizer;
import org.finos.legend.engine.persistence.components.relational.api.IngestStatus;
import org.finos.legend.engine.persistence.components.relational.transformer.RelationalTransformer;
import org.finos.legend.engine.persistence.components.transformer.TransformOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public abstract class AppendLogDatasetUtilsTest
{

    private final ZonedDateTime executionZonedDateTime = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    private final TransformOptions transformOptions = TransformOptions.builder().executionTimestampClock(Clock.fixed(executionZonedDateTime.toInstant(), ZoneOffset.UTC)).build();

    private AppendLogMetadataDataset appendLogMetadataDataset = AppendLogMetadataDataset.builder().build();


    @Test
    public void testInsertAppendMetadata()
    {
        AppendLogMetadataUtils appendLogMetadataUtils = new AppendLogMetadataUtils(appendLogMetadataDataset);
        StringValue batchIdValue = StringValue.of("batch_id_123");
        StringValue appendLogTableName = StringValue.of("appeng_log_table_name");
        StringValue batchStatusValue = StringValue.of(IngestStatus.SUCCEEDED.toString());
        StringValue batchLineageValue = StringValue.of("my_lineage_value");
        Insert operation = appendLogMetadataUtils.insertMetaData(batchIdValue, appendLogTableName, BatchStartTimestamp.INSTANCE,
                BatchEndTimestampAbstract.INSTANCE, batchStatusValue, batchLineageValue);

        RelationalTransformer transformer = new RelationalTransformer(getRelationalSink(), transformOptions);
        LogicalPlan logicalPlan = LogicalPlan.builder().addOps(operation).build();
        SqlPlan physicalPlan = transformer.generatePhysicalPlan(logicalPlan);
        List<String> list = physicalPlan.getSqlList();
        String expectedSql = getExpectedSqlForAppendMetadata();
        Assertions.assertEquals(expectedSql, list.get(0));
    }

    public abstract String getExpectedSqlForAppendMetadata();

    @Test
    public void testInsertAppendMetadataInUpperCase()
    {
        AppendLogMetadataUtils appendLogMetadataUtils = new AppendLogMetadataUtils(appendLogMetadataDataset);
        StringValue batchIdValue = StringValue.of("batch_id_123");
        StringValue appendLogTableName = StringValue.of("appeng_log_table_name");
        StringValue batchStatusValue = StringValue.of(IngestStatus.SUCCEEDED.toString());
        StringValue batchLineageValue = StringValue.of("my_lineage_value");

        Insert operation = appendLogMetadataUtils.insertMetaData(batchIdValue, appendLogTableName,
                BatchStartTimestamp.INSTANCE, BatchEndTimestampAbstract.INSTANCE, batchStatusValue, batchLineageValue);

        RelationalTransformer transformer = new RelationalTransformer(getRelationalSink(), transformOptions.withOptimizers(new UpperCaseOptimizer()));
        LogicalPlan logicalPlan = LogicalPlan.builder().addOps(operation).build();
        SqlPlan physicalPlan = transformer.generatePhysicalPlan(logicalPlan);
        List<String> list = physicalPlan.getSqlList();
        String expectedSql = getExpectedSqlForAppendMetadataUpperCase();
        Assertions.assertEquals(expectedSql, list.get(0));
    }

    public abstract String getExpectedSqlForAppendMetadataUpperCase();

    public abstract RelationalSink getRelationalSink();
}
