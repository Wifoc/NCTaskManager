package ua.edu.sumdu.j2se.gorbulenko.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.setTime(time);
        active = false;
    }

    public Task() {
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.setTime(start,end,interval);
        active = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRepeated() {
        return interval > 0;
    }

    public int getTime() {
        return start;
    }

    public void setTime(int time) {
        repeated = false;
        this.setTime(time,time,0);
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        if (end < 0) {
            end = 0;
            return 0;
        } else {
            return end;
        }
    }

    public int getRepeatInterval() {

        if (end >= 0 || end >= start && repeated && interval > 0) {
            return interval;
        } else {
            return 0;
        }
    }

    public void setTime(int start, int end, int interval) {
        if (start < 0) { // если старт отрицательный значения по дефолту
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        } else if (end >= 0 && interval >= 0) { // повторение
            this.start = start;
            this.end = end;
            this.interval = interval;
            repeated = true;
        } else { // конкретная дата
            this.start = start;
            this.end = start;
            this.interval = 0;
        }

    }

    public int nextTimeAfter(int current) {
        if (this.isActive() && current <= end ) {
            if (current < this.getTime()) {
                return this.getTime();
            } else if ((current < start + interval || current == start) && this.isRepeated()) {
                return start + interval;
            } else if (current > end - interval && current < end && this.isRepeated()) {
                return end;
            } else {
                int num1 = (end - start) / interval;

                for (int i = 1; i < num1; ++i) {
                    int a = start + interval * i;
                    if (current == a) {
                        return a + interval;
                    }
                }

                for (int a = start; a < end; a += interval) {
                    if (current >= a && current < a + interval && current < end && a + interval <= end
                            || current < end - interval && current > end - interval * 2) {

                        return a + interval;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }
}







