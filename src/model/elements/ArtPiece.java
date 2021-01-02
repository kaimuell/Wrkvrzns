package model.elements;

public class ArtPiece {
    String name;
    String technique;
    ArtworkType type;
    int height;
    int width;
    int depth;
    int length;
    int year;
    boolean isSold;
    int buyerID;

    public ArtPiece(String name, String technique, ArtworkType type, int height, int width, int depth, int length, int year, boolean isSold, int buyerID) {
        this.name = name;
        this.technique = technique;
        this.type = type;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.length = length;
        this.year = year;
        this.isSold = isSold;
        this.buyerID = buyerID;
    }

    public String getMeatDataRepresentation(){
        if (type == ArtworkType.PAINTING || type == ArtworkType.GRAFIK){
            return (height + " x " + width + " cm");
        } else if (type == ArtworkType.SCULPTURE ||type == ArtworkType.INSTALLATION){
            return (height + " x " + width + " x " + depth + " cm");
        } else if (type == ArtworkType.VIDEO){
            return (length + " min");
        }
        return ("");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public ArtworkType getType() {
        return type;
    }

    public void setType(ArtworkType type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
}
