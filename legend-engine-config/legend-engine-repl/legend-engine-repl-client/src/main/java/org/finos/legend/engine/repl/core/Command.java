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

package org.finos.legend.engine.repl.core;

import org.eclipse.collections.api.list.MutableList;
import org.jline.reader.Candidate;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

public interface Command
{
    boolean process(String cmd) throws Exception;

    String documentation();

    default String description()
    {
        return "";
    }

    default Command parentCommand()
    {
        return null;
    }

    MutableList<Candidate> complete(String cmd, LineReader lineReader, ParsedLine parsedLine);
}
