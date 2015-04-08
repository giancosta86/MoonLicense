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

import java.util.regex.Matcher;

/**
 * Basic implementation of StringNoticeService.
 */
public class DefaultStringNoticeService implements StringNoticeService {
    @Override
    public String setNotice(String sourceString, NoticeFormat noticeFormat, String notice) {
        String formattedNotice = noticeFormat.formatNotice(notice);

        Matcher existingMatcher = noticeFormat.matchNotice(sourceString);

        if (existingMatcher.find()) {
            return existingMatcher.replaceFirst(String.format("%s%s%s",
                    existingMatcher.group(1),
                    formattedNotice,
                    existingMatcher.group(3)
            ));
        } else {
            int insertionPoint = noticeFormat.getInsertionIndex(sourceString);

            return sourceString.substring(0, insertionPoint)
                    + formattedNotice
                    + sourceString.substring(insertionPoint, sourceString.length());
        }
    }

    @Override
    public String removeNotice(NoticeFormat noticeFormat, String sourceString) {
        Matcher existingMatcher = noticeFormat.matchNotice(sourceString);

        if (existingMatcher.find()) {
            return existingMatcher.replaceFirst(
                    existingMatcher.group(1) + existingMatcher.group(3)
            );
        } else {
            return sourceString;
        }
    }
}
