
public class HitBoxA extends Checl {

    public HitBoxA(Data data) {
        super(data, CheckType.ACCESS, "HitBox (A)");
    }
    
    @Override
    public void handle(@NotNull Event e) {
        if (e instanceof UseEntity) {
            UseEntity event = (UseEntity) e;
            Data data = DataManager.getPlayer((Player) event.getDamager());
            Entity entity = event.getEntity();
            double lookingAt = isLookingAt(data.user, entity.getLocation());
            double hitbox_rate = .34;
            double distance = data.user.getLocation().distance(entity.getLocation());
            if (distance < 0.7) {
                hitbox_rate = .1;
            } else if (distance > 4.3) {
                hitbox_rate = .89;
            } else if (distance > 3.5) {
                hitbox_rate = .81;
            } else if (distance > 2.8) {
                hitbox_rate = .76;
            } else if (distance > 1.95) {
                hitbox_rate = .62;
            }
            if (distance > 0.5 && lookingAt < hitbox_rate) {
                // Flag system
                flag(String.format(" { lookingAt: %s; dy: %s; } ", lookingAt, distance));
            }
        }
    }

    private double isLookingAt(@NotNull Player player, @NotNull Location target) {
        Location eye = player.getEyeLocation();
        Vector toEntity = target.toVector().subtract(eye.toVector());
        return toEntity.normalize().dot(eye.getDirection());
    }

}
