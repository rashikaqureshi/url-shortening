package models;

import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name="url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String longUrl;
    @Column(nullable = false)
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }



    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
