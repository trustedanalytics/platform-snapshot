/**
 * Copyright (c) 2015 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trustedanalytics.platformsnapshot.client.decoder;

import org.trustedanalytics.platformsnapshot.client.CfOperations;

import java.net.URI;
import java.util.function.BiFunction;

import rx.Observable;

public class CcRxPageResolver implements BiFunction<URI, CfOperations, Observable<?>> {

    @Override
    public Observable<?> apply(URI uri, CfOperations cfOperations) {
        final String path = uri.toString();

        if(path.startsWith("/v2/spaces") && path.contains("apps")) {
            return cfOperations.getApplications(uri);
        }
        if(path.startsWith("/v2/spaces")) {
            return cfOperations.getSpaces(uri);
        }
        if(path.startsWith("/v2/services")) {
            return cfOperations.getServices(uri);
        }

        throw new IllegalStateException("Unable to resolve page: " + uri);
    }
}
