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

import java.util.Objects;

/**
 * Provides shared behaviour for NoticeFormat implementations.
 */
public abstract class DefaultNoticeFormat implements NoticeFormat {
    protected static final String lineSeparator = System.lineSeparator();


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NoticeFormat)) {
            return false;
        }

        NoticeFormat other = (NoticeFormat) obj;

        return Objects.equals(
                toString(),
                other.toString()
        );
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
