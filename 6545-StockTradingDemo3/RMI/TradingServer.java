package RMI;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import StockTradingServer.BrokerageFirm;
import StockTradingServer.CustomerInfo;
import StockTradingServer.DatabaseConnector;
import StockTradingServer.Order;
import StockTradingServer.StatusesOptions;
import StockTradingServer.Stock;
import StockTradingServer.User;
import StockTradingServer.Validator;

public class TradingServer extends UnicastRemoteObject implements
		ServerInterface {

	private static final int PORT = 2019;

	public TradingServer() throws Exception {
		super(PORT, new RMISSLClientSocketFactory(),
				new RMISSLServerSocketFactory());
	}

	public String sayHello() {
		System.out.println("request from client");
		return "Hello World! using my keys";
	}

	public String getHello() {
		return DatabaseConnector.getHello();
	}

	@Override
	public ArrayList<BrokerageFirm> selectBrokerageFirmsAll() {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectBrokerageFirmsAll();
	}

	public BrokerageFirm selectBrokerageFirm(int idToSelect) {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectBrokerageFirm(idToSelect);
	}

	public Validator insertNewBrokerageFirm(BrokerageFirm newFirm) {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.insertNewBrokerageFirm(newFirm);
	}

	@Override
	public Validator updateBrokerageFirm(int idToUpdate,
			BrokerageFirm firmToUpdate) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.updateBrokerageFirm(idToUpdate, firmToUpdate);
	}

	@Override
	public ArrayList<User> selectBrokersAll(int pStatusId)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectBrokersAll(pStatusId);
	}

	@Override
	public ArrayList<User> selectBrokersAllbyFirm(int pFirmId)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectBrokersAllbyFirm(pFirmId);
	}

	@Override
	public User selectBrokerUser(int idToSelect) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectBrokerUser(idToSelect);
	}

	@Override
	public Validator insertNewBroker(User newUser) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.insertNewBroker(newUser);
	}

	@Override
	public Validator updateBroker(int idToUpdate, User user)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.updateBroker(idToUpdate, user);
	}

	@Override
	public ArrayList<Stock> selectStockAll() throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectStockAll();
	}

	@Override
	public Stock selectStock(int idToSelect) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectStock(idToSelect);
	}

	@Override
	public Validator insertNewStock(Stock newStock) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.insertNewStock(newStock);
	}

	@Override
	public Validator updateStock(int idToUpdate, Stock stock)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.updateStock(idToUpdate, stock);
	}

	@Override
	public ArrayList<Order> selectOrdersAll() throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectOrdersAll();
	}

	@Override
	public Order selectOrder(int idToSelect) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectOrder(idToSelect);
	}

	@Override
	public Validator insertNewOrder(Order newOrder) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.insertNewOrder(newOrder);
	}

	@Override
	public Validator updateOrder(int idToUpdate, Order order)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.updateOrder(idToUpdate, order);
	}

	@Override
	public ArrayList<CustomerInfo> selectCustomerInfoAll()
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectCustomerInfoAll();
	}

	@Override
	public CustomerInfo selectCustomerInfo(int idToSelect)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectCustomerInfo(idToSelect);
	}

	@Override
	public Validator insertNewCustomerInfo(CustomerInfo newCustomer)
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.insertNewCustomerInfo(newCustomer);
	}

	@Override
	public Validator updateCustomerInfo(int idToUpdate,
			CustomerInfo customerToUpdate) throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.updateCustomerInfo(idToUpdate, customerToUpdate);
	}

	@Override
	public ArrayList<StatusesOptions> selectAllStatuses()
			throws RemoteException {
		DatabaseConnector dc = new DatabaseConnector();
		return dc.selectAllStatuses();
	}

	public static void main(String args[]) {

		// Create and install a security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			// Create SSL-based registry
			Registry registry = LocateRegistry.createRegistry(PORT,
					new RMISSLClientSocketFactory(),
					new RMISSLServerSocketFactory());

			TradingServer obj = new TradingServer();

			// Bind this object instance to the name "TradingServer"
			registry.bind("TradingServer", obj);

			System.out.println("Trading Server bound in registry");
		} catch (Exception e) {
			System.out.println("TradingServer err: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
