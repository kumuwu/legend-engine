// Copyright 2025 Goldman Sachs
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

package org.finos.legend.pure.code.core;

import org.finos.legend.pure.m3.tests.AbstractCompiledStateIntegrityTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestCoreRelationalTrinoCompiledStateIntegrity extends AbstractCompiledStateIntegrityTest
{
    @BeforeClass
    public static void initialize()
    {
        initialize("core_relational_trino");
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testReferenceUsages()
    {
        super.testReferenceUsages();
    }
}
