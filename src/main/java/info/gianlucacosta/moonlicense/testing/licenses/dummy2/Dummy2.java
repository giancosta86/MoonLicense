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

package info.gianlucacosta.moonlicense.testing.licenses.dummy2;

import info.gianlucacosta.moonlicense.DefaultLicense;

/**
 * Another dummy license.
 */
public class Dummy2 extends DefaultLicense {
    @Override
    public String getName() {
        return "Another dummy license";
    }

    @Override
    public String getUrl() {
        return "http://gianlucacosta.info/licenses/dummy2";
    }
}
