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

package org.finos.legend.engine.persistence.components.ingestmode;

import org.finos.legend.engine.persistence.components.ingestmode.emptyhandling.DeleteTargetData;
import org.finos.legend.engine.persistence.components.ingestmode.emptyhandling.EmptyDatasetHandling;
import org.finos.legend.engine.persistence.components.ingestmode.partitioning.NoPartitioning;
import org.finos.legend.engine.persistence.components.ingestmode.partitioning.PartitioningStrategy;
import org.finos.legend.engine.persistence.components.ingestmode.transactionmilestoning.TransactionMilestoned;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.VersioningStrategyVisitor;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.NoVersioningStrategyAbstract;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.AllVersionsStrategyAbstract;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.MaxVersionStrategyAbstract;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.MergeDataVersionResolver;
import org.finos.legend.engine.persistence.components.ingestmode.versioning.DigestBasedResolverAbstract;
import org.immutables.value.Value;

import java.util.*;

import static org.immutables.value.Value.Immutable;
import static org.immutables.value.Value.Style;

@Immutable
@Style(
    typeAbstract = "*Abstract",
    typeImmutable = "*",
    jdkOnly = true,
    optionalAcceptNullable = true,
    strictBuilder = true
)
public interface UnitemporalSnapshotAbstract extends IngestMode, TransactionMilestoned
{
    String digestField();

    @Value.Default
    default PartitioningStrategy partitioningStrategy()
    {
        return NoPartitioning.builder().build();
    }

    @Value.Default
    default EmptyDatasetHandling emptyDatasetHandling()
    {
        return DeleteTargetData.builder().build();
    }

    @Override
    default <T> T accept(IngestModeVisitor<T> visitor)
    {
        return visitor.visitUnitemporalSnapshot(this);
    }

    @Value.Check
    default void validate()
    {
        // Allowed Versioning Strategy - NoVersioning, MaxVersioining
        this.versioningStrategy().accept(new VersioningStrategyVisitor<Void>()
        {
            @Override
            public Void visitNoVersioningStrategy(NoVersioningStrategyAbstract noVersioningStrategy)
            {
                return null;
            }

            @Override
            public Void visitMaxVersionStrategy(MaxVersionStrategyAbstract maxVersionStrategy)
            {
                Optional<MergeDataVersionResolver> versionResolver = maxVersionStrategy.mergeDataVersionResolver();
                if (!versionResolver.isPresent())
                {
                    throw new IllegalStateException("Cannot build UnitemporalSnapshot, MergeDataVersionResolver is mandatory for MaxVersionStrategy");
                }
                if (!(versionResolver.orElseThrow(IllegalStateException::new) instanceof DigestBasedResolverAbstract))
                {
                    throw new IllegalStateException("Cannot build UnitemporalSnapshot, Only DIGEST_BASED VersioningResolver allowed for this ingest mode");
                }
                return null;
            }

            @Override
            public Void visitAllVersionsStrategy(AllVersionsStrategyAbstract allVersionsStrategyAbstract)
            {
                throw new IllegalStateException("Cannot build UnitemporalSnapshot, AllVersionsStrategy not supported");
            }
        });

    }
}
