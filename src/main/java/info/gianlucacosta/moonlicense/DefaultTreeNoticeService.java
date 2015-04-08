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
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Basic implementation of TreeNoticeService.
 */
public class DefaultTreeNoticeService implements TreeNoticeService {
    private abstract class NoticeVisitor extends SimpleFileVisitor<Path> {
        private final Path rootPath;

        protected NoticeVisitor(Path rootPath) {
            this.rootPath = rootPath;
        }


        @Override
        public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) throws IOException {
            String dirName = dirPath.getName(dirPath.getNameCount() - 1).toString();

            if (skipDotItems && dirName.startsWith(".")) {
                return FileVisitResult.SKIP_SUBTREE;
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
            String fileName = filePath.getName(filePath.getNameCount() - 1).toString();

            if (skipDotItems && fileName.startsWith(".")) {
                return FileVisitResult.SKIP_SUBTREE;
            }


            Path relativeFilePath = rootPath.relativize(filePath);


            for (LicensedFileSet licensedFileSet : licensedFileSets) {
                NoticeFormat noticeFormat = licensedFileSet.getNoticeFormat(
                        relativeFilePath.toString()
                );

                if (noticeFormat != null) {
                    onFileOpeningHandlers.forEach(handler -> handler.accept(relativeFilePath));

                    String initialFileContent = new String(Files.readAllBytes(filePath));

                    String updatedFileContent = processFileContent(licensedFileSet, noticeFormat, initialFileContent);

                    if (!updatedFileContent.equals(initialFileContent)) {
                        Files.write(
                                filePath,
                                updatedFileContent.getBytes()
                        );

                        onFileWrittenHandlers.forEach(handler -> handler.accept(relativeFilePath));
                    }


                    break;
                }
            }


            return super.visitFile(filePath, attrs);
        }

        protected abstract String processFileContent(LicensedFileSet licensedFileSet, NoticeFormat noticeFormat, String fileContent);
    }

    private final StringNoticeService stringNoticeService;
    private final boolean skipDotItems;

    private final List<LicensedFileSet> licensedFileSets = new ArrayList<>();

    private final List<Consumer<Path>> onFileOpeningHandlers = new ArrayList<>();
    private final List<Consumer<Path>> onFileWrittenHandlers = new ArrayList<>();

    /**
     * Creates the service
     *
     * @param stringNoticeService The service whose operations will be applied to every matching file.
     * @param skipDotItems        Set to true if files and directories starting with "." must be skipped.
     */
    public DefaultTreeNoticeService(StringNoticeService stringNoticeService, boolean skipDotItems) {
        this.stringNoticeService = stringNoticeService;
        this.skipDotItems = skipDotItems;
    }

    @Override
    public void addLicensedFileSet(LicensedFileSet licensedFileSet) {
        licensedFileSets.add(licensedFileSet);
    }

    @Override
    public void addOnFileOpeningHandler(Consumer<Path> handler) {
        onFileOpeningHandlers.add(handler);
    }

    @Override
    public void addOnFileWrittenHandler(Consumer<Path> handler) {
        onFileWrittenHandlers.add(handler);
    }


    @Override
    public void setNotices(File rootDir, int currentYear) throws IOException {
        if (!rootDir.isDirectory()) {
            throw new IllegalArgumentException("rootDir is not a directory");
        }

        Map<LicensedFileSet, String> noticeCache = new HashMap<>();

        Path rootPath = rootDir.toPath();

        Files.walkFileTree(rootPath, new NoticeVisitor(rootPath) {
            @Override
            protected String processFileContent(LicensedFileSet licensedFileSet, NoticeFormat noticeFormat, String fileContent) {
                String notice = noticeCache.get(licensedFileSet);

                if (notice == null) {
                    String noticeTemplate = licensedFileSet.getLicense().getNoticeTemplate();
                    ProductInfoInjector productInfoInjector = new ProductInfoInjector(licensedFileSet.getProductInfo(), currentYear);

                    notice = productInfoInjector.inject(noticeTemplate);
                    noticeCache.put(licensedFileSet, notice);
                }

                return stringNoticeService.setNotice(
                        fileContent, noticeFormat, notice
                );
            }
        });
    }


    @Override
    public void removeNotices(File rootDir) throws IOException {
        Path rootPath = rootDir.toPath();

        Files.walkFileTree(rootPath, new NoticeVisitor(rootPath) {
            @Override
            protected String processFileContent(LicensedFileSet licensedFileSet, NoticeFormat noticeFormat, String fileContent) {
                return stringNoticeService.removeNotice(noticeFormat, fileContent);
            }
        });
    }

    @Override
    public String toString() {
        return "DefaultTreeNoticeService{" +
                ", skipDotItems=" + skipDotItems +
                ", licensedFileSets=" + licensedFileSets +
                '}';
    }
}
