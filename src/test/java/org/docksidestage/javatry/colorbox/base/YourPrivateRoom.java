/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox.base;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.CompactColorBox;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.size.BoxSize;

/**
 * @author jflute
 */
public class YourPrivateRoom {

    // ===================================================================================
    //                                                                         Entry Point
    //                                                                         ===========
    public List<ColorBox> getColorBoxList() {
        List<ColorBox> colorBoxList = new ArrayList<>();
        colorBoxList.add(makeFirstColorBox());
        colorBoxList.add(makeSecondColorBox());
        colorBoxList.add(makeThirdColorBox());
        colorBoxList.add(makeFourthColorBox());
        colorBoxList.add(makeFifthColorBox());
        colorBoxList.add(makeSixthColorBox());
        colorBoxList.add(makeSeventhColorBox());
        return colorBoxList;
    }

    // ===================================================================================
    //                                                                              Making
    //                                                                              ======
    private StandardColorBox makeFirstColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("green"), new BoxSize(40, 50, 30));
        colorBox.getUpperSpace().setContent("おるぐどっくさいどすてーじ");
        colorBox.getMiddleSpace().setContent(null);
        colorBox.getLowerSpace().setContent(3);
        return colorBox;
    }

    private StandardColorBox makeSecondColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("red"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(806);
        colorBox.getMiddleSpace().setContent(54);
        colorBox.getLowerSpace().setContent("Waterfront");
        return colorBox;
    }

    private CompactColorBox makeThirdColorBox() {
        CompactColorBox colorBox = new CompactColorBox(new BoxColor("blue"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(new File("/tmp/javatry/docksidestage.txt"));
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();

        // when 2019/04/15
        map.put("1-Day Passport", 7400);
        map.put("After 6 Passport", 4200);
        map.put("2-Day Passport", 13200);
        map.put("Land Annual Passport", 61000);
        map.put("Sea Annual Passport", 61000);
        map.put("2-Park Annual Passport", 89000);

        colorBox.getLowerSpace().setContent(map);
        return colorBox;
    }

    private StandardColorBox makeFourthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("yellow"), new BoxSize(40, 50, 30));
        colorBox.getUpperSpace().setContent(LocalDateTime.of(1983, 4, 15, 23, 59, 59));
        colorBox.getMiddleSpace().setContent(LocalDate.of(2001, 9, 4));
        List<BigDecimal> decimalList = new ArrayList<BigDecimal>();
        decimalList.add(new BigDecimal(2));
        decimalList.add(new BigDecimal("3.14159"));
        decimalList.add(new BigDecimal("4.753"));
        decimalList.add(new BigDecimal("2.0"));
        decimalList.add(new BigDecimal("1.4"));
        colorBox.getLowerSpace().setContent(decimalList);
        return colorBox;
    }

    private StandardColorBox makeFifthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("white"), new BoxSize(30, 40, 50));
        SecretBox upperBox = asPrivate("map:{ dockside = over ; hangar = mystic ; broadway = bbb }"); // simple map
        colorBox.getUpperSpace().setContent(upperBox);

        SecretBox middleBox = // second value is nested
                asPrivate("map:{ dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb }");
        colorBox.getMiddleSpace().setContent(middleBox);

        SecretBox lowerBox = // third value is nested
                asPrivate("map:{ dockside = over ; hangar = mystic ; broadway = map:{ encore! = musical ; bbb = review } }");
        colorBox.getLowerSpace().setContent(lowerBox);
        return colorBox;
    }

    private StandardColorBox makeSixthColorBox() {
        StandardColorBox colorBox = new StandardColorBox(new BoxColor("pink"), new BoxSize(50, 40, 30));
        colorBox.getUpperSpace().setContent(asDevil("high tower"));
        colorBox.getMiddleSpace().setContent(asDevil(null));
        colorBox.getLowerSpace().setContent(asDevil("hotel"));
        return colorBox;
    }

    private CompactColorBox makeSeventhColorBox() {
        CompactColorBox colorBox = new CompactColorBox(new BoxColor("purple"), new BoxSize(50, 30, 40));
        colorBox.getUpperSpace().setContent(true);
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        // when 2019/04/15
        map.put("Small Coin Locker", 300);
        map.put("Resort Line", 250);
        map.put("Cinema Piari", "1800"); // trap

        colorBox.getLowerSpace().setContent(map);
        return colorBox;
    }

    // ===================================================================================
    //                                                                              Secret
    //                                                                              ======
    private SecretBox asPrivate(String mapString) {
        return new SecretBox(mapString);
    }

    public static class SecretBox {

        private final String text;

        public SecretBox(String text) {
            if (text == null) {
                throw new IllegalArgumentException("The argument 'text' should not be null.");
            }
            this.text = text;
        }

        /**
         * Get the text in the box.
         * @return The string of text. (NotNull)
         */
        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "{secret}";
        }
    }

    // ===================================================================================
    //                                                                               Devil
    //                                                                               =====
    private DevilBox asDevil(String mapString) {
        return new DevilBox(mapString);
    }

    public static class DevilBox {

        private final String text; // null allowed
        private boolean wakeup;
        private boolean allowed;
        private boolean open;

        public DevilBox(String text) {
            this.text = text;
        }

        public void wakeUp() {
            wakeup = true;
        }

        public void allowMe() {
            if (!wakeup) {
                String msg = "The devil is sleep now so call wakeUp() method before calling this.";
                throw new IllegalStateException(msg);
            }
            allowed = true;
        }

        public void open() {
            if (!allowed) {
                String msg = "You should call allowMe() method before calling this.";
                throw new IllegalStateException(msg);
            }
            open = true;
        }

        /**
         * Get the text in the box.
         * @return The string of text. (NotNull: exception if none)
         * @throws DevilBoxTextNotFoundException When the text is null.
         */
        public String getText() {
            if (!open) {
                String msg = "You should call open() method before calling this.";
                throw new IllegalStateException(msg);
            }
            if (text == null) {
                String msg = "Not found the text in the devil, meaning null value.";
                throw new DevilBoxTextNotFoundException(msg);
            }
            return text;
        }

        @Override
        public String toString() {
            return "{Ha ha ha!}";
        }
    }

    public static class DevilBoxTextNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public DevilBoxTextNotFoundException(String msg) {
            super(msg);
        }
    }
}
