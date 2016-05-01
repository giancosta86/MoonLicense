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

package info.gianlucacosta.moonlicense;

import info.gianlucacosta.moonlicense.util.PatternWrapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A set of files sharing the same license and the same product info.
 * <p>
 * The set is defined by <i>includes</i> and <i>excludes</i>:
 * <ul>
 * <li><b>includes</b> is a <i>map</i> of (regexString -&gt; {@link info.gianlucacosta.moonlicense.NoticeFormat}):
 * each entry adds files to the set, registering a NoticeFormat for them</li>
 * <li><b>excludes</b> is a list of regex strings, removing files from the set created by <b>includes</b></li>
 * </ul>
 * <p>
 * When a NoticeFormat is requested for a path, the following algorithm is employed:
 * <ol>
 * <li>The <i>includes</i> are applied sequentially to the path; if one of them matches, the related NoticeFormat is selected
 * and Step 2 is immediately executed. If no regex in <i>includes</i> matches, null is returned</li>
 * <li>If the path does not match any regex in <i>excludes</i>, the NoticeFormat found in step 1 is returned.
 * Otherwise, null is returned</li>
 * </ol>
 */
public class LicensedFileSet {
    private final License license;
    private final ProductInfo productInfo;
    private final Map<PatternWrapper, NoticeFormat> includes = new HashMap<>();
    private final PatternWrapper excludesPattern;

    public LicensedFileSet(License license, ProductInfo productInfo, Map<String, NoticeFormat> includes, List<String> excludes) {
        if (license == null) {
            throw new IllegalArgumentException("license cannot be null");
        }

        if (productInfo == null) {
            throw new IllegalArgumentException("productInfo cannot be null");
        }

        if (includes == null) {
            throw new IllegalArgumentException("includes cannot be null");
        }


        if (excludes == null) {
            throw new IllegalArgumentException("excludes cannot be null");
        }


        this.license = license;
        this.productInfo = productInfo;

        includes.forEach((pathRegex, noticeFormat) -> this.includes.put(
                new PatternWrapper(Pattern.compile(pathRegex)),
                noticeFormat
        ));

        String excludesRegex = String.join(
                "|",
                excludes
                        .stream()
                        .map(
                                regex -> "(?:" + regex + ")")
                        .collect(Collectors.toList())
        );

        if (excludesRegex.isEmpty()) {
            excludesRegex = "^$";
        }

        this.excludesPattern = new PatternWrapper(Pattern.compile(excludesRegex));
    }


    public License getLicense() {
        return license;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    /**
     * If <i>path</i> is included in this set, this method return the suitable NoticeFormat.
     * <p>
     * <strong>NOTE:</strong> the path separator (that is, <i>File.separator</i>) will be replaced by
     * "/" before applying the patterns.
     *
     * @param path A file path.
     * @return A valid {@link info.gianlucacosta.moonlicense.NoticeFormat} if the file belongs to the set,
     * null otherwise.
     */
    public NoticeFormat getNoticeFormat(String path) {
        NoticeFormat noticeFormat = null;

        String uniformPath = path.replaceAll(Pattern.quote(File.separator), "/");

        for (Map.Entry<PatternWrapper, NoticeFormat> includeEntry : includes.entrySet()) {
            PatternWrapper includePattern = includeEntry.getKey();

            if (includePattern.matcher(uniformPath).find()) {
                noticeFormat = includeEntry.getValue();
                break;
            }
        }


        if (noticeFormat != null) {
            if (!excludesPattern.matcher(uniformPath).find()) {
                return noticeFormat;
            }
        }

        return null;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LicensedFileSet)) {
            return false;
        }

        LicensedFileSet other = (LicensedFileSet) obj;

        return Objects.equals(license, other.license)
                && Objects.equals(productInfo, other.productInfo)
                && Objects.equals(includes, other.includes)
                && Objects.equals(excludesPattern, other.excludesPattern);
    }

    @Override
    public int hashCode() {
        return license.hashCode() + productInfo.hashCode();
    }

    @Override
    public String toString() {
        return "LicensedFileSet{" +
                "license=" + license +
                ", productInfo=" + productInfo +
                ", includes=" + includes +
                ", excludesPattern=" + excludesPattern +
                '}';
    }
}
