public class AutoBlockA extends Check {

    boolean attacked;
    int ticks;
    
    public AutoBlockA(Data data) {
        super(data, CheckType.COMBAT, "AutoBlock (A)");
    }
    
    @Override
    public void handle(Event e) {
        if (e instanceof UseEntity) {
            this.attacked = true;
        } else if (e instanceof PacketPlayInFlying) {
            ++this.ticks;
        } else if (e instanceof BlockPlace) {
            if (this.attacked) {
                if (this.ticks < 1) {
                    if (++preVL > 1) {
                        flag(data, "low tick delay, t: " + this.ticks);
                    }
                } else {
                    preVL = 0;
                }
                this.attacked = false;
            }
            this.ticks = 0;
        }
    }
}
