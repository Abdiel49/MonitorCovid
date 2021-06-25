package cargarsintomas.gui;

public enum ESize {

    WIDTH(500),
    HEIGHT(400);

    private final int size;
    ESize(int d){
        size = d;
    }

    public int get(){
        return size;
    }
}
