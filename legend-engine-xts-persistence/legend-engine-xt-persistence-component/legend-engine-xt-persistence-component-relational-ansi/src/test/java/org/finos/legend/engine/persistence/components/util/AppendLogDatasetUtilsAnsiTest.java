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

import org.finos.legend.engine.persistence.components.relational.RelationalSink;
import org.finos.legend.engine.persistence.components.relational.ansi.AnsiSqlSink;

public class AppendLogDatasetUtilsAnsiTest extends AppendLogDatasetUtilsTest
{

    public String getExpectedSqlForAppendMetadata()
    {
        return "INSERT INTO appendlog_batch_metadata " +
                "(\"batch_id\", \"table_name\", \"batch_start_ts_utc\", \"batch_end_ts_utc\", \"batch_status\", \"batch_source_info\")" +
                " (SELECT 'batch_id_123','appeng_log_table_name','2000-01-01 00:00:00',CURRENT_TIMESTAMP(),'SUCCEEDED',PARSE_JSON('my_lineage_value'))";
    }

    public String getExpectedSqlForAppendMetadataUpperCase()
    {
        return "INSERT INTO APPENDLOG_BATCH_METADATA (\"BATCH_ID\", \"TABLE_NAME\", \"BATCH_START_TS_UTC\", \"BATCH_END_TS_UTC\", \"BATCH_STATUS\", \"BATCH_SOURCE_INFO\") " +
                "(SELECT 'batch_id_123','APPEND_LOG_TABLE_NAME','2000-01-01 00:00:00',CURRENT_TIMESTAMP(),'SUCCEEDED',PARSE_JSON('my_lineage_value'))";
    }

    public RelationalSink getRelationalSink()
    {
        return AnsiSqlSink.get();
    }
}
