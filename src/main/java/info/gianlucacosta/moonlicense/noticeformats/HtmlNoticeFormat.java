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

package info.gianlucacosta.moonlicense.noticeformats;

import info.gianlucacosta.moonlicense.DefaultNoticeFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Notice format for HTML.
 * <p>
 * In particular, it preserves any DOCTYPE declaration at the beginning, to comply with the standard.
 */
public class HtmlNoticeFormat extends DefaultNoticeFormat {
    private static final Pattern licensePattern = Pattern.compile("(?s)^\\s*((?:<!DOCTYPE [^>]+>(?:\r?\n){0,2})?)\\s*(<!--§.*?-->)()\\s*");
    private static final Pattern doctypePattern = Pattern.compile("(?s)^\\s*<!DOCTYPE [^>]+>\\s*");

    @Override
    public Matcher matchNotice(String content) {
        return licensePattern.matcher(content);
    }

    @Override
    public int getInsertionIndex(String content) {
        Matcher doctypeMatcher = doctypePattern.matcher(content);

        if (doctypeMatcher.find()) {
            return doctypeMatcher.end();
        } else {
            return 0;
        }
    }

    @Override
    public String formatNotice(String notice) {
        return String.format("<!--§%s%s-->%s%s",
                lineSeparator,
                notice,
                lineSeparator,
                lineSeparator);
    }
}
