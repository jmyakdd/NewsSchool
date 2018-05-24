package jmy.com.newsschool.bean;

import java.util.List;

public class Menu {
    private String name;
    private boolean isOpen = false;
    private List<Menu>next;
    private int page;
    private int level;

    public Menu(String name, int page, int level) {
        this.name = name;
        this.page = page;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Menu> getNext() {
        return next;
    }

    public void setNext(List<Menu> next) {
        this.next = next;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
