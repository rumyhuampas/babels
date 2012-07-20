package babelsreports;

public class ReportCashRegisterClass {

    private String id;
    private String movimiento;
    private String fecha;
    private float monto;
    private int cancellerId;
    private float subTotal;

    public ReportCashRegisterClass(String Id, String Movimiento, String Fecha, float Monto, float SubTotal) {
        this.id = Id;
        this.movimiento = Movimiento;
        this.fecha = Fecha;
        this.monto = Monto;
        this.subTotal = SubTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getCancellerId() {
        return cancellerId;
    }

    public void setCancellerId(int CancellerId) {
        this.cancellerId = CancellerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float Monto) {
        this.monto = Monto;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float SubTotal) {
        this.subTotal = SubTotal;
    }
}
