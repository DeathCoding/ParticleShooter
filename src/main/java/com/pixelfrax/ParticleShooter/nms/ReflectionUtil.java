package com.pixelfrax.ParticleShooter.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private String VERSION = "";
    private String NMS_PREFIX = "net.minecraft.server.";
    private String CRAFT_PREFIX = "org.bukkit.craftbukkit.";

    public ReflectionUtil() {
        String serverPackageName = Bukkit.getServer().getClass().getPackage().getName();
        VERSION = serverPackageName.substring(serverPackageName.lastIndexOf(".") + 1);
    }

    /**
     * returns a nms Class
     * @param name
     * @return
     */
    public Class<?> getNMSClass(String name) {
        try {

            return Class.forName(NMS_PREFIX + VERSION + "." + name);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * returns a CraftClass
     * @param name
     * @return
     */
    public Class<?> getCraftClass(String name) {
        try {

            return Class.forName(CRAFT_PREFIX + VERSION + "." + name);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * returns the entityPlayer
     * @param p
     * @return
     */
    public Object getEntityPlayer(Player p) {
        try {
            return getCraftClass("entity.CraftPlayer").getMethod("getHandle").invoke(p);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * returns the chat serializer
     * @return
     */
    public Class<?> getClassNmsChatSerializer()
    {
        try
        {
            return getNMSClass("IChatBaseComponent$ChatSerializer");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns nms chat serializer
     * @param text
     * @return
     */
    public Object getObjectNmsChatSerializer(String text)
    {
        try
        {
            Class<?> classSeri = getClassNmsChatSerializer();
            Method serialize = classSeri.getDeclaredMethod("a", new Class[] { String.class });
            return serialize.invoke(null, new Object[] { "{\"text\": \"" + text + "\"}" });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sends a packet
     * @param p
     * @param packet
     */
    public void sendPacket(Player p , Object packet) {
        try {
            Object nmsPlayer = getEntityPlayer(p);

            Field fieldCon = nmsPlayer.getClass().getField("playerConnection");
            fieldCon.setAccessible(true);

            Object nmsCon = fieldCon.get(nmsPlayer);

            Method sendPacket = nmsCon.getClass().getMethod("sendPacket", getNMSClass("Packet"));
            sendPacket.invoke(nmsCon,packet);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getNMS_PREFIX() {
        return NMS_PREFIX;
    }

    public void setNMS_PREFIX(String NMS_PREFIX) {
        this.NMS_PREFIX = NMS_PREFIX;
    }

    public String getCRAFT_PREFIX() {
        return CRAFT_PREFIX;
    }

    public void setCRAFT_PREFIX(String CRAFT_PREFIX) {
        this.CRAFT_PREFIX = CRAFT_PREFIX;
    }
}
