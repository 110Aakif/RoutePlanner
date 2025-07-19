package com.routeplanner.util;

import com.routeplanner.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationUtil {
    public void generateAllRouteOrders(List<Task> tasks, int index, List<List<Task>> results) {
        if (index == tasks.size()) {
            results.add(new ArrayList<>(tasks));
            return;
        }

        for (int i = index; i < tasks.size(); i++) {
            Collections.swap(tasks, i, index);
            generateAllRouteOrders(tasks, index + 1, results);
            Collections.swap(tasks, i, index);
        }
    }
}
