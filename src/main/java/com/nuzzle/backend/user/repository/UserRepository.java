package com.nuzzle.backend.user.repository;

import com.nuzzle.backend.global.dto.type.ERole;
import com.nuzzle.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndRefreshTokenAndIsLogin(Long id, String refreshToken, Boolean isLogin);

    User findByUserName(String UserName);

    @Query("select u.userId as id, u.role as role, u.password as password from User u where u.serialId = :serialId")
    Optional<UserSecurityForm> findSecurityFormBySerialId(String serialId);

    @Query("select u.userId as id, u.role as role, u.password as password from User u where u.userId = :id and u.isLogin = true")
    Optional<UserSecurityForm> findSecurityFormById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.refreshToken = :refreshToken, u.isLogin = :isLogin where u.userId = :userId")
    void updateRefreshTokenAndLoginStatus(Long userId, String refreshToken, Boolean isLogin);

    boolean existsByUserName(String userName);

    Boolean existsBySerialId(String serialId);

    Optional<User> findBySerialId(String serialId);

    interface UserSecurityForm {
        Long getId();

        ERole getRole();

        String getPassword();

        static UserSecurityForm invoke(User user) {
            return new UserSecurityForm() {
                @Override
                public Long getId() {
                    return user.getUserId();
                }

                @Override
                public ERole getRole() {
                    return user.getRole();
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }
            };
        }
    }

}


