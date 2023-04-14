package net.revcn.domain;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableId;

@Component
public class User {
    @TableId
    private Long userId;

    private String nickName;

    private String email;

    private String password;

    private Long deleted;

    public User() {
        this.deleted = 0L;
    }

    public User(String nickName, String email, String password) {
        this();
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }


    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(nickName, user.nickName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(deleted, user.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickName, email, password, deleted);
    }

    
}
