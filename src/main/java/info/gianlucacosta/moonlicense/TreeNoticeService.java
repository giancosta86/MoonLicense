/*§
  ===========================================================================
  MoonLicense
  ===========================================================================
  Copyright (C) 2015 Gianluca Costa
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

package info.gianlucacosta.moonlicense;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Walks a directory tree to set or remove license notices just in the files belonging to a list of
 * {@link info.gianlucacosta.moonlicense.LicensedFileSet} instances.
 * <p>
 * The licensed file sets are added via <i>addLicensedFileSet()</i> and are queried following a FIFO order.
 * <p>
 * It is also supported the registration of callbacks, which will be called when important events occur
 * while processing a file.
 */
public interface TreeNoticeService {
    /**
     * Adds a set of licensed files to be altered by the service's operations.
     *
     * @param licensedFileSet The set of files.
     */
    void addLicensedFileSet(LicensedFileSet licensedFileSet);


    /**
     * Starting from the root directory, sets the license for every file matching one of the added licensed file sets.
     * Licensed file sets are queried sequentially, following a FIFO order.
     *
     * @param rootDir     The root directory.
     * @param currentYear The current year, used, for example, while generating copyright info.
     * @throws IOException If an I/O error occurs.
     */
    void setNotices(File rootDir, int currentYear) throws IOException;

    /**
     * Starting from the root directory, removes the license notice from every file matching one of the added
     * licensed file sets.
     * Licensed file sets are queried sequentially, following a FIFO order.
     *
     * @param rootDir The root directory.
     * @throws IOException If an I/O error occurs.
     */
    void removeNotices(File rootDir) throws IOException;

    /**
     * Adds a handler to be called just before a file is going to be opened for processing.
     *
     * @param handler The handler routine, taking just the file.
     */
    void addOnFileOpeningHandler(Consumer<Path> handler);

    /**
     * Adds a handler to be called just after the file has been processed, and <em>only</em> if the result
     * has been saved to disk (which is <em>not</em> the case when the processing did not change the file content)
     *
     * @param handler The handler routine, taking just the file.
     */
    void addOnFileWrittenHandler(Consumer<Path> handler);
}
