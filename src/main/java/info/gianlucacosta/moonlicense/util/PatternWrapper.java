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

package info.gianlucacosta.moonlicense.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wraps a Pattern object to provide support for equals() and hashCode().
 */
public class PatternWrapper {
    private final Pattern pattern;

    public PatternWrapper(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PatternWrapper)) {
            return false;
        }

        PatternWrapper other = (PatternWrapper) obj;

        return Objects.equals(pattern.pattern(), other.pattern.pattern());
    }

    @Override
    public int hashCode() {
        return pattern.pattern().hashCode();
    }


    public Matcher matcher(CharSequence input) {
        return pattern.matcher(input);
    }
}


