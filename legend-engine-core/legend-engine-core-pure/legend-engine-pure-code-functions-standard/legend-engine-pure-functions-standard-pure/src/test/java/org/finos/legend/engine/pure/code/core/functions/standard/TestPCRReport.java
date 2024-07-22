// Copyright 2024 Goldman Sachs
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

package org.finos.legend.engine.pure.code.core.functions.standard;

import org.finos.legend.pure.m3.pct.shared.provider.PCTReportProviderLoader;
import org.junit.Assert;
import org.junit.Test;

public class TestPCRReport
{
    @Test
    public void canFindPCTReport()
    {
        Assert.assertEquals(5, PCTReportProviderLoader.gatherFunctions().size());
        Assert.assertEquals("essential, grammar, relation, standard, unclassified", PCTReportProviderLoader.gatherFunctions().collect(c -> c.reportScope.module).distinct().sortThis().makeString(", "));
        Assert.assertEquals(5, PCTReportProviderLoader.gatherReports().size());
        Assert.assertEquals("Native", PCTReportProviderLoader.gatherReports().collect(c -> c.adapterKey.adapter.name).distinct().sortThis().makeString(", "));
    }
}
