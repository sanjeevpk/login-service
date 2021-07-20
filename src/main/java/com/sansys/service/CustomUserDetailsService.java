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
package com.sansys.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sansys.dto.UserDTO;
import com.sansys.entity.UsersEntity;
import com.sansys.repository.UsersRepository;

/**
 * 
 *
 * @Author : Sanjeev Kulkarni
 * @Created On : 03-Jul-2021 2:20:25 pm
 *
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    @Autowired
    private UsersRepository usersRepository;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin", "admin", new ArrayList<>());
    }

    /**
     * @param user
     * @return
     */
    public UsersEntity save(UserDTO user) {
        UsersEntity newUser = new UsersEntity();
        newUser.setUserName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setUserRole("ROLE_USER");
//        newUser.setCreatedBy(user.getUsername());
//        newUser.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return usersRepository.save(newUser);
    }

}
