/*§
  ===========================================================================
  MoonLicense
  ===========================================================================
  Copyright (C) 2015-2016 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.moonlicense.util;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Utility class to deal with resources.
 */
public class Resources {
    /**
     * Given a class, returns a related resource as a string.
     *
     * @param referenceClass The class whose package (or a subpackage) contains the resource.
     * @param resourcePath   The relative path of the resource. File.separator will be replaced by "/"
     * @return The content of the resource file, as a string. It never returns null.
     * @throws java.io.IOException Thrown in case of errors - for example, if the resource is missing
     */
    public static String getResourceAsString(Class<?> referenceClass, String resourcePath) throws IOException {
        StringBuilder result = new StringBuilder();

        String uniformResourcePath = resourcePath.replaceAll(
                Pattern.quote(File.separator),
                "/"
        );

        InputStream resourceStream = referenceClass.getResourceAsStream(uniformResourcePath);

        if (resourceStream == null) {
            throw new IOException(String.format("Missing resource: '%s'", resourcePath));
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        resourceStream
                )
        )) {
            reader.lines()
                    .forEach(line -> {
                        result.append(line);
                        result.append(System.lineSeparator());
                    });

            return result.toString();
        }
    }
}
