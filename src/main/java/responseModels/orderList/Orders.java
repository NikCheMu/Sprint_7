package responseModels.orderList;

public class Orders {

    private Order[] orders;
    private PageInfo pageInfo;
    private AvailableStation[] availableStations;

    public Orders(Order[] orders, PageInfo pageInfo, AvailableStation[] availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public Orders() {
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public AvailableStation[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(AvailableStation[] availableStations) {
        this.availableStations = availableStations;
    }
}
