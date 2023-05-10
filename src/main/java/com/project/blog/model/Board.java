package com.project.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량데이터
    private String content;

   // @ColumnDefault("0") 직접넣어줄거임
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board , User = One
    @JoinColumn(name ="userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. 그래서 FK씀
    //자바는 오브젝트를 저장 할 수 있음.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 컬럼을 만들지 마세요.
    @OrderBy("id desc ")
    @JsonIgnoreProperties({"board"})
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;
}
