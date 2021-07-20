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
package com.sansys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sansys.service.impl.AuditorAwareImpl;

/**
 * 
 *
 * @Author : Sanjeev Kulkarni
 * @Created On : 21-Jul-2021 1:07:01 am
 *
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
  
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
