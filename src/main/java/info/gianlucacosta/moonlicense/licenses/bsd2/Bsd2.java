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

package info.gianlucacosta.moonlicense.licenses.bsd2;

import info.gianlucacosta.moonlicense.DefaultLicense;

/**
 * The BSD 2-Clause license.
 */
public class Bsd2 extends DefaultLicense {
    @Override
    public String getName() {
        return "BSD 2-Clause License";
    }

    @Override
    public String getUrl() {
        return "http://opensource.org/licenses/BSD-2-Clause";
    }
}
