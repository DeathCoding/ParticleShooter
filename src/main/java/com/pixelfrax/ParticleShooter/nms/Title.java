package com.pixelfrax.ParticleShooter.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title {

    private Class<?> cEnumTitleAction, cIChatBaseComponent, cPacktePlayOutTitle;
    private Constructor<?> coTitle, coTiming;
    private Object pTitle, pSubTitle, pTiming;
    private ReflectionUtil ru;

    /**
     * constructor for a title object
     */
    public Title() {
        ru = new ReflectionUtil();
        cEnumTitleAction = ru.getNMSClass("PacketPlayOutTitle$EnumTitleAction");
        cIChatBaseComponent = ru.getNMSClass("IChatBaseComponent");
        cPacktePlayOutTitle = ru.getNMSClass("PacketPlayOutTitle");

        try {
            coTitle = cPacktePlayOutTitle.getConstructor(cEnumTitleAction, cIChatBaseComponent);
            coTiming = cPacktePlayOutTitle.getConstructor(int.class, int.class, int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sends a Title to a player
     * @param p
     * @param title
     * @param subTitle
     * @param fadeIn
     * @param stay
     * @param fadeOut
     */
    public void send(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        try {
            pTitle = coTitle.newInstance(cEnumTitleAction.getEnumConstants()[0], ru.getObjectNmsChatSerializer(title));
            pSubTitle = coTitle.newInstance(cEnumTitleAction.getEnumConstants()[1], ru.getObjectNmsChatSerializer(subTitle));
            pTiming = coTiming.newInstance(fadeIn * 20, stay * 20, fadeOut * 20);

            ru.sendPacket(p, pTitle);
            ru.sendPacket(p, pSubTitle);
            ru.sendPacket(p, pTiming);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getcEnumTitleAction() {
        return cEnumTitleAction;
    }

    public void setcEnumTitleAction(Class<?> cEnumTitleAction) {
        this.cEnumTitleAction = cEnumTitleAction;
    }

    public Class<?> getcIChatBaseComponent() {
        return cIChatBaseComponent;
    }

    public void setcIChatBaseComponent(Class<?> cIChatBaseComponent) {
        this.cIChatBaseComponent = cIChatBaseComponent;
    }

    public Class<?> getcPacktePlayOutTitle() {
        return cPacktePlayOutTitle;
    }

    public void setcPacktePlayOutTitle(Class<?> cPacktePlayOutTitle) {
        this.cPacktePlayOutTitle = cPacktePlayOutTitle;
    }

    public Constructor<?> getCoTitle() {
        return coTitle;
    }

    public void setCoTitle(Constructor<?> coTitle) {
        this.coTitle = coTitle;
    }

    public Constructor<?> getCoTiming() {
        return coTiming;
    }

    public void setCoTiming(Constructor<?> coTiming) {
        this.coTiming = coTiming;
    }

    public Object getpTitle() {
        return pTitle;
    }

    public void setpTitle(Object pTitle) {
        this.pTitle = pTitle;
    }

    public Object getpSubTitle() {
        return pSubTitle;
    }

    public void setpSubTitle(Object pSubTitle) {
        this.pSubTitle = pSubTitle;
    }

    public Object getpTiming() {
        return pTiming;
    }

    public void setpTiming(Object pTiming) {
        this.pTiming = pTiming;
    }

    public ReflectionUtil getRu() {
        return ru;
    }

    public void setRu(ReflectionUtil ru) {
        this.ru = ru;
    }
}
