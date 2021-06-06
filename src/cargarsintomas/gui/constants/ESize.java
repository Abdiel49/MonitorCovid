package cargarsintomas.gui.constants;

public enum ESize {

    WIDTH(500),
    HEIGHT(250);

    private final int size;
    ESize(int d){
        size = d;
    }

    public int get(){
        return size;
    }
}
