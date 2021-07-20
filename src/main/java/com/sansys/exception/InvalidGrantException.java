/*
 * Copyright (C) SanSys Pvt. Ltd. SanSys is a registered trademark and the SanSys
 * graphic logo is a trademark of SanSys Pvt. Ltd.
 * SanSys reserves all the right for this source code. 
 * You should not modify or reuse without the noticing it to SanSys. 
 * And need to provide credits where applicable. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific governing permissions and
 * limitations under the License.
 */
package com.sansys.exception;

/**
 * 
 *
 * @Author : Sanjeev Kulkarni
 * @Created On : 20-Jul-2021 2:28:32 pm
 *
 */
public class InvalidGrantException extends Exception {
    private static final long serialVersionUID = 4981638838617304319L;
    
    private String message;

    public InvalidGrantException() {
        super();
    }

    public InvalidGrantException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidGrantException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGrantException(String message) {
        super(message);
    }

    public InvalidGrantException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
