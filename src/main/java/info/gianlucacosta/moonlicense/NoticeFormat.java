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
 * Describes how to format a license notice, usually according to the syntax of a programming or markup language.
 */
public interface NoticeFormat {
    /**
     * Tries to match the existing license notice in the source string - if such notice already exists.
     * <p>
     * This method is internally used to match an existing notice for both replacement and removal.
     * <p>
     * The returned Matcher <strong>MUST</strong> contain 3 nameless groups:
     * <ul>
     * <li><b>Group 1:</b> contains the text <em>before</em> the notice</li>
     * <li><b>Group 2:</b> contains the notice itself</li>
     * <li><b>Group 3:</b> contains the text <em>after</em> the notice</li>
     * </ul>
     * <p>
     * Of the 3 groups, <b>Group 1</b> and <b>Group 3</b> will not be altered, whereas <b>Group 2</b>, matching the
     * notice itself, will be replaced/removed whenever needed. Matched characters not belonging to groups will be lost
     * as well.
     * <p>
     * <b>Group 1</b> and <b>Group 3</b> can be empty (that is, defined by <cite>()</cite> in the
     * underlying regex); <b>Group 2</b> should be, of course, meaningful and matching a notice.
     * <p>
     * {@link info.gianlucacosta.moonlicense.noticeformats.HtmlNoticeFormat} contains an example of <b>Group 1</b>,
     * used to keep track of the DOCTYPE declaration; on the other hand,
     * {@link info.gianlucacosta.moonlicense.noticeformats.JavaNoticeFormat} only has <b>Group 2</b> non-empty.
     *
     * @param content The original content.
     * @return The matcher object, whose "find()" might also be false. Do <strong>NOT</strong> return null.
     */
    Matcher matchNotice(String content);

    /**
     * Returns the insertion index for the license notice when another license notice was not found in the given content.
     *
     * @param content The original content.
     * @return The index, in the string, where the license notice should appear.
     */
    int getInsertionIndex(String content);

    /**
     * Given a notice, just formats it according to a specific syntax.
     * <p>
     * It is not mandatory - but advisable - that the return value of this method make matchNotice() return
     * a matching Matcher.
     *
     * @param notice The notice
     * @return The formatted string.
     */
    String formatNotice(String notice);
}
