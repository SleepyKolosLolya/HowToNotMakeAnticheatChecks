
public class HitBoxA implements Listener {

    @EventHandler
    public static void onDamage(@NotNull EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            KacPlayer kacPlayer = KacPlayerManager.getPlayer((Player) event.getDamager());
            Entity entity = event.getEntity();
            double lookingAt = isLookingAt(kacPlayer.user, entity.getLocation());
            double hitbox_rate = .34;
            double distance = kacPlayer.user.getLocation().distance(entity.getLocation());
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
                Bukkit.getServer().getPluginManager().callEvent(new CheckResult(
                        CheckType.HITBOX,
                        "HitBox (A)",
                        SetBackType.NONE,
                        true,
                        String.format(" { lookingAt: %s; dy: %s; } ", lookingAt, distance),
                        kacPlayer,
                        1)
                );
            }
        }
    }

    private static double isLookingAt(@NotNull Player player, @NotNull Location target) {
        Location eye = player.getEyeLocation();
        Vector toEntity = target.toVector().subtract(eye.toVector());
        return toEntity.normalize().dot(eye.getDirection());
    }

}
