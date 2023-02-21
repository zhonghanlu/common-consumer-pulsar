package com.ymxc.webmvc;

import lombok.Data;

@Data
public class Pageful<T> {

    private int page;

    private int total;

    private T data;

    private Pageful() {
    }

    public static <T> Restful<Pageful<T>> restful(Query query, int count, T list) {
        Pageful<T> pageful = new Pageful<>();
        pageful.page = query.getPage();
        pageful.total = count;
        pageful.data = list;
        return Restful.<Pageful<T>>SUCCESS().object(pageful).build();
    }

}
