package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {
    public long get(int position) {
        return (position == 1 || position == 2) ? 1L : this.get(position - 1) + this.get(position - 2);
    }
}
