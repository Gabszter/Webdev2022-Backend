package com.gabszter.movielistbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "watched")
    private String watched;
    @Column(name = "score")
    private Long score;

    public Movie(){

    }

    public Movie(String name, String watched, Long score){
        this.name=name;
        this.watched=watched;
        this.score=score;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + name + ", watched=" + watched + ", score=" + score + "]";
    }
}
