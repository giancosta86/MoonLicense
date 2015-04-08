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

import info.gianlucacosta.moonlicense.noticeformats.XmlNoticeFormat;
import org.junit.Test;


public class StringNoticeService_Xml_Test extends NoticeServiceTestCase {
    public StringNoticeService_Xml_Test() {
        super("xml", new XmlNoticeFormat());
    }


    @Test
    public void testRemoveNotice_1() throws Exception {
        testRemoveNotice(1);
    }

    @Test
    public void testRemoveNotice_2() throws Exception {
        testRemoveNotice(2);
    }


    @Test
    public void testRemoveNotice_3() throws Exception {
        testRemoveNotice(3);
    }


    @Test
    public void testSetNotice_1() throws Exception {
        testSetNotice(1);
    }

    @Test
    public void testSetNotice_2() throws Exception {
        testSetNotice(2);
    }

    @Test
    public void testSetNotice_3() throws Exception {
        testSetNotice(3);
    }

    @Test
    public void testSetNotice_4() throws Exception {
        testSetNotice(4);
    }


    @Test
    public void testSetNotice_5() throws Exception {
        testSetNotice(5);
    }
}
