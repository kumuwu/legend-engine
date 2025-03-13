// Copyright 2020 Goldman Sachs
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

package org.finos.legend.engine.protocol.pure.v1.model.packageableElement.store.relational.model;

import org.finos.legend.engine.protocol.pure.m3.extension.StereotypePtr;
import org.finos.legend.engine.protocol.pure.m3.extension.TaggedValue;
import org.finos.legend.engine.protocol.pure.v1.model.SourceInformation;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.store.relational.mapping.FilterMapping;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.store.relational.mapping.TablePtr;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.store.relational.model.operation.RelationalOperationElement;

import java.util.Collections;
import java.util.List;

public class View
{
    public String name;
    public TablePtr mainTable;
    public List<String> primaryKey = Collections.emptyList();
    public FilterMapping filter;
    public List<RelationalOperationElement> groupBy = Collections.emptyList();
    public Boolean distinct;
    public List<ColumnMapping> columnMappings = Collections.emptyList();
    public SourceInformation sourceInformation;
    public List<StereotypePtr> stereotypes;
    public List<TaggedValue> taggedValues;
}
