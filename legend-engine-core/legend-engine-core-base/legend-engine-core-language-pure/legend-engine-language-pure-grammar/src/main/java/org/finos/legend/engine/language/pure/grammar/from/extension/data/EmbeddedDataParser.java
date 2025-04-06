// Copyright 2022 Goldman Sachs
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

package org.finos.legend.engine.language.pure.grammar.from.extension.data;

import org.finos.legend.engine.language.pure.grammar.from.ParseTreeWalkerSourceInformation;
import org.finos.legend.engine.language.pure.grammar.from.extension.PureGrammarParserExtensions;
import org.finos.legend.engine.protocol.pure.m3.SourceInformation;
import org.finos.legend.engine.protocol.pure.v1.model.data.EmbeddedData;

public interface EmbeddedDataParser
{
    String getType();

    EmbeddedData parse(String code, ParseTreeWalkerSourceInformation walkerSourceInformation, SourceInformation sourceInformation, PureGrammarParserExtensions extensions);
}
