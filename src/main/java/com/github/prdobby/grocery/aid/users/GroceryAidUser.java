package com.github.prdobby.grocery.aid.users;

import javax.annotation.Nonnull;

public class GroceryAidUser {
    @Nonnull
    private final String id;

    @Nonnull
    private String name;

    public GroceryAidUser(final String id, final String name) {
        if (id == null) {
            throw new NullPointerException("Id is not allowed to be null");
        }

        this.id = id;
        this.setName(name);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String newName) {
        if (newName == null) {
            throw new NullPointerException("Name is not allowed to be null");
        }

        this.name = newName;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.id + ")";
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

        return otherUser.getId().equals(this.name);
    }
}
