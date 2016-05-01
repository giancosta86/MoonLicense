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

package info.gianlucacosta.moonlicense;

/**
 * Information about a product.
 */
public class ProductInfo {
    private final String productName;
    private final int inceptionYear;
    private final String copyrightHolder;

    public ProductInfo(String productName, int inceptionYear, String copyrightHolder) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("productName cannot be null or empty");
        }


        if (inceptionYear == 0) {
            throw new IllegalArgumentException("inceptionYear cannot be empty");
        }


        if (copyrightHolder == null || copyrightHolder.isEmpty()) {
            throw new IllegalArgumentException("copyrightHolder cannot be null or empty");
        }

        this.productName = productName;
        this.inceptionYear = inceptionYear;
        this.copyrightHolder = copyrightHolder;
    }

    public String getProductName() {
        return productName;
    }

    public int getInceptionYear() {
        return inceptionYear;
    }

    public String getCopyrightHolder() {
        return copyrightHolder;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductInfo)) {
            return false;
        }

        ProductInfo other = (ProductInfo) obj;

        return productName.equals(other.productName)
                && (inceptionYear == other.inceptionYear)
                && copyrightHolder.equals(other.copyrightHolder);
    }


    @Override
    public int hashCode() {
        return productName.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", productName, inceptionYear, copyrightHolder);
    }
}
