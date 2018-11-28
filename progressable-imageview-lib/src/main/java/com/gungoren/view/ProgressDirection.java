package com.gungoren.view;

public enum ProgressDirection {

    left_to_right(1), right_to_left(2), top_to_bottom(3), bottom_to_top(4);
    int id;

    ProgressDirection(int id) {
        this.id = id;
    }

    static ProgressDirection fromId(int id){
        for (ProgressDirection direction: values()) {
            if (direction.id == id)
                return direction;
        }
        throw new IllegalArgumentException();
    }
}
