package com.github.prfisher.grocery.aid.users;

import javax.annotation.Nonnull;

public class GroceryAidUser {
    @Nonnull
    private final String id;

    public GroceryAidUser(final String id) {
        if (id == null) {
            throw new NullPointerException("Id is not allowed to be null");
        }

        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroceryAidUser)) {
            return false;
        }

        GroceryAidUser otherUser = (GroceryAidUser) other;

        return otherUser.getId().equals(this.id);
    }
}
