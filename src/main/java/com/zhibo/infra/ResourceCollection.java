package com.zhibo.infra;

import java.util.Collection;
import java.util.List;

/**
 * Created by jichao on 2016/10/27.
 */
public class ResourceCollection {
    public List getResources() {
        return resources;
    }

    public void setResources(List<?> resources) {
        this.resources = resources;
    }

    private List<?> resources;

    public static ResourceCollection create(List<?> collection) {
        return new ResourceCollection(collection);
    }

    private ResourceCollection(List<?> collection) {
        this.resources = collection;
    }

}
