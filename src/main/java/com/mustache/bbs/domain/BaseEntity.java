package com.mustache.bbs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass   //엔티티 클래스가 상속받을 경우 자식 클래스에게 매핑 정보 전달
@EntityListeners(AuditingEntityListener.class)  //엔티티를 DB에 적용 전후로 콜백 요청 (엔티티의 Auditing 정보를 주입하는 JPA 엔티티 리스너 클래스)
public class BaseEntity {
    @CreatedDate    //생성날짜
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedBy //변경날짜
    private LocalDateTime updatedAt;
}
