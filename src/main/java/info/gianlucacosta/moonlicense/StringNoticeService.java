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

/**
 * Sets or removes the license notice from a single string - which is usually (but not necessarily)
 * the content of a file.
 */
public interface StringNoticeService {
    /**
     * Sets the license notice within the given string.
     * <p>
     * If a license notice was already present (according to the specified
     * {@link info.gianlucacosta.moonlicense.NoticeFormat}), it will be overwritten.
     *
     * @param sourceString The source string.
     * @param noticeFormat The format to employ for the notice.
     * @param notice       The notice, still to be formatted.
     * @return The string with the formatted license notice in the right place, as specified by the NoticeFormat.
     */
    String setNotice(String sourceString, NoticeFormat noticeFormat, String notice);


    /**
     * Removes the license notice from the given string.
     *
     * @param noticeFormat The notice format used to find the notice to remove.
     * @param sourceString The source string.
     * @return The string without its formatted license notice.
     */
    String removeNotice(NoticeFormat noticeFormat, String sourceString);
}
