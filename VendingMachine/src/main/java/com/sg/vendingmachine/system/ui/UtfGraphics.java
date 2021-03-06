package com.sg.vendingmachine.system.ui;

public enum UtfGraphics {
    STARS("【\uFEFF＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊】"),
    VENDING_NAME(" \uD835\uDD4E\uD835\uDD56\uD835\uDD5D\uD835\uDD54\uD835\uDD60\uD835\uDD5E\uD835\uDD56 \uD835\uDD65\uD835\uDD60 \uD835\uDD65\uD835\uDD59\uD835\uDD56 \uD835\uDCE2\uD835\uDCF6\uD835\uDCEA\uD835\uDCFB\uD835\uDCFD \uD835\uDCE5\uD835\uDCEE\uD835\uDCF7\uD835\uDCED\uD835\uDCF2\uD835\uDCF7\uD835\uDCF0 \uD835\uDCDC\uD835\uDCEA\uD835\uDCEC\uD835\uDCF1\uD835\uDCF2\uD835\uDCF7\uD835\uDCEE\uD835\uDCE3\uD835\uDCDC \uD835\uDCDF\uD835\uDCF5\uD835\uDCFE\uD835\uDCFC\n"),
    CURRENT_INVENTORY("      ✪✯✪✯✪✯\uD83C\uDD72\uD83C\uDD84\uD83C\uDD81\uD83C\uDD81\uD83C\uDD74\uD83C\uDD7D\uD83C\uDD83 \uD83C\uDD78\uD83C\uDD7D\uD83C\uDD85\uD83C\uDD74\uD83C\uDD7D\uD83C\uDD83\uD83C\uDF69\uD83C\uDD81\uD83C\uDD88✪✯✪✯✪✯"),
    EQUALS_BANNER("『＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝』"),
    THANK_YOU(" \uD83C\uDD43\uD83C\uDD37\uD83C\uDD30\uD83C\uDD3D\uD83C\uDD3A \uD83C\uDD48\uD83C\uDD3E\uD83C\uDD44 \uD83C\uDD35\uD83C\uDD3E\uD83C\uDD41 \uD83C\uDD48\uD83C\uDD3E\uD83C\uDD44\uD83C\uDD41 \uD83C\uDD3F\uD83C\uDD44\uD83C\uDD41\uD83C\uDD32\uD83C\uDD37\uD83C\uDD30\uD83C\uDD42\uD83C\uDD34!"),
    PRESS_ENTER("\uD835\uDE4B\uD835\uDE67\uD835\uDE5A\uD835\uDE68\uD835\uDE68 \uD835\uDE5A\uD835\uDE63\uD835\uDE69\uD835\uDE5A\uD835\uDE67 \uD835\uDE69\uD835\uDE64 \uD835\uDE58\uD835\uDE64\uD835\uDE63\uD835\uDE69\uD835\uDE5E\uD835\uDE63\uD835\uDE6A\uD835\uDE5A."),
    NO_CHANGE("\uD83C\uDD3D\uD83C\uDD3E \uD83C\uDD32\uD83C\uDD37\uD83C\uDD30\uD83C\uDD3D\uD83C\uDD36\uD83C\uDD34 \uD83C\uDD43\uD83C\uDD3E \uD83C\uDD41\uD83C\uDD34\uD83C\uDD43\uD83C\uDD44\uD83C\uDD41\uD83C\uDD3D..."),
    HERE_IS_YOUR_CHANGE("\uD83C\uDD37\uD83C\uDD34\uD83C\uDD41\uD83C\uDD34'\uD83C\uDD42 \uD83C\uDD48\uD83C\uDD3E\uD83C\uDD44\uD83C\uDD41 \uD83C\uDD32\uD83C\uDD37\uD83C\uDD30\uD83C\uDD3D\uD83C\uDD36\uD83C\uDD34..."),
    MAIN_MENU("            【\uFEFFＭＡＩＮ　ＭＥＮＵ】"),
    PLEASE_SELECT("\uD835\uDE4B\uD835\uDE61\uD835\uDE5A\uD835\uDE56\uD835\uDE68\uD835\uDE5A \uD835\uDE68\uD835\uDE5A\uD835\uDE61\uD835\uDE5A\uD835\uDE58\uD835\uDE69 \uD835\uDE5B\uD835\uDE67\uD835\uDE64\uD835\uDE62 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE56\uD835\uDE57\uD835\uDE64\uD835\uDE6B\uD835\uDE5A \uD835\uDE58\uD835\uDE5D\uD835\uDE64\uD835\uDE5E\uD835\uDE58\uD835\uDE5A\uD835\uDE68."),
    UNKNOWN_COMMAND("\uD83C\uDD44\uD83C\uDD3D\uD83C\uDD3A\uD83C\uDD3D\uD83C\uDD3E\uD83C\uDD46\uD83C\uDD3D \uD83C\uDD32\uD83C\uDD3E\uD83C\uDD3C\uD83C\uDD3C\uD83C\uDD30\uD83C\uDD3D\uD83C\uDD33!!");

    private final String graphic;

    UtfGraphics(String graphic) {
        this.graphic = graphic;
    }

    public String getGraphic() {
        return graphic;
    }
}
