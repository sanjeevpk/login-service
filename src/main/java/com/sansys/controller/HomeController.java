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
package com.sansys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @Author : Sanjeev Kulkarni
 * @Created On : 03-Jul-2021 2:30:04 pm
 *
 */

@RestController
public class HomeController {

    @GetMapping("/dashboard")
    public String home() {
        return "Welcome User!!!";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminHome() {
        return "Welcome Admin!!!";
    }
}
