package com.project.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // insert시에 null 필드를 제외시켜줌
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;  // 시퀀스, auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;
    //DB는 RoleType이라는게 없기때문에 추가
    @Enumerated(EnumType.STRING)
    // @ColumnDefault("'user'")

    private RoleType role; //Enum을 쓰는게 좋다.//ADMIN,USER

    private String oauth; //kakao, google
    @CreationTimestamp
    private Timestamp createDate;

}
