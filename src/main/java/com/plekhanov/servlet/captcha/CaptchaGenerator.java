package com.plekhanov.servlet.captcha;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.ENABLE_CHARS;
import static com.plekhanov.constant.servlet.captcha.CaptchaGeneratorConstants.*;

/**
 * generates {@link Captcha}
 */
public class CaptchaGenerator {
    /**
     * @param captcha - entity where writes result
     */
    public void generate(Captcha captcha) {
        captcha.setNumber(nextString());
        captcha.setImage(createImage(captcha.getNumber()));
        System.out.println(captcha.getNumber());
        generateId(captcha);
    }

    /**
     * generate  captcha id
     *
     * @param captcha - entity where writes id
     */
    private void generateId(Captcha captcha) {
        Random random = new Random();
        captcha.setId(new AtomicLong(random.nextLong()));
    }

    /**
     * generate  captcha image
     *
     * @param captchaNumber - captcha number
     * @return created image
     */
    private BufferedImage createImage(String captchaNumber) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        setStilesGraphics2D(graphics2D);
        drawString(graphics2D, captchaNumber);
        drawCaptchaNumber(graphics2D);
        return bufferedImage;
    }

    /**
     * @param graphics2D fills stiles
     */
    private void setStilesGraphics2D(Graphics2D graphics2D) {
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, WIDTH, HEIGHT);
        graphics2D.setColor(Color.black);
        graphics2D.setFont(TEXT_FONT);
        graphics2D.setColor(CIRCLE_COLOR);
        drawCircleOnBackground(graphics2D);
    }

    /**
     * @param graphics2D - graphics that draws captcha number
     */
    private void drawCaptchaNumber(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        graphics2D.dispose();
    }

    /**
     * @param graphics2D -  graphics that draws background
     */
    private void drawCircleOnBackground(Graphics2D graphics2D) {
        for (int i = 0; i < CIRCLES_TO_DRAW; i++) {
            int L = (int) (Math.random() * HEIGHT / 2.0);
            int X = (int) (Math.random() * WIDTH - L);
            int Y = (int) (Math.random() * HEIGHT - L);
            graphics2D.draw3DRect(X, Y, L * 2, L * 2, true);
        }
    }

    /**
     * generate  captcha number
     */
    private String nextString() {
        char[] chars = ENABLE_CHARS.toCharArray();
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < CHARS_TO_PRINT; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);
        }
        return finalString.toString();
    }

    /**
     * @param graphics2D - graphics that draws string
     * @param s          - string is drawn
     */
    private void drawString(Graphics2D graphics2D, String s) {
        int i = 0;
        for (char c : s.toCharArray()) {
            drawCharacter(graphics2D, c, i);
            i++;
        }
    }

    /**
     * @param graphics2D      - graphics that draws character
     * @param characterToShow - character that need to show
     * @param index           - offset
     */
    private void drawCharacter(Graphics2D graphics2D, char characterToShow, int index) {
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int charDim = Math.max(fontMetrics.getMaxAdvance(), fontMetrics.getHeight());
        int halfCharDim = (charDim / 2);
        BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
        Graphics2D charGraphics = charImage.createGraphics();
        charGraphics.translate(halfCharDim, halfCharDim);
        charGraphics.transform(AffineTransform.getRotateInstance((Math.random() - 0.5) * ROTATION_RANGE));
        charGraphics.translate(-halfCharDim, -halfCharDim);
        charGraphics.setColor(Color.black);
        charGraphics.setFont(TEXT_FONT);
        int charX = (int) (0.5 * charDim - 0.5 * fontMetrics.charWidth(characterToShow));
        charGraphics.drawString("" + characterToShow, charX, ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
        graphics2D.drawImage(charImage, (int) getX(index, charDim), getY(charDim), charDim, charDim, null, null);
        charGraphics.dispose();
    }

    /**
     * @param index   - offset
     * @param charDim - character thar shifts
     * @return incline on x
     */
    private float getX(int index, int charDim) {
        return HORIZ_MARGIN + (-HORIZ_MARGIN * 2 + WIDTH) / (CHARS_TO_PRINT - 1.0f) * (index) - charDim / 2.0f;
    }

    /**
     * @param charDim - character thar shifts
     * @return incline on y
     */
    private int getY(int charDim) {
        return ((HEIGHT - charDim) / 2);
    }
}
