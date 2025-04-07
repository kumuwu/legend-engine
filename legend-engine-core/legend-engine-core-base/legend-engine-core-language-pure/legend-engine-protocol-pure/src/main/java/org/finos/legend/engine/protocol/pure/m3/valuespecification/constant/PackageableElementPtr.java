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

package org.finos.legend.engine.protocol.pure.m3.valuespecification.constant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.finos.legend.engine.protocol.pure.m3.SourceInformation;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.ValueSpecification;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.ValueSpecificationVisitor;
import org.finos.legend.engine.protocol.pure.m3.valuespecification.One;

import java.io.IOException;
import java.util.Objects;

public class PackageableElementPtr extends One
{
    public String fullPath;

    public PackageableElementPtr()
    {

    }

    public PackageableElementPtr(String fullPath)
    {
        this.fullPath = fullPath;
    }

    @Override
    public <T> T accept(ValueSpecificationVisitor<T> visitor)
    {
        return visitor.visit(this);
    }


    protected static ValueSpecification convert(JsonParser parser) throws IOException
    {
        JsonNode node = parser.readValueAsTree();
        JsonNode name = node.get("fullPath");
        ValueSpecification result = new PackageableElementPtr(name.asText());
        JsonNode sourceInformation = node.get("sourceInformation");
        if (sourceInformation != null)
        {
            result.sourceInformation = parser.getCodec().treeToValue(sourceInformation, SourceInformation.class);
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof PackageableElementPtr))
        {
            return false;
        }
        PackageableElementPtr that = (PackageableElementPtr) o;
        return Objects.equals(fullPath, that.fullPath);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(fullPath);
    }
}
