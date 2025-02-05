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

package org.finos.legend.engine.repl.autocomplete;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.ProcessingContext;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.PureModel;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.ValueSpecification;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.Variable;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.AppliedFunction;
import org.finos.legend.pure.generated.Root_meta_pure_metamodel_valuespecification_VariableExpression_Impl;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel.multiplicity.Multiplicity;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel.type.generics.GenericType;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel.valuespecification.VariableExpression;

public abstract class FunctionHandler
{
    public abstract String functionName();

    public void handleFunctionAppliedParameters(AppliedFunction currentFunc, GenericType leftType, ProcessingContext processingContext, PureModel pureModel)
    {
    }

    protected static VariableExpression buildTypedVariable(Variable variable, GenericType type, Multiplicity multiplicity, PureModel pureModel)
    {
        return new Root_meta_pure_metamodel_valuespecification_VariableExpression_Impl("", null,pureModel.getClass("meta::pure::metamodel::valuespecification::VariableExpression"))
                ._name(variable.name)
                ._genericType(type)
                ._multiplicity(multiplicity);
    }

    public MutableList<CompletionItem> proposedParameters(AppliedFunction currentFunc, GenericType leftType, PureModel pureModel, Completer completer, ProcessingContext processingContext, ValueSpecification currentVS)
    {
        return Lists.mutable.empty();
    }
}
