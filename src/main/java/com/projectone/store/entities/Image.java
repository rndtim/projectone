package com.projectone.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;
  @Column(name="name")
  private String name;
  @Column(name="original_file_name")
  private String originalFileName;
  @Column(name="size")
  private Long size;
  @Column(name="content_type")
  private String contentType;
  @Column(name="path")
  private String path;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "post_id")
  private PostEntity post;
}
