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

import info.gianlucacosta.moonlicense.testing.licenses.dummy.Dummy;
import info.gianlucacosta.moonlicense.util.Resources;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public abstract class NoticeServiceTestCase {
    private final String language;
    private final NoticeFormat noticeFormat;


    protected NoticeServiceTestCase(String language, NoticeFormat noticeFormat) {
        this.language = language;
        this.noticeFormat = noticeFormat;
    }


    protected void testSetNotice(int number) throws IOException {
        ProductInfo productInfo = new TestProductInfo();
        ProductInfoInjector productInfoInjector = new ProductInfoInjector(productInfo);

        Dummy dummyLicense = new Dummy();
        String notice = productInfoInjector.inject(dummyLicense.getNoticeTemplate());


        String initialContent = productInfoInjector.inject(
                Resources.getResourceAsString(getClass(), String.format("singleFiles/%s/setNotice_%d_initial.txt", language, number))
        );


        String expectedContent = productInfoInjector.inject(
                Resources.getResourceAsString(getClass(), String.format("singleFiles/%s/setNotice_%d_expected.txt", language, number))
        );

        StringNoticeService stringNoticeService = new DefaultStringNoticeService();
        String resultContent = stringNoticeService.setNotice(initialContent, noticeFormat, notice);

        assertEquals(expectedContent, resultContent);
    }


    protected void testRemoveNotice(int number) throws IOException {
        String initialContent = Resources.getResourceAsString(getClass(), String.format("singleFiles/%s/removeNotice_%d_initial.txt", language, number));
        String expectedContent = Resources.getResourceAsString(getClass(), String.format("singleFiles/%s/removeNotice_%d_expected.txt", language, number));

        StringNoticeService stringNoticeService = new DefaultStringNoticeService();
        String resultContent = stringNoticeService.removeNotice(noticeFormat, initialContent);

        assertEquals(expectedContent, resultContent);
    }
}