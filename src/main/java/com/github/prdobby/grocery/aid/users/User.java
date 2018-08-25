package com.github.prdobby.grocery.aid.users;

import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

public class User {
    @Id
    private final String id;

    @Field("auth_id")
    @Indexed(unique = true)
    private final String authId;

    @Field("created_date")
    @CreatedDate
    private final Instant createdDate;

    public User(final String id, final String authId, final Instant createdDate) {
        this.id = id;
        this.authId = authId;
        this.createdDate = createdDate;
    }

    public String getId() {
        return this.id;
    }

    public String getAuthId() {
        return this.authId;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public String toString() {
        return "[id: " + this.id + ", authId: " + this.authId + ", created: " + this.createdDate + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.authId, this.createdDate);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;

        return Objects.equals(this.id, otherUser.getId()) &&
            Objects.equals(this.authId, otherUser.getAuthId()) &&
            Objects.equals(this.createdDate, otherUser.getCreatedDate());
    }
}
