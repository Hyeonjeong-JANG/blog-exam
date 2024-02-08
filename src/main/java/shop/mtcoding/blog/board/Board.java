package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Table(name = "board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 20)
    String title;

    @Column(length = 20)
    String content;
    String author;

}
