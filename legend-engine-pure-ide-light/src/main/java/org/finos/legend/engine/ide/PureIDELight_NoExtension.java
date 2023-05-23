//  Copyright 2022 Goldman Sachs
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package org.finos.legend.engine.ide;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.finos.legend.engine.pure.runtime.compiler.interpreted.natives.LegendCompileMixedProcessorSupport;
import org.finos.legend.pure.ide.light.PureIDEServer;
import org.finos.legend.pure.ide.light.SourceLocationConfiguration;
import org.finos.legend.pure.m3.serialization.filesystem.usercodestorage.RepositoryCodeStorage;
import org.finos.legend.pure.runtime.java.interpreted.FunctionExecutionInterpreted;

public class PureIDELight_NoExtension extends PureIDEServer
{
    public static void main(String[] args) throws Exception
    {
        new PureIDELight_NoExtension().run(args.length == 0 ? new String[]{"server", "legend-engine-pure-ide-light/src/main/resources/ideLightConfig.json"} : args);
    }

    @Override
    protected MutableList<RepositoryCodeStorage> buildRepositories(SourceLocationConfiguration sourceLocationConfiguration)
    {
        return Lists.mutable.empty();
    }

    @Override
    protected void postInit()
    {
        FunctionExecutionInterpreted fe = (FunctionExecutionInterpreted) this.getPureSession().getFunctionExecution();
        fe.setProcessorSupport(new LegendCompileMixedProcessorSupport(fe.getRuntime().getContext(), fe.getRuntime().getModelRepository(), fe.getProcessorSupport()));
    }
}
