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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CopyrightYearsBuilderTest {

    @Test
    public void testGet_withInceptionYearEqualToCurrentYear() throws Exception {
        assertEquals(
                "2014",

                new CopyrightYearsBuilder()
                        .setInceptionYear(2014)
                        .setCurrentYear(2014)
                        .toString()
        );
    }


    @Test
    public void testGet_withInceptionYearBeforeCurrentYear() throws Exception {
        assertEquals(
                "2011-2014",

                new CopyrightYearsBuilder()
                        .setInceptionYear(2011)
                        .setCurrentYear(2014)
                        .toString()
        );
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGet_withInceptionYearAfterCurrentYear() throws Exception {
        assertEquals(
                "2014",

                new CopyrightYearsBuilder()
                        .setInceptionYear(2014)
                        .setCurrentYear(2001)
        );
    }
}