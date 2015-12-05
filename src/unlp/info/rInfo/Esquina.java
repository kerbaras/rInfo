package unlp.info.rInfo;

/**
 * Created by matias on 11/20/15.
 */
public class Esquina {
    private int flores = 0;
    private int papeles = 0;
    private boolean obstaculo = false;
    private boolean blocked = false;

    public int getFlores() {
        return flores;
    }

    public void setFlores(int flores) {
        this.flores = flores;
    }

    public void depositarFlor(){
        flores = flores + 1;
    }

    public void tomarFlor() throws Exception{
        if (flores > 0) {
            flores = flores - 1;
        }else{
            throw new Exception("No hay flores en la esquina");
        }
    }

    public boolean hayFlor(){
        return (this.flores > 0);
    }

    public int getPapeles() {
        return papeles;
    }

    public void setPapeles(int papeles) {
        this.papeles = papeles;
    }

    public void depositarPapel(){
        papeles = papeles + 1;
    }

    public void tomarPapel() throws Exception{
        if (papeles > 0) {
            papeles = papeles - 1;
        }else{
            throw new Exception("No hay flores en la esquina");
        }
    }

    public boolean hayPapel(){
        return (papeles > 0);
    }

    public boolean hayObstaculo() {
        return obstaculo;
    }

    public void setObstaculo(boolean obstaculo) {
        this.obstaculo = obstaculo;
    }

    public synchronized boolean isBlocked() {
        return blocked;
    }

    public synchronized void bloquear() throws Exception{
        if(blocked){
            throw new Exception("No se puede bloquear una esquina bloqueada");
        }
        blocked = true;
    }

    public synchronized void liberar(){
        blocked = false;
        notifyAll();
    }
}
