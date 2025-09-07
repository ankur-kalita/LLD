package com.example.render;

public final class TextStyle {
    private final String font;
    private final int size;
    private final boolean bold;

    public TextStyle(String font, int size, boolean bold) {
        this.font = font; this.size = size; this.bold = bold;
    }

    public String getFont() { return font; }
    public int getSize() { return size; }
    public boolean isBold() { return bold; }

    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     TextStyle that = (TextStyle) o;
    //     return size == that.size && bold == that.bold && font.equals(that.font);
    // }

    // public int hashCode() {
    //     int result = font.hashCode();
    //     result = 31 * result + size;
    //     result = 31 * result + (bold ? 1 : 0);
    //     return result;
    // }

}
