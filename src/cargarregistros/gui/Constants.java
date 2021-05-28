package cargarregistros.gui;

public enum Constants {
    WIDTH(500),
    HEIGHT(500);

    private final int value;
    Constants(int value){
        this.value = value;
    }

    public int get(){
        return value;
    }
}
