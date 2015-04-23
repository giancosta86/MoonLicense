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
 * Builds a "copyright years" string, via its toString() method, that complies with the following rules:
 * <ul>
 * <li>if inceptionYear == currentYear, that value is returned</li>
 * <li>if inceptionYear &lt; currentYear, {inceptionYear}-{currentYear} is returned</li>
 * <li>otherwise, an IllegalArgumentException is thrown</li>
 * </ul>
 */
public class CopyrightYearsBuilder {
    private Integer inceptionYear;
    private Integer currentYear;

    public CopyrightYearsBuilder setInceptionYear(Integer inceptionYear) {
        this.inceptionYear = inceptionYear;
        return this;
    }

    public CopyrightYearsBuilder setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
        return this;
    }


    @Override
    public String toString() {
        if (inceptionYear == null) {
            throw new UnsupportedOperationException("Missing inceptionYear");
        }


        if (currentYear == null) {
            throw new UnsupportedOperationException("Missing currentYear");
        }


        if (inceptionYear > currentYear) {
            throw new IllegalArgumentException("inceptionYear cannot come after currentYear");
        }

        return (Objects.equals(currentYear, inceptionYear)) ?
                String.valueOf(currentYear)
                :
                String.format("%d-%d", inceptionYear, currentYear);
    }
}
