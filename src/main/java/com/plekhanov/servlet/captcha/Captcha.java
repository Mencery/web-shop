package com.plekhanov.servlet.captcha;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicLong;

/**
 * captcha entity
 */
public class Captcha {
    private BufferedImage image;
    private String number;
    private AtomicLong id;

    public AtomicLong getId() {
        return id;
    }

    public void setId(AtomicLong id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
