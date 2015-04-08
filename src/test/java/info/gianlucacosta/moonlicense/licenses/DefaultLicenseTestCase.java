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

package info.gianlucacosta.moonlicense.licenses;

import info.gianlucacosta.moonlicense.DefaultLicense;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public abstract class DefaultLicenseTestCase {
    private final DefaultLicense license;

    public DefaultLicenseTestCase(DefaultLicense license) {
        this.license = license;
    }

    @Test
    public void testGetNoticeTemplate() throws Exception {
        String noticeTemplate = license.getNoticeTemplate();

        assertNotNull(noticeTemplate);
    }


    @Test
    public void testGetFullTemplate() throws Exception {
        String fullTemplate = license.getFullTemplate();

        assertNotNull(fullTemplate);
    }
}
