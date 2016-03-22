package Lesson4;


public enum TankColor {
    BLACK(0), DARK_GREEN(1), RED(2), YELLOW(3), BROWN(4), BLUE(5), DARK_BLUE(6),
    GREEN(7), LIGHT_GREEN(8), GREY(9), ORANGE(10), PURPLE(11), RUBY(12), LILAC(13),
    LIGHT_GREY(14);
	

    private int id;
    private TankColor(int id) {
        this.id = id;
    }

    TankColor getDefaultColor() {
        return BLACK;
    }
}